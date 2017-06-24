package com.ysn.examplecrudkotlin.views.main

import android.content.Context
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
    private lateinit var context: Context
    private lateinit var adapterDataStudentRecyclerView: AdapterDataStudentRecyclerView

    override fun onAttach(view: View) {
        mainActivityView = view as MainActivityView
    }

    override fun onDetach() {
        mainActivityView = null
    }

    fun onLoadData(context: Context) {
        this.context = context
        initDbHelper(context)
        val listDataStudent = dbHelper?.getAllData()
        val listenerAdapterDataStudentRecyclerView = object : AdapterDataStudentRecyclerView.ListenerAdapterDataStudentRecyclerView {
            override fun onClickDelete(student: Student) {
                mainActivityView?.clickDelete(student)
            }

            override fun onClickEdit(student: Student) {
                // todo: onClickEdit
                mainActivityView?.clickEdit(student)
            }
        }
        adapterDataStudentRecyclerView = AdapterDataStudentRecyclerView(
                listStudents = listDataStudent,
                listenerAdapterDataStudentRecyclerView = listenerAdapterDataStudentRecyclerView
        )
        mainActivityView?.loadData(adapterDataStudentRecyclerView)
    }

    private fun initDbHelper(context: Context) {
        if (dbHelper == null) {
            dbHelper = DBHelper(context)
        }
    }

    fun onDeleteAllData() {
        initDbHelper(context = context)
        val execDeleteAll = dbHelper?.deleteAllData() as Boolean
        if (execDeleteAll) {
            adapterDataStudentRecyclerView.clearData()
            mainActivityView?.deleteAllData()
        } else {
            mainActivityView?.deleteAllDataFailed()
        }
    }

    fun  onDeleteItem(student: Student) {
        initDbHelper(context = context)
        val execDeleteItem = dbHelper?.deleteData(student.nim) as Boolean
        if (execDeleteItem) {
            adapterDataStudentRecyclerView.removeItem(student)
            mainActivityView?.deleteItem()
        } else {
            mainActivityView?.deleteItemFailed()
        }
    }

}