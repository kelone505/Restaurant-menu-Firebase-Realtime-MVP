package com.kelvin.quickmenu.order.interfaces


import com.kelvin.quickmenu.common.Callback
import com.kelvin.quickmenu.menu.itemsByCategory.model.ItemByCategory
import java.lang.StringBuilder

interface OrderContract {
    interface View{
        fun showItemselected(item:String,quantity:String,price:String,total:String)
        fun showHeaderOrder(header:String)
        fun showDetailsAmount(subTotal:String,tax:String,tips:String,total:String)
        fun showMsgConfirmation(msg:String)
        fun allowTips()
        fun showProgressDialog()
        fun hideProgressDialog()
        fun orderInProcess(post:Boolean)
    }
    interface Presenter{
        fun confirmTips(conf:Boolean)
        fun checkboxStatus():Boolean
        fun listOfItems()
        fun onDestroy()
        fun onViewCreated()
        fun onResume()
        fun headerInfo()
        fun loadOrderInvoice()
        fun onClickOrderButton()
    }
    interface Interactor{
        fun addItemQuantity(item:ItemByCategory,quantity:Int)
        fun removeItem(item:ItemByCategory)
        fun getItems():HashMap<ItemByCategory,Int>
        fun getClient():String
        fun setTips(confirm:Boolean)
        fun getTips():Boolean
        fun getTotalCalc():Double
        fun getTaxCalc():Double
        fun getTipsCalc():Double
        fun getSubtotal():Double
        fun getName():String
        fun getQuantity():String
        fun getPrice():String
        fun getTotalByItems():String
        fun postOrder(listener:Callback):Boolean
    }
}