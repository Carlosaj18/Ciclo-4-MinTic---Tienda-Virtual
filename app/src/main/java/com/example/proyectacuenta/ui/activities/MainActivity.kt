package com.example.proyectacuenta.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.proyectacuenta.R
import com.example.proyectacuenta.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Me sirve para hacer un debug a partir de la palabra ACTIVITY
        Log.d("ACTIVITY", "OnCreate called")
    }

    override fun onStart() {
        super.onStart()
        Log.d("ACTIVITY", "OnStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ACTIVITY", "OnResume called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("ACTIVITY", "OnRestart called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("ACTIVITY", "OnPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("ACTIVITY", "OnStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ACTIVITY", "OnDestroy called")
    }
}

 //   override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
 //       menuInflater.inflate(R.menu.menu_main, menu)
 //       return true
 //   }

 //   override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
 //       return when (item.itemId) {
 //           R.id.action_settings -> true
 //           else -> super.onOptionsItemSelected(item)
 //      }
 //   }

    /*override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }*/
