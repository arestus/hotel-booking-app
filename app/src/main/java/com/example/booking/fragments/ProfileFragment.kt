package com.example.booking.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.contains
import androidx.navigation.fragment.findNavController
import com.avatarfirst.avatargenlib.AvatarConstants
import com.avatarfirst.avatargenlib.AvatarGenerator
import com.example.booking.R

import com.example.booking.databinding.FragmentProfileBinding
import com.example.booking.viewmodels.ImageStorageManager
import com.squareup.picasso.Picasso
import java.io.File


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val CAMERA_PERMISSION_CODE = 1
        private const val CAMERA_REQUEST_CODE = 2
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        binding.backButtonProfile.setOnClickListener{
            findNavController().navigate(R.id.action_profileFragment_to_fragment_hotels_list)
        }
        binding.profileUserPhoto.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this.requireContext(), android.Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED){
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, CAMERA_REQUEST_CODE)
            } else {
                ActivityCompat.requestPermissions(
                    this.requireActivity(),
                    arrayOf(android.Manifest.permission.CAMERA),
                    CAMERA_PERMISSION_CODE
                )
            }
        }



//        if(){
//            Picasso.get()
//                .load("https://brokenfortest")
//                .resize(150, 150)
//                .placeholder(AvatarGenerator.avatarImage(this.requireContext(), 200, AvatarConstants.CIRCLE, binding.emailTextView.text.toString()))
//                .into(binding.profileUserPhoto)
//        } else {
//            val currentPhoto = ImageStorageManager.getImageFromInternalStorage(this.requireContext(), "profile")
//            binding.profileUserPhoto.setImageBitmap(currentPhoto)
//        }

        //val currentPhoto = null
//        if(currentPhoto != null){
//            binding.profileUserPhoto.setImageBitmap(currentPhoto)
//        } else {
//            Picasso.get()
//                .load("https://brokenfortest")
//                .resize(150, 150)
//                .placeholder(AvatarGenerator.avatarImage(this.requireContext(), 200, AvatarConstants.CIRCLE, binding.emailTextView.text.toString()))
//                .into(binding.profileUserPhoto)
//        }

        val yourFilePath = requireContext().filesDir.toString() + "/" + "profile"
        val yourFile = File(yourFilePath)

        if(!yourFile.isFile){
            Picasso.get()
                .load("https://brokenfortest")
                .resize(150, 150)
                .placeholder(AvatarGenerator.avatarImage(this.requireContext(), 200, AvatarConstants.CIRCLE, binding.emailTextView.text.toString()))
                .into(binding.profileUserPhoto)
        } else {
            val currentPhoto = ImageStorageManager.getImageFromInternalStorage(this.requireContext(), "profile")
            binding.profileUserPhoto.setImageBitmap(currentPhoto)
        }

//        binding.deleteAvatar.setOnClickListener {
//            ImageStorageManager.deleteImageFromInternalStorage(this.requireContext(), "profile")
//            Picasso.get()
//                .load("https://brokenfortest")
//                .resize(150, 150)
//                .placeholder(AvatarGenerator.avatarImage(this.requireContext(), 200, AvatarConstants.CIRCLE, binding.emailTextView.text.toString()))
//                .into(binding.profileUserPhoto)
//        }

        //return inflater.inflate(R.layout.fragment_profile, container, false)
        return binding.root
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == CAMERA_PERMISSION_CODE){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, CAMERA_REQUEST_CODE)
            } else {
                Toast.makeText(this.requireContext(), "Oops you just denied permission + " +
                        "for camera. Don't worry you can allow in in the settings", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            if(requestCode == CAMERA_REQUEST_CODE){
                val thumbNail: Bitmap = data!!.extras!!.get("data") as Bitmap
                ImageStorageManager.saveToInternalStorage(this.requireContext(), thumbNail, "profile")
                val savedPhoto = ImageStorageManager.getImageFromInternalStorage(this.requireContext(), "profile")
                binding.profileUserPhoto.setImageBitmap(savedPhoto)
            }
        }
    }

}