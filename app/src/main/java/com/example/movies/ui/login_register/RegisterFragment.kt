package com.example.movies.ui.login_register

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
import com.example.movies.data.models.User
import com.example.movies.utils.*
import kotlinx.android.synthetic.main.activity_login_register_navigation.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.register_email_txt
import kotlinx.android.synthetic.main.toolbar.view.*
import nouri.`in`.goodprefslib.GoodPrefs

class RegisterFragment : Fragment() {
    private lateinit var edtName: String
    private lateinit var edtLastName: String
    private lateinit var edtRegisterPassword: String
    private lateinit var edtRegisterEmail: String
    private lateinit var user: User
    private var genderText: String = ""
    private lateinit var getbackuser: User
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
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

           val pass= arguments?.getString(PASS)
       val email= arguments?.getString(MOVIE_EMAIL)

       register_pass_txt.setText(pass)
       register_email_txt.setText(email)

        requireActivity().login_registerPage_includeToolbar.toolbar1_back_img.setOnClickListener {
            requireActivity().onBackPressed()
        }
        requireActivity().login_registerPage_includeToolbar.toolbar1_tv.text = "Register"

        register_genderRadioGrp?.setOnCheckedChangeListener { group, checkedId ->

            genderText = if (R.id.register_radioBtn_male == checkedId) "Male" else "Female"
        }

        register_register_btn.setOnClickListener {
            //Initialize view
            edtName = register_name_txt.text.toString()
            edtLastName = register_lastName_txt.text.toString()
            edtRegisterPassword = register_pass_txt.text.toString()
            edtRegisterEmail = register_email_txt.text.toString()

            if (edtName.isNotEmpty()) {
                register_name_txt.error = null
            } else {
                register_name_txt.error = "Please enter your Name"
            }
            if (edtLastName.isNotEmpty()) {
                register_lastName_txt.error = null
            } else {
                register_lastName_txt.error = "Please enter your LastName"
            }
            if (edtRegisterPassword.length<6){
                register_pass_txt.error = "Please enter password more than 6 character"
            }else{
                register_pass_txt.error = null
            }
            if (edtRegisterEmail.isNotEmpty()&& Patterns.EMAIL_ADDRESS.matcher(edtRegisterEmail).matches()&&edtRegisterPassword.length>=6) {
                register_email_txt.error = null
            } else {
                register_email_txt.error = "Please enter your Phone or Email"
            }
            if (edtRegisterPassword.isNotEmpty()) {
                register_pass_txt.error = null
            } else {
                register_pass_txt.error = "Please enter your Password"
            }
            if (genderText.isNotEmpty()) {
            } else {
                Toast.makeText(requireContext(), "Please select Gender", Toast.LENGTH_SHORT).show()
            }
            if (edtName.isNotEmpty() && edtLastName.isNotEmpty() && edtRegisterEmail.isNotEmpty() &&
                edtRegisterPassword.isNotEmpty() && genderText.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(edtRegisterEmail).matches()) {
                user = User(
                    edtName,
                    edtLastName,
                    edtRegisterEmail,
                    edtRegisterPassword,
                    genderText
                )
                //Insert in room Db
                dataBase.userDao().insertUser(user)
                getbackuser =
                    dataBase.userDao().getUserInfo(user.numberOrEmail, user.password)

                //Insert in Prefs
                GoodPrefs.getInstance().saveBoolean(PREFS_IS_REGISTERED_OR_NOT, PREFS_IS_REGISTERED)
                if (PREFS_IS_REGISTERED && getbackuser != null) {
                    findNavController().navigate(R.id.action_registerFragment2_to_mainActivity)
                }
            }
        }
    }

}