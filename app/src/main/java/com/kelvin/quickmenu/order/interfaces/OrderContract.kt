package com.kelvin.quickmenu.order.interfaces


import com.kelvin.quickmenu.common.Callback
import com.kelvin.quickmenu.menu.itemsByCategory.model.ItemByCategory
import com.kelvin.quickmenu.order.model.OrderSingleton
import java.lang.StringBuilder

interface OrderContract {
    interface View{
        fun showItemselected(details:StringBuilder)
        fun showHeaderOrder(header:String)
        fun showDetailsAmount(subTotal:String,tax:String,tips:String,total:String)
        fun showMsgConfirmation(msg:String)
    }
    interface Presenter{
        fun listOfItems()
        fun onDestroy()
        fun onViewCreated()
        fun headerInfo()
    }
    interface Interactor{
        fun getItems(listener:Callback):ArrayList<ItemByCategory>
    }
}