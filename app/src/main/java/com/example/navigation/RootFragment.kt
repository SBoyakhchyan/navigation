package com.example.navigation

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.navigation.databinding.FragmentRootBinding

class RootFragment : Fragment(R.layout.fragment_root) {
    private lateinit var binding: FragmentRootBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRootBinding.bind(view)

        binding.tvOpenYellowBoxButton.setOnClickListener {
            openBox(Color.YELLOW, "Yellow")
        }
        binding.tvOpenGreenBoxButton.setOnClickListener {
            openBox(Color.GREEN, "Green")
        }

        val liveData =
            findNavController().currentBackStackEntry?.savedStateHandle
                ?.getLiveData<Int>(Constants.EXTRA_RANDOM_NUMBER)
        liveData?.observe(viewLifecycleOwner) { randomNumber ->
            if (randomNumber != null) {
                Toast.makeText(
                    requireContext(),
                    "Random generated number is $randomNumber",
                    Toast.LENGTH_SHORT
                ).show()
                liveData.value = null

            }
        }
//        parentFragmentManager.setFragmentResultListener(
//            Constants.REQUEST_CODE,
//            viewLifecycleOwner
//        ) { _, data ->
//            val number = data.getInt(Constants.EXTRA_RANDOM_NUMBER)
//            Toast.makeText(
//                requireContext(),
//                "Random generated number is $number",
//                Toast.LENGTH_SHORT
//            ).show()
//
//        }
    }

    private fun openBox(color: Int, colorName: String) {
        val direction = RootFragmentDirections.actionRootFragmentToBoxFragment(color, colorName)

        findNavController().navigate(
            direction,
            navOptions {
                anim {
                    enter = R.anim.anim
                    exit = R.anim.anim
                    popEnter = R.anim.anim
                    popExit = R.anim.anim
                }
            }
        )
    }
}

