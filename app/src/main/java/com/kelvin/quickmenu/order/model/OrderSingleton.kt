package com.kelvin.quickmenu.order.model


import com.jaredrummler.android.device.DeviceName
import com.kelvin.quickmenu.common.utility
import com.kelvin.quickmenu.menu.itemsByCategory.model.ItemByCategory
import com.kelvin.quickmenu.order.interfaces.OrderContract
import java.math.BigDecimal
import kotlin.collections.ArrayList

object OrderSingleton:OrderContract.Interactor {
    const val TAX: Double = 0.15
    const val TIPS: Double = 0.10

    private var client: String = ""
    private var itemQuantity:HashMap<ItemByCategory,Int>
    private var tips:Boolean=true

    init {
        this.client = DeviceName.getDeviceName()
        this.itemQuantity= HashMap()
    }

    override  fun addItemQuantity(item:ItemByCategory,quantity:Int){
        this.itemQuantity.put(item,quantity)
    }
    fun removeItem(item:ItemByCategory){
        if(itemQuantity!!.contains(item)) this.itemQuantity!!.remove(item)}
    override fun getItems(): HashMap<ItemByCategory, Int> {
        return this.itemQuantity
    }
    override fun getClient(): String {
        return if(!this.client.isNullOrBlank()) this.client else ""
    }
    override fun setTips(confirm:Boolean){this.tips=confirm}
    override fun getTips():Boolean{return this.tips}
    override fun getTotalCalc(): Double {
        return getSubtotal()+ getTaxCalc()+ getTipsCalc()
    }

    override fun getTaxCalc(): Double {
        return getSubtotal() * TAX
    }

    override fun getTipsCalc(): Double {
        return if(this.tips) getSubtotal() * TIPS else 0.0
    }

    override fun getSubtotal(): Double {
        var subTotal=0.0
        for (x in getItems()){subTotal+=(x.key.getPrice() * BigDecimal(x.value.toDouble())).toDouble()}
        return subTotal
    }

    override fun getName(): String {
        var s="...................."
        var itemList=""
        for(x in getItems()){itemList +="${if (x.key.getName().length < 20)
            x.key.getName().plus(s).substring(0, 20) else x.key.getName().substring(0, 20)}\n"}
        return itemList
    }

    override fun getQuantity(): String {
        var quantityList=""
        for(x in getItems()){ quantityList+="${x.value}\n"}
        return quantityList
    }

    override fun getPrice(): String {
        var priceList=""
        for(x in getItems()){ priceList+="${utility.currencyFormat.format(x.key.getPrice())}\n"}
        return priceList
    }

    override fun getTotalByItems(): String {
        var totalList=""
        for(x in getItems()){totalList+="${utility.currencyFormat.format(x.key.getPrice() * BigDecimal(x.value.toDouble()))}\n"}
        return totalList

    }


}
