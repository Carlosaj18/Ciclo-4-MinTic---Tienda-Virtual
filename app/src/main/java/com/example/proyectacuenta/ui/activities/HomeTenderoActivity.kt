package com.example.proyectacuenta.ui.activities

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.proyectacuenta.R
import com.example.proyectacuenta.databinding.ActivityHomeTenderoBinding
import com.google.android.material.navigation.NavigationView

class HomeTenderoActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityHomeTenderoBinding
    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeTenderoBinding.inflate(layoutInflater)
        // Se setea la nueva vista
        setContentView(binding.root)

        //setSupportActionBar(binding.toolbar_home_tendero)
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar_home_tendero)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)

        toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.drawer_navigation)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                // Aqui se puede hacer la logica para redireccionar
                R.id.homeFragment -> Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                R.id.commentFragment -> Toast.makeText(this, "Comments", Toast.LENGTH_SHORT).show()
                R.id.profileFragment -> Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                R.id.productFragment -> Toast.makeText(this, "Products", Toast.LENGTH_SHORT).show()
            }

            drawer.closeDrawer(GravityCompat.START)
            return true
        }

    override fun onPostCreate(savedInstanceState: Bundle?){
            super.onPostCreate(savedInstanceState)
            toggle.syncState()
        }

    override fun onConfigurationChanged(newConfig: Configuration){
            super.onConfigurationChanged(newConfig)
            toggle.onConfigurationChanged(newConfig)
        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
            if(toggle.onOptionsItemSelected(item)){
                return true
            }
            return super.onOptionsItemSelected(item)
        }
}


/*
// Como en el manifest le indique que no action bar aqui se la tengo que setear con el toolbar y su ID (toolbar)
// setSupportActionBar(binding.na)
// Busco el navegation controler por ID
val navController = findNavController(R.id.nav_host_fragment_content_home_stores)
// Le indico cual va a ser su graph
appBarConfiguration =
    AppBarConfiguration(navController.graph) //setOf(R.id.homeFragment, R.id.productFragment, R.id.commentFragment, R.id.profileFragment)
// Seteo mi actionbar con navegationController

setupActionBarWithNavController(navController, appBarConfiguration)
// Le indico a mi botton navegation cual sera su controller
binding.drawerNavigation.setupWithNavController(navController)

 // Sirve para navegar con la flecha hacia atras ya que todos los fragmentos estan en el mismo nivel
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home_stores)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
*/