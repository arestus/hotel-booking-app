package com.example.booking.fragments.otherUser
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.booking.data.LocalUser
import com.example.booking.databinding.OtherUserRowBinding


class OtherUsersListAdapter(private var userList: List<LocalUser>) :
    RecyclerView.Adapter<OtherUsersListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OtherUsersListAdapter.MyViewHolder {
        val binding = OtherUserRowBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount() = userList.size


    override fun onBindViewHolder(holder: OtherUsersListAdapter.MyViewHolder, position: Int) {
        val currentItem = userList[position]
        with(holder) {
            with(currentItem) {
                binding.email.text = email
                binding.reservation.text = reservationsAt
                binding.img.text = email.uppercase().take(1)

            }

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(user: List<LocalUser>) {
        this.userList = user
        notifyDataSetChanged()
    }
    inner class MyViewHolder(val binding: OtherUserRowBinding) : RecyclerView.ViewHolder(binding.root)
}