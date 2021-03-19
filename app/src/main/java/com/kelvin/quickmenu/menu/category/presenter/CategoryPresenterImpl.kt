package com.kelvin.quickmenu.menu.category.presenter

import com.kelvin.quickmenu.common.Callback
import com.kelvin.quickmenu.menu.category.interfaces.CategoryContract
import com.kelvin.quickmenu.menu.category.model.Category

class CategoryPresenterImpl:CategoryContract.Presenter {

    var interactor:CategoryContract.Interactor
    var view:CategoryContract.View?

    constructor(pView:CategoryContract.View, pInteractor:CategoryContract.Interactor){
        this.view=pView
        this.interactor=pInteractor
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
    override fun onViewDestroy() {
        view=null;
    }
    override fun onViewCreated() {
        listCategory()
    }


}
