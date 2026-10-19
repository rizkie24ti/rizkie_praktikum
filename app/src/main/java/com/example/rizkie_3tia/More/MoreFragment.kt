package com.example.rizkie_3tia.More

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.rizkie_3tia.databinding.FragmentMoreBinding

class MoreFragment : Fragment() {

    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!

    private val dataListWithDesc = listOf(
        mapOf("title" to "Kotlin", "desc" to "Bahasa resmi untuk membuat aplikasi Android"),
        mapOf("title" to "Java", "desc" to "Bahasa berorientasi objek yang sudah lama dipakai"),
        mapOf("title" to "Python", "desc" to "Bahasa dengan penulisan sederhana, populer untuk data"),
        mapOf("title" to "C++", "desc" to "Bahasa cepat untuk game dan sistem"),
        mapOf("title" to "JavaScript", "desc" to "Bahasa utama untuk membuat web interaktif"),
        mapOf("title" to "Dart", "desc" to "Bahasa di balik framework Flutter"),
        mapOf("title" to "Swift", "desc" to "Bahasa untuk membuat aplikasi iPhone dan iPad"),
        mapOf("title" to "Go", "desc" to "Bahasa buatan Google untuk server yang ringan"),
        mapOf("title" to "PHP", "desc" to "Bahasa untuk membuat sisi server pada website"),
        mapOf("title" to "SQL", "desc" to "Bahasa untuk mengelola dan mengambil data di database")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "More"

        val adapter = SimpleAdapter(
            requireContext(),
            dataListWithDesc,
            android.R.layout.simple_list_item_2,
            arrayOf("title", "desc"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )
        binding.listViewItems.adapter = adapter

        binding.listViewItems.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = dataListWithDesc[position]
            val title = selectedItem["title"]
            val desc = selectedItem["desc"]
            Toast.makeText(
                requireContext(),
                "Kamu memilih: $title ($desc)",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}