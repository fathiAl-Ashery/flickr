package com.tigerspike.flickralbum.presentation.album;

import com.google.common.collect.Lists;
import com.tigerspike.flickralbum.TestUseCaseScheduler;
import com.tigerspike.flickralbum.data.AlbumDataSource;
import com.tigerspike.flickralbum.data.AlbumRepository;
import com.tigerspike.flickralbum.domain.Images.LoadAlbumUsecase;
import com.tigerspike.flickralbum.domain.UseCaseHandler;
import com.tigerspike.flickralbum.domain.model.Album;
import com.tigerspike.flickralbum.domain.model.Image;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

/**
 * Unit tests for the implementation of {@link AlbumPresenter}
 */
public class AlbumPresenterTest {

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


    private static final Image IMAGE = new Image(IMAGE_TITLE, IMAGE_DESCRIPTION, IMAGE_AUTHOR, IMAGE_DATE, IMAGE_URL);

    private static final Album ALBUM_WITH_TAG = new Album(ALBUM_TITLE, ALBUM_DESCRIPTION, ALBUM_TAGS, IMAGES);
    private static final Album ALBUM_WITH_OUT_TAG = new Album(ALBUM_TITLE, ALBUM_DESCRIPTION, NO_TAGS, IMAGES);
    private static final Album ALBUM_WITH_NO_IMAGES = new Album(ALBUM_TITLE, ALBUM_DESCRIPTION, ALBUM_TAGS, null);

    private static final Album EMPTY_ALBUM = null;

    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private AlbumContract.View view;

    /**
     * {@link ArgumentCaptor} is a powerful Mockito API to capture argument values and use them to
     * perform further actions or assertions on them.
     */
    @Captor
    private ArgumentCaptor<AlbumDataSource.LoadAlbumCallback> mLoadAlbumCallbackCaptor;

    private AlbumPresenter albumPresenter;

    @Before
    public void setupTasksPresenter() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        albumPresenter = givenAlbumPresenter();

    }

    private AlbumPresenter givenAlbumPresenter() {
        UseCaseHandler useCaseHandler = new UseCaseHandler(new TestUseCaseScheduler());
        LoadAlbumUsecase loadAlbumUsecase = new LoadAlbumUsecase(albumRepository);

        return new AlbumPresenter(useCaseHandler, view, loadAlbumUsecase);
    }

    @Test
    public void loadAlbumFromRepositoryIntoView() {

        albumPresenter.loadAlbum(true);

        // Callback is captured and invoked with stubbed tasks
        verify(albumRepository).getAlbum(mLoadAlbumCallbackCaptor.capture());
        mLoadAlbumCallbackCaptor.getValue().onAlbumLoaded(ALBUM_WITH_OUT_TAG);

        InOrder inOrder = inOrder(view);
        inOrder.verify(view).setLoadingIndicator(true);

        inOrder.verify(view).setLoadingIndicator(false);
        verify(view).showAlbum(ALBUM_WITH_OUT_TAG);

    }

    @Test
    public void loadAlbumWithNoImagesFromRepositoryIntoView() {
//
        albumPresenter.loadAlbum(true);

        // Callback is captured and invoked with stubbed tasks
        verify(albumRepository).getAlbum(mLoadAlbumCallbackCaptor.capture());
        mLoadAlbumCallbackCaptor.getValue().onAlbumLoaded(ALBUM_WITH_NO_IMAGES);

        InOrder inOrder = inOrder(view);
        inOrder.verify(view).setLoadingIndicator(true);

        inOrder.verify(view).setLoadingIndicator(false);
        verify(view).showNoImages();
    }

    @Test
    public void clickOnTask_ShowsDetailUi() {

        // When open task details is requested
        albumPresenter.openImageDetails(IMAGE);

        // Then task detail UI is shown
        verify(view).showImageDetailsUi(IMAGE);
    }

    @Test
    public void unavailableTasks_ShowsError() {
        // When tasks are loaded
        albumPresenter.loadAlbum(true);

        // And the tasks aren't available in the repository
        verify(albumRepository).getAlbum(mLoadAlbumCallbackCaptor.capture());
        mLoadAlbumCallbackCaptor.getValue().onDataNotAvailable();

        // Then an error message is shown
        verify(view).showLoadingError();
    }
}
