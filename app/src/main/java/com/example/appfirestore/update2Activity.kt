package com.example.appfirestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class update2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update2)

        val db = Firebase.firestore
        val edtNome: EditText = findViewById(R.id.edtNome)
        val edtEndereco: EditText = findViewById(R.id.edtEndereco)
        val edtBairro: EditText = findViewById(R.id.edtBairro)
        val edtCep: EditText = findViewById(R.id.edtCep)
        val btnAtualizar: Button = findViewById(R.id.btnAtualizar)
        val btnVoltar: Button = findViewById(R.id.btnVoltar)


        val nome: String = intent.getStringExtra("Nome").toString()

        if (nome != "null") {
            edtNome.isEnabled = false
            db.collection("cadastro").document(nome)
                .get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        edtNome.setText("${document.data?.get("nome")}")
                        edtEndereco.setText("${document.data?.get("endereco")}")
                        edtBairro.setText("${document.data?.get("bairro")}")
                        edtCep.setText("${document.data?.get("cep")}")
                    } else {
                        Toast.makeText(this, "Documento não apresenta dados", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(
                        this,
                        "Não foi possível pegar os dados desse cadastro",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }

        btnAtualizar.setOnClickListener {
            val pessoa = hashMapOf(
                "nome" to edtNome.text.toString(),
                "endereco" to edtEndereco.text.toString(),
                "bairro" to edtBairro.text.toString(),
                "cep" to edtCep.text.toString()
            )

            db.collection("cadastro").document(edtNome.text.toString())
                .set(pessoa)
                .addOnSuccessListener { Toast.makeText(this, "Cadastro atualizado com sucesso!", Toast.LENGTH_SHORT).show() }
                .addOnFailureListener { Toast.makeText(this, "Cadastro não atualizou!", Toast.LENGTH_SHORT).show() }
        }

        btnVoltar.setOnClickListener {
            val i = Intent(this, InterfaceActivity::class.java)
            startActivity(i)
        }
    }
}