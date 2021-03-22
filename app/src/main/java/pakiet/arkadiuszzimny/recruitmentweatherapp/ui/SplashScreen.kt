package pakiet.arkadiuszzimny.recruitmentweatherapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.splash_screen.*
import pakiet.arkadiuszzimny.recruitmentweatherapp.R
import pakiet.arkadiuszzimny.recruitmentweatherapp.databinding.SplashScreenBinding

class SplashScreen: AppCompatActivity() {

    private lateinit var binding: SplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val middleAnimation = AnimationUtils.loadAnimation(this, R.anim.middle_animation)

        logo.startAnimation(middleAnimation)

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 1400L)
    }
}