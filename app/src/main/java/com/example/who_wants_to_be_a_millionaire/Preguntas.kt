package com.example.who_wants_to_be_a_millionaire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.who_wants_to_be_a_millionaire.databinding.ActivityPreguntasBinding

class Preguntas : AppCompatActivity() {
    lateinit var binding: ActivityPreguntasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreguntasBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}