package com.example.movies.ui.login_register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.movies.R
import kotlinx.android.synthetic.main.activity_login_register_navigation.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.view.*

class LoginRegisterNavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register_navigation)
       // setUpNavigation()
    }
    private fun setUpNavigation() {
        val navController = Navigation.findNavController(this, R.id.navigationPage_container)
           NavigationUI.setupWithNavController(toolbar1, navController)
    }
    override fun onSupportNavigateUp() =
        Navigation.findNavController(this, R.id.navigationPage_container).navigateUp()


}