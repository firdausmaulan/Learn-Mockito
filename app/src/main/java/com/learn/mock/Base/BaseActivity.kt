package com.learn.mock.Base

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import es.dmoral.toasty.Toasty

open class BaseActivity : AppCompatActivity(), BaseView {

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.setAppContext(this)
        initProgressDialog()
    }

    private fun initProgressDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog.setCancelable(false)
        progressDialog.setMessage("Loading")
    }

    override fun showSuccess(message: String?) {
        Toasty.success(this, message.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun showSuccess(stringResId: Int) {
        Toasty.success(this, getString(stringResId), Toast.LENGTH_SHORT).show()
    }

    override fun showError(error: String?) {
        Toasty.error(this, error.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun showError(stringResId: Int) {
        Toasty.error(this, getString(stringResId), Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(stringResId: Int) {
        Toasty.info(this, getString(stringResId), Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(message: String?) {
        Toasty.info(this, message.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun showProgressDialog() {
        if (progressDialog.isShowing) progressDialog.dismiss()
        progressDialog.show()
    }

    override fun hideProgressDialog() {
        if (progressDialog.isShowing) progressDialog.dismiss()
    }
}
