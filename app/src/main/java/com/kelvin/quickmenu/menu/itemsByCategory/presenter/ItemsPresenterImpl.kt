package com.kelvin.quickmenu.menu.itemsByCategory.presenter

import android.widget.Toast
import com.kelvin.quickmenu.common.Callback
import com.kelvin.quickmenu.menu.itemsByCategory.interfaces.ItemContract
import com.kelvin.quickmenu.menu.itemsByCategory.model.ItemByCategory
import com.kelvin.quickmenu.order.model.OrderSingleton

class ItemsPresenterImpl:ItemContract.Presenter {
    private var mView:ItemContract.View?
    private var mInteractor:ItemContract.Interactor
    constructor(pView:ItemContract.View,pInteractor:ItemContract.Interactor){
        this.mView=pView
        this.mInteractor=pInteractor
    }

    override fun listItems(id:Int) {
        var listItem:ArrayList<ItemByCategory> = ArrayList()
        listItem=mInteractor.getAllItemsByCategory(id, object : Callback{
            override fun onSuccess() {
                if(mView!=null) mView!!.showItemsByCategory(listItem)
            }
            override fun onFailure(errorMsg: String) {
                mView!!.showErrorMsg(errorMsg)
            }
        })
    }

    override fun onViewDestroy() {
        mView=null
    }

  /*  override fun onViewCreated() {
        listItems()
    }*/


}