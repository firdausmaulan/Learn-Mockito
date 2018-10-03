package com.learn.mock.feature.profile

import android.os.Bundle
import com.learn.mock.Base.BaseActivity
import com.learn.mock.R
import com.learn.mock.network.ApiHelper
import kotlinx.android.synthetic.main.activity_main.*

class ProfileActivity : BaseActivity(), ProfilePresenter.View {

    private lateinit var presenter: ProfilePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = ProfilePresenter(this)
        presenter.requestProfile(ApiHelper().service.requestProfile())
    }

    override fun onSuccess(name: String?) {
        tvHello.text = name
    }
}
