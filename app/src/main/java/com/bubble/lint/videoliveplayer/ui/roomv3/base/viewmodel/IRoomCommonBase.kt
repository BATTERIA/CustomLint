package com.bubble.lint.videoliveplayer.ui.roomv3.base.viewmodel

import com.bubble.lint.videoliveplayer.ui.roomv3.base.rxbus.Event

/**
 * @author batteria
 * @date 2020/11/3
 */
interface IRoomCommonBase {

    fun postMainEvent(event: Event) {}

    fun postBackgroundEvent(event: Event) {}
}