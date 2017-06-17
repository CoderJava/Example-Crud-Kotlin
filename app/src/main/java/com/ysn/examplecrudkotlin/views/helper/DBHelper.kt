package com.ysn.examplecrudkotlin.views.helper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.ysn.examplecrudkotlin.model.Student

/**
 * Created by root on 12/06/17.
 */
class DBHelper(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version) {

    private val TAG: String? = DBHelper::class.simpleName
    private val TABLE_NAME: String? = "Student.db"
    private val STUDENT_COLUMN_ID: String? = "id"
    private val STUDENT_COLUMN_NIM: String? = "nim"
    private val STUDENT_COLUMN_NAME: String? = "name"
    private val STUDENT_COLUMN_ADDRESS: String? = "address"
    private val STUDENT_COLUMN_PHONE_NUMBER: String? = "phone_number"
    private val STUDENT_COLUMN_BIRTHDAY: String? = "birthday"
    private var sqliteDatabase: SQLiteDatabase? = null

    override fun onCreate(p0: SQLiteDatabase?) {
        val queryCreateTable = "" +
                "create table " + TABLE_NAME + "" +
                "(" +
                "" + STUDENT_COLUMN_ID + " integer unsigned not null primary key, " +
                "" + STUDENT_COLUMN_NAME + " varchar(100) not null, " +
                "" + STUDENT_COLUMN_ADDRESS + " text not null, " +
                "" + STUDENT_COLUMN_PHONE_NUMBER + " text not null, " +
                "" + STUDENT_COLUMN_BIRTHDAY + " text not null"
        ")"
        sqliteDatabase?.execSQL(queryCreateTable)
    }

    override fun onUpgrade(sqliteDatabase: SQLiteDatabase?, newVersion: Int, oldVersion: Int) {
        val queryUpgradeTable = "" +
                "drop table if exists " + TABLE_NAME
        sqliteDatabase?.execSQL(queryUpgradeTable)
        onCreate(sqliteDatabase)
    }

    fun insertData(student: Student): Boolean {
        sqliteDatabase = this.writableDatabase
        val contentValues: ContentValues? = null
        contentValues.let { contentValues -> ContentValues() }
        contentValues?.put(STUDENT_COLUMN_ID, student.id)
        contentValues?.put(STUDENT_COLUMN_NAME, student.name)
        contentValues?.put(STUDENT_COLUMN_ADDRESS, student.address)
        contentValues?.put(STUDENT_COLUMN_PHONE_NUMBER, student.phoneNumber)
        contentValues?.put(STUDENT_COLUMN_BIRTHDAY, student.birthday)
        sqliteDatabase?.insert(TABLE_NAME, null, contentValues)
        sqliteDatabase?.close()
        return true
    }

    fun insertData(id: String, name: String, address: String, phoneNumber: String, birthday: String): Boolean {
        sqliteDatabase = this.writableDatabase
        val contentValues: ContentValues? = null
        contentValues.let { contentValues -> ContentValues() }
        contentValues?.put(STUDENT_COLUMN_ID, id)
        contentValues?.put(STUDENT_COLUMN_NAME, name)
        contentValues?.put(STUDENT_COLUMN_ADDRESS, address)
        contentValues?.put(STUDENT_COLUMN_PHONE_NUMBER, phoneNumber)
        contentValues?.put(STUDENT_COLUMN_BIRTHDAY, birthday)
        sqliteDatabase?.insert(TABLE_NAME, null, contentValues)
        sqliteDatabase?.close()
        return true
    }

    fun isDuplicate(nim: String): Boolean {
        sqliteDatabase = this.readableDatabase
        val cursor = sqliteDatabase?.rawQuery(
                "select " + STUDENT_COLUMN_NIM + " " +
                        "where " + STUDENT_COLUMN_NIM + " = " +
                        nim,
                null
        )
        cursor?.moveToFirst()
        while (cursor?.isAfterLast?.not() as Boolean) {
            sqliteDatabase?.close()
            return true
        }
        sqliteDatabase?.close()
        return false
    }

    fun numberOfRows(): Int {
        sqliteDatabase = this.readableDatabase
        val numRows = DatabaseUtils.queryNumEntries(sqliteDatabase, TABLE_NAME).toInt()
        sqliteDatabase?.close()
        return numRows
    }

