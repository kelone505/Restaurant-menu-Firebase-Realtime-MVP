package com.kelvin.quickmenu.order.presenter

import com.kelvin.quickmenu.common.Callback
import com.kelvin.quickmenu.common.utility
import com.kelvin.quickmenu.menu.itemsByCategory.model.ItemByCategory
import com.kelvin.quickmenu.order.interfaces.OrderContract
import com.kelvin.quickmenu.order.model.Order
import com.kelvin.quickmenu.order.model.OrderSingleton
import java.lang.StringBuilder
import java.math.BigDecimal

class OrderPresenterImpl:OrderContract.Presenter {
    private var mInteractor:OrderContract.Interactor
    private var mView:OrderContract.View?

    constructor(pView:OrderContract.View, pInteractor:OrderContract.Interactor){
        this.mView=pView
        this.mInteractor=pInteractor
    }

    override fun confirmTips(conf: Boolean) {
        OrderSingleton.setTips(conf)
    }

    override fun checkboxStatus(): Boolean {
        return OrderSingleton.getTips()
    }

    override fun listOfItems(){
      //  mView!!.showProgressDialog()
        var v=OrderSingleton
       if(mView!=null){
           mView!!.showItemselected(v.getName(),v.getQuantity(),v.getPrice(),v.getTotalByItems())
       }
    }

    override fun onDestroy() {
        mView=null
    }

    override fun onViewCreated() {
        headerInfo()
        listOfItems()
        loadOrderInvoice()

    }

    override fun onResume() {
        Thread.sleep(1000)
        mView!!.hideProgressDialog()
    }

    override fun headerInfo() {
        mView!!.showHeaderOrder(mInteractor.getClient())
    }

    override fun loadOrderInvoice() {
        var v=OrderSingleton
        if (mView!=null && v.getItems().size!=0){
                mView!!.showDetailsAmount("${utility.currencyFormat.format(v.getSubtotal())}",
                "${utility.currencyFormat.format(v.getTaxCalc())}",
                        "${utility.currencyFormat.format(v.getTipsCalc())}",
                        "${utility.currencyFormat.format(v.getTotalCalc())}")
            }
        }

    }
