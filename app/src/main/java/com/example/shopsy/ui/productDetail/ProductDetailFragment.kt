package com.example.shopsy.ui.productDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.shopsy.databinding.FragmentProductDetailBinding
import com.example.shopsy.utils.Constant.hideBottomNavigation
import com.example.shopsy.utils.Constant.showBottomNavigation
import com.example.shopsy.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {


    private val arguments by navArgs<ProductDetailFragmentArgs>()

    private var _binding: FragmentProductDetailBinding? = null

    private val binding get() = _binding!!

    private val viewModel by viewModels<ProductDetailViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.let { hideBottomNavigation(it) }
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.getSingleProduct(arguments.id)
        observer()
    }

    private fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.singleProduct.collectLatest { response ->
                    binding.progressBar.visibility = View.GONE
                    when (response) {
                        is NetworkResult.Error -> {
                            Toast.makeText(requireContext(), "Error -> ${response.message}", Toast.LENGTH_LONG).show()
                        }

                        is NetworkResult.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.productDetailLayout.visibility = View.GONE
                        }

                        is NetworkResult.Success -> {
                            binding.product = response.data
                            binding.productDetailLayout.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        activity?.let { showBottomNavigation(it) }
    }




}