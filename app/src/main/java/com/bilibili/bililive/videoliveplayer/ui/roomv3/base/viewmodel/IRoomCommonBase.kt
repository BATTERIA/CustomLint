package com.bilibili.bililive.videoliveplayer.ui.roomv3.base.viewmodel

import com.bilibili.bililive.videoliveplayer.ui.roomv3.base.rxbus.Event

/**
 * @author dfate
 * @date 2020/11/3
 */
interface IRoomCommonBase {

    fun postMainEvent(event: Event) {}

    fun postBackgroundEvent(event: Event) {}
}