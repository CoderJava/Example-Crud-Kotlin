package com.ysn.examplecrudkotlin.views.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.ysn.examplecrudkotlin.R

class MainActivity : AppCompatActivity(), MainActivityView {

    private val TAG: String? = MainActivity::class.simpleName
    private val mainActivityPresenter: MainActivityPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initPresenter()
    }

    private fun initPresenter() {
        mainActivityPresenter.let { mainActivityPresenter -> MainActivityPresenter() }
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
                // todo: add data
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

    override fun onStart() {
        doLoadData()
        super.onStart()
    }

    private fun doLoadData() {
        mainActivityPresenter?.onLoadData()
    }

    override fun onDestroy() {
        onDetachView()
        super.onDestroy()
    }
}
