package com.kelvin.quickmenu.firebaseRT

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

 object FirebaseRealtimeDatabaseAPI {
    const val PATH_MENU="menu"
     const val PATH_CAT="category"
     const val PATH_ITEM="items"
     const val PATH_ORDER="order";

    private var firebaseRealtimeDBcon:DatabaseReference;

      init{ this.firebaseRealtimeDBcon=FirebaseDatabase.getInstance().getReference()}


     fun getRoot():DatabaseReference{return firebaseRealtimeDBcon.root}
     fun getReferenceRT():DatabaseReference{return getRoot().ref}
     fun getMenu():DatabaseReference{return getReferenceRT().child(PATH_MENU)}
     fun getMenuCategorie():DatabaseReference{return getMenu().child(PATH_CAT)}
     fun getItemByCategory(id:Int):DatabaseReference{
         return getMenuCategorie().child(id.toString()).child(PATH_ITEM)}
     fun getOrder():DatabaseReference{return getReferenceRT().child(PATH_ORDER)}

}