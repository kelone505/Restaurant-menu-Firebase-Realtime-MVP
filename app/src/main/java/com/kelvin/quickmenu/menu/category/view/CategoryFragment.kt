package com.kelvin.quickmenu.menu.category.view

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.kelvin.quickmenu.R
import com.kelvin.quickmenu.common.Callback
import com.kelvin.quickmenu.menu.category.interfaces.CategoryContract
import com.kelvin.quickmenu.menu.category.model.Category
import com.kelvin.quickmenu.menu.category.model.CategoryInteractorImpl
import com.kelvin.quickmenu.menu.category.presenter.CategoryPresenterImpl
import com.kelvin.quickmenu.menu.category.view.adapter.CategoryAdapter


class CategoryFragment : Fragment(),CategoryContract.View {
    var presenter:CategoryContract.Presenter =CategoryPresenterImpl(this,CategoryInteractorImpl())
    lateinit var adapter:CategoryAdapter
    lateinit var gridView:GridView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root=inflater.inflate(R.layout.fragment_category, container, false)
        gridView=root.findViewById(R.id.gvCategoryMenu)
        presenter.onViewCreated()
        return root
    }

    override fun showCategory(list: ArrayList<Category>) {
        adapter = CategoryAdapter(requireContext(), R.layout.gv_category_menu,list)
        gridView!!.adapter=adapter
        adapter.notifyDataSetChanged()
        }

    override fun onDestroy() {
        presenter.onViewDestroy()
        super.onDestroy()
    }

    override fun showErrorMsg(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }

}