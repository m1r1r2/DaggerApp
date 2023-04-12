package com.example.daggerapp.screens.commons.dialogs

import androidx.fragment.app.FragmentManager

import javax.inject.Inject

class DialogNavigator @Inject constructor(private val  fragmentManager: FragmentManager) {
    fun ShowServerErrorDialog()
    {
        fragmentManager.beginTransaction()
        .add(ServerErrorDialogFragment.newInstance(), null)
        .commitAllowingStateLoss()

    }
}