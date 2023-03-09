package com.maverick.fragmentssimpledemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maverick.fragmentssimpledemo.databinding.FragmentABinding

class FragmentA : Fragment() {

    private var _binding: FragmentABinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentABinding.inflate(inflater, container, false)
        val view = binding.root

        /**
         * How to Get Data from another Fragment?
         */

        parentFragmentManager.setFragmentResultListener("requestKey2", this) { requestKey, result ->
            val data = result.getString("key2")
            binding.tvDataA.text = data.toString()
        }

        binding.btnSendA.setOnClickListener {

            /**
             * How to Pass Data Between Two Fragment?
             */

            // Via Bundle
            val bundle = Bundle()

            // Add Data in Key-value Pair
            bundle.putString("key1", "Banana")

            // set Bundle's object in FragmentResult using parentFragmentManager with RequestKey
            parentFragmentManager.setFragmentResult("requestKey1", bundle)

            /**
             * How to Switch to Another Fragment From One Fragment?
             */

            val fragmentManager = parentFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayout, FragmentB())
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