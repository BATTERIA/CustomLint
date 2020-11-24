package com.bilibili.bililive.utils

import com.android.tools.lint.detector.api.*
import com.bilibili.bililive.config.bean.BaseConfigProperty
import org.jetbrains.uast.UCallExpression
import org.jetbrains.uast.UIdentifier
import org.jetbrains.uast.UReferenceExpression

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

fun Context.report(
    issue: Issue,
    location: Location,
    baseProperty: BaseConfigProperty
) {
    this.report(
        Issue.create(
            issue.id,
            baseProperty.message,
            issue.getExplanation(TextFormat.TEXT),
            issue.category,
            issue.priority,
            baseProperty.lintSeverity,
            issue.implementation
        ),
        location,
        baseProperty.message
    )
}