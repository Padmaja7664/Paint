package com.example.paint

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import com.example.paint.PaintView.Companion.colorList
import com.example.paint.PaintView.Companion.currentBrush
import com.example.paint.PaintView.Companion.pathList

class MainActivity : AppCompatActivity() {

    companion object {

        //for drawing anything we need two things i.e path and brush
        var path = Path()
        var paintBrush = Paint()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val redbtn=findViewById<ImageButton>(R.id.redColor)
        val bluebtn=findViewById<ImageButton>(R.id.blueColor)
        val blackbtn=findViewById<ImageButton>(R.id.blackColor)
        val eraser=findViewById<ImageButton>(R.id.whiteColor)

        redbtn.setOnClickListener {
             Toast.makeText(this,"Clicked",Toast.LENGTH_SHORT).show()

             paintBrush.setColor(Color.RED)
             currentColor(paintBrush.color)
        }

        bluebtn.setOnClickListener {
            Toast.makeText(this,"Clicked",Toast.LENGTH_SHORT).show()
            paintBrush.setColor(Color.BLUE)
            currentColor(paintBrush.color)
        }

        blackbtn.setOnClickListener {
            Toast.makeText(this,"Clicked",Toast.LENGTH_SHORT).show()
            paintBrush.setColor(Color.BLACK)
            currentColor(paintBrush.color)
        }

        eraser.setOnClickListener {
            Toast.makeText(this,"Clicked",Toast.LENGTH_SHORT).show()
            pathList.clear()
            colorList.clear()
            path.reset()


        }
    }
    //method used to reflect the changes in paintview activity
    private fun currentColor(color:Int){
        currentBrush = color
        path =Path()
    }
}