package com.ysn.examplecrudkotlin.views.helper

import android.content.ContentValues
import android.content.Context
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.ysn.examplecrudkotlin.model.Student

/**
 * Created by root on 12/06/17.
 */
class DBHelper(context: Context?) : SQLiteOpenHelper(context, "Student.db", null, 1) {

    private val TAG: String? = DBHelper::class.simpleName
    private val TABLE_NAME: String? = "Student"
    private val STUDENT_COLUMN_ID: String? = "id"
    private val STUDENT_COLUMN_NIM: String? = "nim"
    private val STUDENT_COLUMN_NAME: String? = "name"
    private val STUDENT_COLUMN_ADDRESS: String? = "address"
    private val STUDENT_COLUMN_PHONE_NUMBER: String? = "phone_number"
    private val STUDENT_COLUMN_BIRTHDAY: String? = "birthday"

    override fun onCreate(sqliteDatabase: SQLiteDatabase?) {
        val queryCreateTable = "" +
                "create table " + TABLE_NAME + "" +
                "(" +
                "" + STUDENT_COLUMN_ID + " integer primary key autoincrement, " +
                "" + STUDENT_COLUMN_NIM + " varchar(20) not null, " +
                "" + STUDENT_COLUMN_NAME + " varchar(100) not null, " +
                "" + STUDENT_COLUMN_ADDRESS + " text not null, " +
                "" + STUDENT_COLUMN_PHONE_NUMBER + " text not null, " +
                "" + STUDENT_COLUMN_BIRTHDAY + " text not null" +
                ")"
        sqliteDatabase?.execSQL(queryCreateTable)
    }

    override fun onUpgrade(sqliteDatabase: SQLiteDatabase?, newVersion: Int, oldVersion: Int) {
        val queryUpgradeTable = "" +
                "drop table if exists " + TABLE_NAME
        sqliteDatabase?.execSQL(queryUpgradeTable)
        onCreate(sqliteDatabase)
    }

    fun insertDataContentValues(student: Student): Boolean {
        val sqliteDatabase = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(STUDENT_COLUMN_NIM, student.nim)
        contentValues.put(STUDENT_COLUMN_NAME, student.name)
        contentValues.put(STUDENT_COLUMN_ADDRESS, student.address)
        contentValues.put(STUDENT_COLUMN_PHONE_NUMBER, student.phoneNumber)
        contentValues.put(STUDENT_COLUMN_BIRTHDAY, student.birthday)
        sqliteDatabase?.insert(TABLE_NAME, null, contentValues)
        sqliteDatabase?.close()
        return true
    }

