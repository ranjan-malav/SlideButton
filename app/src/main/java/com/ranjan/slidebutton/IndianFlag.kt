package com.ranjan.slidebutton

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.annotation.ColorInt
import kotlin.math.cos
import kotlin.math.sin

/**
 * Created by BlueSapling on 2020-01-23.
 */

class IndianFlag : RelativeLayout {
    constructor(context: Context?) : super(context) {
        init()
        setWillNotDraw(false)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
        setWillNotDraw(false)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
        setWillNotDraw(false)
    }

    @ColorInt
    val SAFFRON = -0x66cd
    @ColorInt
    val WHITE = -0x1
    @ColorInt
    val GREEN = -0xec77f8
    @ColorInt
    val BLUE = -0xffff80

    private val rect: Rect = Rect()
    private lateinit var paint: Paint

    private var scaledHeight: Int = 0
    private var scaledWidth: Int = 0
    private var xo: Int = 0
    private var yo: Int = 0

    private fun init() {
        paint = Paint()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        when {
            width == (1.5 * height).toInt() -> {
                scaledHeight = height
                scaledWidth = width
            }
            width > 1.5 * height -> {
                scaledHeight = height
                scaledWidth = (height * 1.5).toInt()
                xo = (width - scaledWidth) / 2
                yo = 0
            }
            else -> {
                scaledHeight = (width * 0.67).toInt()
                scaledWidth = width
                xo = 0
                yo = (height - scaledHeight) / 2
            }
        }
        createSaffron(canvas)
        createWhite(canvas)
        createGreen(canvas)
        createCircle(canvas)
        createSpokes(canvas)
    }

    private fun createSaffron(canvas: Canvas?) {
        rect.apply {
            left = xo
            top = yo
            right = xo + scaledWidth
            bottom = yo + (scaledHeight / 3)
        }
        paint.color = SAFFRON
        canvas?.drawRect(rect, paint)
    }

    private fun createWhite(canvas: Canvas?) {
        rect.apply {
            left = xo
            top = yo + (scaledHeight / 3)
            right = xo + scaledWidth
            bottom = yo + ((2 * scaledHeight) / 3)
        }
        paint.color = WHITE
        canvas?.drawRect(rect, paint)
    }

    private fun createGreen(canvas: Canvas?) {
        rect.apply {
            left = xo
            top = yo + ((2 * scaledHeight) / 3)
            right = xo + scaledWidth
            bottom = yo + scaledHeight
        }
        paint.color = GREEN
        canvas?.drawRect(rect, paint)
    }

    private fun createCircle(canvas: Canvas?) {
        paint.color = BLUE
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = scaledHeight * 0.02f
        canvas?.drawCircle(
            xo + (scaledWidth / 2f),
            yo + (scaledHeight / 2f),
            (0.8f * scaledHeight / 6f),
            paint
        )

        paint.style = Paint.Style.FILL
        canvas?.drawCircle(
            xo + (scaledWidth / 2f),
            yo + (scaledHeight / 2f),
            (0.02f * scaledHeight),
            paint
        )
    }

    private fun createSpokes(canvas: Canvas?) {
        paint.color = BLUE
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = scaledHeight * 0.01f
        var angle = 0.0
        val xo = xo + (scaledWidth / 2f)
        val yo = yo + (scaledHeight / 2f)
        val radius = (0.7f * scaledHeight / 6f)
        repeat(24) {
            val x1 = xo + (radius * cos(Math.toRadians(angle)))
            val y1 = yo - (radius * sin(Math.toRadians(angle)))
            canvas?.drawLine(xo, yo, x1.toFloat(), y1.toFloat(), paint)
            angle += (360f / 24)
        }
    }
}