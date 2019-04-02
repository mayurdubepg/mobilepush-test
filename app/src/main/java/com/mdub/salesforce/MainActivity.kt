package com.mdub.salesforce

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.widget.TextView
import com.salesforce.marketingcloud.MarketingCloudSdk
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MarketingCloudSdk.requestSdk { sdk ->
            addtext("pushtoken", "${sdk.pushMessageManager.pushToken}")
            addtext("isPushEnabled", "${sdk.pushMessageManager.isPushEnabled}")
            addtext("Android API version", "${Build.VERSION.SDK_INT}")
            addtext("Product", Build.PRODUCT)
            addtext("Brand and Model", "${Build.BRAND}, ${Build.MODEL}")
            printJsonObject("", sdk.sdkState)
        }
    }
    private fun printJsonObject(key: String, jsonObject: JSONObject) {
        jsonObject.keys().forEach {
            val obj = jsonObject.get(it)
            if (obj is JSONObject) {
                printJsonObject("$key.$it", obj)
            } else {
                addtext("$key.$it", "$obj")
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun addtext(key: String, value: String) {
        val textView = LayoutInflater.from(container.context).inflate(R.layout.textview, container, false) as TextView
        textView.text = "$key\n$value"
        container.addView(textView)
    }
}
