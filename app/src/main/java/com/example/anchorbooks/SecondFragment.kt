package com.example.anchorbooks

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
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
    var title: String = ""

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
            idImage = requireArguments().getInt("Lista")
            title = requireArguments().getString("Title", "")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var adapter = AdapterDetail()
        binding.rvDetail.adapter = adapter
        binding.rvDetail.layoutManager = GridLayoutManager(context, 1)

        viewModel.returnBookDetail(idImage).observe(viewLifecycleOwner,
            androidx.lifecycle.Observer {
            it?.let {
                Log.d("idImage", "$it")
                adapter.update(it)
            }
        })
        binding.btBuy.setOnClickListener{
            sendEmail()
        }
    }
    fun sendEmail() {
        val para = arrayOf("ventas@anchorBooks.cl")
        val copia = arrayOf("")
        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.data = Uri.parse("mailto:")
        emailIntent.type = "text/plain"
        emailIntent.putExtra(Intent.EXTRA_EMAIL, para)
        emailIntent.putExtra(Intent.EXTRA_CC, copia)

        emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.asunto, title, idImage))
        emailIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.texto, title, idImage))
        try {
            startActivity(Intent.createChooser(emailIntent, "Enviar email..."))
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(context,
                "No tienes clientes de email instalados.", Toast.LENGTH_SHORT).show()
        }
    }
}