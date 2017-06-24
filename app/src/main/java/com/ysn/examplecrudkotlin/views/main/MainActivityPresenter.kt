package com.ysn.examplecrudkotlin.views.main

import android.content.Context
import android.util.Log
import com.ysn.examplecrudkotlin.model.Student
import com.ysn.examplecrudkotlin.views.base.Presenter
import com.ysn.examplecrudkotlin.views.base.View
import com.ysn.examplecrudkotlin.views.helper.DBHelper
import com.ysn.examplecrudkotlin.views.main.adapter.AdapterDataStudentRecyclerView

/**
 * Created by root on 12/06/17.
 */
class MainActivityPresenter : Presenter<MainActivityView> {

    private val TAG: String? = MainActivityPresenter::class.simpleName
    private var mainActivityView: MainActivityView? = null
    private var dbHelper: DBHelper? = null

    override fun onAttach(view: View) {
        mainActivityView = view as MainActivityView
    }

    override fun onDetach() {
        mainActivityView = null
    }

    fun onLoadData(context: Context) {
        initDbHelper(context)
        val listDataStudent = dbHelper?.getAllData()
        val listenerAdapterDataStudentRecyclerView = object : AdapterDataStudentRecyclerView.ListenerAdapterDataStudentRecyclerView {
            override fun onClickDelete(student: Student) {
                // todo: onClickDelete
            }

            override fun onClickEdit(student: Student) {
                // todo: onClickEdit
            }
        }
        val adapterDataStudentRecyclerView = AdapterDataStudentRecyclerView(
                listStudents = listDataStudent,
                listenerAdapterDataStudentRecyclerView = listenerAdapterDataStudentRecyclerView
        )
        mainActivityView?.loadData(adapterDataStudentRecyclerView)
    }

    private fun initDbHelper(context: Context) {
        dbHelper = DBHelper(context)
    }
}