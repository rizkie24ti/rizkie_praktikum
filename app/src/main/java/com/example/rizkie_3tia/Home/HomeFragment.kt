package com.example.rizkie_3tia.Home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.rizkie_3tia.Home.pertemuan_5.LimaActivity
import com.example.rizkie_3tia.Home.pertemuan_6.SixthActivity
import com.example.rizkie_3tia.Home.pertemuan_9.NinthActivity
import com.example.rizkie_3tia.LoginActivity
import com.example.rizkie_3tia.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Home"

        val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)

        // Sapaan pengguna
        val username = sharedPref.getString("username", "Pengguna")
        binding.tvWelcome.text = "Halo, $username"

        binding.btnSnackBar.setOnClickListener {
            Snackbar.make(binding.root, "Halo, ini SnackBar", Snackbar.LENGTH_LONG)
                .setAction("OKE") {
                    Toast.makeText(requireContext(), "SnackBar ditutup", Toast.LENGTH_SHORT).show()
                }
                .show()
        }

        binding.btnAlert.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Hapus data")
                .setMessage("Data yang dihapus tidak bisa dikembalikan.")
                .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
                .setPositiveButton("Hapus") { dialog, _ -> dialog.dismiss() }
                .setCancelable(false)
                .show()
        }

        binding.btnToLima.setOnClickListener {
            startActivity(Intent(requireContext(), LimaActivity::class.java))
        }

        binding.btnToSixth.setOnClickListener {
            startActivity(Intent(requireContext(), SixthActivity::class.java))
        }

        // Pertemuan 9
        binding.btnToNinth.setOnClickListener {
            startActivity(Intent(requireContext(), NinthActivity::class.java))
        }

        // Logout: dialog konfirmasi, hapus SharedPreferences, kembali ke Login
        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    sharedPref.edit().clear().apply()
                    dialog.dismiss()
                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
