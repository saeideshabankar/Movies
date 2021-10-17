package com.example.movies.ui.login_register

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.movies.R
import com.example.movies.data.dataBase.UserDataBase
import com.example.movies.utils.DATABASE_USER_NAME
import com.example.movies.utils.MOVIE_EMAIL
import com.example.movies.utils.MOVIE_ID
import com.example.movies.utils.PASS
import kotlinx.android.synthetic.main.activity_login_register_navigation.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.toolbar.view.*


class LoginFragment : Fragment() {

    private lateinit var edtLoginPassword: String
    private lateinit var edtLoginEmail: String

    //Room Db
    private val dataBase: UserDataBase by lazy {
        Room.databaseBuilder(requireContext(), UserDataBase::class.java, DATABASE_USER_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //check in room Db if user is exist u have login else user is not register have to go register fragment
        requireActivity().login_registerPage_includeToolbar.toolbar1_back_img.visibility = View.GONE
        requireActivity().login_registerPage_includeToolbar.toolbar1_img_menu.visibility = View.GONE
        requireActivity().login_registerPage_includeToolbar.toolbar1_tv.text = "Sign in"

        login_login_btn.setOnClickListener {
            edtLoginPassword = login_pass_txt.text.toString()
            edtLoginEmail = login_email_txt.text.toString()

            if (edtLoginEmail.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(edtLoginEmail).matches()) {
                login_email_txt.error = null
            } else {
                login_email_txt.error = "Please enter your  correct Email"
            }
            if (edtLoginPassword.isNotEmpty()) {
                login_pass_txt.error = null
            } else {
                login_pass_txt.error = "Please enter your Password"
            }
            if (edtLoginPassword.length<6){
                login_pass_txt.error = "Please enter password more than 6 character"
            }else{
                login_pass_txt.error = null
            }
            if (edtLoginEmail.isNotEmpty() && edtLoginPassword.isNotEmpty()&& Patterns.EMAIL_ADDRESS.matcher(edtLoginEmail).matches() &&edtLoginPassword.length>=6) {
                login_email_txt.error = null
                login_pass_txt.error = null


                var getBackUser =
                    dataBase.userDao().getUserInfo(edtLoginEmail, edtLoginPassword)

                if (getBackUser==null){
                    val bundle = Bundle()
                    val pass = edtLoginPassword
                    val email=edtLoginEmail
                    bundle.putString(PASS, pass)
                    bundle.putString(MOVIE_EMAIL, email)

                    findNavController().navigate(R.id.action_loginFragment2_to_registerFragment2,bundle)
                }else{

                    findNavController().navigate(R.id.action_loginFragment2_to_mainActivity)

                }
            }
        }

    }
}