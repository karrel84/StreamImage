package karrel.kr.co.streamimage

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


/**
 * Created by Rell on 2018. 12. 17..
 *
 * 액티비티 종류시 Disposable 의 dispose를 반드시 호출해주어야함
 *
 */
class StreamImageView : View, Disposable {

    private val bitmapOptions = BitmapFactory.Options()

    private var bitmap: Bitmap? = BitmapFactory.decodeResource(resources, R.drawable.line01, bitmapOptions)

    private var disposable: Disposable? = null

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context) : super(context)

    private var imageLeft: Float = 0f

    init {
        disposable = Observable.interval(1L, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                if (bitmapOptions.outWidth <= imageLeft) imageLeft = 0f
            }
            .subscribe {
                imageLeft += 1
                invalidate()
            }
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawBitmap(bitmap, -imageLeft, 0f, null)

        super.onDraw(canvas)
    }


    override fun isDisposed(): Boolean {
        return disposable!!.isDisposed
    }

    override fun dispose() {
        disposable?.dispose()
    }
}

