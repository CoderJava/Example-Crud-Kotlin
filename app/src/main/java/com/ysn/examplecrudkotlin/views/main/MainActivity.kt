package com.ysn.examplecrudkotlin.views.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.ysn.examplecrudkotlin.R
import com.ysn.examplecrudkotlin.views.main.adapter.AdapterDataStudentRecyclerView
import com.ysn.examplecrudkotlin.views.submenu.student.add.StudentAddActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityView {

    private val TAG: String? = MainActivity::class.simpleName
    private var mainActivityPresenter: MainActivityPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initPresenter()
        onAttachView()
    }

    private fun initPresenter() {
        mainActivityPresenter = MainActivityPresenter()
    }

    override fun onAttachView() {
        mainActivityPresenter?.onAttach(this)
    }

    override fun onDetachView() {
        mainActivityPresenter?.onDetach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_main_add_data -> {
                startActivity(Intent(this, StudentAddActivity::class.java))
            }
            R.id.menu_main_delete_all_data -> {
                // todo: delete all data
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
        return false
    }

    override fun onResume() {
        doLoadData()
        super.onResume()
    }

    private fun doLoadData() {
        mainActivityPresenter?.onLoadData(this)
    }

    override fun onDestroy() {
        onDetachView()
        super.onDestroy()
    }

    override fun loadData(adapterDataStudentRecyclerView: AdapterDataStudentRecyclerView) {
        Log.d(TAG, "loadData view")
        recycler_view_data_student_activity_main.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recycler_view_data_student_activity_main.addItemDecoration(dividerItemDecoration)
        recycler_view_data_student_activity_main.adapter = adapterDataStudentRecyclerView
    }
}
