package com.hamdanainurriza_5210411302.hamdanainurtugasmandiri

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.appcompat.app.AlertDialog

class Receiver : BroadcastReceiver() {

    override fun onReceive(context:  Context?, intent: Intent?) {
        when (intent?.action) {
            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                if (airplanEnabled(context)){
                    showAirplaneDialog(context)
            } else {
                showAirplaneDialog(context)
                }
            }
        }

    }

    private fun showAirplaneDialog(context: Context?) {
        val alertDialogBuilder = AlertDialog.Builder(context!!)
        alertDialogBuilder.setTitle("Airplane Mode Activated")
        alertDialogBuilder.setMessage("Airplane mode is currently active. Please disable it to continue.")
        alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }



    private fun airplanEnabled(context: Context?): Boolean {
        return Settings.Global.getInt(
            context?.contentResolver,
            Settings.Global.AIRPLANE_MODE_ON,
            0
        ) != 0

    }


}


