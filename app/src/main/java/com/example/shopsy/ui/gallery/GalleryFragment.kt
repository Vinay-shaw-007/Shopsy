package com.example.shopsy.ui.gallery

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
import com.example.shopsy.databinding.FragmentGalleryBinding
import com.example.shopsy.model.SingleGalleryResponse
import com.example.shopsy.ui.adapter.GalleryAdapter
import com.example.shopsy.ui.adapter.GalleryClickListener
import com.example.shopsy.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint

class GalleryFragment : Fragment(), GalleryClickListener {

    private var _binding: FragmentGalleryBinding? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GalleryAdapter

    private val viewModel by viewModels<GalleryViewModel>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val myApp = activity?.application as ShopsyApplication

        recyclerView = binding.rvGalleryItem

        adapter = GalleryAdapter(this)

        if (!myApp.isDataLoadedForGalleryPage()) {
            viewModel.getAllGalleryPhotos()
            myApp.setDataLoadedForGalleryPage(true)
        }

        observer()
    }

    private fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.allPhotos.collectLatest { response ->
                    binding.progressBar.visibility = View.GONE
                    when (response) {
                        is NetworkResult.Error -> {
                            Toast.makeText(requireContext(), "Error -> ${response.message}", Toast.LENGTH_LONG).show()
                        }

                        is NetworkResult.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.rvGalleryItem.visibility = View.GONE
                        }

                        is NetworkResult.Success -> {
                            response.data?.let { galleryItemList ->
                                adapter.submitList(galleryItemList)
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

    override fun onGalleryClickListener(galleryItem: SingleGalleryResponse) {
        val action = GalleryFragmentDirections.actionNavigationGalleryToGalleryDetailFragment(galleryItem.id)
        findNavController().navigate(action)
    }
}