package com.bubble.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.Issue
import com.bubble.lint.detector.*

/**
 * User: batteria
 * Date: 2020/10/27
 * Time: 3:55 PM
 */
class CustomIssueRegistry : IssueRegistry() {

    override val issues: List<Issue>
        get() = listOf(
            DependencyApiDetector.ISSUE,
            RxBusEventDetector.ISSUE,
            DataStoreWriteDetector.ISSUE
//            SerializableClassDetector.ISSUE,
//            HandleExceptionDetector.ISSUE,
//            AvoidUsageApiDetector.ISSUE,
//            ResourceNameDetector.ISSUE,
        )

    override val api: Int
        get() = com.android.tools.lint.detector.api.CURRENT_API

    override val minApi: Int
        get() = 1
}