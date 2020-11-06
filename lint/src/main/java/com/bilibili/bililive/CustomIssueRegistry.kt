package com.bilibili.bililive

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.Issue
import com.bilibili.bililive.detector.*

/**
 *User: yaobeihaoyu
 * Date: 2020/10/27
 * Time: 3:55 PM
 */
class CustomIssueRegistry : IssueRegistry() {

    override val issues: List<Issue>
        get() = listOf(
            SerializableClassDetector.ISSUE,
            HandleExceptionDetector.ISSUE,
            AvoidUsageApiDetector.ISSUE,
            DependencyApiDetector.ISSUE,
            ResourceNameDetector.ISSUE
        )

    override val api: Int
        get() = 5
}