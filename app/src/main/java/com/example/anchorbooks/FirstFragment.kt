package com.example.anchorbooks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.anchorbooks.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val viewModelBook: AnchorBooksViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
           return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = AdapterBooks()
        binding.rvBooks.adapter = adapter
        binding.rvBooks.layoutManager = GridLayoutManager(context, 1)

        viewModelBook.aBooksLiveDataFromDB.observe(viewLifecycleOwner, {
            it?.let{
                adapter.update(it)
            }
        })

        adapter.booksItem().observe(viewLifecycleOwner, {
            it?.let{
                val bundle = Bundle()
                bundle.putInt("Lista", it.id)
                viewModelBook.getBookDetail(it.id)
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
        })

    }
}