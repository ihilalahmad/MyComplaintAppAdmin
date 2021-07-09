package com.example.mycomplaintadmin.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mycomplaintadmin.R
import com.example.mycomplaintadmin.databinding.FragmentHomeBinding
import com.example.mycomplaintadmin.utils.AppPreferences

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(
            inflater,
            container,
            false
        )

        val isUserLoggedIn = AppPreferences.getLoginStatus(requireContext())
        if (!isUserLoggedIn) {
            findNavController().navigate(R.id.navigate_home_to_login)
        }

        return binding.root
    }
}