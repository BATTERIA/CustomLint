package com.bilibili.bililive.utils

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2020/11/24 4:58 PM
 * @description:
 */
object Log {
    fun v(tag: String, content: String) {
        log(tag, content, "V")
    }

    fun d(tag: String, content: String) {
        log(tag, content, "D")
    }

    fun i(tag: String, content: String) {
        log(tag, content, "I")
    }

    fun w(tag: String, content: String) {
        log(tag, content, "W")
    }

    fun e(tag: String, content: String) {
        log(tag, content, "E")
    }

    private fun log(tag: String, content: String, level: String) {
        println("$level/$tag: $content")
    }
}

interface Logger {
    val tag: String

    fun logVerbose(content: String) {
        Log.v(tag, content)
    }
    fun logDebug(content: String) {
        Log.d(tag, content)
    }

    fun logInfo(content: String) {
        Log.i(tag, content)
    }

    fun logWarning(content: String) {
        Log.w(tag, content)
    }

    fun logError(content: String) {
        Log.e(tag, content)
    }
}