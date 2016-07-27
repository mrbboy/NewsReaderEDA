package uk.ac.kent.gacc2.newsreadereda;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by gacc2 on 12/04/16.
 */
public class ArticlesApp extends Application {

    static private ArticlesApp instance;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    public static ArticlesApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        instance = this;

        //create the request queue
        requestQueue = Volley.newRequestQueue(this);

        //create the image loader
        int cacheSize = 4 * 1024 * 1024; //4MiB
        imageLoader = new ImageLoader(requestQueue, new LruBitmapCache(cacheSize));
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }
}
