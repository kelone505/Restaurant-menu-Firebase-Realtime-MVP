package com.kelvin.quickmenu.menu.itemsByCategory.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kelvin.quickmenu.R
import com.kelvin.quickmenu.firebaseRT.FirebaseRealtimeDatabaseAPI
import com.kelvin.quickmenu.menu.itemsByCategory.interfaces.ItemContract
import com.kelvin.quickmenu.menu.itemsByCategory.model.ItemByCategory
import com.kelvin.quickmenu.menu.itemsByCategory.model.ItemInteractorImpl
import com.kelvin.quickmenu.menu.itemsByCategory.presenter.ItemsPresenterImpl
import com.kelvin.quickmenu.menu.itemsByCategory.view.adapter.ItemByCategoryAdapter


class ItemsByCategoryFragment : Fragment(),ItemContract.View {
    var mPresenter:ItemsPresenterImpl= ItemsPresenterImpl(this,ItemInteractorImpl())
    var id:Int?=0
    lateinit var adapter:ItemByCategoryAdapter
    lateinit var layoutManager:RecyclerView.LayoutManager
    lateinit var rvItem:RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root=inflater.inflate(R.layout.fragment_items_by_category, container, false)
        id=arguments?.getInt(FirebaseRealtimeDatabaseAPI.PATH_CAT)!!
        rvItem=root.findViewById(R.id.rvItemsByCategory)
        mPresenter.listItems(id!!)
        return root
    }

    override fun showItemsByCategory(items: ArrayList<ItemByCategory>) {
        adapter= ItemByCategoryAdapter(items,requireContext())
        layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        rvItem.adapter=adapter
        rvItem.layoutManager=layoutManager
        adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        mPresenter.onViewDestroy()
        super.onDestroy()
    }
    override fun showErrorMsg(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }


}