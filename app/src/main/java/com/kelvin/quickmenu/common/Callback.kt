package com.kelvin.quickmenu.common

interface Callback {
    fun onSuccess()
    fun onFailure(errorMsg:String)
}