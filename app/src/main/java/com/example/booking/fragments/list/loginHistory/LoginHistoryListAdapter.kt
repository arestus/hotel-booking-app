package com.example.booking.fragments.list.loginHistory

import android.annotation.SuppressLint

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.booking.data.LoginHistory
import com.example.booking.databinding.HistoryItemBinding
import java.text.SimpleDateFormat
import java.util.*

class LoginHistoryListAdapter (private var historyList: List<LoginHistory>) :
    RecyclerView.Adapter<LoginHistoryListAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoginHistoryListAdapter.MyViewHolder {
        val binding = HistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoginHistoryListAdapter.MyViewHolder, position: Int) {

        val currentItem = historyList[position +1]
        with(holder) {
            with(currentItem) {
                var time = getDateTime(loginTimestamp)
                binding.history.text ="Logged in: $time"


            }
        }
    }

    override fun getItemCount() = historyList.size



    @SuppressLint("NotifyDataSetChanged")
    fun setData(user: List<LoginHistory>) {
        this.historyList = user
        notifyDataSetChanged()
    }

    private fun getDateTime(s: String): String? {
        try {
            val sdf = SimpleDateFormat("dd MMMM yyyy")
            val netDate = Date(s.toLong())
            return sdf.format(netDate)
        } catch (e: Exception) {
            return e.toString()
        }
    }

    inner class MyViewHolder(val binding: HistoryItemBinding) : RecyclerView.ViewHolder(binding.root)

}





