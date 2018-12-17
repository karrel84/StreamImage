package karrel.kr.co.streamimage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import kotlinx.android.synthetic.main.activity_main.*

class StreamImageActivity1 : AppCompatActivity() {

    private var translateAnimation: TranslateAnimation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupStreamImage()
        setupStreamImageEvent()
    }

    private fun setupStreamImageEvent() {
        streamImage.setOnClickListener {
            it.startAnimation(translateAnimation)
        }
    }

    private fun setupStreamImage() {
        translateAnimation = TranslateAnimation(0f, -1500f, 0f, 0f)
        translateAnimation?.duration = 5000
        translateAnimation?.repeatCount = Animation.INFINITE
        translateAnimation?.repeatMode = Animation.RESTART
    }
}
