package com.example.eventvalidation

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.example.eventvalidation.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDangKy.setOnClickListener {
            kiemTraThongTin()
        }
    }

    private fun kiemTraThongTin() {
        val hoTen = binding.edtHoTen.text.toString().trim()
        val email = binding.edtEmail.text.toString().trim()
        val sdt = binding.edtSDT.text.toString().trim()
        val msv = binding.edtMSV.text.toString().trim()
        val lop = binding.edtLop.text.toString().trim()

        var hopLe = true

        binding.edtHoTen.error = null
        binding.edtEmail.error = null
        binding.edtSDT.error = null
        binding.edtMSV.error = null
        binding.edtLop.error = null

        val regexHoTen = Regex(
            "^[\\p{L}]+(?:\\s+[\\p{L}]+)+$"
        )

        if (!hoTen.matches(regexHoTen) ||
            hoTen.length !in 5..50
        ) {
            binding.edtHoTen.error =
                "Họ tên phải từ 5-50 ký tự"
            hopLe = false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.edtEmail.error = "Email không đúng định dạng"
            hopLe = false
        }

        if (!sdt.matches(Regex("^0[0-9]{9}$"))) {
            binding.edtSDT.error =
                "Số điện thoại phải gồm 10 chữ số và bắt đầu bằng 0"
            hopLe = false
        }

        if (!msv.matches(Regex("^\\d{13,15}$"))) {
            binding.edtMSV.error =
                "Mã sinh viên phải phải từ 13-15 chữ số"
            hopLe = false
        }

        if (!lop.matches(Regex("^[A-Za-z0-9-]{2,20}$"))) {
            binding.edtLop.error =
                "Lớp phải gồm 2-20 ký tự chữ và số"
            hopLe = false
        }

        if (!hopLe) {
            when {
                binding.edtHoTen.error != null ->
                    binding.edtHoTen.requestFocus()

                binding.edtEmail.error != null ->
                    binding.edtEmail.requestFocus()

                binding.edtSDT.error != null ->
                    binding.edtSDT.requestFocus()

                binding.edtMSV.error != null ->
                    binding.edtMSV.requestFocus()

                binding.edtLop.error != null ->
                    binding.edtLop.requestFocus()
            }
            return
        }

        val snackbar = Snackbar.make(
            binding.layoutDangKy,
            "Đăng ký thành công!",
            Snackbar.LENGTH_INDEFINITE
        )

        snackbar.setAction("XEM CHI TIẾT") {
            val intent = Intent(this, ChiTietActivity::class.java)

            intent.putExtra("HO_TEN", hoTen)
            intent.putExtra("EMAIL", email)
            intent.putExtra("SDT", sdt)
            intent.putExtra("MSV", msv)
            intent.putExtra("LOP", lop)

            startActivity(intent)
        }

        snackbar.show()
    }
}

