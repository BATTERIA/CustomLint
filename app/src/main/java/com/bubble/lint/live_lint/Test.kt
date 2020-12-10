package com.bubble.lint.live_lint

import android.animation.Animator
import android.animation.ObjectAnimator
import android.graphics.Color
import android.widget.Toast

/**
 * @author: batteria
 * @version: 1.0
 * @since: 2020/11/11 11:07 AM
 * @description:
 */
class CommonBean1 {
    private var s: String = "abc"

    init {
        ObjectAnimator.ofInt(1, 3).start()
        val a: Animator = ObjectAnimator.ofInt(1, 3)
        a.start()
        val b = ObjectAnimator.ofInt(1, 3)
        b.start()
        b.cancel()
        val sb:StringBuilder = StringBuilder()
        sb.append(1)
        StringBuilder().append(1)
        java.lang.StringBuilder().append(1)
        Toast(null).show()

        Color.parseColor("123")
    }
}
