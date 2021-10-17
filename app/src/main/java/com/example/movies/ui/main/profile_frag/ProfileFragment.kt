package com.example.movies.ui.main.profile_frag

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.movies.R
import com.example.movies.data.dataBase.UserDataBase
import com.example.movies.data.models.User
import com.example.movies.utils.DATABASE_USER_NAME
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.toolbar.view.*

class ProfileFragment : Fragment() {
    private lateinit var edtProfileName: String
    private lateinit var edtProfileLastName: String
    private lateinit var edtProfileRegisterPassword: String
    private lateinit var edtProfileEmail: String
    private lateinit var userProfile: User
    private lateinit var previuosUser: User
    private var genderProfileText: String = ""

    private val userList: MutableList<User> = mutableListOf()

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
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().navigationPage_includeToolbar.visibility = View.VISIBLE
        requireActivity().navigationPage_includeToolbar.toolbar1_tv.text = "Profile"
        requireActivity().navigationPage_includeToolbar.toolbar1_back_img.visibility = View.GONE
        requireActivity().navigationPage_includeToolbar.toolbar1_favorite_tv.visibility =
            View.VISIBLE
        requireActivity().navigationPage_includeToolbar.toolbar1_favorite_tv.setOnClickListener {
            this.findNavController().navigate(R.id.action_ProfileFragment_to_favoriteFragment)
        }

        if (dataBase.userDao().getAllPeople().isNotEmpty()) {
            userList.addAll(dataBase.userDao().getAllPeople())
            previuosUser = userList[0]
            profile_name_txt.setText(previuosUser.name)
            profile_lastName_txt.setText(previuosUser.lastName)
            profile_pass_txt.setText(previuosUser.password)
            profile_phoneOrEmail_txt.setText(previuosUser.numberOrEmail)
            genderProfileText = previuosUser.gender
            if (genderProfileText == "Female") {
                profile_radioBtn_Female.isChecked = true
            } else {
                profile_radioBtn_male.isChecked = true
                genderProfileText = "Male"
            }
        } else {
            Toast.makeText(requireContext(), "user not available", Toast.LENGTH_SHORT).show()

        }

        profile_genderRadioGrp?.setOnCheckedChangeListener { group, checkedId ->


            genderProfileText = if (R.id.profile_radioBtn_male == checkedId) {
                "Male"
            } else {
                "Female"
            }
        }
        profileFab.setOnClickListener {
            //Initialize view
            edtProfileName = profile_name_txt.text.toString()
            edtProfileLastName = profile_lastName_txt.text.toString()
            edtProfileRegisterPassword = profile_pass_txt.text.toString()
            edtProfileEmail = profile_phoneOrEmail_txt.text.toString()

            if (edtProfileName.isNotEmpty()) {
                profile_name_txt.error = null
            } else {
                profile_name_txt.error = "Please enter your Name"
            }

            if (edtProfileLastName.isNotEmpty()) {
                profile_lastName_txt.error = null
            } else {
                profile_lastName_txt.error = "Please enter your LastName"
            }

            if (edtProfileEmail.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(edtProfileEmail).matches()) {
                profile_phoneOrEmail_txt.error = null
            } else {
                profile_phoneOrEmail_txt.error = "Please enter your Phone or Email"
            }

            if (edtProfileRegisterPassword.isNotEmpty()) {
                profile_pass_txt.error = null
            } else {
                profile_pass_txt.error = "Please enter your Password"
            }

            if (edtProfileName.isNotEmpty() && edtProfileLastName.isNotEmpty() && edtProfileEmail.isNotEmpty()
                && Patterns.EMAIL_ADDRESS.matcher(edtProfileEmail).matches() && edtProfileRegisterPassword.isNotEmpty()) {
                userProfile = User(
                    edtProfileName,
                    edtProfileLastName,
                    edtProfileEmail,
                    edtProfileRegisterPassword,
                    genderProfileText
                )
                dataBase.userDao().updateUser(userProfile)
                val saveBtnColor = ContextCompat.getColor(requireContext(), R.color.teal_200)

                profileFab.backgroundTintList = ColorStateList.valueOf(saveBtnColor)
            } else {
                Toast.makeText(requireContext(), "user not saved", Toast.LENGTH_SHORT).show()
            }
        }
    }
}