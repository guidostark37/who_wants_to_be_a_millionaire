package com.example.who_wants_to_be_a_millionaire

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.example.who_wants_to_be_a_millionaire.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    var punto:Int =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var time = 10
        var record = 0
        var timer = object : CountDownTimer(11000, 1000) {

            override fun onTick(millisUntilFinished: Long) {

                --time
                var guardar = getSharedPreferences("respuesta", Context.MODE_PRIVATE).edit()
                var prueba = getSharedPreferences("respuesta", Context.MODE_PRIVATE).getString("premio", "")
                if (prueba!=""){
                    punto+=1
                    if (punto>0){
                        binding.muestra10.setBackgroundResource(R.drawable.correcto)
                    }else if (punto>1){
                        binding.muestra9.setBackgroundResource(R.drawable.correcto)
                    }else if (punto>2){
                        binding.muestra9.setBackgroundResource(R.drawable.correcto)
                    }else if (punto>3){
                        binding.muestra8.setBackgroundResource(R.drawable.correcto)
                    }else if (punto>4){
                        binding.muestra7.setBackgroundResource(R.drawable.correcto)
                    }else if (punto>5){
                        binding.muestra6.setBackgroundResource(R.drawable.correcto)
                    }else if (punto>6){
                        binding.muestra5.setBackgroundResource(R.drawable.correcto)
                    }else if (punto>7){
                        binding.muestra9.setBackgroundResource(R.drawable.correcto)
                    }else if (punto>8){
                        binding.muestra9.setBackgroundResource(R.drawable.correcto)
                    }else if (punto>9){
                        binding.muestra9.setBackgroundResource(R.drawable.correcto)
                    }else{
                        Toast.makeText(this@Home, "Tu puntuacion es: ${punto}", Toast.LENGTH_SHORT).show()
                    }
                }


            }


            override fun onFinish() {

                time = 10
                startActivity(Intent(this@Home, Preguntas::class.java))





            }

        }
        timer.start()

    }
}