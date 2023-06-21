package com.myapplication.finalproject.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
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
        if (savedInstanceState==null){
            //initDefaultScreen()
        }


        binding.bottomNavMenu.setOnItemSelectedListener {
            if (it.itemId == R.id.navCharacters)navigateToCharacters()
            if (it.itemId == R.id.navEpisodes)navigateToEpisodes()
            if (it.itemId == R.id.navLocations)navigateToLocations()
            return@setOnItemSelectedListener true}
    }
    fun initDefaultScreen(){
        setContentView(R.layout.splash_screen)
        Thread.sleep(2000)
    }
    fun navigateToFragment(fragment: Fragment){
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment).setReorderingAllowed(true)
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