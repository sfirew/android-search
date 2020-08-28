package com.snowfirewolf.sfirew.beta.ui.home

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.ImageView

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.snowfirewolf.sfirew.beta.R

import com.bumptech.glide.Glide

import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val hideButton: Button = root.findViewById(R.id.hide_btn)
        hideButton.setOnClickListener { view ->
            Snackbar.make(view, "你點了隱藏按鈕", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

            showHide(view)
        }


        // load image
        val mcfalloutImage: ImageView = root.findViewById(R.id.mcfalloutImage)
        val mcfalloutMedia = "https://www.mcfallout.net/uploads/7/7/9/4/77949402/background-images/1087368782.png"

        if(mcfalloutMedia !== null) {
            Glide.with(this)
                .load(mcfalloutMedia)
                .into(mcfalloutImage)
        }else{
            mcfalloutImage.setImageResource(R.drawable.ic_launcher_background)
        }


        return root
    }

    fun showHide(view:View) {
        view.visibility = if (view.visibility == View.VISIBLE) {
            View.INVISIBLE
        } else {
            View.VISIBLE
        }
    }
}