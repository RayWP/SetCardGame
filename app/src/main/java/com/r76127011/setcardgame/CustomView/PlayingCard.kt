package com.r76127011.setcardgame.CustomView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.util.AttributeSet
import android.view.View
import androidx.compose.material3.Card
import com.r76127011.setcardgame.R

class PlayingCard: View {

    var number: Int = 0
        set(value) {
            if (value in 1..10) {
                field = value
                invalidate()
            }
        }

    var type: String = ""
        set(value) {
            if (value in listOf("diamond", "squiggle", "oval")) {
                field = value
                invalidate()
            }
        }

    var shading: String = ""
        set(value) {
            if (value in listOf("solid", "striped", "open")) {
                field = value
                invalidate()
            }
        }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.PlayingCard)
        number = typedArray.getInt(R.styleable.PlayingCard_number, 1)
        type = typedArray.getString(R.styleable.PlayingCard_type) ?: "diamond"
        shading = typedArray.getString(R.styleable.PlayingCard_shading) ?: "solid"
        typedArray.recycle()
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (number != null) {
            drawCard(canvas)
        }
    }

    private fun drawCard(canvas: Canvas) {
        val paint = Paint(STRIKE_THRU_TEXT_FLAG)
        canvas.drawCircle(number.toFloat(), number.toFloat(), (number*2).toFloat(),  paint)
    }

}