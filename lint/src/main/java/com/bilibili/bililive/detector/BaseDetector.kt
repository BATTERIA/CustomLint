package com.bilibili.bililive.detector

import com.android.tools.lint.detector.api.Context
import com.android.tools.lint.detector.api.Detector
import com.bilibili.bililive.config.LintConfig

/**
 *User: yaobeihaoyu
 * Date: 2020/10/27
 * Time: 8:07 PM
 */
open class BaseDetector : Detector() {

    lateinit var lintConfig: LintConfig

    override fun beforeCheckRootProject(context: Context) {
        super.beforeCheckRootProject(context)
        lintConfig = LintConfig.getInstance(context)
    }
}