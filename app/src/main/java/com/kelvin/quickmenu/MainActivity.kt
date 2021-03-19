package com.kelvin.quickmenu

import android.os.Bundle
import android.view.View
import android.widget.Toast

import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.kelvin.quickmenu.common.utility
import com.kelvin.quickmenu.menu.category.view.CategoryFragment
import com.kelvin.quickmenu.menu.itemsByCategory.view.ItemsByCategoryFragment
import com.kelvin.quickmenu.order.view.OrderFragment

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var manager: FragmentManager
        val categoryFragment = CategoryFragment()
        var itemByCategory = ItemsByCategoryFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(1)
        setTheme(R.style.Theme_QuickMenu)
        Toast.makeText(this@MainActivity, utility.date.toString(), Toast.LENGTH_SHORT).show()
        manager = supportFragmentManager
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navFragCat: FragmentContainerView = findViewById(R.id.nav_host_fragment)
        val navFragOrder: FragmentContainerView = findViewById(R.id.nav_host_fragment_order)
          val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_menu, R.id.navigation_order))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setOnNavigationItemSelectedListener { item ->
            var orderFragment = OrderFragment()
            when (item.itemId) {
                R.id.navigation_menu -> {
                    navFragCat.visibility = View.VISIBLE
                    navFragOrder.visibility = View.GONE
                    manager.beginTransaction().show(categoryFragment).commit()
                    orderFragment.onDestroy()
                }
                R.id.navigation_order -> {
                    navFragOrder.visibility = View.VISIBLE
                    navFragCat.visibility = View.GONE
                    manager.beginTransaction().hide(categoryFragment)
                            .replace(navFragOrder.id, orderFragment).show(orderFragment).commit()
                }
            }
            true
        }
    }

}



