package com.example.shopsy.ui.home

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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.shopsy.ShopsyApplication
import com.example.shopsy.databinding.FragmentHomeBinding
import com.example.shopsy.model.SingleProduct
import com.example.shopsy.ui.adapter.ProductAdapter
import com.example.shopsy.ui.adapter.ProductClickListener
import com.example.shopsy.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment(), ProductClickListener {

    companion object {
        const val TAG = "HomeFragment"
    }

    private var _binding: FragmentHomeBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter

    private val viewModel by viewModels<HomeViewModel>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val myApp = activity?.application as ShopsyApplication

        recyclerView = binding.rvProductItem

        adapter = ProductAdapter(this)

        if (!myApp.isDataLoadedForHomePage()) {
            viewModel.getAllProducts()
            myApp.setDataLoadedForHomePage(true)
        }

        observer()
    }

    private fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.allProduct.collectLatest { response ->
                    binding.progressBar.visibility = View.GONE
                    when (response) {
                        is NetworkResult.Error -> {
                            Toast.makeText(requireContext(), "Error -> ${response.message}", Toast.LENGTH_LONG).show()
                        }

                        is NetworkResult.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            recyclerView.visibility = View.GONE
                        }

                        is NetworkResult.Success -> {

                            response.data?.products?.let { singleProductList ->
                                adapter.submitList(singleProductList)
                                recyclerView.adapter = adapter
                                recyclerView.visibility = View.VISIBLE
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onProductClickListener(product: SingleProduct) {
       val action = HomeFragmentDirections.actionNavigationHomeToProductDetailFragment(product.id)
        findNavController().navigate(action)
    }
}