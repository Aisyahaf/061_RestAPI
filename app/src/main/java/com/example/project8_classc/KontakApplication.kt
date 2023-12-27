package com.example.project8_classc

import android.app.Application
import com.example.project8_classc.repository.AppContainer
import com.example.project8_classc.repository.KontakContainer

class KontakApplication : Application(){
    lateinit var container: AppContainer
    override fun onCreate(){
        super.onCreate()
        container = KontakContainer()
    }
}