package com.ysn.examplecrudkotlin.views.submenu.student.edit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.ysn.examplecrudkotlin.R
import com.ysn.examplecrudkotlin.library.RxBus
import com.ysn.examplecrudkotlin.library.registerInBus
import com.ysn.examplecrudkotlin.model.Student
import kotlinx.android.synthetic.main.activity_student_edit.*
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1

class StudentEditActivity : AppCompatActivity(), StudentEditActivityView {

    private val TAG: String? = StudentEditActivity::class.java.simpleName
    private var studentEditActivityViewPresenter: StudentEditActivityPresenter? = null
    private lateinit var student: Student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_edit)
        initPresenter()
        onAttachView()
        doLoadData()
        initialListener()
        title = "Edit Data"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun doLoadData() {
        /*RxBus.observe<Student>()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{ it }
                .registerInBus(this)
        Log.d(TAG, "student: $student")*/
        val intentArgs = intent
        this.student = intentArgs.getSerializableExtra("student") as Student
        text_input_edit_text_student_nim_activity_student_edit.let { it.setText(student.nim) }
        text_input_edit_text_student_name_activity_student_edit.let { it.setText(student.name) }
        text_input_edit_text_student_address_activity_student_edit.let { it.setText(student.address) }
        text_input_edit_text_student_phone_number_activity_student_edit.let { it.setText(student.phoneNumber) }
        text_input_edit_text_student_birthday_activity_student_edit.let { it.setText(student.birthday) }
    }

    private fun initialListener() {
        floating_action_button_save_activity_student_edit.setOnClickListener({
            val nim = text_input_edit_text_student_nim_activity_student_edit.text.toString()
            val name = text_input_edit_text_student_name_activity_student_edit.text.toString()
            val address = text_input_edit_text_student_address_activity_student_edit.text.toString()
            val phoneNumber = text_input_edit_text_student_phone_number_activity_student_edit.text.toString()
            val birthday = text_input_edit_text_student_birthday_activity_student_edit.text.toString()
            val student = Student(nim = nim, name = name, address = address, phoneNumber = phoneNumber, birthday = birthday)
            studentEditActivityViewPresenter?.onUpdateData(this, this.student.nim, student)
        })
    }

    private fun initPresenter() {
        studentEditActivityViewPresenter = StudentEditActivityPresenter()
    }

    override fun onAttachView() {
        studentEditActivityViewPresenter?.onAttach(this)
    }

    override fun onDetachView() {
        studentEditActivityViewPresenter?.onDetach()
    }

    override fun onDestroy() {
        super.onDestroy()
        onDetachView()
    }

    override fun updateData() {
        Toast.makeText(this, "Data has been updated!", Toast.LENGTH_LONG)
                .show()
        finish()
    }

    override fun updateDataFailed() {
        Toast.makeText(this, "Update data failed!", Toast.LENGTH_LONG)
                .show()
    }
}
