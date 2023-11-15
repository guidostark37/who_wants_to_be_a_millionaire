package com.example.who_wants_to_be_a_millionaire

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.example.who_wants_to_be_a_millionaire.Model.datos
import com.example.who_wants_to_be_a_millionaire.databinding.ActivityPreguntasBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.ktx.Firebase
import kotlin.random.Random as Random

class Preguntas : AppCompatActivity() {
    lateinit var binding: ActivityPreguntasBinding
    lateinit var db:FirebaseDatabase
    lateinit var dataref:DatabaseReference
    var list:MutableList<datos> = mutableListOf()
    var respuesta:String = ""


    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreguntasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var time=10


        db = FirebaseDatabase.getInstance()
        dataref =db.getReference("datos")

        traer()
        var timer=object: CountDownTimer(11000,1000){

            override fun onTick(millisUntilFinished: Long) {
                binding.txttiempo.text = time.toString()
                --time


            }

            override fun onFinish() {

                time=10

                list.clear()
                startActivity(Intent(this@Preguntas,Home::class.java))


            }

        }
        timer.start()


    }

    fun traer(){

        dataref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {


              snapshot.children.forEach {
                  var dato=it.getValue(datos::class.java)

                  list.add(datos(dato!!.pregunta,dato!!.respuesta,dato!!.opcion1,dato!!.opcion2,dato!!.opcion3,dato!!.opcion4))

              }
                var num = Random.nextInt(0,9)
                var guardar = getSharedPreferences("respuesta", Context.MODE_PRIVATE).edit()
                binding.apply {
                    txtpregunta.text =list[num].pregunta
                    txtopcion1.text = list[num].opcion1
                    txtopcion2.text = list[num].opcion2
                    txtopcion3.text = list[num].opcion3
                    txtopcion4.text = list[num].opcion4
                    respuesta = list[num].respuesta

                    txtopcion1.setOnClickListener {
                        txtopcion1.setBackgroundResource(R.drawable.seleccionado)
                        if (txtopcion1.text ==respuesta){
                            guardar.putString("premio","gano").commit()
                            txtopcion1.setBackgroundResource(R.drawable.correcto)
                        }else{
                            txtopcion1.setBackgroundResource(R.drawable.incorrecto)
                            guardar.putString("premio","").commit()
                        }
                    }


                    txtopcion2.setOnClickListener {
                        txtopcion2.setBackgroundResource(R.drawable.seleccionado)
                        if (txtopcion2.text ==respuesta){
                            guardar.putString("premio",1.toString()).commit()
                            txtopcion2.setBackgroundResource(R.drawable.correcto)
                        }else{
                            txtopcion2.setBackgroundResource(R.drawable.incorrecto)
                            guardar.putString("premio","").commit()
                        }
                    }

                    txtopcion3.setOnClickListener {
                        txtopcion3.setBackgroundResource(R.drawable.seleccionado)
                        if (txtopcion3.text ==respuesta){
                            guardar.putString("premio",1.toString()).commit()
                            txtopcion3.setBackgroundResource(R.drawable.correcto)
                        }else{
                            txtopcion3.setBackgroundResource(R.drawable.incorrecto)
                            guardar.putString("premio","").commit()
                        }
                    }

                    txtopcion4.setOnClickListener {
                        txtopcion4.setBackgroundResource(R.drawable.seleccionado)
                        if (txtopcion4.text ==respuesta){
                            guardar.putString("premio",1.toString()).commit()
                            txtopcion4.setBackgroundResource(R.drawable.correcto)
                        }else{
                            txtopcion4.setBackgroundResource(R.drawable.incorrecto)
                            guardar.putString("premio","").commit()
                        }
                    }
                }


            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

}