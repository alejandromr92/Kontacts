package com.alejandromr.kontacts.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alejandromr.kontacts.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ft = supportFragmentManager.beginTransaction()
        val fragmentList = FragmentList()
        ft.replace(
            R.id.contentLayout, fragmentList,
            FragmentList.TAG
        )
        ft.commit()
    }
}
