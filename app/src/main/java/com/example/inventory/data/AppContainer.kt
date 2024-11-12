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

import android.content.Context

/**
 * AInterface AppContainer digunakan untuk mendefinisikan kontainer dependensi di mana itemsRepository akan disediakan
 * untuk mendukung Dependency Injection.
 */
interface AppContainer {
    val itemsRepository: ItemsRepository
}

/**
 * Implementasi dari AppContainer, yaitu AppDataContainer, yang menyediakan instance OfflineItemsRepository untuk
 * kebutuhan data aplikasi.
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Membuat instance itemsRepository secara lazy (hanya akan diinisialisasi saat pertama kali dipanggil)
     * menggunakan OfflineItemsRepository, yang mengambil ItemDao dari database.
     */
    override val itemsRepository: ItemsRepository by lazy {
        OfflineItemsRepository(InventoryDatabase.getDatabase(context).itemDao())
    }
}
