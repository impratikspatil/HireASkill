package com.example.hireaskill.Settings

import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.hireaskill.R


class HelpFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_help, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textView1 : TextView = view.findViewById(R.id.mobile_contact)
        textView1.movementMethod = LinkMovementMethod.getInstance()
        val textView2 : TextView = view.findViewById(R.id.email_link)
        textView2.movementMethod = LinkMovementMethod.getInstance()
        val textView3 : TextView = view.findViewById(R.id.app_link)
        textView3.movementMethod = LinkMovementMethod.getInstance()

    }

}