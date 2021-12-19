package com.example.booking.fragments

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booking.BookingApp
import com.example.booking.HttpApiService
import com.example.booking.R
import com.example.booking.SessionManager
import com.example.booking.data.BookingDao
import com.example.booking.data.BookingDatabase
import com.example.booking.data.User
import com.example.booking.data.UserMin
import com.example.booking.databinding.FragmentLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.util.*
import kotlin.concurrent.schedule
import kotlin.coroutines.cancellation.CancellationException

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var session: SharedPreferences

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        val myApplication = activity?.application as BookingApp
        val httpApiService = myApplication.httpApiService

        session = SessionManager(requireActivity())

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        binding.textViewRegister.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            binding.progressBarBackground.visibility = View.VISIBLE
            Timer("Test", false).schedule(2000) {
                requireActivity().runOnUiThread {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.progressBarBackground.visibility = View.INVISIBLE

                    findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
                }
            }


        }
        binding.textViewLogin.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            binding.progressBarBackground.visibility = View.VISIBLE

            val userEmail = binding.outlinedTextFieldEmail.editText?.text.toString()
            val userPassword = binding.outlinedTextFieldPassword.editText?.text.toString()

            val maskedEmail =
                if (userEmail.isNullOrEmpty()) ""
                else userEmail//.lowercase()

            val maskedPassword =
                if (userPassword.isNullOrEmpty()) ""
                else userPassword

            CoroutineScope(Dispatchers.IO).launch {

                val user: UserMin = UserMin(maskedEmail, maskedPassword)
                var loggedUser: User = User()
                var failedLogin: Int = 200

                try {
                    loggedUser = httpApiService.login(user)
                }
                catch (e: CancellationException) {
                    println(" +++++++++++++++ Rethrowing HttpException 1 : $")
                    failedLogin = 401
                    // cancellation exception is rethrown, yet the original IOException gets to the handler
                }
                catch (e: HttpException) {
                    println(" +++++++++++++++ Rethrowing HttpException 2 : ${e.code()}")
                    failedLogin = e.code()
                    // cancellation exception is rethrown, yet the original IOException gets to the handler
                }  finally {

                    withContext(Dispatchers.Main){

                        Timer("Test", false).schedule(2000) {
                            requireActivity().runOnUiThread {

                                if (failedLogin == 200)
                                {
                                    binding.progressBar.visibility = View.INVISIBLE
                                    binding.progressBarBackground.visibility = View.INVISIBLE
                                    (session as SessionManager).createLoginSession(loggedUser.token, loggedUser.email, loggedUser.memberSince)
                                    CoroutineScope(Dispatchers.IO).launch {
                                        fillLocalDB(httpApiService)
                                        withContext(Dispatchers.Main){
                                            Log.d("BookingDBString", "DB Filled 1")
                                        }
                                    }

                                    findNavController().navigate(R.id.action_loginFragment_to_hotelsListFragment)
                                }
                                else {

                                    Toast.makeText(myApplication, "Oops! Sorry, wrong username or password", 300).show()
                                }
                            }
                        }

                    }
                    println("Child is cancelled")
                }
            }


        }

        return binding.root
    }

    fun fillLocalDB(httpApiService: HttpApiService) {

        val dao = BookingDatabase.getInstance(requireActivity()).bookingDao()

        CoroutineScope(Dispatchers.IO).launch {

            val hotels = httpApiService.getHotels().hotels?.toTypedArray() ?: emptyArray()
            val reservations = httpApiService.getReservations().reservations?.toTypedArray() ?: emptyArray()
            val localUsers = httpApiService.getLocalUsers().users?.toTypedArray() ?: emptyArray()
            val loginHistory = httpApiService.getLoginHistory().loginEntries?.toTypedArray() ?: emptyArray()
            if (!hotels.isNullOrEmpty())
                dao.insertAllHotels(*hotels)
            if (!reservations.isNullOrEmpty() && !hotels.isNullOrEmpty()){
                dao.delReservation()
                dao.insertAllReservations(*reservations)
            }
            if (!localUsers.isNullOrEmpty())
                dao.insertAllLocalUsers(*localUsers)
            if (!loginHistory.isNullOrEmpty())
                dao.insertAllLoginHistory(*loginHistory)

            withContext(Dispatchers.Main){
                Log.d("BookingDBString", "DB Filled")
            }
        }

    }
}