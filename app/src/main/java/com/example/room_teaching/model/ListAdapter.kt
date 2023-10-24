package com.example.room_teaching.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.room_teaching.database.User
import com.example.room_teaching.databinding.RvRowBinding
import com.example.room_teaching.ui.view.ListFragment
import com.example.room_teaching.ui.view.ListFragmentDirections

class ListAdapter: RecyclerView.Adapter<ListAdapter.ListViewholder>() {

    private var userList = emptyList<User>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewholder {
        val itemBinding = RvRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ListViewholder(itemBinding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ListViewholder, position: Int) {
        val OurItem = userList[position]
        holder.bind(OurItem)

        holder.itemBinding.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(OurItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    class ListViewholder(val itemBinding: RvRowBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(ourItem: User) {
            itemBinding.txtFirstName.text = ourItem.firstName
            itemBinding.txtLastName.text = ourItem.lastName
            itemBinding.txtColor.text = ourItem.favColor
            itemBinding.heightIn.text = ourItem.heightIn.toString()
        }
    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}