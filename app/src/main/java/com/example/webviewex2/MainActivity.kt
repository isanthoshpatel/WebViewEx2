package com.example.webviewex2

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var webview: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webview = findViewById(R.id.webview)

        webview.webViewClient = WebViewClient()

        webview.settings.javaScriptEnabled = true
        webview.loadUrl("https://www.google.com")

        if (18 < Build.VERSION.SDK_INT) {
            //18 = JellyBean MR2, KITKAT=19
            webview.settings.cacheMode = WebSettings.LOAD_NO_CACHE
        }
        search.setOnClickListener {
            if (et_search.text.trim().isEmpty()) {
                Toast.makeText(this, "Pl enter proper site name", Toast.LENGTH_LONG).show()
            } else {
                var url = et_search.text.trim().toString()
                if (!(url.contains("https://www.") && url.contains(".com"))) webview.loadUrl("https://www.$url.com")
                else if (!url.contains("http://wwww.")) webview.loadUrl("http://www.$url")
                else if (!url.contains("http://")) webview.loadUrl("http://$url")

            }

        }
        fb.setOnClickListener {
            webview.loadUrl("https://www.facebook.com")
        }
        google.setOnClickListener {
            webview.loadUrl("https://www.google.co.in/")
        }
        youtube.setOnClickListener {
            webview.loadUrl("https://www.youtube.com")
        }


    }

    override fun onBackPressed() {
        if(webview.canGoBack()) webview.goBack()
        else if(webview.canGoForward()) webview.goForward()
        else super.onBackPressed()
    }
}
