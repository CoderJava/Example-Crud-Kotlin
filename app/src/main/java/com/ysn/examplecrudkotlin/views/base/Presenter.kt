package com.ysn.examplecrudkotlin.views.base

/**
 * Created by root on 12/06/17.
 */
interface Presenter<T : View> {

    fun onAttach(view: View)

    fun onDetach()

}