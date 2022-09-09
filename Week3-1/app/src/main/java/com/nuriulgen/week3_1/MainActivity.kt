package com.nuriulgen.week3_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.databinding.DataBindingUtil
import com.nuriulgen.week3_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.chronometerButton.setOnClickListener {
            // used to show fragment
            showFragment()
        }

    }

    private fun showFragment(){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val homeFragment = HomeFragment()
        fragmentTransaction.replace(R.id.frameLayout,homeFragment).commit()
    }


}