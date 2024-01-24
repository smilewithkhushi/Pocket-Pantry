package com.example.pocketpantry

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.pocketpantry.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {
    private lateinit var binding : FragmentSignInBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentSignInBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        setStatusBarColor()
        getUserNumber()

        onContinueBtnClick()
        return binding.root
    }

    private fun onContinueBtnClick(){
        binding.btnContinue.setOnClickListener{
            val number = binding.getUserNumber.text.toString()
            if (number.isEmpty() || number.length !=10){
                Toast.makeText(this, "Enter a Valid Phone Number!", Toast.LENGTH_SHORT).show()
            }
            else{
                val bundle = Bundle()
                bundle.putString("number", number)
                findNavController().navigate(R.id.action_signInFragment_to_OTPFragment, bundle)
            }
        }
    }

    //function to set the color to yellow of the topmost status bar (where the time and battery percentage is shown)
    private fun setStatusBarColor(){
        activity?.window?.apply{
            var statusBarColors = ContextCompat.getColor(requireContext(), R.color.yellow300)
            statusBarColor = statusBarColors
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
    }

    private fun getUserNumber(){

        binding.getUserNumber.addTextChangedListener{

            object : TextWatcher{

                override fun beforeTextChanged(
                    p0: CharSequence?,
                    p1: Int,
                    p2: Int,
                    p3: Int,
                ) {

                }

                override fun onTextChanged(number: CharSequence?,
                                           p1: Int,
                                           p2: Int,
                                           p3: Int,) {
                val len = number ?.length

                    if (len==10){
                        binding.btnContinue.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green))
                    }else{
                        binding.btnContinue.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.yellow300))
                    }

                }

                override fun afterTextChanged(number: Editable?) {

                }


            }
        }
    }
}