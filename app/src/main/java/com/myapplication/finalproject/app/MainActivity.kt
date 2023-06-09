package com.myapplication.finalproject.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.myapplication.finalproject.R
import com.myapplication.finalproject.app.app.App
import javax.inject.Inject
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var vm: MainViewModel
    @Inject
    lateinit var mvFactory: MainViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (applicationContext as App).appComponent.inject(this)
        vm = ViewModelProvider(this,mvFactory)
            .get(MainViewModel::class.java)
        vm.getInfo()
       // vm.getMovie()
        //vm.withoutDAgger()
        vm._live.observe(this, Observer {
            println("sssssss")
            vm.saveInDb(vm._live.value!!)
        })
    }
}