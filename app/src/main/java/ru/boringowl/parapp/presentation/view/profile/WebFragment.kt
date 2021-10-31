package ru.boringowl.parapp.presentation.view.profile

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.boringowl.parapp.databinding.WebFragmentBinding
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import okhttp3.internal.wait
import ru.boringowl.parapp.BuildConfig
import ru.boringowl.parapp.presentation.viewmodel.profile.WebViewModel


class WebFragment : Fragment() {
    private var authUrl: String = "${BuildConfig.BASE_LOGIN_URL}authorize" +
            "?client_id=${BuildConfig.CLIENT_ID}&" +
            "redirect_uri=${BuildConfig.CLIENT_REDIRECT}&" +
            "scope=${BuildConfig.GH_SCOPE}"
    private lateinit var binding: WebFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = WebFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CookieManager.getInstance().removeAllCookies(null)
        Log.d("kekes", authUrl)
        binding.progressBar.isVisible = true
        val viewModel = ViewModelProvider(this).get(WebViewModel::class.java)
        binding.web.clearCache(true)
        binding.web.settings.javaScriptEnabled = true
        binding.web.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                binding.progressBar.isVisible = viewModel
                    .getAndSaveTokenFromCode(url, requireActivity(), binding.root)
            }
        }
        binding.web.loadUrl(authUrl)
        }

}
