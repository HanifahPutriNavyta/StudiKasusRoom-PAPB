package com.example.inventory.data
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// Antarmuka ItemDao yang menyediakan metode akses ke database untuk entitas Item.
//Anotasi @Dao menandakan bahwa ItemDao adalah Data Access Object, yang berisi metode-metode untuk mengakses dan mengelola data di database.
// Room menggunakan @Dao untuk menghasilkan kode akses database.
@Dao
interface ItemDao {

    // Menyisipkan data Item baru ke dalam tabel.
    // Jika data sudah ada, strategi IGNORE akan mengabaikan data baru yang sama.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    // Memperbarui data Item yang ada di tabel.
    @Update
    suspend fun update(item: Item)

    // Menghapus data Item dari tabel.
    @Delete
    suspend fun delete(item: Item)

    // Menggunakan SQL untuk mengambil data Item dari tabel berdasarkan id tertentu. Data dihasilkan dalam bentuk Flow untuk akses data secara reaktif.
    // Penggunaan Flow<Item> memudahkan pemantauan perubahan data secara reaktif.
    @Query("SELECT * from items WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

    // Menggunakan SQL untuk mengambil semua data Item dari tabel dan mengurutkan secara alfabetis berdasarkan nama, dan mengembalikan hasil sebagai Flow<List<Item>>.
    // Menggunakan Flow untuk memudahkan pembaruan data secara real-time.
    @Query("SELECT * from items ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>
}

