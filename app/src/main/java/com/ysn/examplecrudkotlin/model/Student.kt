package com.ysn.examplecrudkotlin.model

import java.io.Serializable

/**
 * Created by root on 17/06/17.
 */
data class Student(
        val id: String = "0",
        var nim: String,
        val name: String,
        val address: String,
        val phoneNumber: String,
        val birthday: String
        ) : Serializable
