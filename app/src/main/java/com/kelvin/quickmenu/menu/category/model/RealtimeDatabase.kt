package com.kelvin.quickmenu.menu.category.model


import com.google.firebase.database.*
import com.kelvin.quickmenu.common.Callback
import com.kelvin.quickmenu.firebaseRT.FirebaseRealtimeDatabaseAPI


class RealtimeDatabase {
    private var mDatabaseApi:FirebaseRealtimeDatabaseAPI

    constructor() {
        this.mDatabaseApi=FirebaseRealtimeDatabaseAPI
        Category.listCategory= ArrayList()
    }

    fun getAllCategory(listener: Callback):ArrayList<Category>{

        mDatabaseApi.getMenuCategorie().addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                Category.listCategory.clear()
                        try {
                            if (snapshot.getValue()!=null) {
                                for (n in snapshot.children) {
                                    val cat:Category = n.getValue(Category::class.java) as Category
                                     if (cat.getImageCategory().isNullOrBlank() || cat.getImageCategory().isNullOrBlank()) {
                                         cat.setId(n.key!!.toInt())
                                         cat.setNameCategory(n.child("name").getValue(String::class.java).toString())
                                         cat.setImageCategory(n.child("image").getValue(String::class.java).toString())
                                         cat.setStatusCat(n.child("status").getValue(Boolean::class.java) as Boolean)
                                     }
                                    if (cat.getStatusCat() && !Category.listCategory.contains(cat)) Category.listCategory.add(cat)
                                    else Category.listCategory.remove(cat)
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


        return Category.listCategory
    }
}