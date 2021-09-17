package ru.boringowl.parapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.boringowl.parapp.presentation.repository.Repository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Repository.initRepository(application)
    }
}