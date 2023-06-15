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
        vm = ViewModelProvider(this,mvFactory)
            .get(CharactersViewModel::class.java)
        vm.getInfo()
       // vm.getMovie()
        //vm.withoutDAgger()
        vm._live.observe(this, Observer {
            println("sssssss")
            vm.saveInDb(vm._live.value!!)
            val rv =findViewById<RecyclerView>(R.id.rvForCharacters)
            adapter.list.addAll(it.results!!)
            rv.adapter = adapter
            val view = findViewById<ConstraintLayout>(R.id.constr_id)

            val listenerForRv = object :RecyclerView.OnScrollListener(){
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(-1)&&newState == RecyclerView.SCROLL_STATE_IDLE){
                        println("верхний порог")
                        val progressBar = findViewById<ProgressBar>(R.id.progressStart)
                        progressBar.visibility = View.VISIBLE
                        val progressBar2 = findViewById<ProgressBar>(R.id.progressEnd)
                        progressBar2.visibility=View.GONE
                    }
                    if (!recyclerView.canScrollVertically(1)&&newState == RecyclerView.SCROLL_STATE_IDLE){
                        println("нижний порог")
                        val progressBar = findViewById<ProgressBar>(R.id.progressEnd)
                        progressBar.visibility=View.VISIBLE
                        val progressBar2 = findViewById<ProgressBar>(R.id.progressStart)
                        progressBar2.visibility = View.GONE
                        vm.loadNewPage(vm._live.value!!.info?.next.toString())
                    }
                }
            }
            rv.addOnScrollListener(listenerForRv)
        })
    }
}