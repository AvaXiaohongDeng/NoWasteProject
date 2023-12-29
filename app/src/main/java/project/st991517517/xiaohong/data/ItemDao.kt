/*
 * Name: Xiaohong Deng
 * Student ID: 991517517
 * Assignment: Final Group Project - NoWasteFinalProject APP
 * Nov 18, 2021
 *
 * Description of ItemDao interface:
 * This is to create a Data Access Object(DAO) for the Room. It will provide a custom interface
 * includes some convenience methods for inserting, updating, deleting, querying the database.
 *
 * @author dengxiao
* */
package project.st991517517.xiaohong.data


import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * from item WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

    @Query("SELECT * from item ORDER BY name ASC")
    fun getItems(): Flow<List<Item>>

    @Query("SELECT * from item WHERE expiry_date < :today")
    fun getExpiredItems(today: String): Flow<List<Item>>

    @Query("SELECT * from item WHERE expiry_date = :today")
    fun getExpireTomorrowItems(today: String): Flow<List<Item>>
}