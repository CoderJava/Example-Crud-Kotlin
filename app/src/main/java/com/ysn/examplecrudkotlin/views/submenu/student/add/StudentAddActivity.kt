package com.ysn.examplecrudkotlin.views.submenu.student.add

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.ysn.examplecrudkotlin.R
import com.ysn.examplecrudkotlin.model.Student
import kotlinx.android.synthetic.main.activity_student_add.*

class StudentAddActivity : AppCompatActivity(), StudentAddActivityView {

    private val TAG: String? = StudentAddActivity::class.java.simpleName
    private var studentAddActivityPresenter: StudentAddActivityPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_add)
        initPresenter()
        onAttachView()
        initialListener()
        title = "Add Data"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        studentAddActivityPresenter?.onDetach()
    }

    private fun initialListener() {
        floating_action_button_save_activity_student_add.setOnClickListener({
            val student = Student(
                    nim = text_input_edit_text_student_nim_activity_student_add.text.toString(),
                    name = text_input_edit_text_student_name_activity_student_add.text.toString(),
                    address = text_input_edit_text_student_address_activity_student_add.text.toString(),
                    phoneNumber = text_input_edit_text_student_phone_number_activity_student_add.text.toString(),
                    birthday = text_input_edit_text_student_birthday_activity_student_add.text.toString()
            )
            studentAddActivityPresenter?.onSave(this, student)
        })
    }

    private fun initPresenter() {
        studentAddActivityPresenter = StudentAddActivityPresenter()
    }

    override fun onAttachView() {
        studentAddActivityPresenter?.onAttach(this)
    }

    override fun onDetachView() {
        studentAddActivityPresenter?.onDetach()
    }

    override fun saveInvalid(message: String) {
        Log.d(TAG, "saveInvalid view")
        Toast.makeText(this, message, Toast.LENGTH_LONG)
                .show()
    }

    override fun save() {
        text_input_edit_text_student_nim_activity_student_add.setText("")
        text_input_edit_text_student_name_activity_student_add.setText("")
        text_input_edit_text_student_address_activity_student_add.setText("")
        text_input_edit_text_student_phone_number_activity_student_add.setText("")
        text_input_edit_text_student_birthday_activity_student_add.setText("")
        text_input_edit_text_student_nim_activity_student_add.requestFocus()
        Toast.makeText(this, "Data has been saved!", Toast.LENGTH_LONG)
                .show()
    }

    override fun saveFailed() {
        Toast.makeText(this, "Data has been failed to save!", Toast.LENGTH_LONG)
                .show()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
            else -> {
                // nothing to do in here
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

