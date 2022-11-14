package com.example.capstone_project.presentation.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstone_project.R
import com.example.capstone_project.data.Resource
import com.example.capstone_project.databinding.FragmentCriptoListBinding
import com.example.capstone_project.domain.model.BookDomain
import com.example.capstone_project.presentation.ui.adapter.AvailableBookAdapter
import com.example.capstone_project.presentation.ui.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [CriptoListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class CriptoListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentCriptoListBinding? = null
    private val binding get() = _binding!!

    private val criptoViewModel: MainActivityViewModel by viewModels()
    private val adapterBook = AvailableBookAdapter { onItemListener(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            criptoViewModel.avaibleBooks.collect {
                when (it) {
                    is Resource.Error -> {
                        Toast.makeText(activity, "Ha ocurrido un error", Toast.LENGTH_LONG)
                    }
                    is Resource.Loading -> {
                        _binding?.progressBar?.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        _binding?.progressBar?.visibility = View.GONE

                        adapterBook.submitList(it.data)
                        binding.apply {
                            recyclerAvailableBooks.layoutManager = LinearLayoutManager(requireContext())
                            recyclerAvailableBooks.adapter = adapterBook
                        }
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCriptoListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun onItemListener(book: BookDomain) {
        val bundle = bundleOf("bookName" to book.book)
        findNavController().navigate(R.id.action_criptoListFragment_to_cryptoDetailFragment, bundle)
    }
}
