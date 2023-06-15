package com.myapplication.finalproject.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.finalproject.R
import com.myapplication.finalproject.app.adapter.AdapterForCharacters
import com.myapplication.finalproject.app.app.App
import com.myapplication.finalproject.app.presentation.CharactersFragment
import com.myapplication.finalproject.app.presentation.CharactersViewModel
import com.myapplication.finalproject.app.presentation.MainViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private val adapter = AdapterForCharacters()
    lateinit var vm: CharactersViewModel
    lateinit var timerNotChangedEditPath: CountDownTimer
    @Inject
    lateinit var mvFactory: MainViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (applicationContext as App).appComponent().inject(this)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,CharactersFragment())
            .commit()
    }
}