package com.example.rizkie_3tia

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rizkie_3tia.databinding.ActivityMainBinding
import com.example.rizkie_3tia.pertemuan_5.LimaActivity
import com.example.rizkie_3tia.pertemuan_6.SixthActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // [P6] Buka penyimpanan user_pref
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        // [P6] Username diambil dari intent, kalau kosong (karena auto-login dari Splash)
        // diambil dari SharedPreferences
        val user = intent.getStringExtra("username")
            ?: sharedPref.getString("username", "Pengguna")
        val pass = intent.getStringExtra("password")

        binding.txtusername.text = user
        binding.txtpassword.setText(pass)

        // [P6] Latihan 5.1: sapaan pengguna
        binding.tvGreeting.text = "Halo, ${sharedPref.getString("username", "Pengguna")}"

        binding.btnSnackBar.setOnClickListener {
            Snackbar.make(
                binding.root, "Hal,Ini SnackBar",
                Snackbar.LENGTH_LONG
            )
                .setAction("KEMBALI") {
                    // kembalikan item
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Kembali Ke Halaman Activity", Toast.LENGTH_SHORT).show()
                }
                .show()
        }

        binding.btnAlert.setOnClickListener {
            MaterialAlertDialogBuilder(this).setTitle("Hapus data")
                .setMessage("Data yang dihapus tidak bisa dikembalikan.")
                .setNegativeButton("Batal") { dialog, _ ->

                }
                .setPositiveButton("Hapus") { dialog, _ ->
                    // proses hapus
                    dialog.dismiss()
                }
                .setCancelable(false)
                .show()
        }

        binding.btnToLima.setOnClickListener {
            startActivity(Intent(this, LimaActivity::class.java))
        }

        // [P6] Tombol menuju Pertemuan 6
        binding.btnToSixth.setOnClickListener {
            startActivity(Intent(this@MainActivity, SixthActivity::class.java))
        }

        // [P6] Logout: dialog konfirmasi, hapus SharedPreferences, lalu tutup halaman
        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()
                    dialog.dismiss()
                    finish()
                }
                .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }
}