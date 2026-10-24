package com.example.eventvalidation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.eventvalidation.databinding.ActivityChiTietBinding

class ChiTietActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChiTietBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChiTietBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hoTen = intent.getStringExtra("HO_TEN") ?: ""
        val email = intent.getStringExtra("EMAIL") ?: ""
        val sdt = intent.getStringExtra("SDT") ?: ""
        val msv = intent.getStringExtra("MSV") ?: ""
        val lop = intent.getStringExtra("LOP") ?: ""

        binding.txtHoTen.text = "Họ và tên: $hoTen"
        binding.txtEmail.text = "Email: $email"
        binding.txtSDT.text = "Số điện thoại: $sdt"
        binding.txtMSV.text = "Mã số sinh viên: $msv"
        binding.txtLop.text = "Lớp: $lop"

        binding.btnQuayLai.setOnClickListener {
            finish()
        }
    }
}

