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
        fun disableUIElement()
    }
    interface Presenter{
        fun confirmTips(conf:Boolean)
        fun checkboxStatus():Boolean
        fun listOfItems()
        fun onDestroy()
        fun onViewCreated()
        fun headerInfo()
        fun loadOrderInvoice()
        fun onClickOrderButton()
    }

}