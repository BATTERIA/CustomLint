package com.bilibili.bililive.test

import com.android.tools.lint.checks.infrastructure.LintDetectorTest
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Issue
import com.bilibili.bililive.detector.DependencyApiDetector

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2020/11/5 7:20 PM
 * @description:
 */
class DetectorTest : LintDetectorTest() {
    override fun getDetector(): Detector {
        return DependencyApiDetector()
    }

    override fun getIssues(): MutableList<Issue> {
        return mutableListOf(DependencyApiDetector.ISSUE)
    }

    fun test() {
        lint().files(kotlin("""
import android.animation.Animator
import android.animation.ObjectAnimator
class CommonBean {
    private var s: String = "abc"
    val a:Animator = ObjectAnimator.ofInt(1, 3)
    init {
        ObjectAnimator.ofInt(1, 3).start()
        a.start()
        val b: ObjectAnimator = ObjectAnimator.ofInt(1, 3)
        b.start()
    }
    
    fun test() {
        a.cancel()
    }
}
""")).run().expect("No warnings.")
    }
}

class NamingDetectorTest : LintDetectorTest() {
    override fun getDetector(): Detector {
        return NamingConventionDetector()
    }

    override fun getIssues(): MutableList<Issue> {
        return mutableListOf(NamingConventionDetector.ISSUE)
    }

    fun test() {
        lint().files(kotlin("""
import android.animation.Animator
import android.animation.ObjectAnimator
class c {
    private var s: String = "abc"

    fun Ger() {
    
    }
}
""")).run().expect("No warnings.")
    }
}