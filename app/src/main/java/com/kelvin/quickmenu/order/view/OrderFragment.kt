package com.kelvin.quickmenu.order.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.kelvin.quickmenu.R
import com.kelvin.quickmenu.common.utility
import com.kelvin.quickmenu.order.interfaces.OrderContract
import com.kelvin.quickmenu.order.model.OrderSingleton
import com.kelvin.quickmenu.order.presenter.OrderPresenterImpl

class OrderFragment : Fragment(),OrderContract.View {
    private var presenter=OrderPresenterImpl(this,OrderSingleton)
   private lateinit var tvItem:TextView
    private lateinit var tvPrice:TextView
    private lateinit var tvQuantity:TextView
    private lateinit var tvTotal:TextView
   private lateinit var tvHeaderO:TextView
   private lateinit var etSubTotal:EditText
   private lateinit var etTax:EditText
    private lateinit var etTips:EditText
    private lateinit var etTotal:EditText
    private lateinit var btnConfirm:Button
    private lateinit var pbLoadingOrder:ProgressBar
    private lateinit var cbTips:CheckBox
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_order, container, false)
        val tvTax=root.findViewById(R.id.tvTax) as TextView
        cbTips=root.findViewById<CheckBox>(R.id.cbTips)
        tvItem=root.findViewById(R.id.tvItem)
        tvQuantity=root.findViewById(R.id.tvQuantityItem)
        tvPrice=root.findViewById(R.id.tvPriceItem)
        tvTotal=root.findViewById(R.id.tvSubTotalItem)
        tvHeaderO=root.findViewById(R.id.tvDateOrder)
        etSubTotal=root.findViewById(R.id.etSubTotal)
        etTax=root.findViewById(R.id.etTax)
        etTips=root.findViewById(R.id.etTips)
        etTotal=root.findViewById(R.id.etTotal)
        btnConfirm=root.findViewById(R.id.btnConfirm)
        pbLoadingOrder=root.findViewById(R.id.pbLoadingOrder)
        presenter.onViewCreated()
        cbTips.isChecked=presenter.checkboxStatus()
        tvTax.setText("TAX ${utility.percentage.format(OrderSingleton.TAX)}:")
        cbTips.setText("TIPS ${utility.percentage.format(OrderSingleton.TIPS)}:")

        cbTips.setOnClickListener {
            allowTips()
            presenter.loadOrderInvoice()
        }
        btnConfirm.setOnClickListener {
            presenter.onClickOrderButton()
        }
        return root
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showItemselected(item: String, quantity: String, price: String, total: String) {
        tvItem.setText("Items\n${item}")
        tvQuantity.setText("Quantity\n${quantity}")
        tvPrice.setText("Price\n${price}")
        tvTotal.setText("Total\n${total}")

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

    override fun allowTips(){
        if(cbTips.isChecked) presenter.confirmTips(true)
        else presenter.confirmTips(false)
    }

    override fun showProgressDialog() {
        pbLoadingOrder.visibility=View.VISIBLE
    }


    override fun hideProgressDialog() {
      pbLoadingOrder.visibility=View.GONE
    }

    override fun orderInProcess(post: Boolean) {
        presenter.onClickOrderButton()
    }


}