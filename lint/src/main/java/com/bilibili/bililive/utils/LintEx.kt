package com.bilibili.bililive.utils

import com.android.tools.lint.detector.api.*
import com.bilibili.bililive.config.bean.BaseConfigProperty
import com.intellij.psi.impl.source.PsiClassReferenceType
import org.jetbrains.uast.UCallExpression
import org.jetbrains.uast.UIdentifier
import org.jetbrains.uast.UReferenceExpression
import org.jetbrains.uast.getContainingClass

/**
 * User: yaobeihaoyu
 * Date: 2020/6/11
 * Time: 4:37 PM
 */

/**
 * 获取该表达式的标准名称
 * 例：android.content.ContextWrapper.getSharedPreferences
 */
fun UCallExpression.getQualifiedName(): String {
    return resolve()?.containingClass?.qualifiedName + "." + resolve()?.name
}

/**
 * 拿到调用方法的对象名
 * todo: 可能会出现链式调用
 */
fun UCallExpression.getReferenceObjectName(): String {
    return ((receiver as? UReferenceExpression)?.referenceNameElement as? UIdentifier)?.name ?: ""
}

/**
 * 获取引用的类名
 */
fun UReferenceExpression.getClassName(): String {
    return (getExpressionType() as? PsiClassReferenceType)?.reference?.qualifiedName ?: ""
}

fun Context.report(
    issue: Issue,
    location: Location,
    baseProperty: BaseConfigProperty?,
    msg: String = ""
) {
    val msg = "livelint: ${baseProperty?.message ?: msg}"
    this.report(
        Issue.create(
            issue.id,
            msg,
            issue.getExplanation(TextFormat.TEXT),
            issue.category,
            issue.priority,
            baseProperty?.lintSeverity ?: Severity.INFORMATIONAL,
            issue.implementation
        ),
        location,
        msg
    )
}