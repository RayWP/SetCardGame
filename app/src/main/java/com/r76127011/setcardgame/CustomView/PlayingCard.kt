package com.r76127011.setcardgame.CustomView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.minus
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

    private fun drawStroke(canvas: Canvas, shape: RectF) {
        val numberOfStrokes = 20;
        // Initialize the hatch paint
        // Initialize the hatch paint
        val hatchPaint = Paint()
        hatchPaint.setColor(-0x1000000) // Black color
        hatchPaint.strokeWidth = 2f


    }

    private fun drawCard(canvas: Canvas) {
        val paint = Paint(Paint.SUBPIXEL_TEXT_FLAG)
        paint.color = -0xff0100 // Set the color to green
        paint.strokeWidth = 20f

        if(shading == "solid") {
            paint.style = Paint.Style.FILL_AND_STROKE
        } else {
            paint.style = Paint.Style.STROKE
        }

        val width = width // get views width
        val height = height // get views height
        val shapeWidth = (width * 0.7)
        val shapeHeight = (height * 0.2)


        if(type == "oval") {

            // Calculate spacing for the ovals
            val totalOvalHeight = shapeHeight * number
            val totalSpacing = height - totalOvalHeight
            val spacing = totalSpacing / (number + 1)

            for (i in 0 until number) {
                val left = ((width - shapeWidth) / 2).toFloat()
                val top = (spacing * (i + 1) + shapeHeight * i).toFloat()
                val right = left + shapeWidth
                val bottom = top + shapeHeight
                val oval = RectF(left, top, right.toFloat(), bottom.toFloat())
                canvas.drawOval(oval, paint)
            }

        } else if(type == "diamond") {
            // Calculate spacing for the diamonds
            val totalDiamondHeight: Float = (shapeHeight * number).toFloat()
            val totalSpacing = height - totalDiamondHeight
            val spacing: Float = totalSpacing / (number + 1)


            var lastPath = Path()
            for (i in 0 until number) {
                val centerX = (width / 2).toFloat()
                val centerY: Float = (spacing * (i + 1) + shapeHeight * i + shapeHeight / 2).toFloat()

                // Create a path for the diamond shape
                val path = Path()

                path.moveTo(centerX, (centerY - shapeHeight / 2).toFloat()) // Top point
                path.lineTo((centerX - shapeWidth / 2).toFloat(), centerY) // Left point
                path.lineTo(centerX, (centerY + shapeHeight / 2).toFloat()) // Bottom point
                path.lineTo((centerX + shapeWidth / 2).toFloat(), centerY) // Right point
                path.lineTo(centerX, (centerY - shapeHeight / 2).toFloat()) // Right point
                canvas.drawPath(path, paint)
            }

        } else if (type == "squiggle") {

        }

    }

}