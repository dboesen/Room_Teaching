package com.example.room_teaching.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room_teaching.R
import com.example.room_teaching.database.UserViewModel
import com.example.room_teaching.databinding.FragmentListBinding
import com.example.room_teaching.model.ListAdapter

class ListFragment : Fragment() {


    private lateinit var  ListFragBinding: FragmentListBinding
    private lateinit var mUserViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ListFragBinding = FragmentListBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        val view = ListFragBinding.root

        ListFragBinding.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_userFragment)
        }

        //recyclerview
        val adapter = ListAdapter()
        val rv1 = ListFragBinding.rv1
        rv1.adapter = adapter
        rv1.layoutManager = LinearLayoutManager(requireContext())

        //viewmodel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner,{
            user ->
            adapter.setData(user)
        })

        return view
    }



}