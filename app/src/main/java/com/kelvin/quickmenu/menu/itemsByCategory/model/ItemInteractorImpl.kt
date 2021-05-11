package com.kelvin.quickmenu.menu.itemsByCategory.model

import com.kelvin.quickmenu.common.Callback
import com.kelvin.quickmenu.menu.itemsByCategory.interfaces.ItemContract

class ItemInteractorImpl:ItemContract.Interactor {
    private var realtimeDatabase:RealtimeDatabase
    constructor(){this.realtimeDatabase= RealtimeDatabase()
    }
    override fun getAllItemsByCategory(id:String,listener: Callback): ArrayList<ItemByCategory> {
        var listItem:ArrayList<ItemByCategory> = ArrayList()
        listItem=realtimeDatabase.getAllItemsByCategory(id, object : Callback{
            override fun onSuccess() {
                listener.onSuccess()
            }
            override fun onFailure(errorMsg: String) {
                listener.onFailure(errorMsg)
            }
        })
        return listItem
    }
}