package com.tkdev.nuomaddressbook.adapters

import android.widget.Button
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout


@BindingAdapter("setError")
fun setError(textInputLayout: TextInputLayout, errorMsg: String?) {
    textInputLayout.error = errorMsg
}

@BindingAdapter("enableSaveContact")
fun enableSaveContact(button: Button, enabled: Boolean) {
    button.isEnabled = enabled
    when (enabled) {
        true -> button.alpha = 1.0f
        false -> button.alpha = 0.8f
    }
}
