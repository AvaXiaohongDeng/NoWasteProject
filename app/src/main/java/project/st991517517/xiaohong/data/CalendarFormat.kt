/*
 * Name: Xiaohong Deng
 * Student ID: 991517517
 * Assignment: Final Group Project - NoWasteFinalProject APP
 * Nov 18, 2021
 *
 * Description of Calculations object:
 * This is to create a object to calculate the date and time then display in a specific format.
 *
 * @author dengxiao
* */
package project.st991517517.xiaohong.data

import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat

object CalendarFormat {

    fun formDate(_day: Int, _month: Int, _year: Int): String {
        var day = _day.toString()
        var month = _month.toString()

        if (_day < 10) {
            day = "0$_day"
        }

        if (_month < 9) {
            month = "0${_month + 1}"
        } else if (_month >= 9 && _month <= 11) {
            month = (_month + 1).toString()
        }

        return "$_year$month$day"
    }
}