package com.learn.mock.repository.profile;

import com.learn.mock.model.ProfileData;

import java.util.List;

/**
 * Created by eminartiys on 17/02/18.
 */

public interface ProfileDataSource {

    void requestProfile(LoadDataCallback callback);

    interface LoadDataCallback {

        void onDataLoaded(ProfileData profileData);

        void onNoDataLoaded();

        void onError(Throwable e);
    }
}
