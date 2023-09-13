package com.example.bitsandpizzas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.bitsandpizzas.databinding.FragmentOrderBinding
import com.google.android.material.snackbar.Snackbar

class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener {
            val pizzaType = binding.pizzaGroup.checkedRadioButtonId
            if (pizzaType == -1) {
                Toast.makeText(
                    activity,
                    "You need to choose a pizza type",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                var text = (when (pizzaType) {
                    R.id.radioDiavolo -> "Diavolo pizza"
                    else -> "Funghi pizza"
                })
                text += if (binding.parmesan.isChecked) ", extra parmesan" else ""
                text += if (binding.chiliOil.isChecked) ", extra chili oil" else ""
                Snackbar.make(binding.fab, text, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            OrderFragment()
    }
}