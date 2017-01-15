/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tigerspike.flickralbum.data.source.remote;

import android.support.annotation.NonNull;

import com.tigerspike.flickralbum.data.ImagesDataSource;

/**
 * Implementation of the data source that adds a latency simulating network.
 */
public class ImagesRemoteDataSource implements ImagesDataSource {

    private static ImagesRemoteDataSource INSTANCE;



    public static ImagesRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ImagesRemoteDataSource();
        }
        return INSTANCE;
    }

    // Prevent direct instantiation.
    private ImagesRemoteDataSource() {}

    @Override
    public void getImages(final @NonNull ImagesDataSource.LoadImagesCallback callback) {
        // Call the Networkings' classes to retrieve the Images from the remote service
    }

    //TODO:: Add another method to retrieve images with tag

    @Override
    public void getImage(@NonNull String imageId, final @NonNull GetImageCallback callback) {

    }


    @Override
    public void refreshImages() {
        // Not required because the {@link ImagesRepository} handles the logic of refreshing the
        // images from all the available data sources.
    }


}
