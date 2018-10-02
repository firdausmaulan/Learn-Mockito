package com.learn.mock.repository.profile;

import com.learn.mock.model.ProfileData;
import com.learn.mock.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by eminartiys on 17/02/18.
 */

public class ProfileRemoteDataSource implements ProfileDataSource {

    private ApiService service;

    public ProfileRemoteDataSource(ApiService service) {
        this.service = service;
    }

    @Override
    public void requestProfile(final LoadDataCallback callback) {
        service.requestProfile().enqueue(new Callback<ProfileData>() {
            @Override
            public void onResponse(Call<ProfileData> call, Response<ProfileData> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    callback.onDataLoaded(response.body());
                } else {
                    callback.onNoDataLoaded();
                }
            }

            @Override
            public void onFailure(Call<ProfileData> call, Throwable t) {
                callback.onError(t);
            }
        });
    }
}
