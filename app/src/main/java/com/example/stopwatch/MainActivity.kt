package com.example.stopwatch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.example.stopwatch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val atg = AnimationUtils.loadAnimation(this,R.anim.atg)
        val btgone = AnimationUtils.loadAnimation(this,R.anim.btgone)
        val btgtwo = AnimationUtils.loadAnimation(this,R.anim.btgtwo)

        binding.ivSplash.startAnimation(atg)
        binding.tvSplash.startAnimation(btgone)
        binding.tvSubSplash.startAnimation(btgone)
        binding.btnget.startAnimation(btgtwo)

        binding.btnget.setOnClickListener {
            val intent = Intent(this,stopWatch::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
        setContentView(binding.root)
    }
}