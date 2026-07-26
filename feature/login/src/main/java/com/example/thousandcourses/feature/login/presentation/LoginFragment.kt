package com.example.thousandcourses.feature.login.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.bundle.bundleOf
import androidx.core.net.toUri
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.thousandcourses.feature.login.R
import com.example.thousandcourses.feature.login.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModel()

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(
        view: View ,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view , savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)

        binding.emailEditText.doAfterTextChanged { editable ->
            viewModel.onEmailChanged(editable?.toString().orEmpty())
        }

        binding.passwordEditText.doAfterTextChanged { editable ->
            viewModel.onPasswordChanged(editable?.toString().orEmpty())
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    binding.loginButton.isEnabled = state.isLoginEnabled
                }
            }
        }

        binding.vkButton.setOnClickListener {
            openUrl("https://vk.com/")
        }

        binding.okButton.setOnClickListener {
            openUrl("https://ok.ru/")
        }

        binding.loginButton.setOnClickListener {

            Log.d(
                "LoginFragment" ,
                "LOGIN BUTTON CLICKED"
            )

            parentFragmentManager.setFragmentResult(
                "login_request" ,
                bundleOf(
                    "success" to true
                )
            )

            Log.d(
                "LoginFragment" ,
                "RESULT SENT"
            )

        }
    }

    private fun openUrl(url: String) {

        val intent = Intent(
            Intent.ACTION_VIEW ,
            url.toUri()
        )

        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}