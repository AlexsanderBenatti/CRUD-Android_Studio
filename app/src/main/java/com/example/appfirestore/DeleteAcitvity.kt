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

class DeleteAcitvity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_acitvity)
        val edtNome: EditText = findViewById(R.id.editDeletar)
        val btnDelete: Button = findViewById(R.id.deleteBtn)
        val btnVoltar: Button = findViewById(R.id.btnVoltar)

        val db = Firebase.firestore
        btnDelete.setOnClickListener {
            val nomeDeletar = edtNome.text.toString()
            db.collection("cadastro").document(nomeDeletar)
                .delete()
                .addOnSuccessListener {
                    Toast.makeText(this, "Excluído com sucesso!", Toast.LENGTH_SHORT)
                }
                .addOnFailureListener {
                    Toast.makeText(this, "A exclusão falhou!", Toast.LENGTH_SHORT)
                }
        }

        btnVoltar.setOnClickListener {
            val i = Intent(this, InterfaceActivity::class.java)
            startActivity(i)
        }
    }
}