package com.example.mobirollertask.ui.splash

import android.animation.Animator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mobirollertask.R
import com.example.mobirollertask.databinding.FragmentSplashBinding

class SplashFragment:Fragment() {
    private  var _binding: FragmentSplashBinding? = null
    private  val binding get() = _binding !!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animatorListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun animatorListener() {

        //Animation Listener
        binding.lottieAnimationView.addAnimatorListener(object: Animator.AnimatorListener{
            override fun onAnimationStart(animation: Animator?) {
                Log.v("Animation","Started")
            }

            override fun onAnimationEnd(animation: Animator?) {
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            }
            override fun onAnimationCancel(animation: Animator?) {
                Log.v("Animation","Canceled")
            }

            override fun onAnimationRepeat(animation: Animator?) {
                Log.v("Animation","Repeated")
            }
        })
    }

}