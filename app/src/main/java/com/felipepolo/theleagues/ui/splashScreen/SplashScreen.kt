package com.felipepolo.theleagues.ui.splashScreen

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.felipepolo.theleagues.R
import com.felipepolo.theleagues.databinding.FragmentSplashScreenBinding


class SplashScreen : Fragment() {

    private lateinit var binding: FragmentSplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashScreenBinding.bind(
            inflater.inflate(
                R.layout.fragment_splash_screen,
                container,
                false
            )
        )
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler().postDelayed({
            findNavController().navigate(R.id.action_splashScreen_to_homeFragment)
        }, 2000)
    }
}