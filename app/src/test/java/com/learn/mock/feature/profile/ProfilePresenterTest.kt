package com.learn.mock.feature.profile

import com.learn.mock.BuildConfig
import com.learn.mock.model.ProfileData
import com.learn.mock.network.ApiService
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.*
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import org.mockito.Mockito




@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class ProfilePresenterTest {

    @Mock
    lateinit var view: ProfilePresenter.View
    @Mock
    lateinit var apiService: ApiService
    @Mock
    lateinit var callProfile: Call<ProfileData>
    @Captor
    private lateinit var callbackArgumentCaptor: ArgumentCaptor<Callback<ProfileData>>

    private lateinit var activity: ProfileActivity
    private lateinit var presenter: ProfilePresenter

    @Before
    fun setUp() {
        activity = Robolectric.buildActivity(ProfileActivity::class.java)
                .create()
                .resume()
                .get()
        MockitoAnnotations.initMocks(this)
        presenter = ProfilePresenter(view)
    }

    @Test
    @Throws(Exception::class)
    fun shouldNotBeNull() {
        assertNotNull(activity)
    }

    @Test
    fun requestProfile() {
        presenter.requestProfile(callProfile)
        Mockito.verify(view).showProgressDialog()
        Mockito.verify(callProfile).enqueue(callbackArgumentCaptor.capture())
        callbackArgumentCaptor.value.onResponse(
                Mockito.mock(Call::class.java) as Call<ProfileData>,
                Response.success(profileDataDummy()))
        Mockito.verify(view).hideProgressDialog()
        Mockito.verify(view).onSuccess(profileDataDummy().name)
    }

    private fun profileDataDummy(): ProfileData {
        val data = ProfileData()
        data.success = true
        data.message = "ok"
        data.name = "Maulana"
        data.phone = "0818585858"
        data.address = "Sukabumi"
        return data
    }
}