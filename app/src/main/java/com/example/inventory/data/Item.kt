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
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Kelas data `Item` ini merepresentasikan sebuah entitas atau tabel di dalam database.
 * Setiap instance dari kelas `Item` mewakili satu baris (row) dalam tabel database.
 */


@Entity(tableName = "items")
data class Item(
    // Mengidentifikasi kolom id sebagai primary key yang unik untuk setiap item, dengan auto-generate yang memungkinkan id dibuat otomatis oleh database.
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0,

    // Kolom untuk menyimpan nama item.
    val name: String,

    // Kolom untuk menyimpan harga item dalam bentuk double.
    val price: Double,

    // Kolom untuk menyimpan jumlah (kuantitas) item dalam bentuk integer.
    val quantity: Int
)
