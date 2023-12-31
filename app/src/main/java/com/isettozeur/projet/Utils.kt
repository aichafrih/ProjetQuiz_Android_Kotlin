package com.isettozeur.projet

import android.widget.Toast
import android.content.Context

object Utils {
    fun showToast(context: Context, msg: String) {

        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()

    }


}