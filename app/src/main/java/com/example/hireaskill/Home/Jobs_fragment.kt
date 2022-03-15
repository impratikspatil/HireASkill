package com.example.hireaskill.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.hireaskill.databinding.FragmentJobsFragmentBinding


class Jobs_fragment : Fragment() {

    private lateinit var binding : FragmentJobsFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJobsFragmentBinding.inflate(layoutInflater)
        return binding.root
        (activity as MainActivity?)?.setTitle("Jobs")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.settingsBtn.setOnClickListener {
//            val intent = Intent(context, SettingsActivity::class.java)
//            startActivity(intent)
//        }

    }
}