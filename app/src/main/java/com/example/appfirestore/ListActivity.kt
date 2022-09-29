package com.example.appfirestore

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val db = Firebase.firestore
        val edtNomeAtualizar: EditText = findViewById(R.id.edtNomeAtualizar)
        val btnAtualizarNome: Button = findViewById(R.id.btnListar)

        val nome = edtNomeAtualizar.text.toString()

        val resultTxt: TextView = findViewById(R.id.listTxt)
        db.collection("cadastro")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val textoAtual = resultTxt.text.toString()
                    val textoNovo = textoAtual + "Nome: ${ document.data["nome"] }\nEndereço: ${ document.data["endereco"] }\nBairro: ${ document.data["bairro"] }\nCEP: ${ document.data["cep"] }\n\n"
                    resultTxt.text = textoNovo
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Erro na listagem", Toast.LENGTH_SHORT).show()
            }

        val btnVoltarMenu = findViewById<Button>(R.id.btnVoltar)

        btnAtualizarNome.setOnClickListener {
            val i = Intent(this, update2Activity::class.java)
            i.putExtra("nomeAtualizar", nome)
            startActivity(i)
        }

        btnVoltarMenu.setOnClickListener {
            val i = Intent(this, InterfaceActivity::class.java)
            startActivity(i)
        }
    }
}