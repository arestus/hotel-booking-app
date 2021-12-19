package com.example.booking.fragments.list.reservedlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import java.util.*
import kotlin.concurrent.schedule
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.booking.R
import com.example.booking.databinding.FragmentMyResrvationBinding
import com.example.booking.viewmodels.ReservationViewModel


class MyReservationFragment : Fragment() {
    private lateinit var mUserViewModel: ReservationViewModel
    private var _binding: FragmentMyResrvationBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyResrvationBinding.inflate(inflater, container, false)
        val adapter = MyReservationListAdapter(emptyList())
        val recyclerView = binding.reservationRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        mUserViewModel = ViewModelProvider(this)[ReservationViewModel::class.java]
        mUserViewModel.readAllReservation.observe(viewLifecycleOwner, { user ->
            adapter.setData(user)
        })
        binding.backButton.setOnClickListener{
            findNavController().navigate(R.id.action_myReservationFragment_to_hotelsListFragment)
        }
        binding.reservationFragmentTitle.text = "Your Reservation"
        Timer("Test", false).schedule(2000) {
            requireActivity().runOnUiThread {

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