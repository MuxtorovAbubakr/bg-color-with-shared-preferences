package com.example.backgroundcolorwithcache

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.backgroundcolorwithcache.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var image: ImageView
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var textcolor: TextView
    private lateinit var bitmap: Bitmap
    private lateinit var color: String
    private lateinit var colorView: View

    @SuppressLint("ClickableViewAccessibility", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        image = findViewById(R.id.image_color)
        textcolor = findViewById(R.id.text_color)
        colorView = findViewById(R.id.main1)


        image.isDrawingCacheEnabled = true
        image.buildDrawingCache(true)
        image.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE) {
                bitmap = image.drawingCache
                val pixel = bitmap.getPixel(event.x.toInt(), event.y.toInt())
                val r = Color.red(pixel)
                val g = Color.green(pixel)
                val b = Color.blue(pixel)
                color = "#${Integer.toHexString(pixel)}"
                colorView.setBackgroundColor(Color.rgb(r, g, b))
                sharedPreferences.edit().putString("color", color).apply()
                MyObject.color = colorView.background
            }
            true
        }
    }

}