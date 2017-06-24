package com.ysn.examplecrudkotlin.views.main

import com.ysn.examplecrudkotlin.views.base.View
import com.ysn.examplecrudkotlin.views.main.adapter.AdapterDataStudentRecyclerView

/**
 * Created by root on 12/06/17.
 */
interface MainActivityView : View {

    fun loadData(adapterDataStudentRecyclerView: AdapterDataStudentRecyclerView)

    fun deleteAllData()

    fun deleteAllDataFailed()

}