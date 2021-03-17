package com.kelvin.quickmenu.menu.category.model

class Category {
    companion object{
        lateinit var listCategory:ArrayList<Category>
    }

    private var _id:Int=0
    private var name:String=""
    private var image:String=""
    private var status:Boolean=false

     constructor()

   /*  constructor(pid:Int,pname:String,pimage:String,pStatus:Boolean){
        this._id=pid
        this.name=pname
        this.image=pimage
        this.status=pStatus
    }
    constructor(pname:String,pimage:String,pStatus:Boolean){
        this.name=pname
        this.image=pimage
        this.status=pStatus
    }*/

    fun setId(id:Int){this._id=id}
    fun getId():Int{return this._id}
    fun setNameCategory(name:String){this.name=name}
    fun getNameCategory():String{return this.name}
    fun setImageCategory(image:String){this.image=image}
    fun getImageCategory():String{return this.image}
    fun setStatusCat(status:Boolean){this.status=status}
    fun getStatusCat():Boolean{return this.status}
}