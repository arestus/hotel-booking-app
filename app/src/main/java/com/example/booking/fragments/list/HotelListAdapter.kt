package com.example.booking.fragments.list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.booking.data.Hotel
import com.example.booking.databinding.HotelRowCustomBinding
import com.squareup.picasso.Picasso

class HotelListAdapter(private var hotelList: List<Hotel>) :
    RecyclerView.Adapter<HotelListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelListAdapter.MyViewHolder {
        val binding = HotelRowCustomBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount() = hotelList.size


    override fun onBindViewHolder(holder: HotelListAdapter.MyViewHolder, position: Int) {
        val currentItem = hotelList[position]
        with(holder) {
            with(currentItem) {
                Log.d("tre", "test")
                binding.hotelName.text = name
                Picasso.get().load(url).into( binding.hotelPhoto)
                binding.pricePerNight.text = pricePerNight.toString()
                binding.roomsLeft.text = roomsLeft.toString()

                binding.hotelRowCustom.setOnClickListener { view ->
                    val action = HotelsListFragmentDirections.actionHotelsListFragmentToSpecificHotelFragment(currentItem)
                    Navigation.findNavController(view).navigate(action)
                }
            }

        }
    }

    fun setData(user: List<Hotel>) {
        this.hotelList = user
        notifyDataSetChanged()
    }
    inner class MyViewHolder(val binding: HotelRowCustomBinding) : RecyclerView.ViewHolder(binding.root)
}
//
//override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.MyViewHolder {
//    val binding = RowCustomBinding
//        .inflate(LayoutInflater.from(parent.context), parent, false)
//    return MyViewHolder(binding)
//}
//
//override fun getItemCount() = userList.size
//
//
//override fun onBindViewHolder(holder: ListAdapter.MyViewHolder, position: Int) {
//    val currentItem = userList[position]
//    with(holder) {
//        with(currentItem) {
//            binding.textViewId.text = id.toString()
//            binding.textViewFirstName.text = firstName
//            binding.textViewLastName.text = lastName
//            binding.textViewAge.text = age.toString()
//
//            binding.rowLayout.setOnClickListener { view ->
//                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
//                Navigation.findNavController(view).navigate(action)
//            }
//        }
//
//    }
//}
//
//fun setData(user: List<User>) {
//    this.userList = user
//    notifyDataSetChanged()
//}
//inner class MyViewHolder(val binding: RowCustomBinding) : RecyclerView.ViewHolder(binding.root)
