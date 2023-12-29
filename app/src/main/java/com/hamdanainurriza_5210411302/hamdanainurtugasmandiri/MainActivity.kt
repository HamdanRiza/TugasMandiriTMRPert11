package com.hamdanainurriza_5210411302.hamdanainurtugasmandiri

import android.Manifest
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity(), View.OnClickListener{
    private val receiver = Receiver()
    private val CALL_PERMISSION_REQUEST_CODE = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMoveIntent: Button = findViewById(R.id.btn_move_intent)
        btnMoveIntent.setOnClickListener(this)

        val btnMoveWithUri : Button = findViewById(R.id.btn_move_uri)
        btnMoveWithUri.setOnClickListener(this)

        val filter = IntentFilter().apply {
            addAction("android.net.conn.CONNECTVITY_CHANGE")
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        }
        registerReceiver(receiver,filter)

        val btnCall: Button = findViewById(R.id.btn_call)
        btnCall.setOnClickListener {
            makePhoneCall()
        }

        val btnMsg: Button = findViewById(R.id.btn_msg)
        btnMsg.setOnClickListener{
            sendSMS()
        }
        val btnOpenWebsite : Button = findViewById(R.id.btn_open_website)
        btnOpenWebsite.setOnClickListener{
            openWebsite()
        }

        val btnSendEmail : Button = findViewById(R.id.btn_send_email)
        btnSendEmail.setOnClickListener{
            sendEmail()
        }

        val btnOpenMaps : Button = findViewById(R.id.btn_open_map)
        btnOpenMaps.setOnClickListener{
            openGoogleMaps()
        }


    }

    private fun openGoogleMaps() {
        val latitude = -7.084221
        val longitude = 109.3643147

        val gmIntentUri = Uri.parse("geo:$latitude, $longitude")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")

        if (mapIntent.resolveActivity(packageManager) != null) {
            startActivity(mapIntent)
        }

    }


    private fun sendEmail() {
        val recipient = "hamdanriza28@gmail.com"
        val subject = "fatihgesang06@gmail.com"
        val message = "Isi pesan email anda disini"

        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:$recipient")
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, message)

        startActivity(Intent.createChooser(intent, "Pilih aplikasi Email"))
    }

    private fun openWebsite() {
        var url = "https://www.uty.ac.id"

        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    private fun sendSMS() {
        val phoneNumber = "085725271599"
        val message = "Halo, ini hamdan"

        val smsIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:$phoneNumber"))
        smsIntent.putExtra("sms_body", message)
        startActivity(smsIntent)
    }

    private fun makePhoneCall() {
        val phoneNumber = "085725271599"
        val dialIntent = Intent(Intent.ACTION_CALL)
        dialIntent.data = Uri.parse("tel:$phoneNumber")

        //izin untuk melakukan panggilan
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            startActivity(dialIntent)
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CALL_PHONE),
                CALL_PERMISSION_REQUEST_CODE
            )
        }
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            //intent filter
            R.id.btn_move_intent -> {
                val moveIntent = Intent(this@MainActivity, MoveIntentActivity::class.java)
                startActivity(moveIntent)
            }
            R.id.btn_move_uri -> {
                val moveWithUri = Intent(this@MainActivity, MoveWithUriActivity::class.java)
                moveWithUri.putExtra(MoveWithUriActivity.EXTRA_NAME,"Hamdan Ainur Riza S")
                moveWithUri.putExtra(MoveWithUriActivity.EXTRA_INT, 20)
                startActivity(moveWithUri)
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == CALL_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }



}