package com.kelvin.quickmenu.menu.category.model

import com.kelvin.quickmenu.common.Callback
import com.kelvin.quickmenu.menu.category.interfaces.CategoryContract

class CategoryInteractorImpl:CategoryContract.Interactor {
    private var realtimeDatabase:RealtimeDatabase
    constructor(){
        this.realtimeDatabase= RealtimeDatabase()
    }


    override fun getCategory(listener:Callback): ArrayList<Category> {
        var list:ArrayList<Category>
        list=realtimeDatabase.getAllCategory(object : Callback{
            override fun onSuccess() {
                listener.onSuccess()
            }
            override fun onFailure(errorMsg: String) {
                listener.onFailure(errorMsg)
            }
        })

        return list

    }


}
