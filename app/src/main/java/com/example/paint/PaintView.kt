package com.example.paint

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.paint.MainActivity.Companion.paintBrush
import com.example.paint.MainActivity.Companion.path
import java.nio.file.Path


class PaintView : View {

    //is responsible for what height and width of canvas w.r.t the parent layout
    var param : ViewGroup.LayoutParams? =null

    companion object{
        //it will store all the paths that we have drawen on page
        var pathList =ArrayList<android.graphics.Path>()

        var colorList =ArrayList<Int>()

        //it will store the default color of the brush as well as curr color of brush
        var currentBrush =Color.BLACK
    }

    constructor(context: Context) : this(context, null){
            init()
    }
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0){
            init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }


    //method that initialize the  brush
    private fun init(){
        //for making the texture smooth
        paintBrush.isAntiAlias = true

        paintBrush.color = currentBrush
        paintBrush.style =Paint.Style.STROKE

        //is end of any path
        paintBrush.strokeJoin =Paint.Join.ROUND
        paintBrush.strokeWidth =8f

        param =ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
    }


    // to register the movements of the fingers of the the user
    override fun onTouchEvent(event: MotionEvent): Boolean {
         val x =event.x
         val y =event.y

         //in kotlin we have when insted of switch
          when(event.action){
              MotionEvent.ACTION_DOWN ->{
                  path.moveTo(x,y)
                  return true
              }

              MotionEvent.ACTION_MOVE ->{
                  path.lineTo(x,y)
                  pathList.add(path)
                  colorList.add(currentBrush)
              }
              else -> return false
          }
          // postinvalidate is written after when statement and used to inform the non UI threads that some changes have done on UI
          postInvalidate()
          return false
    }

    //now after the user have drawn something
    override fun onDraw(canvas: Canvas) {
        //we will get all the paths from the arrayList  one by one and draw on screen
       for(i in pathList.indices){
           paintBrush.setColor(colorList[i])
           canvas.drawPath(pathList[i],paintBrush)
           invalidate() //same as postInvalidate

       }
    }
}