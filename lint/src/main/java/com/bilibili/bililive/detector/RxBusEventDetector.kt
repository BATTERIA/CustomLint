package com.bilibili.bililive.detector

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import com.bilibili.bililive.utils.*
import com.intellij.psi.impl.source.PsiClassReferenceType
import org.jetbrains.uast.UCallExpression
import org.jetbrains.uast.UElement
import org.jetbrains.uast.UReferenceExpression
import org.jetbrains.uast.util.isConstructorCall

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2020/11/25 11:04 AM
 * @description: 直播RxBus事件声明规范
 */
class RxBusEventDetector : BaseDetector(), Detector.UastScanner {
    companion object {
        private const val explanation = "直播RxBus事件需分类放在com.bilibili.bililive.videoliveplayer.ui.roomv3.base.events包下"
        val ISSUE = Issue.create(
            "RxBusEventCheck",
            "直播RxBus事件定义规范",
            explanation,
            Category.TESTING,
            5,
            Severity.INFORMATIONAL,
            Implementation(RxBusEventDetector::class.java, Scope.JAVA_FILE_SCOPE)
        )

        private val postEventMethods = listOf(
            "com.bilibili.bililive.videoliveplayer.ui.roomv3.base.rxbus.ILiveRxBusManager.postEvent",
            "com.bilibili.bililive.videoliveplayer.ui.roomv3.base.viewmodel.IRoomCommonBase.postMainEvent",
            "com.bilibili.bililive.videoliveplayer.ui.roomv3.base.viewmodel.IRoomCommonBase.postBackgroundEvent"
        )
        private const val eventPackage = "com.bilibili.bililive.videoliveplayer.ui.roomv3.base.events"
    }

    override fun getApplicableUastTypes(): List<Class<out UElement>>? {
        return listOf(UCallExpression::class.java)
    }

    override fun createUastHandler(context: JavaContext): UElementHandler? {
        return object : UElementHandler() {
            override fun visitCallExpression(node: UCallExpression) {
                // 非post方法return
                postEventMethods.find {
                    LintMatcher.match(null, it, node.getQualifiedName())
                } ?: return

                val arguments = node.valueArguments
                if (arguments.isNotEmpty()) {
                    // 第一个入参为构造方法
                    if ((arguments[0] as? UCallExpression)?.isConstructorCall() == true) {
                        // 头部包名匹配判断是否在包下
                        if (!((arguments[0] as UCallExpression).getExpressionType() as PsiClassReferenceType)
                                .reference.qualifiedName.startsWith(eventPackage)) report(node)
                        return
                    }

                    // 第一个入参是事件的引用
                    val qualifiedName =
                        ((node.valueArguments[0] as? UReferenceExpression)?.getExpressionType() as? PsiClassReferenceType)?.reference?.qualifiedName ?: return
                    if (!qualifiedName.startsWith(eventPackage)) {
                        report(node)
                    }
                }
            }

            private fun report(node: UCallExpression) {
                context.report(ISSUE, context.getLocation(node), null, explanation)
            }
        }
    }
}