package com.learn.mock.repository.profile;

import com.learn.mock.model.ProfileData;

/**
 * Created by eminartiys on 17/02/18.
 */

public class ProfileRepository implements ProfileDataSource {

    private ProfileDataSource remoteDataSource;

    public ProfileRepository(ProfileDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void requestProfile(final LoadDataCallback callback) {
        remoteDataSource.requestProfile(new LoadDataCallback() {
            @Override
            public void onDataLoaded(ProfileData profileData) {
                callback.onDataLoaded(profileData);
            }

            @Override
            public void onNoDataLoaded() {
                callback.onNoDataLoaded();
            }

            @Override
            public void onError(Throwable e) {
                callback.onError(e);
            }
        });
    }
}
