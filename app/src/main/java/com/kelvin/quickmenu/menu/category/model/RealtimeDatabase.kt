package com.kelvin.quickmenu.menu.category.model


import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseException
import com.google.firebase.database.ValueEventListener
import com.kelvin.quickmenu.common.Callback
import com.kelvin.quickmenu.firebaseRT.FirebaseRealtimeDatabaseAPI


class RealtimeDatabase {
    private var mDatabaseApi:FirebaseRealtimeDatabaseAPI
    private var list:ArrayList<Category>

    constructor() {
        this.mDatabaseApi=FirebaseRealtimeDatabaseAPI
        this.list= ArrayList()
    }

    fun getAllCategory(listener: Callback):ArrayList<Category>{
        mDatabaseApi.getMenuCategorie().addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                        try {
                            if (snapshot.getValue()!=null) {
                                for (n in snapshot.children) {
                                    var cat=n.getValue(Category::class.java) as Category
                                         cat.setId(n.key!!.toInt())
                                    if (cat.getStatus() && !list.contains(cat)) list.add(cat)
                                    else list.remove(cat)
                                }
                                listener.onSuccess()
                            }
                        }catch (e: DatabaseException){
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