package com.example.booking.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.booking.R
import com.example.booking.databinding.FragmentHotelsListBinding
import com.example.booking.viewmodels.HotelViewModel
import java.util.*
import kotlin.concurrent.schedule
import androidx.appcompat.app.AppCompatActivity





class HotelsListFragment : Fragment() {
    private lateinit var mUserViewModel: HotelViewModel
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<HotelListAdapter.MyViewHolder>? = null
//

    private var _binding: FragmentHotelsListBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHotelsListBinding.inflate(inflater, container, false)
        val adapter = HotelListAdapter(emptyList())
        val recyclerView = binding.hotelRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        mUserViewModel = ViewModelProvider(this)[HotelViewModel::class.java]
        mUserViewModel.readAllHotels.observe(viewLifecycleOwner, { user ->
            adapter.setData(user)
        })
        binding.backButton.setOnClickListener{
            findNavController().navigate(R.id.action_hotelsListFragment_to_loginFragment)
        }

        Timer("Test", false).schedule(2000) {
            requireActivity().runOnUiThread {

//                findNavController().navigate(R.id.action_hotelsListFragment_to_specificHotelFragment)
            }
        }
//        binding.floatingActionButton.setOnClickListener {
//
//            findNavController().navigate(R.id.action_listFragment_to_addFragment)
//
//        }

        setHasOptionsMenu(true)
        return binding.root
    }


}