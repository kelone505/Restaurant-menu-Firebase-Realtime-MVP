package com.kelvin.quickmenu.menu.itemsByCategory.view.adapter

import android.content.Context
import android.telecom.Call
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kelvin.quickmenu.MainActivity
import com.kelvin.quickmenu.R
import com.kelvin.quickmenu.common.Callback
import com.kelvin.quickmenu.common.utility
import com.kelvin.quickmenu.menu.itemsByCategory.model.ItemByCategory
import com.kelvin.quickmenu.order.interfaces.OrderContract
import com.kelvin.quickmenu.order.model.Order
import com.kelvin.quickmenu.order.model.OrderSingleton
import java.math.BigDecimal


class ItemByCategoryAdapter(private val itemList:ArrayList<ItemByCategory>,
                            private val context:Context)
    :RecyclerView.Adapter<ItemByCategoryAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemByCategoryAdapter.ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(
                R.layout.rv_items_category,parent,false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemByCategoryAdapter.ViewHolder, position: Int) {
        holder.bindItem(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun bindItem(item:ItemByCategory){
            var img=itemView.findViewById<ImageView>(R.id.ivItemCategory)
            var name=itemView.findViewById<TextView>(R.id.tvItemName)
            var descrip=itemView.findViewById<TextView>(R.id.tvDescription)
            var quantity=itemView.findViewById<TextView>(R.id.tvQuantity)
            var price=itemView.findViewById<TextView>(R.id.tvPrice)
            var btnLess=itemView.findViewById<Button>(R.id.btnLess)
            var btnPlus=itemView.findViewById<Button>(R.id.btnPlus)
            var cbAddToOrder=itemView.findViewById<CheckBox>(R.id.cbAddToOrder)
            var count=0

            btnLess.setEnabled(false)

            Glide.with(context).load(item.getImage()).centerCrop()
                    .error(android.R.drawable.ic_menu_report_image)
                    //.circleCrop()
                    .apply(RequestOptions())
                    .into(img)
            name.setText(item.getName())

            if(OrderSingleton.getItems().any{it.key.getName()==name.text}){
                count = OrderSingleton.getItems().filter {it.key.getName()==name.text}.map{it.value}[0]
                Toast.makeText(context, "testt ${count}", Toast.LENGTH_SHORT).show()
                btnLess.isEnabled=true
                cbAddToOrder.isChecked=true
                quantity.setText("${count}")
                price.setText("${utility.currencyFormat.format(item.getPrice()*count.toBigDecimal())}")
            }else{
                quantity.setText("${count}")
                price.setText("${utility.currencyFormat.format(item.getPrice())}")
                }


             descrip.setText(item.getDescription())

            btnLess.setOnClickListener {
                if(count>0) {
                    quantity.setText("${--count}")
                    price.setText("${utility.currencyFormat.format(item.getPrice()* BigDecimal.valueOf(count.toDouble()))}")
                    if(count==0){btnLess.setEnabled(false)
                        price.setText("${utility.currencyFormat.format(item.getPrice())}")
                    }
                }else btnLess.setEnabled(false)
                if (cbAddToOrder.isChecked && count>0){
                    OrderSingleton.addItemQuantity(item,count)
                }else if(count==0) {OrderSingleton.removeItem(item)
                    cbAddToOrder.isChecked=false
                }
            }
            btnPlus.setOnClickListener {
                if(count<item.getAvailable())
                    quantity.setText("${++count}")
                    price.setText("${utility.currencyFormat.format(item.getPrice()* BigDecimal.valueOf(count.toDouble()))}")
                btnLess.setEnabled(true)
                if(cbAddToOrder.isChecked){OrderSingleton.addItemQuantity(item,count)}
            }
            cbAddToOrder.setOnClickListener {
                if(cbAddToOrder.isChecked){
                    OrderSingleton.addItemQuantity(item,count)
                } else if(OrderSingleton.getItems().contains(item))
                {OrderSingleton.removeItem(item)
                    count=0
                    quantity.setText("${count}")
                    btnLess.isEnabled=false}

            }

        }
    }

}