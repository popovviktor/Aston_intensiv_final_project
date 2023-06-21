package com.myapplication.finalproject.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.core.os.HandlerCompat.postDelayed
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.myapplication.finalproject.R
import com.myapplication.finalproject.databinding.ActivityMainBinding
import com.myapplication.finalproject.featureChararcters.presentation.CharactersFragment
import com.myapplication.finalproject.featureEpisodes.presentation.EpisodesFragment
import com.myapplication.finalproject.featureLocation.presentation.LocationsFragment
import kotlinx.coroutines.delay

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
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack("character")
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