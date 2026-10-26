package com.example.rizkie_3tia.Home.pertemuan_9

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rizkie_3tia.R
import com.example.rizkie_3tia.databinding.ActivityNinthBinding
import com.google.android.material.chip.Chip

class NinthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNinthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNinthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Toolbar + tombol back
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Pertemuan 9"
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.toolbar.setNavigationOnClickListener { finish() }

        // Tahap 1: validasi sederhana pada tombol Login
        binding.btnLogin.setOnClickListener {
            val nama = binding.etNama.text.toString().trim()
            if (nama.isEmpty()) {
                binding.textInputLayout.error = "Nama tidak boleh kosong"
            } else {
                binding.textInputLayout.error = null
                Toast.makeText(this, "Halo, $nama", Toast.LENGTH_SHORT).show()
            }
        }

        // Tahap 2: deteksi chip yang dipilih
        binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedChipId = checkedIds.firstOrNull()
            if (selectedChipId != null) {
                val chip = group.findViewById<Chip>(selectedChipId)
                Toast.makeText(this, "Filter: ${chip.text}", Toast.LENGTH_SHORT).show()
            }
        }

        // Tahap 4 + Latihan 7.2: tiap tombol kopi menampilkan Toast
        val tombolKopi = listOf(
            binding.btnEspresso,
            binding.btnLatte,
            binding.btnCappuccino,
            binding.btnAmericano,
            binding.btnMocha,
            binding.btnKopiSusu
        )
        tombolKopi.forEach { tombol ->
            tombol.setOnClickListener {
                Toast.makeText(this, "Kamu memilih: ${tombol.text}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
