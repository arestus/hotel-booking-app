package com.example.booking.fragments.list.loginHistory

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booking.R
import com.example.booking.SessionManager
import com.example.booking.databinding.FragmentLoginHistoryBinding
import com.example.booking.viewmodels.LoginHistoryViewModel
import java.text.SimpleDateFormat
import java.util.*


class LoginHistoryFragment : Fragment() {
    private lateinit var mUserViewModel: LoginHistoryViewModel
    private var _binding: FragmentLoginHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var session: SharedPreferences



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        session = SessionManager(requireActivity())

        _binding = FragmentLoginHistoryBinding.inflate(inflater, container, false)
        var adapter = LoginHistoryListAdapter(emptyList())
        val recyclerView = binding.rvRecycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mUserViewModel = ViewModelProvider(this)[LoginHistoryViewModel::class.java]

        mUserViewModel.historyList.observe(viewLifecycleOwner, { user ->
            adapter.setData(user)
        })
        binding.loginHistoryBack.setOnClickListener{
            findNavController().navigate(R.id.action_loginHistoryFragment_to_profileFragment)
        }
        binding.loginHistoryTitle.text = "Login history"
        var memberFrom = getDateTime((session as SessionManager).getTime())
        binding.title.text = "Member since: + $memberFrom "
        setHasOptionsMenu(true)
        return binding.root
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


}

