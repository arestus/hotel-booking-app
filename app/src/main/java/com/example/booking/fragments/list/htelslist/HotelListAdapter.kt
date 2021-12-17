package com.example.booking.fragments.list.htelslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.booking.data.Hotel
import com.example.booking.databinding.HotelRowCustomBinding
import com.squareup.picasso.Picasso

class HotelListAdapter(private var hotelList: List<Hotel>) :
    RecyclerView.Adapter<HotelListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = HotelRowCustomBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount() = hotelList.size


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = hotelList[position]
        with(holder) {
            with(currentItem) {
                val formattedPricePerNight = "Rooms left:<b> $roomsLeft <b>"
                binding.hotelName.text = name
                Picasso.get().load(url).into( binding.hotelPhoto)
                binding.pricePerNight.text = "$pricePerNight $ per night"
                binding.roomsLeft.text = HtmlCompat.fromHtml("Rooms left:<b> $roomsLeft <b>", HtmlCompat.FROM_HTML_MODE_LEGACY)

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
