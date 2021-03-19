package com.kelvin.quickmenu.menu.itemsByCategory.model

import com.kelvin.quickmenu.common.utility
import java.math.BigDecimal

class ItemByCategory {
    companion object{
        const val MIN_PRICE:Double=0.01
    }

    private var _id:Int=0
    private var name:String=""
    private var image:String=""
    private var description:String=""
    private var price:Double= MIN_PRICE
    private var available:Int=0
    private var status:Boolean=false

    constructor(){}

    fun setID(id:Int){this._id=id}
    fun getID():Int{return this._id}
    fun setName(name:String){this.name=name}
    fun getName():String{return this.name}
    fun setImage(image:String){this.image=image}
    fun getImage():String{return this.image}
    fun setDescription(desc:String){this.description=desc}
    fun getDescription():String{return this.description}
    fun setPrice(price:Double){
        if(price<=0.0) this.price= MIN_PRICE
        else this.price=price}
    fun getPrice():Double{
        return this.price}
    fun setAvailable(available:Int){this.available=available}
    fun getAvailable():Int{return this.available}
    fun setStatus(status:Boolean){this.status=status}
    fun getStatus():Boolean{return this.status}
}