package com.ysn.examplecrudkotlin.views.main

import com.ysn.examplecrudkotlin.views.base.Presenter
import com.ysn.examplecrudkotlin.views.base.View

/**
 * Created by root on 12/06/17.
 */
class MainActivityPresenter : Presenter<MainActivityView> {

    var TAG : String? = MainActivityPresenter::class.simpleName
    val mainActivityView : MainActivityView? = null

    override fun onAttach(view: View) {
        mainActivityView.let { mainActivityView -> view }
    }

    override fun onDetach() {
        mainActivityView.let { mainActivityView -> null }
    }

    fun onLoadData() {

    }
}