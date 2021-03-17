package com.kelvin.quickmenu.menu.category.interfaces

import com.kelvin.quickmenu.common.Callback
import com.kelvin.quickmenu.menu.category.model.Category

interface CategoryContract {
    interface View{
        fun showCategory(list:ArrayList<Category>)
        fun showErrorMsg(msg:String)
    }
    interface Presenter{
        fun listCategory()
        fun itemselected()
        fun onViewDestroy()
        fun onViewCreated()
        fun onPause()
    }
    interface Interactor{
        fun getCategory(listener:Callback):ArrayList<Category> //
    }
}