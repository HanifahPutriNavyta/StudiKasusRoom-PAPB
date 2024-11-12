package com.example.inventory.data
import androidx.room.Database
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Room

// Mendefinisikan database Room dengan entitas Item dan versi 1, tanpa mengekspor skema.
// `exportSchema = false` mencegah pembuatan file skema untuk migrasi database.
@Database(entities = [Item::class], version = 1, exportSchema = false)

// Kelas abstrak InventoryDatabase berfungsi sebagai basis data utama dengan Room Database.
// Untuk mengakses DAO, menggunakan metode `itemDao`.
abstract class InventoryDatabase : RoomDatabase() {

    // Mendeklarasikan fungsi abstrak untuk mengakses ItemDao, yang berisi metode CRUD untuk Item.
    abstract fun itemDao(): ItemDao
    companion object {

        // Variabel Instance bersifat @Volatile agar perubahan yang terjadi pada Instance dapat segera terlihat oleh semua thread, sehingga menghindari masalah sinkronisasi.
        private var Instance: InventoryDatabase? = null

        // Fungsi getDatabase memastikan bahwa hanya ada satu instance database yang digunakan.
        fun getDatabase(context: Context): InventoryDatabase {
            // Memeriksa jika Instance belum diinisialisasi, maka akan disinkronisasi
            // dan Room database instance dibangun. Jika sudah ada, Instance dikembalikan.
            return Instance ?: synchronized(this) {

                // Membangun database jika Instance masih null, dengan nama "item_database"
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")
                    .build()

                    // Menyimpan instance yang dibangun ke dalam Instance.
                    .also { Instance = it }
            }
        }

    }
}