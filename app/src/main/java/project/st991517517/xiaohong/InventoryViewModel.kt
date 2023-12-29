/*
 * Name: Xiaohong Deng
 * Student ID: 991517517
 * Assignment: Final Group Project - NoWasteFinalProject APP
 * Nov 18, 2021
 *
 * Description of InventoryViewModel class:
 * This is to create a ViewModel to interact with the database via the DAO
 * and provide data to the UI
 *
 * @author dengxiao
* */
package project.st991517517.xiaohong

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import project.st991517517.xiaohong.data.Item
import project.st991517517.xiaohong.data.ItemDao
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class InventoryViewModel(private val itemDao: ItemDao) : ViewModel() {

    val allItems: LiveData<List<Item>> = itemDao.getItems().asLiveData()

    @RequiresApi(Build.VERSION_CODES.O)
    val current = LocalDateTime.now()
    @RequiresApi(Build.VERSION_CODES.O)
    val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
    @RequiresApi(Build.VERSION_CODES.O)
    val today = current.format(formatter)

    val expiredItems: LiveData<List<Item>> = itemDao.getExpiredItems(today).asLiveData()

    val expireTomorrowItems: LiveData<List<Item>> = itemDao.getExpireTomorrowItems(today).asLiveData()

    private fun updateItem(item: Item) {
        viewModelScope.launch {
            itemDao.update(item)
        }
    }

    fun consumeItem(item: Item) {
        if (item.quantityInStock > 0) {
            // Decrease the quantity by 1
            val newItem = item.copy(quantityInStock = item.quantityInStock - 1)
            updateItem(newItem)
        }
    }

    fun isStockAvailable(item: Item): Boolean {
        return (item.quantityInStock > 0)
    }

    fun deleteItem(item: Item)  {
        viewModelScope.launch {
            itemDao.delete(item)
        }
    }

    private fun getUpdatedItemEntry(
        itemId: Int,
        itemName: String,
        itemExpiryDate: String,
        itemCount: String
    ): Item {
        return Item(
            id = itemId,
            itemName = itemName,
            expiry_date = itemExpiryDate,
            quantityInStock = itemCount.toInt()
        )
    }

    fun updateItem(
        itemId: Int,
        itemName: String,
        itemExpiryDate: String,
        itemCount: String
    ) {
        val updatedItem = getUpdatedItemEntry(itemId, itemName, itemExpiryDate, itemCount)
        updateItem(updatedItem)
    }

    fun retrieveItem(id: Int): LiveData<Item> {
        return itemDao.getItem(id).asLiveData()
    }

    private fun insertItem(item: Item) {
        viewModelScope.launch {
            itemDao.insert(item)
        }
    }


    private fun getNewItemEntry(itemName: String, itemExpiryDate: String, itemCount: String): Item {
        return Item(
            itemName = itemName,
            expiry_date = itemExpiryDate,
            quantityInStock = itemCount.toInt()
        )
    }

    fun addNewItem(itemName: String, itemExpiryDate: String, itemCount: String) {
        val newItem = getNewItemEntry(itemName, itemExpiryDate, itemCount)
        insertItem(newItem)
    }

    fun isEntryValid(itemName: String, itemExpiryDate: String, itemCount: String): Boolean {
        if (itemName.isBlank() || itemExpiryDate.isBlank() || itemCount.isBlank()) {
            return false
        }
        return true
    }
}

class InventoryViewModelFactory(private val itemDao: ItemDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(InventoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InventoryViewModel(itemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}