package com.isettozeur.projet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    private lateinit var  splashImage:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        splashImage = findViewById(R.id.splash_imageView)
        animateZoomOut()
    }

    private fun animateZoomOut() {
        splashImage.animate()
            .scaleX(0.4f)
            .scaleY(0.4f)
            .setDuration(2000)
            .withEndAction {

                animateZoomIn()
            }

            .start()
    }
    private fun animateZoomIn() {


        splashImage.animate()
            .scaleX(1.3f)
            .scaleY(1.3f)
            .setDuration(3000)
            .withEndAction {

                startNewActivity()
            }

            .start()
    }
    private fun startNewActivity(){
        startActivity(Intent(this,login::class.java))
        finish()
    }    }