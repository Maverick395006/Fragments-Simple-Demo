package com.maverick.fragmentssimpledemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maverick.fragmentssimpledemo.databinding.FragmentBBinding

class FragmentB : Fragment() {

    private var _binding: FragmentBBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBBinding.inflate(inflater, container, false)
        val view = binding.root

        /**
         * How to Get Data from another Fragment?
         */

        parentFragmentManager.setFragmentResultListener("requestKey1", this) { requestKey, result ->
            val data = result.getString("key1")
            binding.tvDataB.text = data.toString()
        }

        binding.btnSendB.setOnClickListener {

            /**
             * How to Pass Data Between Two Fragment?
             */

            // Via Bundle
            val bundle = Bundle()

            // Add Data in Key-value Pair
            bundle.putString("key2", "Apple")

            // set Bundle's object in FragmentResult using parentFragmentManager with RequestKey
            parentFragmentManager.setFragmentResult("requestKey2", bundle)

            /**
             * How to Switch to Another Fragment From One Fragment?
             */

            val fragmentManager = parentFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayout, FragmentA())
            fragmentTransaction.commit()
        }

        // Inflate the layout for this fragment
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}