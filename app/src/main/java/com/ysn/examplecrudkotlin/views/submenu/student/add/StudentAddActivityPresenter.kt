package com.ysn.examplecrudkotlin.views.submenu.student.add

import android.content.Context
import android.util.Log
import com.ysn.examplecrudkotlin.model.Student
import com.ysn.examplecrudkotlin.views.base.Presenter
import com.ysn.examplecrudkotlin.views.base.View
import com.ysn.examplecrudkotlin.views.helper.DBHelper

/**
 * Created by root on 24/06/17.
 */
class StudentAddActivityPresenter : Presenter<StudentAddActivityView> {

    private val TAG: String? = StudentAddActivityPresenter::class.java.simpleName
    private var studentAddActivityView: StudentAddActivityView? = null

    override fun onAttach(view: View) {
        studentAddActivityView = view as StudentAddActivityView
    }

    override fun onDetach() {
        studentAddActivityView = null
    }

    fun onSave(context: Context, student: Student) {
        Log.d(TAG, "onSave presenter")
        if (student.nim.isEmpty()) {
            studentAddActivityView?.saveInvalid("Field NIM is empty!")
            return
        } else if (student.name.isEmpty()) {
            studentAddActivityView?.saveInvalid("Field Name is empty!")
            return
        } else if (student.address.isEmpty()) {
            studentAddActivityView?.saveInvalid("Field Address is empty!")
            return
        } else if (student.phoneNumber.isEmpty()) {
            studentAddActivityView?.saveInvalid("Field Phone Number is empty!")
            return
        } else if (student.birthday.isEmpty()) {
            studentAddActivityView?.saveInvalid("Field Birthday is empty!")
            return
        }
        val dbHelper = DBHelper(context)
        val execInsert = dbHelper.insertData(student)
        if (execInsert) {
            studentAddActivityView?.save()
        } else {
            studentAddActivityView?.saveFailed()
        }
    }
}