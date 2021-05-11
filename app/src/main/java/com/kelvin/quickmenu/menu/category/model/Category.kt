package com.kelvin.quickmenu.menu.category.model

class Category {
    private var _id:String=""
    private var name:String=""
    private var image:String=""
    private var status:Boolean=false

    constructor(){}

    fun setId(id:String){this._id=id}
    fun getId():String{return this._id}
    fun setName(name:String){this.name=name}
    fun getName():String{return this.name}
    fun setImage(image:String){this.image=image}
    fun getImage():String{return this.image}
    fun setStatus(status:Boolean){this.status=status}
    fun getStatus():Boolean{return this.status}
}