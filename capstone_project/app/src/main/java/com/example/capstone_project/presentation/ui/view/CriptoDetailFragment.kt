package com.example.capstoneproject.presentation.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstoneproject.R
import com.example.capstoneproject.data.Resource
import com.example.capstoneproject.databinding.FragmentCriptoDetailBinding
import com.example.capstoneproject.presentation.ui.adapter.AskAdapter
import com.example.capstoneproject.presentation.ui.adapter.BidsAdapter
import com.example.capstoneproject.presentation.ui.viewmodel.CriptoDetailViewModel
import com.example.capstoneproject.presentation.util.Util
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [CriptoDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class CriptoDetailFragment : Fragment() {

    private var _binding: FragmentCriptoDetailBinding? = null
    private val binding get() = _binding!!

    private var bookName: String = ""
    private val criptoViewModel: CriptoDetailViewModel by viewModels()
    private var adapterBids = BidsAdapter()
    private var adapterAsks = AskAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bookName = arguments?.getString("bookName").toString()
        criptoViewModel.getBook(bookName)
        _binding = FragmentCriptoDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            criptoViewModel.getAsks(bookName)
            criptoViewModel.getBids(bookName)
            criptoViewModel.asks.collect {
                when (it) {
                    is Resource.Error -> {
                        Toast.makeText(activity, R.string.errorToast, Toast.LENGTH_LONG).show()
                    }
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        _binding?.progressBar?.visibility = View.GONE

                        adapterAsks.submitList(it.data)
                        binding.apply {
                            this.imageBitcoinDetail.setImageResource(Util.getResources(bookName))
                            recyclerAsks.adapter = adapterAsks
                            recyclerAsks.layoutManager = LinearLayoutManager(requireContext())
                        }
                    }
                }
                viewLifecycleOwner.lifecycleScope.launch {
                    criptoViewModel.bids.collect {
                        when (it) {
                            is Resource.Error -> {
                                Toast.makeText(activity, R.string.errorToast, Toast.LENGTH_LONG).show()
                            }
                            is Resource.Loading -> {}
                            is Resource.Success -> {
                                binding.apply {
                                    adapterBids.submitList(it.data)
                                    recyclerBids.layoutManager = LinearLayoutManager(requireContext())
                                    recyclerBids.adapter = adapterBids
                                }
                            }
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            criptoViewModel.getTicker(bookName)
            criptoViewModel.tickerlive.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Error -> {
                        Toast.makeText(activity, R.string.errorToast, Toast.LENGTH_LONG).show()
                    }
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        binding.txtBookNameDetail.text = it.data.book
                        binding.txtLastPrice.text =
                            activity?.resources?.getString(R.string.last_price, it.data.last)
                        binding.txtHighPrice.text =
                            activity?.resources?.getString(R.string.highPrice, it.data.high)
                        binding.txtlowPrice.text =
                            activity?.resources?.getString(R.string.lowPrice, it.data.low)
                    }
                }
            }
        }
    }
}
