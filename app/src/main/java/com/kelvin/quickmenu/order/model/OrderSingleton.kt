package com.kelvin.quickmenu.order.model

import com.jaredrummler.android.device.DeviceName
import com.kelvin.quickmenu.common.Callback
import com.kelvin.quickmenu.menu.itemsByCategory.model.ItemByCategory
import com.kelvin.quickmenu.order.interfaces.OrderContract

object OrderSingleton {
    const val TAX:Double=0.15
    const val TIPS:Double=0.10

    private var client:String=""
    private var items:ArrayList<ItemByCategory>
    init{
        this.client= DeviceName.getDeviceName()
        this.items= ArrayList()
    }
    fun addItem(item: ItemByCategory){

        if(item!=null)
        {this.items.add(item)

        }}
    fun removeItem(item: ItemByCategory){
        if(items.contains(item)) this.items.remove(item)}
    fun getArrayItems():ArrayList<ItemByCategory>{
         return this.items}
    fun getClient():String{return this.client}

}
