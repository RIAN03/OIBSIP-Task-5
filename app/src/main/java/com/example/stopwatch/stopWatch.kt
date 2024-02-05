package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.stopwatch.databinding.ActivityStopWatchBinding

class stopWatch : AppCompatActivity() {
    lateinit var binding: ActivityStopWatchBinding
    private var isTimerRunning = false
    private var pauseTime: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStopWatchBinding.inflate(layoutInflater)

        binding.btnstop.alpha = 0F
        binding.btnpause.alpha = 0F

        val roundingAlone = AnimationUtils.loadAnimation(this, R.anim.roundingalone)

        binding.btnstart.setOnClickListener {
            binding.icanchor.startAnimation(roundingAlone)
            binding.btnstop.animate().alpha(1F).translationY(-80F).setDuration(300).start()
            binding.btnpause.animate().alpha(1F).translationY(-80F).setDuration(300).start()
            binding.btnstart.animate().alpha(0F).setDuration(300).start()

            if (!isTimerRunning) {
                binding.timer.base = SystemClock.elapsedRealtime()
                binding.timer.start()
                isTimerRunning = true
            }
        }

        binding.btnpause.setOnClickListener {
            /*binding.icanchor.startAnimation(roundingAlone)
            binding.btnstart.animate().alpha(1F).translationY(-80F).setDuration(300).start()
            binding.btnstop.animate().alpha(0F).setDuration(300).start()

            if (isTimerRunning) {
                binding.timer.stop()
                isTimerRunning = false
            }*/

            if (isTimerRunning) {
                // Pause the timer
                binding.icanchor.clearAnimation()
                //binding.btnstart.animate().alpha(1F).translationY(-80F).setDuration(300).start()
                //binding.btnstop.animate().alpha(0F).setDuration(300).start()

                binding.timer.stop()
                isTimerRunning = false

                // Store the current timestamp
                pauseTime = System.currentTimeMillis()
            } else if (pauseTime != null) {
                // Resume the timer from the stored timestamp
                val elapsedTime = System.currentTimeMillis() - pauseTime!!
                binding.icanchor.startAnimation(roundingAlone)
                //binding.btnstart.animate().alpha(1F).translationY(-80F).setDuration(300).start()
                //binding.btnstop.animate().alpha(0F).setDuration(300).start()

                binding.timer.base += elapsedTime
                binding.timer.start()
                isTimerRunning = true

                // Reset the pauseTime variable
                pauseTime = null
            }
        }

        binding.btnstop.setOnClickListener {
            binding.icanchor.clearAnimation()
            binding.btnstart.animate().alpha(1F).translationY(-80F).setDuration(300).start()
            binding.btnstop.animate().alpha(0F).setDuration(300).start()
            binding.btnpause.animate().alpha(0F).setDuration(300).start()

            if (isTimerRunning) {
                binding.timer.stop()
                isTimerRunning = false
            }

            // Reset the timer
            binding.timer.base = SystemClock.elapsedRealtime()
        }

        setContentView(binding.root)
    }
}