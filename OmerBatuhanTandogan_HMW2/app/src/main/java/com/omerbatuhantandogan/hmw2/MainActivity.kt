package com.omerbatuhantandogan.hmw2

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.GestureDetectorCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.omerbatuhantandogan.hmw2.adapter.CustomRecyclerViewAdapter
import com.omerbatuhantandogan.hmw2.database.Device
import com.omerbatuhantandogan.hmw2.database.DeviceViewModel
import com.omerbatuhantandogan.hmw2.databinding.ActivityMainBinding
import com.omerbatuhantandogan.hmw2.databinding.DialogBinding
import java.util.Collections

class MainActivity : AppCompatActivity(), CustomRecyclerViewAdapter.RecyclerAdapterInterface  {
    var gDetector: GestureDetectorCompat? = null
    private lateinit var binding: ActivityMainBinding
    lateinit  var layoutManager: LinearLayoutManager
    lateinit  var adapter: CustomRecyclerViewAdapter
    private lateinit var deviceViewModel: DeviceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(view)

        layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerDevice.layoutManager = layoutManager
        deviceViewModel = ViewModelProvider(this).get(DeviceViewModel::class.java)
        prepareData()
        adapter = CustomRecyclerViewAdapter(this)
        binding.recyclerDevice.adapter = adapter
        getData()

        binding.addButton.setOnClickListener {
            addDevice()
        }

        gDetector =  GestureDetectorCompat(this, CustomGesture())
        gDetector?.setOnDoubleTapListener(CustomGesture())
    }

    fun getData() {
        deviceViewModel.readAllData.observe(this, Observer { devices ->
            adapter.setData(devices)
        })
    }

    fun prepareData(){
        var devices=ArrayList<Device>()
        Collections.addAll(devices,
            Device(111,"Iphone 15 Pro Max", "Phone", 57050.0),
            Device(222,"Huawei s20", "Phone", 25000.2),
            Device(333,"Macbook Pro", "Laptop", 45000.1),
            Device(444,"Lenovo Notebook", "Laptop", 25000.2),
            Device(555,"Apple Watch", "Watch", 4000.1),
            Device(666,"Samsung Watch", "Watch", 2000.2))
        deviceViewModel.addDevices(devices)
    }

    fun addDevice() {
        val intent = Intent(this, AddActivity::class.java)
        activityResultLauncher.launch(intent)
    }

    var activityResultLauncher = registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()) { result  ->

        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val receivedDevice: Device? = data!!.getParcelableExtra("addedDevice", Device::class.java)
            if (receivedDevice != null) {
                deviceViewModel.addDevice(receivedDevice)
                Toast.makeText(this@MainActivity, "Device added.", Toast.LENGTH_SHORT).show()
            }
        }

    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        gDetector?.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    inner class CustomGesture: GestureDetector.SimpleOnGestureListener() {

        override fun onLongPress(e: MotionEvent) {
            Toast.makeText(this@MainActivity,R.string.longPress, Toast.LENGTH_SHORT).show()
        }

        override fun onFling(e1: MotionEvent?, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
            Toast.makeText(this@MainActivity,R.string.fling, Toast.LENGTH_SHORT).show()
            return false
        }
        override fun onDoubleTapEvent(e: MotionEvent): Boolean {
            Toast.makeText(this@MainActivity,R.string.doubleTap, Toast.LENGTH_SHORT).show()
            return true
        }
    }

    fun displayDialog(deviceSelected: Device) {
        val dialog = Dialog(this)
        val bindingDialog: DialogBinding = DialogBinding.inflate(layoutInflater)
        val view = bindingDialog.root
        dialog.setContentView(view)

        bindingDialog.tvDeviceName.text = deviceSelected.toString()
        bindingDialog.btnDialogClose.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

    override fun displayDevice(sc: Device) {
        displayDialog(sc)
    }
}