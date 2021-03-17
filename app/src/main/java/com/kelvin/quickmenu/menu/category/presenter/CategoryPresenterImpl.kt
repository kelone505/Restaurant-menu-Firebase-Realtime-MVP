package com.kelvin.quickmenu.menu.category.presenter

import com.kelvin.quickmenu.common.Callback
import com.kelvin.quickmenu.menu.category.interfaces.CategoryContract
import com.kelvin.quickmenu.menu.category.model.Category

class CategoryPresenterImpl:CategoryContract.Presenter {

    var interactor:CategoryContract.Interactor
    var view:CategoryContract.View?

    constructor(pview:CategoryContract.View, pinteractor:CategoryContract.Interactor){
        this.view=pview
        this.interactor=pinteractor
    }

    override fun listCategory() {
        var list:ArrayList<Category> = ArrayList()
        list=interactor.getCategory(object  : Callback {
            override fun onSuccess() {
                if(view!=null) view!!.showCategory(list)
            }
            override fun onFailure(errorMsg: String) {
                view!!.showErrorMsg(errorMsg)
            }
        })
    }

    override fun itemselected() {
        onPause()
    }

    override fun onViewDestroy() {
        view=null;
    }

    override fun onViewCreated() {
        listCategory()
    }

    override fun onPause() {
        view==null
    }
}
