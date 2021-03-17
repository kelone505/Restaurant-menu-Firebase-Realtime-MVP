package com.kelvin.quickmenu.order.presenter

import com.kelvin.quickmenu.common.Callback
import com.kelvin.quickmenu.menu.itemsByCategory.model.ItemByCategory
import com.kelvin.quickmenu.order.interfaces.OrderContract
import com.kelvin.quickmenu.order.model.Order
import com.kelvin.quickmenu.order.model.OrderSingleton
import java.lang.StringBuilder

class OrderPresenterImpl:OrderContract.Presenter {
    private var mInteractor:OrderContract.Interactor
    private var mView:OrderContract.View?

    constructor(pView:OrderContract.View, pInteractor:OrderContract.Interactor){
        this.mView=pView
        this.mInteractor=pInteractor
    }

    override fun listOfItems() {
        var detail:StringBuilder=StringBuilder()
        var list:ArrayList<ItemByCategory> = ArrayList()
            list=mInteractor.getItems(object :Callback{
            override fun onSuccess() {
                   for(x in list){
                       detail.append(x.getName() + "\n")
                   }
                    mView!!.showItemselected(detail)
            }
            override fun onFailure(errorMsg: String) {
                mView!!.showMsgConfirmation(errorMsg)
            }
        })

    }

    override fun onDestroy() {
        mView=null!!
    }

    override fun onViewCreated() {
        headerInfo()
        listOfItems()
    }

    override fun headerInfo() {
        mView!!.showHeaderOrder(OrderSingleton.getClient())
    }
}