package com.example.shopsy.ui.galleryDetail

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
import com.example.shopsy.databinding.FragmentGalleryDetailBinding
import com.example.shopsy.utils.Constant
import com.example.shopsy.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GalleryDetailFragment : Fragment() {


    private var _binding: FragmentGalleryDetailBinding? = null


    private val viewModel by viewModels<GalleryDetailViewModel>()

    private val arguments by navArgs<GalleryDetailFragmentArgs>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryDetailBinding.inflate(inflater, container, false)
        activity?.let { Constant.hideBottomNavigation(it) }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.getSingleGalleryPhotos(arguments.id)
        observer()
    }

    private fun observer() {

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.singlePhoto.collectLatest { response ->
                    binding.progressBar.visibility = View.GONE
                    when (response) {
                        is NetworkResult.Error -> {
                            Toast.makeText(requireContext(), "Error -> ${response.message}", Toast.LENGTH_LONG).show()
                        }

                        is NetworkResult.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.galleryImageItem.visibility = View.GONE
                        }

                        is NetworkResult.Success -> {
                            binding.galleryItem = response.data
                            binding.galleryImageItem.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        activity?.let { Constant.showBottomNavigation(it) }
    }
}