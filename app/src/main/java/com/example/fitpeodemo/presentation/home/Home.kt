package com.example.fitpeodemo.presentation.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.VERTICAL
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitpeodemo.R
import com.example.fitpeodemo.databinding.FragmentHomeBinding
import com.example.fitpeodemo.domain.paging.adapter.PhotoPagingDataAdapter
import com.example.fitpeodemo.presentation.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class Home : Fragment() {

    @Inject
    lateinit var photoPagingDataAdapter: PhotoPagingDataAdapter

    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(requireContext())
        collectPhotosAndSetToAdapter()
    }

    private fun initView(context: Context) {
        photoPagingDataAdapter.setOnClick {
            val action = HomeDirections.actionHome2ToDetail(it)
            findNavController().navigate(action)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = photoPagingDataAdapter
    }

    private fun collectPhotosAndSetToAdapter() {
        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.getPhotos().collectLatest {
                photoPagingDataAdapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}