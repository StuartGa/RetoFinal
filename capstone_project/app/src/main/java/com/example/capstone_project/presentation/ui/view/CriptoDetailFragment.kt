package com.example.capstone_project.presentation.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstone_project.R
import com.example.capstone_project.data.Resource
import com.example.capstone_project.databinding.FragmentCriptoDetailBinding
import com.example.capstone_project.presentation.util.Util
import com.example.capstone_project.presentation.ui.adapter.AskAdapter
import com.example.capstone_project.presentation.ui.adapter.BidsAdapter
import com.example.capstone_project.presentation.ui.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
    private val criptoViewModel: MainActivityViewModel by viewModels()
    private var adapterBids = BidsAdapter()
    private var adapterAsks = AskAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bookName = arguments?.getString("bookName").toString()
        _binding = FragmentCriptoDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.Main).launch {
            criptoViewModel.getAsks(bookName)
            criptoViewModel.getBids(bookName)
            criptoViewModel.asks.collect {
                when (it) {
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        adapterAsks.submitList(it.data)
                        binding.apply {
                            this.imageBitcoinDetail.setImageResource(Util.getResources(bookName))
                            recyclerAsks.adapter = adapterAsks
                            recyclerAsks.layoutManager = LinearLayoutManager(requireContext())
                        }
                    }
                }
                CoroutineScope(Dispatchers.Main).launch {
                    criptoViewModel.bids.collect {
                        when (it) {
                            is Resource.Error -> {}
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

        CoroutineScope(Dispatchers.Main).launch {
            criptoViewModel.getTicker(bookName)
            criptoViewModel.tickers.collect {
                when (it) {
                    is Resource.Error -> {}
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
