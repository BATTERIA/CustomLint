package com.bubble.lint.detector

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import com.bubble.lint.utils.*
import org.jetbrains.uast.*

/**
 * @author: batteria
 * @version: 1.0
 * @since: 2020/11/25 11:04 AM
 * @description: LiveRoomDataStore写规范
 */
class DataStoreWriteDetector : BaseDetector(), Detector.UastScanner {
    companion object {
        private const val explanation = "LiveRoomDataStore内所有变量都需要通过write方法进行写操作"
        val ISSUE = Issue.create(
            "DataStoreWriteCheck",
            "LiveRoomDataStore写规范",
            explanation,
            Category.TESTING,
            5,
            Severity.INFORMATIONAL,
            Implementation(DataStoreWriteDetector::class.java, Scope.JAVA_FILE_SCOPE)
        )

        private const val TAG = "DataStoreWriteDetector"
    }

    override fun getApplicableUastTypes(): List<Class<out UElement>>? {
        return listOf(UBinaryExpression::class.java)
    }

    override fun createUastHandler(context: JavaContext): UElementHandler? {
        return object : UElementHandler(), Logger {
            // 搜索二元运算符=
            // 获取二元运算符左端
            // 判断限定表达式是否不符合目标write方法
            override fun visitBinaryExpression(node: UBinaryExpression) {
                if (node.operator != UastBinaryOperator.ASSIGN) return
                val left = node.leftOperand as? UQualifiedReferenceExpression ?: return
                // 只观察上面某级的receiver的类型是否符合目标类
                var receiver: UReferenceExpression? = left.receiver as? UReferenceExpression
                while (receiver != null) {
                    // 类型名
                    val className = receiver.getClassName()
//                logInfo("className: $className")
                    val classes = lintConfig.dataStoreWriteConfig.classes
                    if (classes.contains(className)) {
                        report(node)
                        return
                    }
                    if (receiver is UQualifiedReferenceExpression) {
                        receiver = receiver.receiver as? UReferenceExpression
                        continue
                    }
                    return
                }
            }

            private fun report(node: UElement) {
                context.report(
                    ISSUE,
                    context.getLocation(node),
                    null,
                    explanation
                )
            }

            override val tag: String
                get() = TAG
        }
    }
}