    fun insertData(student: Student): Boolean {
        val sqliteDatabase = writableDatabase
        try {
            val query = "insert into $TABLE_NAME " +
                        "values " +
                        "(null, \"${student.nim}\", \"${student.name}\", \"${student.address}\", \"${student.phoneNumber}\", \"${student.birthday}\")"
            sqliteDatabase.execSQL(query)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    fun insertDataContentValues(nim: String, name: String, address: String, phoneNumber: String, birthday: String): Boolean {
        val sqliteDatabase = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(STUDENT_COLUMN_NIM, nim)
        contentValues.put(STUDENT_COLUMN_NAME, name)
        contentValues.put(STUDENT_COLUMN_ADDRESS, address)
        contentValues.put(STUDENT_COLUMN_PHONE_NUMBER, phoneNumber)
        contentValues.put(STUDENT_COLUMN_BIRTHDAY, birthday)
        sqliteDatabase?.insert(TABLE_NAME, null, contentValues)
        sqliteDatabase?.close()
        return true
    }

    fun insertData(nim: String, name: String, address: String, phoneNumber: String, birthday: String): Boolean {
        val sqliteDatabase = writableDatabase
        try {
            val query = "insert into $TABLE_NAME" +
                        "values " +
                        "(null, $nim, $name, $address, $phoneNumber, $birthday)"
            sqliteDatabase.execSQL(query)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    fun isDuplicate(nim: String): Boolean {
        val sqliteDatabase = readableDatabase
        val cursor = sqliteDatabase?.rawQuery(
                "select " + STUDENT_COLUMN_NIM + " " +
                        "where " + STUDENT_COLUMN_NIM + " = " +
                        nim,
                null
        )
        cursor?.moveToFirst()
        while (cursor?.isAfterLast?.not() as Boolean) {
            sqliteDatabase.close()
            return true
        }
        sqliteDatabase.close()
        return false
    }

    fun numberOfRows(): Int {
        val sqliteDatabase = readableDatabase
        val numRows = DatabaseUtils.queryNumEntries(sqliteDatabase, TABLE_NAME).toInt()
        sqliteDatabase?.close()
        return numRows
    }

    fun updateData(nim: String, student: Student): Boolean {
        try {
            val sqliteDatabase = writableDatabase
            val contentValues = ContentValues()
            contentValues.put(STUDENT_COLUMN_NIM, student.nim)
            contentValues.put(STUDENT_COLUMN_NAME, student.name)
            contentValues.put(STUDENT_COLUMN_ADDRESS, student.address)
            contentValues.put(STUDENT_COLUMN_PHONE_NUMBER, student.phoneNumber)
            contentValues.put(STUDENT_COLUMN_BIRTHDAY, student.birthday)
            sqliteDatabase?.update(TABLE_NAME, contentValues, STUDENT_COLUMN_NIM + " = ?", arrayOf(nim))
            sqliteDatabase?.close()
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    fun updateData(nimOld: String, nimNew: String, name: String, address: String, phoneNumber: String, birthday: String): Boolean {
        try {
            val sqliteDatabase = writableDatabase
            val contentValues = ContentValues()
            contentValues.put(STUDENT_COLUMN_NIM, nimNew)
            contentValues.put(STUDENT_COLUMN_NAME, name)
            contentValues.put(STUDENT_COLUMN_ADDRESS, address)
            contentValues.put(STUDENT_COLUMN_PHONE_NUMBER, phoneNumber)
            contentValues.put(STUDENT_COLUMN_BIRTHDAY, birthday)
            sqliteDatabase?.update(TABLE_NAME, contentValues, STUDENT_COLUMN_NIM + " = ?", arrayOf(nimOld))
            sqliteDatabase?.close()
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    fun deleteData(student: Student): Boolean {
        try {
            val sqliteDatabase = writableDatabase
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
            val sqliteDatabase = writableDatabase
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
            val sqliteDatabase = writableDatabase
            sqliteDatabase?.execSQL("delete from " + TABLE_NAME)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    fun getData(nim: String): Student {
        val sqliteDatabase = readableDatabase
        val queryGetOne = "select * from $TABLE_NAME where $STUDENT_COLUMN_NIM = $nim"
        val cursor = sqliteDatabase?.rawQuery(queryGetOne, null)
        cursor?.moveToFirst()
        var student: Student? = null
        while (cursor?.isAfterLast?.not() as Boolean) {
            student = Student(
                    id = cursor.getString(cursor.getColumnIndex(STUDENT_COLUMN_ID)),
                    nim = cursor.getString(cursor.getColumnIndex(STUDENT_COLUMN_NIM)),
                    name = cursor.getString(cursor.getColumnIndex(STUDENT_COLUMN_NAME)),
                    address = cursor.getString(cursor.getColumnIndex(STUDENT_COLUMN_ADDRESS)),
                    phoneNumber = cursor.getString(cursor.getColumnIndex(STUDENT_COLUMN_PHONE_NUMBER)),
                    birthday = cursor.getString(cursor.getColumnIndex(STUDENT_COLUMN_BIRTHDAY))
            )

        }
        sqliteDatabase.close()
        return student!!
    }

    fun getAllData(): ArrayList<Student> {
        val listStudent = ArrayList<Student>()
        val sqliteDatabase = this.readableDatabase
        val queryGetAll = "select * from " + TABLE_NAME
        val cursor = sqliteDatabase?.rawQuery(queryGetAll, null)
        cursor?.moveToFirst()
        while (cursor?.isAfterLast?.not() as Boolean) {
            val student = Student(
                    id = cursor.getString(cursor.getColumnIndex(STUDENT_COLUMN_ID)),
                    nim = cursor.getString(cursor.getColumnIndex(STUDENT_COLUMN_NIM)),
                    name = cursor.getString(cursor.getColumnIndex(STUDENT_COLUMN_NAME)),
                    address = cursor.getString(cursor.getColumnIndex(STUDENT_COLUMN_ADDRESS)),
                    phoneNumber = cursor.getString(cursor.getColumnIndex(STUDENT_COLUMN_PHONE_NUMBER)),
                    birthday = cursor.getString(cursor.getColumnIndex(STUDENT_COLUMN_BIRTHDAY))
            )
            Log.d(TAG, "id: " + cursor.getString(cursor.getColumnIndex(STUDENT_COLUMN_ID)))
            listStudent.add(student)
            cursor.moveToNext()
        }
        cursor.close()
        return listStudent
    }

}

