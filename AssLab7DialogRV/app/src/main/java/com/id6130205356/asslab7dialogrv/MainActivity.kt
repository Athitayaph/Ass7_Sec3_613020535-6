package com.id6130205356.asslab7dialogrv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_dialog_layout.*

class MainActivity : AppCompatActivity() {
    val employeeList = arrayListOf<Employee>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        employeeData()
        recyclear_view.adapter = EmployeeAdapter(this.employeeList, applicationContext)
        recyclear_view.layoutManager = LinearLayoutManager(applicationContext)
    }
    fun employeeData(){
        employeeList.add(Employee("Danny", "Male", "danny@kku.ac.th", 30000))
        employeeList.add(Employee("Sara", "Female", "sara@kku.ac.th", 34000))
    }

    fun addEmployee(view: View){
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.add_dialog_layout, null)
        val myBuilder =AlertDialog.Builder(this)
        myBuilder.setView(mDialogView)

        val mAlertDialog = myBuilder.show()
        mAlertDialog.btn_add.setOnClickListener(){
            var selectID = mAlertDialog.gender.checkedRadioButtonId
            var radioChecked = mAlertDialog.findViewById<RadioButton>(selectID)
            employeeList.add(
                Employee(
                    mAlertDialog.input_name.text.toString(),
                    radioChecked?.text.toString(),
                    mAlertDialog.input_email.text.toString(),
                    mAlertDialog.input_salary.text.toString().toInt()

                )
            )
            recyclear_view.adapter?.notifyDataSetChanged()
            Toast.makeText(
                applicationContext,
                "The Employee is add successfully",
                Toast.LENGTH_LONG
            ).show()
            mAlertDialog.dismiss()
        }
        mAlertDialog.btn_cancle.setOnClickListener(){
            mAlertDialog.dismiss()
        }
    }
}