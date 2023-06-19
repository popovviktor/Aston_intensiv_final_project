package com.myapplication.finalproject.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.finalproject.R
import com.myapplication.finalproject.featureChararcters.presentation.adapter.AdapterForCharacters
import com.myapplication.finalproject.app.app.App
import com.myapplication.finalproject.featureChararcters.presentation.CharactersFragment
import com.myapplication.finalproject.featureChararcters.presentation.CharactersViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.fragment_container, CharactersFragment())
//            .commit()
    }
}