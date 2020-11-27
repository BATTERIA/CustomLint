package com.bilibili.bililive.test

import com.android.tools.lint.checks.infrastructure.LintDetectorTest
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Issue
import com.bilibili.bililive.detector.RxBusEventDetector

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2020/11/24 7:52 PM
 * @description:
 */
class RxBusEventDetectorTest : LintDetectorTest() {
    override fun getDetector(): Detector {
        return RxBusEventDetector()
    }

    override fun getIssues(): MutableList<Issue> {
        return mutableListOf(RxBusEventDetector.ISSUE)
    }

    fun test() {
        lint().files(kotlin("""
class B {
    private val manager: ILiveRxBusManager = LiveRxBusManager()
    fun test() {
        manager.postEvent(TestEvent())
        val event = TestEvent()
        manager.postEvent(event)
    }
}

class LiveRxBusManager : ILiveRxBusManager {

    override fun postEvent(event: Event) {}
}

interface ILiveRxBusManager {
    fun postEvent(event: Event)
}

// RxBus 事件接口
interface Event

class TestEvent : Event
""")).run().expect("No warnings.")
    }
}
