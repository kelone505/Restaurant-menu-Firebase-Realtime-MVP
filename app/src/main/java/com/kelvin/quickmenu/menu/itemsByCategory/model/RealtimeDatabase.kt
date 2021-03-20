package com.kelvin.quickmenu.menu.itemsByCategory.model

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseException
import com.google.firebase.database.ValueEventListener
import com.kelvin.quickmenu.common.Callback
import com.kelvin.quickmenu.firebaseRT.FirebaseRealtimeDatabaseAPI
import com.kelvin.quickmenu.order.model.OrderSingleton


class RealtimeDatabase {
    private var mRealtimeDatabase:FirebaseRealtimeDatabaseAPI
    private var list:ArrayList<ItemByCategory>

    constructor(){
        this.mRealtimeDatabase=FirebaseRealtimeDatabaseAPI
        this.list= ArrayList()
    }

    fun getAllItemsByCategory(id:Int,listener:Callback):ArrayList<ItemByCategory>{

        mRealtimeDatabase.getItemByCategory(id).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                try {
                    if (snapshot.getValue() != null) {
                        for (n in snapshot.children) {
                            val item: ItemByCategory = n.getValue(ItemByCategory::class.java) as ItemByCategory
                            //Verifying that the object is in the order list. If so, the same memory value is assigned
                            if (OrderSingleton.getItems().any { it.key.getName() == item.getName() } && item.getStatus()) {
                                var sameObject=OrderSingleton.getItems().filter { it.key.getName()==item.getName() }.map{it.key}[0]
                                sameObject.setPrice(item.getPrice())
                                list.add(sameObject)
                            } else {
                                item.setID(n.key!!.toInt())
                                if (item.getStatus() && !list.contains(item)) list.add(item)
                                else list.remove(item)
                            }

                        }
                        listener.onSuccess()
                    }
                } catch (e: DatabaseException) {
                    listener.onFailure(e.message.toString())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                listener.onFailure(error.message)
            }
        })
        return list
    }
}