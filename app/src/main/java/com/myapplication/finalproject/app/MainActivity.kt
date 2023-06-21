package com.myapplication.finalproject.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.myapplication.finalproject.R
import com.myapplication.finalproject.featureChararcters.presentation.adapter.AdapterForCharacters
import com.myapplication.finalproject.app.app.App
import com.myapplication.finalproject.databinding.ActivityMainBinding
import com.myapplication.finalproject.featureChararcters.presentation.CharactersFragment
import com.myapplication.finalproject.featureChararcters.presentation.CharactersViewModel
import com.myapplication.finalproject.featureEpisodes.presentation.EpisodesFragment
import com.myapplication.finalproject.featureLocation.presentation.LocationsFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavMenu.setOnItemSelectedListener {
            if (it.itemId == R.id.navCharacters)navigateToCharacters()
            if (it.itemId == R.id.navEpisodes)navigateToEpisodes()
            if (it.itemId == R.id.navLocations)navigateToLocations()
            return@setOnItemSelectedListener true}
    }
    fun navigateToFragment(fragment: Fragment){
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
    fun navigateToCharacters(){
        navigateToFragment(CharactersFragment())
    }
    fun navigateToEpisodes(){
        navigateToFragment(EpisodesFragment())
    }
    fun navigateToLocations(){
        navigateToFragment(LocationsFragment())
    }
}