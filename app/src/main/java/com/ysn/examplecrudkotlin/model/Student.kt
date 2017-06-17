package com.ysn.examplecrudkotlin.model

/**
 * Created by root on 17/06/17.
 */
data class Student(
        val id: String,
        val nim: String,
        val name: String,
        val address: String,
        val phoneNumber: String,
        val birthday: String
        )