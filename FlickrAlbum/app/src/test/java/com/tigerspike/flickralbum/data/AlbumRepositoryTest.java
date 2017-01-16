package com.tigerspike.flickralbum.data;

import com.google.common.collect.Lists;
import com.tigerspike.flickralbum.domain.model.Album;
import com.tigerspike.flickralbum.domain.model.Image;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * Created by admin on 1/16/17.
 */

public class AlbumRepositoryTest {

    private AlbumRepository mAlbumRepository;

    private static final String ALBUM_TITLE = "title";
    private static final String ALBUM_DESCRIPTION = "descriptiom";
    private static final String ALBUM_TAGS = "tag1, tag2";
    private static final String NO_TAGS = "";

    private static final String IMAGE_TITLE = "Image Title";
    private static final String IMAGE_DESCRIPTION = "descriptiom";
    private static final String IMAGE_AUTHOR ="nobody@flickr.com";
    private static final String IMAGE_DATE ="2017-01-15T22:38:30Z";
    private static final String IMAGE_URL ="https://farm1.staticflickr.com/635/31522499323_ca449d2425_m.jpg";


    private static final List<Image> IMAGES = Lists.newArrayList(new Image(IMAGE_TITLE, IMAGE_DESCRIPTION, IMAGE_AUTHOR, IMAGE_DATE, IMAGE_URL),
            new Image(IMAGE_TITLE, IMAGE_DESCRIPTION, IMAGE_AUTHOR, IMAGE_DATE, IMAGE_URL),
            new Image(IMAGE_TITLE, IMAGE_DESCRIPTION, IMAGE_AUTHOR, IMAGE_DATE, IMAGE_URL),
            new Image(IMAGE_TITLE, IMAGE_DESCRIPTION, IMAGE_AUTHOR, IMAGE_DATE, IMAGE_URL));


    private static final Album ALBUM_WITH_TAG = new Album(ALBUM_TITLE, ALBUM_DESCRIPTION, ALBUM_TAGS, IMAGES);
    private static final Album ALBUM_WITH_OUT_TAG = new Album(ALBUM_TITLE, ALBUM_DESCRIPTION, NO_TAGS, IMAGES);
    private static final Album EMPTY_ALBUM = null;

    @Mock
    AlbumDataSource remoteDataSource;

    @Mock
    private AlbumDataSource.LoadAlbumCallback getAlbumCallback;

    @Captor
    private ArgumentCaptor<AlbumDataSource.LoadAlbumCallback> albumCallbackCaptor;


    @Before
    public void setupTasksRepository() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        mAlbumRepository = AlbumRepository.getInstance(remoteDataSource);
    }

    @After
    public void destroyRepositoryInstance() {
        AlbumRepository.destroyInstance();
    }

    @Test
    public void GetAlbum_ReturnValidAlbum_DataSourceAvailable(){
        mAlbumRepository.getAlbum(getAlbumCallback);

        // And the remote data source has data available
        verify(remoteDataSource).getAlbum(albumCallbackCaptor.capture());
        albumCallbackCaptor.getValue().onAlbumLoaded(ALBUM_WITH_OUT_TAG);

        // Verify the tasks from the local data source are returned
        verify(getAlbumCallback).onAlbumLoaded(ALBUM_WITH_OUT_TAG);
    }

    @Test
    public void GetAlbumWithTag_ReturnValidAlbum_DataSourceAvailable(){
        mAlbumRepository.getAlbum(getAlbumCallback);

        // And the remote data source has data available
        verify(remoteDataSource).getAlbum(albumCallbackCaptor.capture());
        albumCallbackCaptor.getValue().onAlbumLoaded(ALBUM_WITH_TAG);

        // Verify the tasks from the local data source are returned
        verify(getAlbumCallback).onAlbumLoaded(ALBUM_WITH_TAG);
    }

    @Test
    public void GetAlbum_DataSourceNotAvailable(){
        mAlbumRepository.getAlbum(getAlbumCallback);


        verify(remoteDataSource).getAlbum(albumCallbackCaptor.capture());
        albumCallbackCaptor.getValue().onDataNotAvailable();


        verify(getAlbumCallback).onDataNotAvailable();
    }

    @Test
    public void GetAlbum_ReturnEmptyList(){
        mAlbumRepository.getAlbum(getAlbumCallback);


        verify(remoteDataSource).getAlbum(albumCallbackCaptor.capture());
        albumCallbackCaptor.getValue().onAlbumLoaded(EMPTY_ALBUM);


        verify(getAlbumCallback).onAlbumLoaded(EMPTY_ALBUM);
    }

    @Test
    public void GetAlbum_CachedData(){
        //TODO:: implement rest of test cases for the repository with Caching
    }

    @Test
    public void GetAlbum_ClearCachedData(){
        //TODO:: implement rest of test cases for the repository with Caching
    }

}
