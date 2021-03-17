package com.kelvin.quickmenu.order.model

import com.kelvin.quickmenu.menu.itemsByCategory.model.ItemByCategory
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

class Order {
    private var client:String=""
    private var items:ArrayList<ItemByCategory>? = null
    constructor(pClient:String, pItem:ItemByCategory?){
        items=ArrayList()
        this.client=pClient
        addItem(pItem!!)
    }
    fun addItem(item:ItemByCategory){this.items!!.add(item)}
    fun removeItem(item:ItemByCategory){
        if(items!!.contains(item)) this.items!!.remove(item)}
}