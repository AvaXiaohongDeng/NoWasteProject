/*
 * Name: Xiaohong Deng
 * Student ID: 991517517
 * Assignment: Final Group Project - NoWasteFinalProject APP
 * Nov 18, 2021
 *
 * Description of InventoryApplication class:
 * This is to create an application to apply ItemRoomDatabase
 *
 * @author dengxiao
* */
package project.st991517517.xiaohong

import android.app.Application
import project.st991517517.xiaohong.data.ItemRoomDatabase

class InventoryApplication : Application(){

    val database: ItemRoomDatabase by lazy { ItemRoomDatabase.getDatabase(this) }
}
