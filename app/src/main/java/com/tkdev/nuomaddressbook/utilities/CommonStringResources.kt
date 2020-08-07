package com.tkdev.nuomaddressbook.utilities

import android.content.Context
import androidx.annotation.StringRes

class CommonStringResourceWrapper(private val context: Context) {

    fun getString(@StringRes stringResId: Int): String = context.getString(stringResId)

}
