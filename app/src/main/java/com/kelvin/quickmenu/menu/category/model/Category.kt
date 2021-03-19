package com.kelvin.quickmenu.menu.category.model

class Category {
    private var _id:Int=0
    private var name:String=""
    private var image:String=""
    private var status:Boolean=false

    constructor(){}

    fun setId(id:Int){this._id=id}
    fun getId():Int{return this._id}
    fun setNameCategory(name:String){this.name=name}
    fun getNameCategory():String{return this.name}
    fun setImageCategory(image:String){this.image=image}
    fun getImageCategory():String{return this.image}
    fun setStatusCat(status:Boolean){this.status=status}
    fun getStatusCat():Boolean{return this.status}
}