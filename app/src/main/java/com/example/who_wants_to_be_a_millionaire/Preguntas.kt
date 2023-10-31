package com.example.who_wants_to_be_a_millionaire

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreguntasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = FirebaseDatabase.getInstance()
        dataref =db.getReference("datos")

        traer()

        binding.apply {
            txtopcion1.setOnClickListener {
                txtopcion1.setBackgroundResource(R.drawable.correcto)
                txtopcion1.setTextColor(R.color.black)
            }
            txtopcion2.setOnClickListener {
                txtopcion2.setBackgroundResource(R.drawable.incorrecto)
                txtopcion2.setTextColor(R.color.black)
            }
            txtopcion3.setOnClickListener {
                txtopcion3.setBackgroundResource(R.drawable.seleccionado)
                txtopcion3.setTextColor(R.color.black)
            }
            txtopcion4.setOnClickListener {

            }
        }
    }
    fun traer(){
        dataref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var list:MutableList<datos> = mutableListOf()

              snapshot.children.forEach {
                  var dato=it.getValue(datos::class.java)

                  list.add(datos(dato!!.pregunta,dato!!.respuesta,dato!!.opcion1,dato!!.opcion2,dato!!.opcion3,dato!!.opcion4))

              }
               var num = Random.nextInt(0,9)

                binding.apply {
                    txtpregunta.text =list[num].pregunta
                    txtopcion1.text = list[num].opcion1
                    txtopcion2.text = list[num].opcion2
                    txtopcion3.text = list[num].opcion3
                    txtopcion4.text = list[num].opcion4
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

}