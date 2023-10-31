package com.example.who_wants_to_be_a_millionaire.Model

data class datos(var pregunta:String,var respuesta: String,var opcion1: String,var opcion2: String,var opcion3: String,var opcion4: String,){
    constructor():this("","","","","","")
}
