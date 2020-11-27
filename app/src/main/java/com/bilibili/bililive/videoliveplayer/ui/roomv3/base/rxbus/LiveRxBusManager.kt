package com.bilibili.bililive.videoliveplayer.ui.roomv3.base.rxbus


/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2020/10/15 3:42 PM
 * @description:
 */
class LiveRxBusManager : ILiveRxBusManager {

    override fun postEvent(event: Event) {}
}

interface ILiveRxBusManager {
    fun postEvent(event: Event)
}

// RxBus 事件接口
interface Event