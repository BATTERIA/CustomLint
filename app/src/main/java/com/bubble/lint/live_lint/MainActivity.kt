package com.bubble.lint.live_lint

import android.animation.Animator
import android.animation.ObjectAnimator
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bubble.lint.videoliveplayer.ui.roomv3.base.TestEvent
import com.bubble.lint.videoliveplayer.ui.roomv3.base.events.bussiness.BattleInfoEvent
import com.bubble.lint.videoliveplayer.ui.roomv3.base.rxbus.IRxBusManager
import com.bubble.lint.videoliveplayer.ui.roomv3.base.rxbus.RxBusManager
import java.io.Serializable
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

class SerializableBean : Serializable {
    private var serializableField: InnerSerializableBean? = null
}

class InnerSerializableBean : Serializable {
    private var commonBean: CommonBean? = null
    private val a: CommonBean = CommonBean()
}

class CommonBean {
    private var s: String = "abc"

    init {
        ObjectAnimator.ofInt(1, 3).start()
        val a: Animator = ObjectAnimator.ofInt(1, 3)
        a.start()
        val b = ObjectAnimator.ofInt(1, 3)
        b.start()
        ObjectAnimator.ofInt(1, 3).start()
        val sb: StringBuilder = StringBuilder()
        sb.append(1)
        StringBuilder().append(1)
        Toast(null).show()

        Color.parseColor("123")
    }

    fun a() {
        ObjectAnimator.ofInt(1, 3).start()
        val a: Animator = ObjectAnimator.ofInt(1, 3)
        a.start()
        val b = ObjectAnimator.ofInt(1, 3)
        b.start()
        ObjectAnimator.ofInt(1, 3).start()
        val sb: StringBuilder = StringBuilder()
        sb.append(1)
        StringBuilder().append(1)
        Thread().run()
        val manager: IRxBusManager = RxBusManager()
        manager.postEvent(TestEvent())
        val e = TestEvent()
        manager.postEvent(e)
        manager.postEvent(BattleInfoEvent())
        val e2 = BattleInfoEvent()
        manager.postEvent(e2)
    }

    fun A() {
        val v = c()
    }
}

class c