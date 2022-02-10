package com.example.fooddairy

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.fooddairy.databinding.ActivityDataSourceBinding

class DataSourceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDataSourceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_source)

        binding.dataSourceCloud.setOnClickListener {
            setDataSourcePreference(false)
            startMainActivity()
        }
        binding.dataSourceLocal.setOnClickListener {
            setDataSourcePreference(true)
            startMainActivity()
        }
    }

    private fun startMainActivity(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    private fun setDataSourcePreference(localStorage: Boolean){
        val pref = applicationContext.getSharedPreferences("datasource",0)
        val editor: SharedPreferences.Editor = pref.edit()
        editor.apply {
            editor.putBoolean("local_storage", localStorage)
        }.apply()
    }
}