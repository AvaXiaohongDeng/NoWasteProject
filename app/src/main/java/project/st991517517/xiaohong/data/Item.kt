/*
 * Name: Xiaohong Deng
 * Student ID: 991517517
 * Assignment: Final Group Project - NoWasteFinalProject APP
 * Nov 18, 2021
 *
 * Description of Item class:
 * This is to create a Entity class that defines a table to hold the values for each item.
 *
 * @author dengxiao
* */
package project.st991517517.xiaohong.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.NumberFormat

@Entity(tableName = "item")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val itemName: String,
    @ColumnInfo(name = "expiry_date")
    val expiry_date: String,
    @ColumnInfo(name = "quantity")
    val quantityInStock: Int
)