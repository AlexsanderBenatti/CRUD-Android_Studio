package com.example.appfirestore

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Firebase.firestore

        val edtNome: EditText = findViewById(R.id.edtNome)
        val edtEndereco: EditText = findViewById(R.id.edtEndereco)
        val edtBairro: EditText = findViewById(R.id.edtBairro)
        val edtCep: EditText = findViewById(R.id.edtCep)
        val btnCadastrar: Button = findViewById(R.id.btnCadastrar)
        val btnVoltar: Button = findViewById(R.id.btnVoltar)


        btnCadastrar.setOnClickListener {
            // Create a new user with a first and last name
            val pessoa = hashMapOf(
                "nome" to edtNome.text.toString(),
                "endereco" to edtEndereco.text.toString(),
                "bairro" to edtBairro.text.toString(),
                "cep" to edtCep.text.toString()
            )

// Add a new document with a generated ID
            db.collection("cadastro").document(edtNome.text.toString())
                .set(pessoa)
                .addOnSuccessListener {
                    Toast.makeText(this, "Cadastro atualizado com sucesso!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Cadastro deu errado!", Toast.LENGTH_SHORT).show()
                }
        }

        btnVoltar.setOnClickListener {
            val i = Intent(this, InterfaceActivity::class.java)
            startActivity(i)
        }
    }


}