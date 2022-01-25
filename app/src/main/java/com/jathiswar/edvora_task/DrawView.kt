package com.jathiswar.edvora_task

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.jathiswar.edvora_task.MainActivity.Companion.paintBrush
import com.jathiswar.edvora_task.MainActivity.Companion.path


class DrawView : View {

    var parameters: ViewGroup.LayoutParams? = null

    companion object {
        var pathList = ArrayList<Path>()
        var colorList = ArrayList<Int>()
        var currentColor = Color.BLACK
        var currentStyle = Paint.Style.STROKE

    }


    constructor(context: Context) : this(context, null) {
        brushconfig()
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        brushconfig()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        brushconfig()
    }

    private fun brushconfig() {
        paintBrush.isAntiAlias = true
        paintBrush.color = currentColor
        paintBrush.style = currentStyle
        paintBrush.strokeJoin = Paint.Join.ROUND
        paintBrush.strokeWidth = 10f


        parameters = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {




        var x = event.x
        var y = event.y
        var radius = 100

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(x, y)
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                path.lineTo(x,y)
                pathList.add(path)
                colorList.add(currentColor)


            }

            else -> return false
        }


        postInvalidate()
            return false

    }


    override fun onDraw(canvas: Canvas) {
        for(i in pathList.indices){
            paintBrush.setColor(colorList[i])
            canvas.drawPath(pathList[i], paintBrush)
            invalidate()
        }


    }





}