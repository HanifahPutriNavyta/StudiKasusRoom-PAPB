/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.inventory.data

/**
 * Interface ItemsRepository digunakan sebagai antarmuka untuk manajemen data item. Repository ini mendefinisikan metode-metode untuk melakukan operasi dasar seperti mengambil semua item, mengambil item berdasarkan ID, menambahkan,
 * menghapus, dan memperbarui item di dalam sumber data.
 */
import kotlinx.coroutines.flow.Flow

/**
 * Interface ItemsRepository digunakan sebagai antarmuka untuk manajemen data item. Repository ini mendefinisikan metode-metode untuk melakukan operasi dasar seperti mengambil semua item, mengambil item berdasarkan ID, menambahkan,
 * menghapus, dan memperbarui item di dalam sumber data.
 */
interface ItemsRepository {

    /**
     * Mengambil semua item dari sumber data dalam bentuk aliran data (Flow) yang berisi daftar [Item].
     * ini berguna untuk menampilkan daftar item secara real-time. Flow memungkinkan UI atau komponen lain untuk berlangganan (subscribe) ke perubahan data
     * secara otomatis, sehingga data akan terupdate di UI ketika ada perubahan di sumber data.
     */
    fun getAllItemsStream(): Flow<List<Item>>

    /**
     * Mengambil satu item dari sumber data yang sesuai dengan [id] yang diberikan.
     * Metode ini untuk mendapatkan informasi detail dari satu item tertentu. Dengan Flow, UI atau logika lain dapat menerima pembaruan secara otomatis
     * ketika item dengan ID yang diinginkan mengalami perubahan.
     */
    fun getItemStream(id: Int): Flow<Item?>

    /**
     * Menyisipkan (menambahkan) item baru ke dalam sumber data.
     * Metode ini untuk menyimpan item baru ke dalam database atau sumber data lainnya. Fungsi ini bersifat suspend sehingga dapat dijalankan secara asinkron dalam coroutine,
     * sehingga tidak memblokir UI atau proses lain.
     */
    suspend fun insertItem(item: Item)

    /**
     * Menghapus item dari sumber data.
     * Metode ini berfungsi untuk menghapus item dari database atau sumber data lain.
     * Suspend memungkinkan operasi ini dilakukan secara asinkron, menghindari pemblokiran thread utama.
     */
    suspend fun deleteItem(item: Item)

    /**
     * Memperbarui data dari item yang sudah ada di dalam sumber data.
     * Metode ini untuk memperbarui informasi dari item yang ada di sumber data, misalnya memperbarui harga atau kuantitas item. Karena bersifat suspend, fungsi ini
     * dapat dijalankan secara asinkron dalam coroutine.
     */
    suspend fun updateItem(item: Item)
}
