package com.android.callreceiver.activity

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import com.android.callreceiver.R
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    private var preferences: SharedPreferences? = null

    private val urlPref = "url"
    private val secretKeyPref = "secretKey"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferences = getSharedPreferences("data", Context.MODE_PRIVATE)
        setContentView(R.layout.activity_settings)
        setupViews()
    }

    private fun setupViews(){
        back.setOnClickListener{ finish() }
        viewListener.setOnClickListener {
            hideKeyBoard()
        }
        url.setText(preferences!!.getString(urlPref, ""))
        secretKey.setText(preferences!!.getString(secretKeyPref, ""))
    }

    override fun onPause() {
        super.onPause()
        preferences!!.edit()
            .putString(urlPref, url.text.toString())
            .putString(secretKeyPref, secretKey.text.toString())
            .apply()
    }
    private fun hideKeyBoard(){
        val view=this.currentFocus
        if (view!=null){
            val hideBoard=getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hideBoard.hideSoftInputFromWindow(view.windowToken,0)
        }
        else{
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        }
    }
}
