package com.learn.mock.Base

import android.support.annotation.StringRes

interface BaseView {
    fun showSuccess(message: String?)

    fun showSuccess(@StringRes stringResId: Int)

    fun showError(error: String?)

    fun showError(@StringRes stringResId: Int)

    fun showMessage(message: String?)

    fun showMessage(@StringRes stringResId: Int)

    fun showProgressDialog()

    fun hideProgressDialog()
}
