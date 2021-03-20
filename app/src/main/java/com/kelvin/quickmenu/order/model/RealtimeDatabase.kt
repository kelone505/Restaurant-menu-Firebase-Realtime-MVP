package com.kelvin.quickmenu.order.model

import com.google.firebase.database.DatabaseException
import com.kelvin.quickmenu.common.Callback
import com.kelvin.quickmenu.common.utility
import com.kelvin.quickmenu.firebaseRT.FirebaseRealtimeDatabaseAPI
import com.kelvin.quickmenu.menu.itemsByCategory.model.ItemByCategory
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class RealtimeDatabase {
    private var mRealtimeDatabase:FirebaseRealtimeDatabaseAPI
    constructor(){
        this.mRealtimeDatabase=FirebaseRealtimeDatabaseAPI
    }

    fun postOrder(listener:Callback):Boolean{
        try{
            var list:List<ItemByCategory> = ArrayList()
            list=OrderSingleton.getItems().map {it.key}
            var orderDetail:HashMap<String,Any> = HashMap()
            var ref=mRealtimeDatabase.getOrder().child(utility.dateFormat).child(OrderSingleton.getClient())
                    .push()
            //Building the array of items that will be stored in firebase
            for(x in 0..list.size-1){
                // for each to add in hashmap all items selected in the order
                var quantity:Int=OrderSingleton.getItems().filter { it.key==list[x] }.map { it.value }[0]
                var itemOrder:HashMap<String,Any> = HashMap()
                itemOrder.put("id",list[x].getID())
                itemOrder.put("name",list[x].getName())
                itemOrder.put("price",list[x].getPrice())
                itemOrder.put("quantity", quantity)
                orderDetail.put("${x}",itemOrder)
            }
            //Detail of the amounts
            orderDetail.put("subtotal",OrderSingleton.getSubtotal())
            orderDetail.put("tax",OrderSingleton.getTaxCalc())
            orderDetail.put("tips",OrderSingleton.getTipsCalc())
            orderDetail.put("total",OrderSingleton.getTotalCalc())
            ref.setValue(orderDetail)
            listener.onSuccess()
        }catch (e:DatabaseException){
            listener.onFailure(e.message.toString())
            return false
        }

        return true
    }
}