package com.example.movies.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.movies.R
import com.example.movies.ui.login_register.LoginRegisterNavigationActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpNavigation()

//          setSupportActionBar(toolbar1)
        toolbar1_img_menu.setOnClickListener {
            val popup = PopupMenu(this, it)
            val inflater: MenuInflater = popup.menuInflater
            inflater.inflate(R.menu.toolbar_menu, popup.menu)
            popup.setOnMenuItemClickListener { menuItem ->
                when(menuItem.itemId){
                    R.id.action_theme -> {
                        Toast.makeText(this, "day", Toast.LENGTH_SHORT).show()
                    }
//                    R.id.action_sign_in-> {
//                        Intent(this, LoginRegisterNavigationActivity::class.java).apply {
//                            startActivity(this)
//                            finish()
//                        }
//                    }
                }
                true
            }
            popup.show()
        }
    }

    private fun setUpNavigation() {
        val navController = Navigation.findNavController(this, R.id.mainPage_container)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }
       /* override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_theme -> {
                Toast.makeText(this, "click on setting", Toast.LENGTH_LONG).show()

            }
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }
*/
    override fun onSupportNavigateUp() =
        Navigation.findNavController(this, R.id.mainPage_container).navigateUp()

}