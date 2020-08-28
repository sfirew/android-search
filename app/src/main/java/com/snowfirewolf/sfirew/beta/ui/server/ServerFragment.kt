package com.snowfirewolf.sfirew.beta.ui.server

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import androidx.fragment.app.Fragment

import com.snowfirewolf.sfirew.beta.R
import com.github.kittinunf.fuel.Fuel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.nav_header_main.view.*

import org.json.JSONObject
import org.w3c.dom.Text


class ServerFragment : Fragment() {

    private lateinit var serverViewModel: ServerViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_server, container, false)



        val searchValueElement = root.findViewById(R.id.searchField) as EditText
        val searchButton: Button = root.findViewById(R.id.searchButton)
        val allData: TextView = root.findViewById(R.id.allData)


        searchButton.setOnClickListener { view ->
            val searchValue = searchValueElement.text.toString()

            // searchValue 不能等於空白
            if(searchValue != "") {
                // 開始搜尋 disabled 物件
                view.setEnabled(false)
                searchButton.text = "搜尋中..."

                Fuel.post("https://api.sfirew.com/api/server/" + searchValue)
                    .response { request, response, result ->

                        val (bytes, error) = result
                        if (bytes != null) {

                            val serverRes = String(bytes)
                            val serverObject = JSONObject(serverRes)

                            allData.text = serverRes

                            // 搜尋完畢 解除 disabled 按鈕
                            view.setEnabled(true)
                            searchButton.text = "搜尋"
                        }
                    }

                Snackbar.make(view, "你點了搜尋" + searchValue, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }


        /*
        val serverLoading: ProgressBar = root.findViewById(R.id.serverLoading)

                    //close loading
                    serverLoading.setVisibility(View.INVISIBLE)


                    serverHostName.text = serverObject.getString("hostname")
                    serverIP.text = serverObject.getString("ip")
                    allData.text = serverRes

                    // load icon image
                    val base64Image: String = serverIconString.split(",").get(1)
                    val decodedString = Base64.decode(base64Image, Base64.DEFAULT)
                    val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
                    iconImage.setImageBitmap(decodedByte)


                }
            }*/


        return root
    }
}