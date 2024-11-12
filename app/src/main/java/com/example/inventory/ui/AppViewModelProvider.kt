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

package com.example.inventory.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.inventory.InventoryApplication
import com.example.inventory.ui.home.HomeViewModel
import com.example.inventory.ui.item.ItemDetailsViewModel
import com.example.inventory.ui.item.ItemEditViewModel
import com.example.inventory.ui.item.ItemEntryViewModel

/**
 * Objek AppViewModelProvider menyediakan Factory untuk membuat instance ViewModel di seluruh aplikasi Inventory.
 */
object AppViewModelProvider {
    /**
     * Deklarasi Factory dengan menggunakan viewModelFactory. Di sini, kita mendefinisikan ViewModel yang akan diinisialisasi di dalam aplikasi.
     */
    val Factory = viewModelFactory {
        // Initializer untuk ItemEditViewModel dengan mengambil SavedStateHandle.
        initializer {
            ItemEditViewModel(
                this.createSavedStateHandle()
            )
        }
        // Initializer untuk ItemEntryViewModel, memerlukan akses ke itemsRepository.
        initializer {
            ItemEntryViewModel(inventoryApplication().container.itemsRepository)
        }

        // Initializer untuk ItemDetailsViewModel dengan SavedStateHandle.
        initializer {
            ItemDetailsViewModel(
                this.createSavedStateHandle()
            )
        }

        // Initializer untuk HomeViewModel tanpa parameter tambahan.
        initializer {
            HomeViewModel()
        }
    }
}

/**
 * Fungsi ekstensi untuk mengambil objek Application dan mengembalikan instance InventoryApplication.
 */
fun CreationExtras.inventoryApplication(): InventoryApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as InventoryApplication)
