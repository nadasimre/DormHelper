package com.example.dormhelper.screens

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.dormhelper.R
import com.example.dormhelper.databinding.ActivityDormBinding

class DormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#3700B3")))

        binding = DataBindingUtil.setContentView(this, R.layout.activity_dorm)
        }
    }
