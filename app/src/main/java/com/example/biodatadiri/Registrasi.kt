package com.example.biodatadiri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_registrasi.*

class Registrasi : AppCompatActivity() {

    private var namaInput : String = ""
    private var emailInput : String = ""
    private var telpInput : String = ""
    private var alamatInput : String = ""
    private var genderInput : String = ""
    private var umurInput : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrasi)

        setDataSpinnerGender()

        buttonSave.setOnClickListener{ validasiInput() }
    }

    private fun validasiInput(){
        namaInput = fieldName.text.toString()
        emailInput = fieldEmail.text.toString()
        telpInput = fieldTelp.text.toString()
        alamatInput = fieldAddress.text.toString()
        genderInput = spinnerGender.selectedItem.toString()
        umurInput = fieldUmur.text.toString()
        when{
            namaInput.isEmpty() -> fieldName.error = "Nama tidak boleh kosong"
            genderInput.equals("Pilih Jenis Kelamin", ignoreCase = true) ->
                tampilToast("Jenis Kelamin harus dipilih")
            emailInput.isEmpty() -> fieldEmail.error = "Email tidak boleh kosong"
            telpInput.isEmpty() -> fieldTelp.error = "Telp tidak boleh kosong"
            alamatInput.isEmpty() -> fieldAddress.error = "Alamat tidak boleh kosong"
            umurInput.isEmpty() -> fieldUmur.error = "Umur tidak boleh kosong"
            else -> {
                tampilToast("Navigasi ke halaman profil")
                goToProfileActivity()
            }
        }
    }

    private fun setDataSpinnerGender(){
        val adapter = ArrayAdapter.createFromResource(this,
            R.array.jenis_kelamin, android.R.layout.simple_spinner_item)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGender.adapter = adapter
    }

    private fun tampilToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun goToProfileActivity() {
        val intent = Intent(this,Profile::class.java)

        val bundle = Bundle()
        bundle.putString("nama", namaInput)
        bundle.putString("gender", genderInput)
        bundle.putString("email", emailInput)
        bundle.putString("telp", telpInput)
        bundle.putString("alamat", alamatInput)
        bundle.putString("umur",umurInput)
        intent.putExtras(bundle)

        startActivity(intent)
    }

}
