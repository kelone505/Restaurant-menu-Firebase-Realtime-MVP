package com.kelvin.quickmenu.menu.itemsByCategory.model

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseException
import com.google.firebase.database.ValueEventListener
import com.kelvin.quickmenu.common.Callback
import com.kelvin.quickmenu.firebaseRT.FirebaseRealtimeDatabaseAPI
import java.math.BigDecimal

class RealtimeDatabase {
    private var mRealtimeDatabase:FirebaseRealtimeDatabaseAPI
    private var list:ArrayList<ItemByCategory>

    constructor(){
        this.mRealtimeDatabase=FirebaseRealtimeDatabaseAPI
        this.list= ArrayList()
    }

    fun getAllItemsByCategory(id:Int,listener:Callback):ArrayList<ItemByCategory>{

        mRealtimeDatabase.getItemByCategory(id).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                try {
                    if(snapshot.getValue()!=null){
                        for(n in snapshot.children){
                            val item:ItemByCategory=n.getValue(ItemByCategory::class.java) as ItemByCategory
                            item.setID(n.key!!.toInt())
                            if(item.getPrice() <= BigDecimal.valueOf(0.0))item.setPrice(ItemByCategory.MIN_PRICE)
                           /* if(item.getName().isNullOrBlank() || item.getImage().isNullOrBlank() || item.getPrice()!=0.0)
                            {
                                item.setID(n.key!!.toInt())
                                item.setName(n.child("name").getValue(String::class.java).toString())
                                item.setImage(n.child("image").getValue(String::class.java).toString())
                                item.setDescription(n.child("description").getValue(String::class.java).toString())
                                item.setPrice(n.child("price").getValue(Double::class.java) as Double)
                                item.setAvailable(n.child("available").getValue(Int::class.java) as Int)
                                item.setStatus(n.child("status").getValue(Boolean::class.java) as Boolean)
                            }*/
                            if(item.getStatus() && !list.contains(item)) list.add(item)
                            else list.remove(item)
                        }
                        listener.onSuccess()
                    }

                }catch (e:DatabaseException){
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