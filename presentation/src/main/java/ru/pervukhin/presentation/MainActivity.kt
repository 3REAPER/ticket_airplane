package ru.pervukhin.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import ru.pervukhin.presentation.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.container) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomBar.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.ticket -> {
                    navController.navigate(R.id.homeFragment)
                    return@setOnItemSelectedListener true
                }

                R.id.hotel -> {
                    navController.navigate(R.id.hotelFragment)
                    return@setOnItemSelectedListener true
                }

                R.id.map -> {
                    navController.navigate(R.id.mapFragment)
                    return@setOnItemSelectedListener true
                }

                R.id.subscribe -> {
                    navController.navigate(R.id.subscribeFragment)
                    return@setOnItemSelectedListener true
                }

                R.id.profile -> {
                    navController.navigate(R.id.profileFragment)
                    return@setOnItemSelectedListener true
                }

                else -> return@setOnItemSelectedListener false

            }
        }
    }

    interface InvisibleToolBar
}