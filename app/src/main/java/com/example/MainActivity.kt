package com.example

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.viewinterop.AndroidView

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    setContent {
      Surface(
        modifier = Modifier
          .fillMaxSize()
          .safeDrawingPadding()
          .testTag("main_surface"),
        color = androidx.compose.material3.MaterialTheme.colorScheme.background
      ) {
        TradeCalcWebView(
          modifier = Modifier
            .fillMaxSize()
            .testTag("tradecalc_webview")
        )
      }
    }
  }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun TradeCalcWebView(modifier: Modifier = Modifier) {
  AndroidView(
    modifier = modifier,
    factory = { context ->
      WebView(context).apply {
        layoutParams = ViewGroup.LayoutParams(
          ViewGroup.LayoutParams.MATCH_PARENT,
          ViewGroup.LayoutParams.MATCH_PARENT
        )
        setBackgroundColor(Color.TRANSPARENT)

        settings.apply {
          javaScriptEnabled = true
          domStorageEnabled = true
          databaseEnabled = true
          allowFileAccess = true
          useWideViewPort = true
          loadWithOverviewMode = true
          cacheMode = WebSettings.LOAD_DEFAULT
          displayZoomControls = false
          builtInZoomControls = false
        }

        webViewClient = object : WebViewClient() {}
        webChromeClient = WebChromeClient()

        loadUrl("file:///android_asset/index.html")
      }
    }
  )
}
