package com.example.pocketpantry

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.pocketpantry.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {
   private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(layoutInflater)

        //calling the function for changed status bar color
        setStatusBarColor()

        // The sign-in fragment will be displayed after 3 seconds of loading
       Handler(Looper.getMainLooper()).postDelayed({
           findNavController().navigate(R.id.action_splashFragment_to_signInFragment)
       }, 3000)

        return binding.root
    }

    //function to set the color to yellow of the topmost status bar
    // status bar is the place where the time and battery percentage is shown)
    private fun setStatusBarColor(){
        activity?.window?.apply{
            var statusBarColors = ContextCompat.getColor(requireContext(), R.color.yellow300)
            statusBarColor = statusBarColors
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
    }

}