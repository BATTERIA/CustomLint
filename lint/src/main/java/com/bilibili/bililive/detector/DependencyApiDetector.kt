package com.bilibili.bililive.detector

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import com.bilibili.bililive.config.ConfigParser
import com.bilibili.bililive.config.LintConfig
import com.bilibili.bililive.config.bean.DependencyApi
import com.bilibili.bililive.utils.*
import org.jetbrains.uast.*
import org.jetbrains.uast.visitor.AbstractUastVisitor

/**
 * 有依赖关系api
 * 目前检查开始条件是[DependencyApi.triggerMethod]方法被调用，
 * 如果满足开始条件则检查[DependencyApi.triggerMethod]后面的方法，
 * 有没有调用[DependencyApi.dependencyMethod]方法如果没调用则report。
 *
 * 警告：⚠️目前只能检查[DependencyApi.triggerMethod]在类中被调用的情况
 * 1、如果在一个类中调用了[DependencyApi.triggerMethod]，在另一个类中调用[DependencyApi.dependencyMethod]无法分析
 * 2、如果[DependencyApi.dependencyMethod]由实例的另一个引用调用则无法分析
 * 3、无法确认[DependencyApi.dependencyMethod]是否真的会被调用，只能说是写了
 *
 * User: yaobeihaoyu
 * Date: 2020/6/16
 * Time: 10:09 AM
 */
class DependencyApiDetector : BaseDetector(), Detector.UastScanner, Logger {
    companion object {
        private const val REPORT_MESSAGE =
            "使用${LintConfig.CONFIG_FILE_NAME}中${ConfigParser.KEY_DEPENDENCY_API}配置的api时必须调用dependencyMethod方法"
        val ISSUE = Issue.create(
            "DependencyApiCheck",
            REPORT_MESSAGE,
            REPORT_MESSAGE,
            Category.CORRECTNESS,
            10,
            Severity.ERROR,
            Implementation(DependencyApiDetector::class.java, Scope.JAVA_FILE_SCOPE)
        )
    }

    override fun getApplicableUastTypes(): List<Class<out UElement>>? {
        return listOf(UCallExpression::class.java)
    }

    override fun createUastHandler(context: JavaContext): UElementHandler? {
        return object : UElementHandler() {

            override fun visitCallExpression(node: UCallExpression) {
                //匹配要检查的dependencyApi
                val dependencyApi = lintConfig.dependencyApiList.find {
                    LintMatcher.match(null, it.triggerMethod, node.getQualifiedName())
                } ?: return

                // 拿到方法调用对象名
                val objectName = node.getReferenceObjectName()
                if (objectName.isEmpty()) logError("can't get object's name")
                logInfo("found triggerMethod(${dependencyApi.triggerMethod}) and object's name is $objectName")

                // 拿到外层类
                val outClass =
                    node.getParentOfType<UClass>(UClass::class.java, true)
                        ?: return

                val dependencyApiFinder = DependencyApiFinder(dependencyApi, objectName)
                outClass.accept(dependencyApiFinder)//检查outMethod内是否有调用dependency_method

                if (dependencyApiFinder.isFound()) {
                    logInfo("found dependencyMethod(${dependencyApi.dependencyMethod}) and object's name is $objectName")
                    return
                }
                logError("调用${dependencyApi.triggerMethod}后必须调用${dependencyApi.dependencyMethod}方法")
                context.report(ISSUE, context.getLocation(node), dependencyApi)
            }

        }
    }


    class DependencyApiFinder(
        private val dependencyApi: DependencyApi,
        private val objectName: String
    ) : AbstractUastVisitor(), Logger {
        override val tag = "DependencyApiFinder"

        private var found = false

        override fun visitCallExpression(node: UCallExpression): Boolean {
            if (LintMatcher.match(null, dependencyApi.dependencyMethod, node.getQualifiedName())
                && node.getReferenceObjectName() == objectName) {
                found = true
            }
            return super.visitCallExpression(node)
        }

        fun isFound() = found
    }

    override val tag: String
        get() = "DependencyApiDetector"
}