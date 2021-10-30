package ru.boringowl.parapp.presentation.view.profile

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.boringowl.parapp.BuildConfig.*
import ru.boringowl.parapp.presentation.viewmodel.profile.ProfileViewModel
import ru.boringowl.parapp.R
import ru.boringowl.parapp.databinding.AuthFragmentBinding
import ru.boringowl.parapp.databinding.ProfileFragmentBinding

class ProfileFragment : Fragment() {


    private lateinit var viewModel: ProfileViewModel
    private lateinit var binding: ProfileFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProfileFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        viewModel.user.observe(viewLifecycleOwner) {
            if (it != null) {
                Log.d("kekes123", "123123$it")
                binding.user = it
                if (it.avatarUrl != null)
                    if (it.avatarUrl!!.isNotEmpty())
                        Picasso.with(context).load(it.avatarUrl)
                            .into(binding.userImage)
                    else
                        binding.userImage.isVisible = false
                if (!it.login.isNullOrEmpty()) {
                    binding.web.clearCache(true)
                    binding.web.setInitialScale(200)
                    binding.web.isSelected = false
                    binding.web.loadUrl(getUrl(it.login!!))
                }
            }
        }
    }
    fun getUrl(login: String) = "https://github-readme-stats.vercel.app/api?username=${login}&show_icons=true&theme=dark&count_private=true"
}