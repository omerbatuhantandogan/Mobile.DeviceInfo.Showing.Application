package com.omerbatuhantandogan.hmw2

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.omerbatuhantandogan.hmw2.database.Device
import com.omerbatuhantandogan.hmw2.databinding.ActivityAddBinding


class AddActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        val view = binding.root
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(view)

        val receivedIntent = intent


        binding.addButton.setOnClickListener{
            if(
                binding.etId.text.isEmpty() ||
                binding.etDeviceName.text.isEmpty() ||
                binding.etDeviceType.text.isEmpty() ||
                binding.etDevicePrice.text.isEmpty()
            ) {
                Toast.makeText(this, "Fill all fields.", Toast.LENGTH_LONG).show()
            }
            else if(binding.etDevicePrice.text.toString().toDoubleOrNull() == null || binding.etId.text.toString().toIntOrNull() == null){
                Toast.makeText(this,"Device Double, Id Integer!", Toast.LENGTH_LONG).show()
            }
            else{
                var device: Device = Device(binding.etId.text.toString().toInt(),
                    binding.etDeviceName.text.toString(),
                    binding.etDeviceType.text.toString(),
                    binding.etDevicePrice.text.toString().toDouble())
                val intent = Intent()
                intent.putExtra("addedDevice", device)
                setResult(RESULT_OK, intent)
                finish()
            }
        }

        binding.closeButton.setOnClickListener {
            val intent = Intent()
            setResult(RESULT_CANCELED, intent)
            finish()
        }

    }
}