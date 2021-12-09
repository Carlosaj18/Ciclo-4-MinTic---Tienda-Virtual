package com.example.proyectacuenta.ui.activities

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter

import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.proyectacuenta.R
import com.example.proyectacuenta.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity(){

    /* SE USA PARA EL APPBAR */
    // Binding para poder acceder por id en el layout
    private lateinit var binding: ActivityHomeBinding

    // Se crea un atributo del objeto appBarConfiguration
    private lateinit var appBarConfiguration: AppBarConfiguration

    // El metodo onCreate crea la vista
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        // Se setea la nueva vista
        setContentView(binding.root)
        // Como en el manifest le indique que no action bar aqui se la tengo que setear con el toolbar y su ID (toolbar)
        setSupportActionBar(binding.toolbar)
        // Busco el navegation controler por ID
        val navController = findNavController(R.id.nav_host_fragment_content_home_stores)
        // Le indico cual va a ser su graph
        appBarConfiguration =
            AppBarConfiguration(navController.graph) //setOf(R.id.homeFragment, R.id.productFragment, R.id.commentFragment, R.id.profileFragment)
        // Seteo mi actionbar con navegationController
        setupActionBarWithNavController(navController, appBarConfiguration)
        // Le indico a mi botton navegation cual sera su controller
        binding.bottonNavigation.setupWithNavController(navController)
    }

        // Sirve para navegar con la flecha hacia atras ya que todos los fragmentos estan en el mismo nivel
        override fun onSupportNavigateUp(): Boolean {
            val navController = findNavController(R.id.nav_host_fragment_content_home_stores)
            return navController.navigateUp(appBarConfiguration)
                    || super.onSupportNavigateUp()
        }

    //
    /*val arrayList_departments = arrayListOf<String>("Santander","Cundinamarca","Antioquia")
    val arrayAdapter_departments= ArrayAdapter(this, R.layout.fragment_home_store, arrayList_departments,
    )

    binding.spinnerDepartments.adapter=arrayAdapter_departments
    //spinnerDepartments.adapter=arrayAdapter_departments

    val arrayList_Santander = arrayListOf<String>("Bucaramanga","Floridablanca","Giron")
    val arrayList_Cundinamarca = arrayListOf<String>("Bogota","Soacha","Villeta")
    val arrayList_Antioquia = arrayListOf<String>("Medellin","Bello","Rionegro")
    val arrayAdapter_cities=ArrayAdapter(applicationContext,R.layout.fragment_home_store,
        arrayList_departments)
    //binding.spinnerCities.adapter=arrayAdapter_cities
    spinnerCities.adapter=arrayAdapter_cities

    //binding.spinnerDepartments.onItemSelectedListener = object : AdapterView.OnItemSelectedListener
    spinnerDepartments.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            if(position==0)
            {
                val arrayAdapter_cities = ArrayAdapter(applicationContext, R.layout.fragment_home_store,arrayList_Santander)
                //binding.spinnerCities.adapter=arrayAdapter_cities
                spinnerCities.adapter=arrayAdapter_cities
            }

            if(position==1)
            {
                val arrayAdapter_cities = ArrayAdapter(applicationContext, R.layout.fragment_home_store,arrayList_Cundinamarca)
                //binding.spinnerCities.adapter=arrayAdapter_cities
                spinnerCities.adapter=arrayAdapter_cities
            }

            if(position==2)
            {
                val arrayAdapter_cities = ArrayAdapter(applicationContext, R.layout.fragment_home_store,arrayList_Antioquia)
                //binding.spinnerCities.adapter=arrayAdapter_cities
                spinnerCities.adapter=arrayAdapter_cities
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
        }
    }*/

    }

    /* DRAWER

    private lateinit var drawer: DrawerLayout

    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer_home)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar_home)
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
            R.id.homeFragment -> Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
            R.id.commentFragment -> Toast.makeText(this, "Comment", Toast.LENGTH_SHORT).show()
            R.id.profileFragment -> Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
            R.id.productFragment -> Toast.makeText(this, "Product", Toast.LENGTH_SHORT).show()

        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){
            return true

    }

        return super.onOptionsItemSelected(item)

    }*/
