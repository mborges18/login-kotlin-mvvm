package com.example.loginmvvm.common.message

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.loginmvvm.R

class Message {

    fun dialog(context: Context, type: EType, text: String) {
        val dialog = AlertDialog.Builder(context)
        val layoutInflater = LayoutInflater.from(context)
        val dialogView = layoutInflater.inflate(R.layout.view_dialog, null)
        dialog.setView(dialogView)

        val alertDialog = dialog.create()
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.show()
        alertDialog.window?.decorView?.setBackgroundResource(android.R.color.transparent)

        dialogView.findViewById<ImageView>(R.id.iconArlert).handlerIcon(type)
        dialogView.findViewById<TextView>(R.id.tvMsg).text = text
        dialogView.findViewById<Button>(R.id.btnOk).apply {
            handlerButton(type)
            setOnClickListener {
                alertDialog.dismiss()
            }
        }
    }

    private fun ImageView.handlerIcon(type: EType) {
        when (type) {
            EType.SUCCESS -> this.setImageResource(R.drawable.ic_success)
            EType.ERROR -> this.setImageResource(R.drawable.ic_error)
            EType.INFO -> this.setImageResource(R.drawable.ic_info)
            EType.WARN -> this.setImageResource(R.drawable.ic_warn)
            EType.CONNECTION -> this.setImageResource(R.drawable.ic_error_connection)
        }
    }

    private fun Button.handlerButton(type: EType) {
        when (type) {
            EType.SUCCESS -> this.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorGreen
                )
            )

            EType.ERROR -> this.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorRed
                )
            )

            EType.INFO -> this.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorBlue
                )
            )

            EType.WARN -> this.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorYellow
                )
            )

            EType.CONNECTION -> this.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.black
                )
            )
        }
    }

    enum class EType {
        SUCCESS, ERROR, WARN, INFO, CONNECTION
    }
}

