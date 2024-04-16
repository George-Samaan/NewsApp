package com.route.newsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.route.newsapp.databinding.ActivityMainBinding
import com.route.newsapp.newsSources.NewsSourcesFragment

class MainActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_contanier, NewsSourcesFragment())
            .commit()
    }
}