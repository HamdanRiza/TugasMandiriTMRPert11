package com.hamdanainurriza_5210411302.hamdanainurtugasmandiri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MoveWithUriActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_INT = "extra_int"
        const val EXTRA_NAME = "extra_name"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_with_uri)

        val tvDataReceived : TextView = findViewById(R.id.tv_data_received)

        val name = intent.getStringExtra(EXTRA_NAME)
        val int = intent.getIntExtra(EXTRA_INT, 20)

        val text = "Nama: $name," +
                "NIM : $int"
        tvDataReceived.text = text


    }
}