package com.r76127011.setcardgame.component

import android.content.Context
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.r76127011.setcardgame.R


class PlayingCard : View {

    var number: Int = 0
        set(value) {
            if (value in 1..10) {
                field = value
                invalidate()
            }
        }

    var shape: String = ""
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

    var color: String = ""
        set(value) {
            if (value in listOf("red", "green", "black")) {
                if (value == "red") {
                    internalColor = Color.RED
                } else if (value == "green") {
                    internalColor = Color.GREEN
                } else {
                    internalColor = Color.BLACK
                }
                field = value
                invalidate()
            }
        }

    private var internalColor: Int = Color.CYAN

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.PlayingCard)
        number = typedArray.getInt(R.styleable.PlayingCard_number, 1)
        shape = typedArray.getString(R.styleable.PlayingCard_shape) ?: "diamond"
        shading = typedArray.getString(R.styleable.PlayingCard_shading) ?: "solid"
        color = typedArray.getString(R.styleable.PlayingCard_colorLine) ?: "red"
        typedArray.recycle()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        // Get the parent's width
        val parentWidth = MeasureSpec.getSize(widthMeasureSpec)
        val parentHeight = MeasureSpec.getSize(heightMeasureSpec)


        var desiredWidth = (parentWidth * 1).toInt()
        var desiredHeight = (parentHeight * 0.3).toInt()

        if(context.resources.configuration.orientation == ORIENTATION_LANDSCAPE){
            desiredWidth = (parentWidth * 1).toInt()
            desiredHeight = (parentHeight * 1).toInt()
        } else {
            desiredWidth = (parentWidth * 1).toInt()
            desiredHeight = (parentHeight * 0.3).toInt()
        }

        // Use the desired width with the original height spec
        val widthSpec = MeasureSpec.makeMeasureSpec(desiredWidth, MeasureSpec.EXACTLY)
        val heightSpec = MeasureSpec.makeMeasureSpec(desiredHeight, MeasureSpec.EXACTLY)

        // Call the super method with the new width spec
        super.onMeasure(widthSpec, heightSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (number != null) {
            drawCard(canvas)
        }
    }


    private fun drawCard(canvas: Canvas) {
        val paint = Paint(Paint.SUBPIXEL_TEXT_FLAG)
        paint.color = internalColor // Set the color to green
        paint.strokeWidth = 10f

        val width = width // get views width
        val height = height // get views height
        val shapeWidth = (width * 0.7)
        val shapeHeight = (height * 0.2)

        val strippingPath = Path()
        if (shading == "solid") {
            paint.style = Paint.Style.FILL_AND_STROKE
        } else if (shading == "open") {
            paint.style = Paint.Style.STROKE
        } else {
            paint.style = Paint.Style.STROKE
            for (i in 0 until width) {
                if (i % 30 == 0) {
                    strippingPath.moveTo(i.toFloat(), 0f)
                    strippingPath.lineTo(i.toFloat(), height.toFloat())
                }
            }
        }

        if (shape == "oval") {

            // Calculate spacing for the ovals
            val totalOvalHeight = shapeHeight * number
            val totalSpacing = height - totalOvalHeight
            val spacing = totalSpacing / (number + 1)

            val path = Path()
            for (i in 0 until number) {
                val left = ((width - shapeWidth) / 2).toFloat()
                val top = (spacing * (i + 1) + shapeHeight * i).toFloat()
                val right = left + shapeWidth
                val bottom = top + shapeHeight
                val oval = RectF(left, top, right.toFloat(), bottom.toFloat())
                path.addOval(oval, Path.Direction.CW)
            }
            canvas.drawPath(path, paint) // draw all the shape path
            canvas.clipPath(path) // cut everything outside the path (diamond shape)
            canvas.drawPath(strippingPath, paint) // draw the stripping

        } else if (shape == "diamond") {
            // Calculate spacing for the diamonds
            val totalDiamondHeight: Float = (shapeHeight * number).toFloat()
            val totalSpacing = height - totalDiamondHeight
            val spacing: Float = totalSpacing / (number + 1)


            var path = Path()
            for (i in 0 until number) {
                val centerX = (width / 2).toFloat()
                val centerY: Float =
                    (spacing * (i + 1) + shapeHeight * i + shapeHeight / 2).toFloat()

                path.moveTo(centerX, (centerY - shapeHeight / 2).toFloat()) // Top point
                path.lineTo((centerX - shapeWidth / 2).toFloat(), centerY) // Left point
                path.lineTo(centerX, (centerY + shapeHeight / 2).toFloat()) // Bottom point
                path.lineTo((centerX + shapeWidth / 2).toFloat(), centerY) // Right point
                path.close() // close the path

            }
            canvas.drawPath(path, paint) // draw all the shape path
            canvas.clipPath(path) // cut everything outside the path (diamond shape)
            canvas.drawPath(strippingPath, paint) // draw the stripping
        } else if (shape == "squiggle") {
            val left: Float = ((width - shapeWidth) / 2).toFloat()
            val top: Float = ((height - shapeHeight * number) / 2).toFloat()

            // draw squiggle for set card game
            val path = Path()
            path.moveTo(100f, 100f); // Starting point

            // Add quadratic Bezier curve
            path.quadTo(150f, 50f, 200f, 100f);

            // Move to a new starting point
            path.moveTo(100f, 200f);

            // Add cubic Bezier curve
            path.cubicTo(150f, 150f, 250f, 250f, 300f, 200f);

            canvas.drawPath(path, paint)
        }

    }


}