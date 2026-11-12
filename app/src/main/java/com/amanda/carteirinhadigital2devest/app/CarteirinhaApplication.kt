package com.amanda.carteirinhadigital2devest.app

import android.app.Application
import com.amanda.carteirinhadigital2devest.app.di.AppContainer
import com.amanda.carteirinhadigital2devest.app.di.DefaultAppContainer

class CarteirinhaApplication : Application() {
    val container: AppContainer by lazy {
        DefaultAppContainer()
    }
}