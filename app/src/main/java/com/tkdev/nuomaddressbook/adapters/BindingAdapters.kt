package com.tkdev.nuomaddressbook.adapters

import android.view.View
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

@BindingAdapter("setVisibility")
fun setVisibility(view: View, enabled: Boolean) {
    when (enabled) {
        true -> view.visibility = View.VISIBLE
        false -> view.visibility = View.INVISIBLE
    }
}
