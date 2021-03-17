package com.kelvin.quickmenu.order.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kelvin.quickmenu.R
import com.kelvin.quickmenu.common.utility
import com.kelvin.quickmenu.menu.itemsByCategory.model.ItemByCategory
import com.kelvin.quickmenu.order.interfaces.OrderContract
import com.kelvin.quickmenu.order.model.OrderInteractorImpl
import com.kelvin.quickmenu.order.model.OrderSingleton
import com.kelvin.quickmenu.order.presenter.OrderPresenterImpl
import java.lang.StringBuilder

class OrderFragment : Fragment(),OrderContract.View {
    private var presenter=OrderPresenterImpl(this,OrderInteractorImpl())
   private lateinit var etDetailO:EditText
   private lateinit var tvHeaderO:TextView
   private lateinit var etSubTotal:EditText
   private lateinit var etTax:EditText
    private lateinit var etTips:EditText
    private lateinit var etTotal:EditText
    private lateinit var btnConfirm:Button
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_order, container, false)
       // val textView: TextView = root.findViewById(R.id.text_dashboard)
        etDetailO=root.findViewById(R.id.etDetailOrder)
        tvHeaderO=root.findViewById(R.id.tvDateOrder)
        etSubTotal=root.findViewById(R.id.etSubTotal)
        etTax=root.findViewById(R.id.etTax)
        etTips=root.findViewById(R.id.etTips)
      //  etTotal=root.findViewById(R.id.etTotal)
        btnConfirm=root.findViewById(R.id.btnConfirm)
        btnConfirm.setOnClickListener {
            var s:String=""
            for(x in OrderSingleton.getArrayItems()){
                s=s + x.getName() + "\n"
                etDetailO.setText(s)
            }
        }
        return root
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun onResume() {
        var s:String=""
        for(x in OrderSingleton.getArrayItems()){
            s=s + x.getName() + "\n"
            etDetailO.setText(s)
        }
        super.onResume()
    }

    override fun onPause() {
        var s:String=""
        for(x in OrderSingleton.getArrayItems()){
            s=s + x.getName() + "\n"
            etDetailO.setText(s)
        }
        super.onPause()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        etTotal=view.findViewById(R.id.etTotal)
       // presenter.onViewCreated()

        super.onViewCreated(view, savedInstanceState)
    }

    override fun showItemselected(details: StringBuilder) {
        etDetailO.setText("")
        etDetailO.append(details)
        Toast.makeText(requireContext(), OrderSingleton.getArrayItems().size.toString(), Toast.LENGTH_SHORT).show()
    }




    override fun showHeaderOrder(header: String) {
        tvHeaderO.setText("Client: "+header)
    }

    override fun showDetailsAmount(subTotal: String, tax: String, tips: String, total: String) {
        etSubTotal.setText(subTotal)
        etTax.setText(tax)
        etTips.setText(tips)
        etTotal.setText(total)
    }


    override fun showMsgConfirmation(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }
}