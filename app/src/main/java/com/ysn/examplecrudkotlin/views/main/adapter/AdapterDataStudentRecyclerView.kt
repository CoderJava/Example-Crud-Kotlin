package com.ysn.examplecrudkotlin.views.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.ysn.examplecrudkotlin.R
import com.ysn.examplecrudkotlin.model.Student

/**
 * Created by root on 17/06/17.
 */
class AdapterDataStudentRecyclerView : RecyclerView.Adapter<AdapterDataStudentRecyclerView.ViewHolderStudent> {

    private val TAG: String? = AdapterDataStudentRecyclerView::class.simpleName
    private var listStudents: ArrayList<Student>? = null
    var listenerAdapterDataStudentRecyclerView: ListenerAdapterDataStudentRecyclerView? = null

    constructor(listStudents: ArrayList<Student>?, listenerAdapterDataStudentRecyclerView: ListenerAdapterDataStudentRecyclerView?) : super() {
        this.listStudents = listStudents
        this.listenerAdapterDataStudentRecyclerView = listenerAdapterDataStudentRecyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolderStudent {
        val view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_data_student_recycler_view_activity_main, null)
        return ViewHolderStudent(view)
    }

    override fun onBindViewHolder(holder: ViewHolderStudent?, position: Int) {
        val student = listStudents?.get(position)
        holder?.textViewStudentNameViewHolderStudent?.text = student?.name
        holder?.textViewStudentNimViewHolderStudent?.text = student?.nim
        holder?.textViewStudentPhoneNumberViewHolderStudent?.text = student?.phoneNumber
    }

    override fun getItemCount(): Int {
        return listStudents!!.size
    }

    fun clearData() {
        listStudents = ArrayList()
        notifyDataSetChanged()
    }

    fun removeItem(student: Student) {
        listStudents?.forEachIndexed {
            index, studentItem ->
            if (studentItem.nim == student.nim) {
                listStudents?.removeAt(index)
                notifyDataSetChanged()
                return@forEachIndexed
            }
        }
    }

    inner class ViewHolderStudent(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        var textViewStudentNameViewHolderStudent: TextView = itemView?.findViewById(R.id.text_view_student_name_item_data_student_recycler_view_activity_main) as TextView
        var textViewStudentNimViewHolderStudent: TextView = itemView?.findViewById(R.id.text_view_student_nim_item_data_student_recycler_view_activity_main) as TextView
        var textViewStudentPhoneNumberViewHolderStudent: TextView = itemView?.findViewById(R.id.text_view_student_phone_number_item_data_student_recycler_view_activity_main) as TextView
        var buttonDeleteStudentViewHolderStudent: Button = itemView?.findViewById(R.id.button_delete_item_data_student_recycler_view_activity_main) as Button
        var buttonEditStudentViewHolderStudent: Button = itemView?.findViewById(R.id.button_edit_item_data_student_recycler_view_activity_main) as Button

        init {
            buttonDeleteStudentViewHolderStudent.setOnClickListener({
                listenerAdapterDataStudentRecyclerView?.onClickDelete(listStudents?.get(adapterPosition) as Student)
            })
            buttonEditStudentViewHolderStudent.setOnClickListener({
                listenerAdapterDataStudentRecyclerView?.onClickEdit(listStudents?.get(adapterPosition) as Student)
            })
        }

    }

    interface ListenerAdapterDataStudentRecyclerView {
        fun onClickDelete(student: Student)

        fun onClickEdit(student: Student)
    }

}