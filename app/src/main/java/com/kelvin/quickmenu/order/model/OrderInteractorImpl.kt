package com.kelvin.quickmenu.order.model

import com.kelvin.quickmenu.MainActivity
import com.kelvin.quickmenu.common.Callback
import com.kelvin.quickmenu.menu.itemsByCategory.model.ItemByCategory
import com.kelvin.quickmenu.order.interfaces.OrderContract
import kotlin.contracts.CallsInPlace

class OrderInteractorImpl:OrderContract.Interactor {
    private var arrList:ArrayList<ItemByCategory>
    constructor(){this.arrList= ArrayList()
    }

    override fun getItems(listener:Callback):ArrayList<ItemByCategory> {
        if(OrderSingleton.getArrayItems().size!=0){
            listener.onSuccess()
        }else listener.onFailure("Please, add items from Menu")
        return OrderSingleton.getArrayItems()
    }


}