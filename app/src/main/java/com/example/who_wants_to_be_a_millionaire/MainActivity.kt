package com.example.who_wants_to_be_a_millionaire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.who_wants_to_be_a_millionaire.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ruleta.animate().setStartDelay(1000).setDuration(6000).rotation(360f).withEndAction{
            startActivity(Intent(this,Home::class.java))
        }   .start()
        pantallaCompleta()

    }
    private fun pantallaCompleta() {
        WindowInsetsControllerCompat(
            window,
            window.decorView
        ).hide(WindowInsetsCompat.Type.systemBars())
    }
}