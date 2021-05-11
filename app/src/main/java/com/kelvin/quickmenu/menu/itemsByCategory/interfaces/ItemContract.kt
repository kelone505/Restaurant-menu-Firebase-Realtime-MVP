package com.kelvin.quickmenu.menu.itemsByCategory.interfaces

import com.kelvin.quickmenu.common.Callback
import com.kelvin.quickmenu.menu.itemsByCategory.model.ItemByCategory

interface ItemContract {
    interface View{
        fun showItemsByCategory(items:ArrayList<ItemByCategory>)
        fun showErrorMsg(msg:String)
    }

    interface Presenter{
        fun listItems(id:String)
        fun onViewDestroy()
    }

    interface Interactor{
        fun getAllItemsByCategory(id:String,listener:Callback):ArrayList<ItemByCategory>
    }
}