package com.example.room_teaching.ui.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.room_teaching.R
import com.example.room_teaching.database.User
import com.example.room_teaching.database.UserViewModel
import com.example.room_teaching.databinding.FragmentUserBinding


class UserFragment : Fragment() {

    private lateinit var UserFragmentBinding: FragmentUserBinding
    private lateinit var ufUserViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        UserFragmentBinding =  FragmentUserBinding.inflate(inflater, container, false)

        val view = UserFragmentBinding.root

        ufUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


        UserFragmentBinding.btnAdd.setOnClickListener{
                insertTheData()
        }


        return view
    }

    private fun insertTheData() {
        val firstName = UserFragmentBinding.etFirstName.text.toString()
        val lastName = UserFragmentBinding.etLastName.text.toString()
        val favColor = UserFragmentBinding.etFavColor.text.toString()
        val heightIn = UserFragmentBinding.etHeightIn.text.toString().toInt()

        if (!(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(favColor) && TextUtils.isEmpty(heightIn.toString()))){
            //create the user object
            val user = User(0,firstName, lastName, favColor, heightIn)
            // now we add it
            ufUserViewModel.addUser(user)
            findNavController().navigate(R.id.action_userFragment_to_listFragment)

        } else {
            Toast.makeText(requireContext(),"Fill out all fields",Toast.LENGTH_LONG).show()
            // validate messages

        }

    }

}