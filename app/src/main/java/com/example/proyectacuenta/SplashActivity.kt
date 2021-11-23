package com.example.proyectacuenta

import android.animation.Animator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectacuenta.databinding.ActivityMainBinding
import com.example.proyectacuenta.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        init()
        events()
    }

    private fun init(){
        // Ejecuta la animacion
        binding.splashAnimation.playAnimation()
    }

    private fun events() {
        binding.splashAnimation.addAnimatorListener(object : Animator.AnimatorListener{
            override fun onAnimationStart(p0: Animator?) {

            }

            override fun onAnimationEnd(p8: Animator?) {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()

            }

            override fun onAnimationCancel(p8: Animator?) {

            }

            override fun onAnimationRepeat(p8: Animator?) {

            }
        })
    }
}