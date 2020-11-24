package com.bilibili.bililive.config.bean

/**
 * 调用指定API时，需要加try-catch处理指定类型的异常
 * User: yaobeihaoyu
 * Date: 2020/6/10
 * Time: 11:06 AM
 */
data class HandleExceptionMethod(
    val exception: String = ""
) : BaseConfigProperty()
