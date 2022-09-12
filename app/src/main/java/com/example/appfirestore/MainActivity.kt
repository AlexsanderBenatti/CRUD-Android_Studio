package com.example.appfirestore

import android.content.ContentValues.TAG
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
        val edtExcluir: EditText = findViewById(R.id.edtDelete)
        val btnCadastrar: Button = findViewById(R.id.btnCadastrar)
        val btnExcluir: Button = findViewById(R.id.btnDelete)

        btnCadastrar.setOnClickListener {
            // Create a new user with a first and last name
            val pessoa = hashMapOf(
                "nome" to edtNome.text.toString(),
                "endereco" to edtEndereco.text.toString(),
                "bairro" to edtBairro.text.toString(),
                "cep" to edtCep.text.toString()
            )

// Add a new document with a generated ID
            db.collection("cadastro")
                .add(pessoa)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")

                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
            Toast.makeText(this, " Cadastrado com sucesso", Toast.LENGTH_SHORT).show()
        }
        btnExcluir.setOnClickListener{
            db.collection("cadastro").document("documentReference.id")
                .delete()
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
            Toast.makeText(this, "Excluído com sucesso", Toast.LENGTH_SHORT).show()
        }
    }


}