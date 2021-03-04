package com.example.anchorbooks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.anchorbooks.databinding.FragmentFirstBinding
import com.example.anchorbooks.databinding.FragmentSecondBinding
import androidx.fragment.app.activityViewModels
import java.util.*

class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private val viewModel: AnchorBooksViewModel by activityViewModels()
    var idImage: Int = 0

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            idImage = requireArguments().getInt("LISTA")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var adapter = AdapterDetail()
        binding.rvDetail.adapter = adapter
        binding.rvDetail.layoutManager = GridLayoutManager(context, 1)

        viewModel.getBookDetail(idImage).observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it?.let {
                adapter.update(it)
            }
        })
    }
}