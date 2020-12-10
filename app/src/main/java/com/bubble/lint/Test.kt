package com.bubble.lint

/**
 * @author: batteria
 * @version: 1.0
 * @since: 2020/12/1 6:37 PM
 * @description:
 */
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