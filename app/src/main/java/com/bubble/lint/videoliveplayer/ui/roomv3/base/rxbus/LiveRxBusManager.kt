package com.bubble.lint.videoliveplayer.ui.roomv3.base.rxbus


/**
 * @author: batteria
 * @version: 1.0
 * @since: 2020/10/15 3:42 PM
 * @description:
 */
class RxBusManager : IRxBusManager {

    override fun postEvent(event: Event) {}
}

interface IRxBusManager {
    fun postEvent(event: Event)
}

// RxBus 事件接口
interface Event