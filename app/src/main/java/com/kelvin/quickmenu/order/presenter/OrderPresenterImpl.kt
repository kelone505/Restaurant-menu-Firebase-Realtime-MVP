package com.kelvin.quickmenu.order.presenter

import com.kelvin.quickmenu.R
import com.kelvin.quickmenu.common.Callback
import com.kelvin.quickmenu.common.utility
import com.kelvin.quickmenu.order.interfaces.OrderContract
import com.kelvin.quickmenu.order.model.OrderSingleton
import com.kelvin.quickmenu.order.model.RealtimeDatabase

class OrderPresenterImpl:OrderContract.Presenter {
    private var mInteractor:OrderContract.Interactor
    private var mView:OrderContract.View?

    constructor(pView:OrderContract.View, pInteractor:OrderContract.Interactor){
        this.mView=pView
        this.mInteractor=pInteractor
    }

    override fun confirmTips(conf: Boolean) {
        mInteractor.setTips(conf)
    }

    override fun checkboxStatus(): Boolean {
        return mInteractor.getTips()
    }

    override fun listOfItems(){
       if(mView!=null){
           mView!!.showItemselected(mInteractor.getName(),mInteractor.getQuantity()
                   ,mInteractor.getPrice(),mInteractor.getTotalByItems())
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
        if (mView!=null && mInteractor.getItems().size!=0){
                mView!!.showDetailsAmount("${utility.currencyFormat.format(mInteractor.getSubtotal())}",
                "${utility.currencyFormat.format(mInteractor.getTaxCalc())}",
                        "${utility.currencyFormat.format(mInteractor.getTipsCalc())}",
                        "${utility.currencyFormat.format(mInteractor.getTotalCalc())}")
            }
        }

    override fun onClickOrderButton() {
      if (mInteractor.getItems().size!=0 && mView!=null){
          RealtimeDatabase().postOrder(object : Callback{
              override fun onSuccess() {
                  mView!!.showMsgConfirmation("Your order is in process!!")
                  mView!!.showProgressDialog()
              }
              override fun onFailure(errorMsg: String) {
                mView!!.showMsgConfirmation(errorMsg)
              }
          })
      } else mView!!.showMsgConfirmation("Please add items from Menu")
    }
}
