package com.learn.mock.feature.profile

import com.learn.mock.Base.BaseView
import com.learn.mock.model.ProfileData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfilePresenter internal constructor(
        private val callProfile: Call<ProfileData>,
        private val view: View) {

    interface View : BaseView {
        fun onSuccess(name: String?)
    }

    fun requestProfile() {
        view.showProgressDialog()
        callProfile.enqueue(object : Callback<ProfileData> {
            override fun onResponse(call: Call<ProfileData>?, response: Response<ProfileData>?) {
                view.hideProgressDialog()
                if (response?.body()?.success == true) {
                    response.body()?.name?.let { view.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<ProfileData>?, t: Throwable?) {
                view.hideProgressDialog()
            }
        })
    }
}