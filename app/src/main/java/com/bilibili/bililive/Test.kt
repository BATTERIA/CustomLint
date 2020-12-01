package com.bilibili.bililive

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2020/12/1 6:37 PM
 * @description:
 */
class B {
    fun test() {
        val p = PP()
        p.t.a2 = 23
        p.t.print()
        val a = Test()
        a.a2 = 23
    }
}

class PP {
    val t = Test()
}

class Test {
    val a1: String = "321"
    var a2: Int = 123
    fun print() {

    }
}