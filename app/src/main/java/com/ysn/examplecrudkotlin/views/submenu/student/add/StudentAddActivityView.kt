package com.ysn.examplecrudkotlin.views.submenu.student.add

import com.ysn.examplecrudkotlin.views.base.View

/**
 * Created by root on 24/06/17.
 */
interface StudentAddActivityView : View {

    fun saveInvalid(message: String)

    fun save()

    fun saveFailed()

}