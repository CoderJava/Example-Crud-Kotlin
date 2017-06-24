package com.ysn.examplecrudkotlin.views.submenu.student.edit

import android.content.Context
import com.ysn.examplecrudkotlin.model.Student
import com.ysn.examplecrudkotlin.views.base.Presenter
import com.ysn.examplecrudkotlin.views.base.View
import com.ysn.examplecrudkotlin.views.helper.DBHelper

/**
 * Created by root on 24/06/17.
 */
class StudentEditActivityPresenter : Presenter<StudentEditActivityView> {

    private val TAG: String? = StudentEditActivityPresenter::class.java.simpleName
    private var studentEditActivityView: StudentEditActivityView? = null
    private lateinit var dbHelper: DBHelper
    private lateinit var context: Context

    override fun onAttach(view: View) {
        studentEditActivityView = view as StudentEditActivityView
    }

    override fun onDetach() {
        studentEditActivityView = null
    }

    fun onUpdateData(context: Context, nim: String, student: Student) {
        initDbHelper(context)
        val execUpdateData = dbHelper.updateData(nim, student)
        if (execUpdateData) {
            studentEditActivityView?.updateData()
        } else {
            studentEditActivityView?.updateDataFailed()
        }
    }

    private fun initDbHelper(context: Context) {
        dbHelper = DBHelper(context)
    }

}