package ru.pervukhin.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import ru.pervukhin.presentation.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var selectedItem = R.id.ticket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.container) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomBar.setOnItemSelectedListener {
            if (selectedItem != it.itemId)
                when (it.itemId) {
                    R.id.ticket -> {
                        selectedItem = it.itemId
                        navController.navigate(R.id.homeFragment)
                        return@setOnItemSelectedListener true
                    }
                    R.id.hotel -> {
                        selectedItem = it.itemId
                        navController.navigate(R.id.hotelFragment)
                        return@setOnItemSelectedListener true
                    }
                    R.id.map -> {
                        selectedItem = it.itemId
                        navController.navigate(R.id.mapFragment)
                        return@setOnItemSelectedListener true
                    }
                    R.id.subscribe -> {
                        selectedItem = it.itemId
                        navController.navigate(R.id.subscribeFragment)
                        return@setOnItemSelectedListener true
                    }
                    R.id.profile -> {
                        selectedItem = it.itemId
                        navController.navigate(R.id.profileFragment)
                        return@setOnItemSelectedListener true
                    }
                }
            false
        }
    }

    interface InvisibleToolBar
}