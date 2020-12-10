package com.bubble.lint.test

import com.android.tools.lint.checks.infrastructure.LintDetectorTest
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Issue
import com.bubble.lint.detector.DataStoreWriteDetector

/**
 * @author: batteria
 * @version: 1.0
 * @since: 2020/11/24 7:52 PM
 * @description:
 */
class DataStoreWriteDetectorTest : LintDetectorTest() {
    override fun getDetector(): Detector {
        return DataStoreWriteDetector()
    }

    override fun getIssues(): MutableList<Issue> {
        return mutableListOf(DataStoreWriteDetector.ISSUE)
    }

    fun test() {
        lint().files(kotlin("""
class B {
    fun test() {
        val p = PP()
        p.t.a2 = 23
        p.t.tt.a2 = 1
        p.t.print()
    }
}

class PP {
    val t = Test()
}

class Test {
    val a1: String = "321"
    var a2: Int = 123
    val tt = Test()
    fun print() {
    
    }
}
""")).run().expect("No warnings.")
    }
}
