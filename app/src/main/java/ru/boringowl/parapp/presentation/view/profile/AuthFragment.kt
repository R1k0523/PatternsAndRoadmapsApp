package ru.boringowl.parapp.presentation.view.profile

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.boringowl.parapp.BuildConfig
import ru.boringowl.parapp.R
import ru.boringowl.parapp.databinding.AuthFragmentBinding
import ru.boringowl.parapp.domain.model.user.User
import ru.boringowl.parapp.presentation.repository.Repository
import ru.boringowl.parapp.presentation.repository.network.github.GHAuth
import ru.boringowl.parapp.presentation.repository.network.github.GithubAPI
import ru.boringowl.parapp.presentation.repository.network.github.response.AccessToken
import ru.boringowl.parapp.presentation.repository.network.github.response.UserResponse
import ru.boringowl.parapp.presentation.utils.PrefsUtils
import ru.boringowl.parapp.presentation.view.posts.RoadmapFragmentArgs
import ru.boringowl.parapp.presentation.viewmodel.profile.AuthViewModel

class AuthFragment : Fragment() {

    private lateinit var viewModel: AuthViewModel
    private lateinit var binding: AuthFragmentBinding
    private val prefs by inject(PrefsUtils::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (prefs.isTokenStored()) {
            findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToProfileFragment())
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AuthFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        viewModel.checkAll(viewLifecycleOwner)
        binding.vkAuth.setOnClickListener {
            findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToWebFragment())
        }
        binding.adminButton.setOnClickListener {
            binding.adminPanel.isVisible = !binding.adminPanel.isVisible
            binding.userPanel.isVisible = !binding.userPanel.isVisible
        }
        binding.auth.setOnClickListener {
            val login = binding.login.text.toString()
            val password = binding.password.text.toString()
            if (login.isNotEmpty() && password.isNotEmpty()) {
                viewModel.auth(login, password, requireActivity()).observe(viewLifecycleOwner) {
                    if (it != null) {
                        viewModel.setUser(it)
                        Toast.makeText(context, "Авторизация успешно пройдена", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Ошибка в данных", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (prefs.isTokenStored()) {
            findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToProfileFragment())
        }
    }
}