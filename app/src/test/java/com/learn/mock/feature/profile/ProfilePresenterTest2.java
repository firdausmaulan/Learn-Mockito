package com.learn.mock.feature.profile;

import com.learn.mock.BuildConfig;
import com.learn.mock.model.ProfileData;
import com.learn.mock.network.ApiService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import kotlin.jvm.Throws;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ProfilePresenterTest2 {

    @Mock
    ProfilePresenter.View view;
    @Mock
    Call<ProfileData> callProfile;
    @Mock
    ApiService apiService;
    @Captor
    private ArgumentCaptor<Callback<ProfileData>> callBackArgumentProfileData;

    ProfileActivity activity;
    ProfilePresenter presenter;

    @Before
    public void setup() {
        activity = Robolectric.buildActivity(ProfileActivity.class)
                .create()
                .resume()
                .get();
        MockitoAnnotations.initMocks(this);
        presenter = new ProfilePresenter(view);
    }

    @Test
    @Throws(exceptionClasses = Exception.class)
    public void shouldNotBeNull() {
        assertNotNull(activity);
    }

    @Test
    public void requestProfile() {
        //Call<ProfileData> callProfile = Mockito.mock(Call.class);
        presenter.requestProfile(callProfile);
        Mockito.verify(view).showProgressDialog();
        Mockito.verify(callProfile).enqueue(callBackArgumentProfileData.capture());
        callBackArgumentProfileData.getValue()
                .onResponse(callProfile,
                        Response.success(profileDataDummy()));
        Mockito.verify(view).hideProgressDialog();
        Mockito.verify(view).onSuccess(profileDataDummy().getName());
    }

    private ProfileData profileDataDummy() {
        ProfileData data = new ProfileData();
        data.setSuccess(true);
        data.setMessage("ok");
        data.setName("Maulana");
        data.setPhone("081577862479");
        data.setAddress("Sukabumi");
        return data;
    }

}
