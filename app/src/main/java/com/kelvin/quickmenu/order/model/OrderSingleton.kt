package com.kelvin.quickmenu.order.model


import com.jaredrummler.android.device.DeviceName
import com.kelvin.quickmenu.common.Callback
import com.kelvin.quickmenu.common.utility
import com.kelvin.quickmenu.menu.itemsByCategory.model.ItemByCategory
import com.kelvin.quickmenu.order.interfaces.OrderContract
import java.math.BigDecimal
import kotlin.collections.ArrayList

object OrderSingleton {
    const val TAX: Double = 0.15
    const val TIPS: Double = 0.10

    private var client: String = ""
    private var itemQuantity: HashMap<ItemByCategory, Int>
    private var tips: Boolean = true

    init {
        this.client = DeviceName.getDeviceName()
        this.itemQuantity = HashMap()
    }

     fun addItemQuantity(item: ItemByCategory, quantity: Int) {
        this.itemQuantity.put(item, quantity)
    }

     fun removeItem(item: ItemByCategory) {
        if (itemQuantity!!.contains(item)) this.itemQuantity!!.remove(item)
    }

     fun getItems(): HashMap<ItemByCategory, Int> {
        return this.itemQuantity
    }

     fun getClient(): String {
        return if (!this.client.isNullOrBlank()) this.client else ""
    }

     fun setTips(confirm: Boolean) {
        this.tips = confirm
    }

     fun getTips(): Boolean {
        return this.tips
    }

     fun getTotalCalc(): Double {
        return getSubtotal() + getTaxCalc() + getTipsCalc()
    }

     fun getTaxCalc(): Double {
        return getSubtotal() * TAX
    }

     fun getTipsCalc(): Double {
        return if (this.tips) getSubtotal() * TIPS else 0.0
    }

     fun getSubtotal(): Double {
        var subTotal = 0.0
        for (x in getItems()) {
            subTotal += (x.key.getPrice() * x.value.toDouble())
        }
        return subTotal
    }

     fun getName(): String {
        var s = "...................."
        var itemList = ""
        for (x in getItems()) {
            itemList += "${
                if (x.key.getName().length < 20)
                    x.key.getName().plus(s).substring(0, 20) else x.key.getName().substring(0, 20)
            }\n"
        }
        return itemList
    }

     fun getQuantity(): String {
        var quantityList = ""
        for (x in getItems()) {
            quantityList += "${x.value}\n"
        }
        return quantityList
    }

     fun getPrice(): String {
        var priceList = ""
        for (x in getItems()) {
            priceList += "${utility.currencyFormat.format(x.key.getPrice())}\n"
        }
        return priceList
    }

     fun getTotalByItems(): String {
        var totalList = ""
        for (x in getItems()) {
            totalList += "${utility.currencyFormat.format(x.key.getPrice() * x.value.toDouble())}\n"
        }
        return totalList

    }

     fun postOrder(listener: Callback): Boolean {
        var post: Boolean = RealtimeDatabase().postOrder(object : Callback {
            override fun onSuccess() {
                listener.onSuccess()
            }
            override fun onFailure(errorMsg: String) {
                listener.onFailure(errorMsg)
            }
        })
        return post
    }
}


