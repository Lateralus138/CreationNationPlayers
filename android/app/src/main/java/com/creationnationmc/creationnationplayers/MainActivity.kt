package com.creationnationmc.creationnationplayers

import android.content.Intent
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val webView: WebView = findViewById(R.id.webview)

        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        // Set a custom WebViewClient to open external links in the default browser
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                val url = request?.url.toString()
                // Check if the URL is an external link (and not a local file)
                if (url.startsWith("http://") || url.startsWith("https://")) {
                    // Create an Intent to open the URL in the default browser
                    val intent = Intent(Intent.ACTION_VIEW, url.toUri())
                    startActivity(intent)
                    // Return true to indicate that we've handled the URL loading
                    return true
                }
                // For other URLs (e.g., file://), let the WebView handle it normally
                return false
            }
        }
        // Load the local HTML file from the assets folder
        webView.loadUrl("file:///android_asset/index.mobile.html")
    }
}