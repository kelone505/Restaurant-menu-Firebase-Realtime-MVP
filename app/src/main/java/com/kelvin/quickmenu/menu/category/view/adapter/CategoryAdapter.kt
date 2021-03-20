package com.kelvin.quickmenu.menu.category.view.adapter

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.kelvin.quickmenu.MainActivity
import com.kelvin.quickmenu.R
import com.kelvin.quickmenu.firebaseRT.FirebaseRealtimeDatabaseAPI
import com.kelvin.quickmenu.menu.category.model.Category
import com.kelvin.quickmenu.menu.itemsByCategory.view.ItemsByCategoryFragment


class CategoryAdapter(private val getContext: Context, private val customlayoutid: Int,
                      private var list: ArrayList<Category>)
    : ArrayAdapter<Category>(getContext, customlayoutid, list){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var inflater = (getContext as Activity).layoutInflater
        var rowView = inflater!!.inflate(customlayoutid, parent, false)
        var txtName = rowView.findViewById(R.id.tvName) as TextView
        var img = rowView.findViewById(R.id.ivItemMenuCat) as ImageView
        txtName!!.setText(list[position].getName())

        Glide.with(getContext)
            .load(list[position].getImage())
            .centerCrop()
            .error(android.R.drawable.ic_menu_report_image)
            .into(img)

        rowView.setOnClickListener {
            val bundle= Bundle()
             MainActivity.itemByCategory= ItemsByCategoryFragment()
            bundle.putInt(FirebaseRealtimeDatabaseAPI.PATH_CAT,list[position].getId())
            MainActivity.itemByCategory.arguments=bundle

            MainActivity.manager.beginTransaction().replace(R.id.nav_host_fragment,MainActivity.itemByCategory)
                    .hide(MainActivity.categoryFragment)
                    .addToBackStack(null).commit()
        }
        return rowView
    }

}