    fun updateData(student: Student): Boolean {
        try {
            sqliteDatabase = this.writableDatabase
            val contentValues: ContentValues? = null
            contentValues.let { contentValues -> ContentValues() }
            contentValues?.put(STUDENT_COLUMN_NAME, student.name)
            contentValues?.put(STUDENT_COLUMN_ADDRESS, student.address)
            contentValues?.put(STUDENT_COLUMN_PHONE_NUMBER, student.phoneNumber)
            contentValues?.put(STUDENT_COLUMN_BIRTHDAY, student.birthday)
            sqliteDatabase?.update(TABLE_NAME, contentValues, STUDENT_COLUMN_ID + " = ?", arrayOf(student.nim))
            sqliteDatabase?.close()
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    fun updateData(nim: String, name: String, address: String, phoneNumber: String, birthday: String): Boolean {
        try {
            sqliteDatabase = this.writableDatabase
            val contentValues: ContentValues? = null
            contentValues.let { contentValues -> ContentValues() }
            contentValues?.put(STUDENT_COLUMN_NAME, name)
            contentValues?.put(STUDENT_COLUMN_ADDRESS, address)
            contentValues?.put(STUDENT_COLUMN_PHONE_NUMBER, phoneNumber)
            contentValues?.put(STUDENT_COLUMN_BIRTHDAY, birthday)
            sqliteDatabase?.update(TABLE_NAME, contentValues, STUDENT_COLUMN_NIM + " = ?", arrayOf(nim))
            sqliteDatabase?.close()
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    fun deleteData(student: Student): Boolean {
        try {
            sqliteDatabase = writableDatabase
            sqliteDatabase?.delete(TABLE_NAME, STUDENT_COLUMN_NIM + " = ?", arrayOf(student.nim))
            sqliteDatabase?.close()
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    fun deleteData(nim: String): Boolean {
        try {
            sqliteDatabase = writableDatabase
            sqliteDatabase?.delete(TABLE_NAME, STUDENT_COLUMN_NIM + " = ?", arrayOf(nim))
            sqliteDatabase?.close()
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    fun deleteAllData(): Boolean {
        try {
            sqliteDatabase = writableDatabase
            sqliteDatabase?.execSQL("delete from " + TABLE_NAME)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    fun getData(nim: String): Student {
        sqliteDatabase = readableDatabase
        val queryGetOne = "select * from " + TABLE_NAME + " where " + STUDENT_COLUMN_NIM + " = " + nim;
        val cursor = sqliteDatabase?.rawQuery(queryGetOne, null)
        cursor?.moveToFirst()
        val student: Student? = null
        while (cursor?.isAfterLast()?.not() as Boolean) {
            student.let {
                student ->
                Student(
                        id = cursor.getString(cursor.getColumnIndex(STUDENT_COLUMN_ID)),
                        nim = cursor.getString(cursor.getColumnIndex(STUDENT_COLUMN_NIM)),
                        name = cursor.getString(cursor.getColumnIndex(STUDENT_COLUMN_NAME)),
                        address = cursor.getString(cursor.getColumnIndex(STUDENT_COLUMN_ADDRESS)),
                        phoneNumber = cursor.getString(cursor.getColumnIndex(STUDENT_COLUMN_PHONE_NUMBER)),
                        birthday = cursor.getString(cursor.getColumnIndex(STUDENT_COLUMN_BIRTHDAY))
                )
            }
            sqliteDatabase?.close()
        }
        return student!!
    }

    fun getAllData(): List<Student> {
        val listStudent: ArrayList<Student>? = null
        listStudent.let { listStudent -> ArrayList<Student>() }
        sqliteDatabase = this.readableDatabase
        val queryGetAll = "select * from " + TABLE_NAME;
        val cursor = sqliteDatabase?.rawQuery(queryGetAll, null)
        cursor?.moveToFirst()
        while (cursor?.isAfterLast()?.not() as Boolean) {
            val student: Student? = null
            student.let { student ->
                Student(
                        id = cursor.getString(cursor.getColumnIndex(STUDENT_COLUMN_ID)),
                        nim = cursor.getString(cursor.getColumnIndex(STUDENT_COLUMN_NIM)),
                        name = cursor.getString(cursor.getColumnIndex(STUDENT_COLUMN_NAME)),
                        address = cursor.getString(cursor.getColumnIndex(STUDENT_COLUMN_ADDRESS)),
                        phoneNumber = cursor.getString(cursor.getColumnIndex(STUDENT_COLUMN_PHONE_NUMBER)),
                        birthday = cursor.getString(cursor.getColumnIndex(STUDENT_COLUMN_BIRTHDAY))
                )
            }
            if (student != null) {
                listStudent?.add(student)
            }
            cursor.moveToNext()
        }
        cursor.close()
        return listStudent!!
    }

}