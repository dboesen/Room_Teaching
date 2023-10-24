package com.example.room_teaching.ui.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.room_teaching.R
import com.example.room_teaching.database.User
import com.example.room_teaching.database.UserViewModel
import com.example.room_teaching.databinding.FragmentUpdateBinding


class UpdateFragment : Fragment() {

    private lateinit var UpdateFragmentBinding: FragmentUpdateBinding
    private lateinit var ufUpdateViewModel: UserViewModel
    private val args by navArgs<UpdateFragmentArgs>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        UpdateFragmentBinding =  FragmentUpdateBinding.inflate(inflater, container, false)

        val view = UpdateFragmentBinding.root

        ufUpdateViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        UpdateFragmentBinding.upFirstName.setText(args.currentUser.firstName)
        UpdateFragmentBinding.upLastName.setText(args.currentUser.lastName)
        UpdateFragmentBinding.upFavColor.setText(args.currentUser.favColor)
        UpdateFragmentBinding.upHeightIn.setText(args.currentUser.heightIn.toString())

        UpdateFragmentBinding.btnUpdate.setOnClickListener{
            updateTheData()
        }

        //Add Menu
        setHasOptionsMenu(true)

        return view
    }

    private fun updateTheData() {
        val firstName = UpdateFragmentBinding.upFirstName.text.toString()
        val lastName = UpdateFragmentBinding.upLastName.text.toString()
        val favColor = UpdateFragmentBinding.upFavColor.text.toString()
        val heightIn = UpdateFragmentBinding.upHeightIn.text.toString().toInt()


        if (!(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(favColor) && TextUtils.isEmpty(heightIn.toString()))){
            //create the user object
            val user = User(args.currentUser.ID,firstName, lastName, favColor, heightIn)
            // now we update it
            ufUpdateViewModel.updateUser(user)
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        } else {
            Toast.makeText(requireContext(),"Fill out all fields", Toast.LENGTH_LONG).show()
            // validate messages

        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            val msgbox = AlertDialog.Builder(requireContext())
            msgbox.setPositiveButton("Yes"){ _, _ ->
                ufUpdateViewModel.deleteUser(args.currentUser)
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            }
            msgbox.setNegativeButton("No") {_, _ ->

            }
            msgbox.setTitle("Delete ${args.currentUser.firstName}? ")
            msgbox.setMessage("are you sure?")
            msgbox.create().show()

        }
        return super.onOptionsItemSelected(item)
    }
}