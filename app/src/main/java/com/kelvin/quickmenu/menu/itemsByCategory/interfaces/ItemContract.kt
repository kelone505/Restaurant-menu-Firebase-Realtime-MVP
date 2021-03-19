package com.kelvin.quickmenu.menu.itemsByCategory.interfaces

import com.kelvin.quickmenu.common.Callback
import com.kelvin.quickmenu.menu.itemsByCategory.model.ItemByCategory

interface ItemContract {
    interface View{
        fun showItemsByCategory(items:ArrayList<ItemByCategory>)
        fun showErrorMsg(msg:String)
    }
    interface ViewAdapter{

    }

    interface Presenter{
        fun listItems(id:Int)
        fun onViewDestroy()
      //  fun onViewCreated()
    }

    interface Interactor{
        fun getAllItemsByCategory(id:Int,listener:Callback):ArrayList<ItemByCategory>
    }
}