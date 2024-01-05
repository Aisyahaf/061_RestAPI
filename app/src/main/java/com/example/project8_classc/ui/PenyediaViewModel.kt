package com.example.project8_classc.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.project8_classc.KontakApplication
import com.example.project8_classc.ui.home.viewmodel.HomeViewModel
import com.example.project8_classc.ui.kontak.viewmodel.DetailsViewModel
import com.example.project8_classc.ui.kontak.viewmodel.EditViewModel
import com.example.project8_classc.ui.kontak.viewmodel.InsertViewModel

object PenyediaViewModel{
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(aplikasiKontak().container.kontakRepository)
        }

        initializer {
            InsertViewModel(aplikasiKontak().container.kontakRepository)
        }

        initializer {
            DetailsViewModel(
                createSavedStateHandle(),
                kontakRepository = aplikasiKontak().container.kontakRepository
            )
        }

        initializer {
            EditViewModel(
                createSavedStateHandle(),
                kontakRepository = aplikasiKontak().container.kontakRepository
            )
        }
    }
}

fun CreationExtras.aplikasiKontak(): KontakApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KontakApplication)