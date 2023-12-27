package com.omerbatuhantandogan.hmw2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.omerbatuhantandogan.hmw2.R
import com.omerbatuhantandogan.hmw2.database.Device


class CustomRecyclerViewAdapter(private val context: Context)
    :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var recyclerItemValues: ArrayList<Device> = ArrayList()
    fun setData(items:List<Device>){
        recyclerItemValues = items as ArrayList<Device>
        notifyDataSetChanged()
    }
    interface RecyclerAdapterInterface {
        fun displayDevice(sc: Device)
    }
    lateinit  var adapterInterface: RecyclerAdapterInterface

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView: View
        val inflator = LayoutInflater.from(viewGroup.context)
        return if (viewType == TYPE_EVEN_ITEM) {
            itemView = inflator.inflate(R.layout.recycler_even_item, viewGroup, false)
            CustomRecyclerViewEvenItemHolder(itemView)
        } else {
            itemView = inflator.inflate(R.layout.recycler_odd_item, viewGroup, false)
            CustomRecyclerViewOddItemHolder(itemView)
        }

        adapterInterface = context as RecyclerAdapterInterface
    }

    override fun onBindViewHolder(
        myRecyclerViewItemHolder: RecyclerView.ViewHolder,
        position: Int
    ) {
        adapterInterface = context as RecyclerAdapterInterface

        val device = recyclerItemValues[position]
        if (getItemViewType(position) == TYPE_EVEN_ITEM) {
            val itemView = myRecyclerViewItemHolder as CustomRecyclerViewEvenItemHolder
            itemView.tvDeviceInfo.text = device.toString()
            itemView.parentLayout.setOnClickListener { adapterInterface.displayDevice(device) }
        } else if (getItemViewType(position) == TYPE_ODD_ITEM) {
            val itemView = myRecyclerViewItemHolder as CustomRecyclerViewOddItemHolder
            itemView.tvDeviceInfo.text = device.toString()
            itemView.parentLayout.setOnClickListener { adapterInterface.displayDevice(device) }
        }
    }

    override fun getItemCount(): Int {
        return recyclerItemValues.size
    }

    override fun getItemViewType(position: Int): Int {
        val sc = recyclerItemValues[position]
        return if (position % 2 == 0) TYPE_EVEN_ITEM else TYPE_ODD_ITEM
    }

    internal inner class CustomRecyclerViewEvenItemHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvDeviceInfo: TextView
        var parentLayout: ConstraintLayout
        init {
            tvDeviceInfo = itemView.findViewById<TextView>(R.id.tvEvenItemDeviceInfo)
            parentLayout = itemView.findViewById<ConstraintLayout>(R.id.itemEvenConstraintLayout)

        }
    }

    internal inner class CustomRecyclerViewOddItemHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvDeviceInfo: TextView
        var parentLayout: ConstraintLayout

        init {
            tvDeviceInfo = itemView.findViewById<TextView>(R.id.tvOddItemDeviceInfo)
            parentLayout = itemView.findViewById<ConstraintLayout>(R.id.itemOddConstraintLayout)
        }
    }

    companion object {
        const val TYPE_ODD_ITEM = 100
        const val TYPE_EVEN_ITEM = 200
    }
}