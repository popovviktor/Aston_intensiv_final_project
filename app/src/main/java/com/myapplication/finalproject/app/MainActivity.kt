package com.myapplication.finalproject.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import com.myapplication.finalproject.R
import com.myapplication.finalproject.databinding.ActivityMainBinding
import com.myapplication.finalproject.featureChararcters.presentation.CharactersFragment
import com.myapplication.finalproject.featureEpisodes.presentation.EpisodesFragment
import com.myapplication.finalproject.featureLocation.presentation.LocationsFragment

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
        binding.imBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        onBackPressedDispatcher.addCallback(this,object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                if (supportFragmentManager.backStackEntryCount>0){
                    val count = supportFragmentManager.backStackEntryCount
                    supportFragmentManager.popBackStack()
                    if (count-1==0){
                        binding.imBack.visibility = View.GONE
                    }else{
                        binding.imBack.visibility = View.VISIBLE
                    }
                }else{finish()}
            }
        })
    }
    fun setGoneBackButton(){
        binding.imBack.visibility = View.GONE
    }
    fun setVisibleBackButton(){
        binding.imBack.visibility = View.VISIBLE
    }
    fun navigateToFragment(fragment: Fragment){
        if (supportFragmentManager.backStackEntryCount>0){
            supportFragmentManager.popBackStack(null,POP_BACK_STACK_INCLUSIVE)
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
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