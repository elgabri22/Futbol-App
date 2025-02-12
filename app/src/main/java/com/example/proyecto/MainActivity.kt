package com.example.proyecto

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.proyecto.databinding.ActivityMainBinding
import com.example.proyecto.ui.login.LoginFragment
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar el ActionBar
        setSupportActionBar(binding.appBarMain.toolbar) // Asegúrate de que tengas un toolbar en tu layout

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.main_menu, R.id.partidos_disponibles
            ), drawerLayout
        )

        // Configurar la barra de acción con el NavController
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Configurar el listener del menú de navegación
        navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.logout -> {
                    borrarSesion() // Llamar a la función para cerrar sesión
                    true
                }
                else -> {
                    drawerLayout.closeDrawer(GravityCompat.START)
                    navController.navigate(item.itemId)
                    true
                }
            }
        }
    }


    private fun borrarSesion() {
        // Cerrar sesión en Firebase Auth
        FirebaseAuth.getInstance().signOut()

        // Verificar si la sesión se cerró correctamente
        if (FirebaseAuth.getInstance().currentUser == null) {
            Toast.makeText(this, "Sesión cerrada correctamente", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Error al cerrar sesión", Toast.LENGTH_SHORT).show()
        }

        // Borrar las preferencias compartidas
        borrarSesionSharedPreferences()

        // Navegar al LoginFragment
        navController.navigate(R.id.loginFragment) // Asegúrate de que este sea el ID correcto de tu LoginFragment

        // Cerrar el Drawer (opcional)
        binding.drawerLayout.closeDrawer(GravityCompat.START)
    }

    private fun borrarSesionSharedPreferences() {
        val sharedPreferences = getSharedPreferences(LoginFragment.Global.preferencias_compartidas, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear() // Borrar todos los datos de SharedPreferences
        editor.apply() // Guardar los cambios
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflar el menú; esto agrega elementos a la barra de acción si está presente
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
