package com.example.rifqi.footballapp.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

object Functions {

    fun replaceCharacter(resource: String): String? {
        return resource.replace(";", "\n")
    }

    fun snackBar(view: View, message: Int) {
        val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        snackBar.show()
    }
}