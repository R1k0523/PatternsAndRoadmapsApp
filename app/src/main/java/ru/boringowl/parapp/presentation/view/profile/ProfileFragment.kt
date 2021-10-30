package ru.boringowl.parapp.presentation.view.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        if (viewModel.user.value != null) {
            binding.user = viewModel.user.value
            if (viewModel.user.value!!.avatarUrl.isNotEmpty())
                Picasso.with(context).load(viewModel.user.value!!.avatarUrl).into(binding.userImage)
            else
                binding.userImage.isVisible = false
            binding.contributions.loadUserName(viewModel.user.value!!.login)
        }
    }

}