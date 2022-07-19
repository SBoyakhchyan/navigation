package com.example.navigation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navOptions
import com.example.navigation.databinding.FragmentBoxBinding
import kotlin.random.Random

class BoxFragment : Fragment(R.layout.fragment_box) {
    private lateinit var binding: FragmentBoxBinding
    private val args: BoxFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentBoxBinding.bind(view)

        var color = args.color
        binding.root.setBackgroundColor(color)

        binding.btnGoBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnOpenSecret.setOnClickListener {
            findNavController().navigate(BoxFragmentDirections.actionBoxFragmentToSecretFragment(),
                navOptions {
                    anim {
                        enter = R.anim.anim
                        exit = R.anim.anim
                        popEnter = R.anim.anim
                        popExit = R.anim.anim
                    }
                })
        }
        binding.btnGenerateNumber.setOnClickListener {
            val number = Random.nextInt(100)
//            parentFragmentManager.setFragmentResult(
//                Constants.REQUEST_CODE,
//                bundleOf(Constants.EXTRA_RANDOM_NUMBER to number))
            findNavController().previousBackStackEntry?.savedStateHandle?.set(
                Constants.EXTRA_RANDOM_NUMBER,
                number
            )
            findNavController().popBackStack()
        }
    }
}