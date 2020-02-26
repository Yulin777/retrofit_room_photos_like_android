package com.yulin.ivan.gurutest.data;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.yulin.ivan.gurutest.R;
import com.yulin.ivan.gurutest.data.entity.Photo;
import com.yulin.ivan.gurutest.data.entity.PhotoList;
import com.yulin.ivan.gurutest.data.rest.GetPhotosListService;
import com.yulin.ivan.gurutest.data.rest.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Ivan Y. on 2020-02-24.
 */

public class PhotoViewModel extends AndroidViewModel {
    private PhotoDao photoDao;

    public PhotoViewModel(@NonNull Application application) {
        super(application);
        AppDatabase appDatabase = AppDatabase.getDatabase(application);
        photoDao = appDatabase.photoDao();
    }

    public void insert(PhotoList photoList, Runnable onFinish) {
        new insertListAsyncTask(photoDao, onFinish).execute(photoList);
    }

    public LiveData<List<Photo>> getAllPhotos() {
        return photoDao.getAllPhotos();
    }

    public LiveData<Photo> getPhoto(String id) {
        return photoDao.getPhoto(id);
    }

    public void updateLiked(String id, boolean liked) {
        new updateLikedAsyncTask(photoDao, id).execute(liked);
    }

    public void fetchData(Context context, Runnable updateUiAfterPhotoListInsert, Runnable onCallError) {
        GetPhotosListService service = RetrofitInstance.getAllPhotosInstance().create(GetPhotosListService.class);
        Call<PhotoList> call = service.getPhotos(context.getString(R.string.member_id), true, 50, 0);

        call.enqueue(new Callback<PhotoList>() {
            @Override
            public void onResponse(Call<PhotoList> call, Response<PhotoList> response) {
                PhotoList photoList = response.body();
                if (photoList != null) {
                    photoList.attachImagesUrls();
                    insert(response.body(), updateUiAfterPhotoListInsert);
                }
            }

            @Override
            public void onFailure(Call<PhotoList> call, Throwable t) {
                onCallError.run();
            }
        });
    }

    public void updateLikes(String id, int likes) {
        new updateLikesAsyncTask(photoDao, id).execute(likes);
    }

    public void updateViews(String id, int views) {
        new updateViewsAsyncTask(photoDao, id).execute(views);
    }

    private static class updateViewsAsyncTask extends AsyncTask<Integer, Void, Void> {
        private final String id;
        private final PhotoDao photoDao;

        public updateViewsAsyncTask(PhotoDao photoDao, String id) {
            this.photoDao = photoDao;
            this.id = id;
        }

        @Override
        protected Void doInBackground(Integer... views) {
            photoDao.updateViews(id, views[0]);
            return null;
        }
    }

    private static class updateLikesAsyncTask extends AsyncTask<Integer, Void, Void> {
        private final String id;
        private final PhotoDao photoDao;

        public updateLikesAsyncTask(PhotoDao photoDao, String id) {
            this.photoDao = photoDao;
            this.id = id;
        }

        @Override
        protected Void doInBackground(Integer... likes) {
            photoDao.updateLikes(id, likes[0]);
            return null;
        }
    }

    private static class updateLikedAsyncTask extends AsyncTask<Boolean, Void, Void> {
        private final String id;
        private final PhotoDao photoDao;

        public updateLikedAsyncTask(PhotoDao photoDao, String id) {
            this.photoDao = photoDao;
            this.id = id;
        }

        @Override
        protected Void doInBackground(Boolean... liked) {
            photoDao.updateLiked(id, liked[0]);
            return null;
        }
    }

    private static class insertListAsyncTask extends AsyncTask<PhotoList, Void, Void> {
        private final PhotoDao photoDao;
        private final Runnable onFinish;

        public insertListAsyncTask(PhotoDao photoDao, Runnable onFinish) {
            this.photoDao = photoDao;
            this.onFinish = onFinish;
        }

        @Override
        protected Void doInBackground(PhotoList... photoLists) {
            photoDao.insert(photoLists[0].getPhotoList());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (onFinish != null) onFinish.run();
        }
    }

}
