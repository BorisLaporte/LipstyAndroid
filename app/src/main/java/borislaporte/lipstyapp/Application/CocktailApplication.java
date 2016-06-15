package borislaporte.lipstyapp.Application;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import borislaporte.lipstyapp.network.LruBitmapCache;

/**
 * Created by moi on 09/06/16.
 */
public class CocktailApplication extends Application {

    private RequestQueue requestQueue;
    //singleton
    private static CocktailApplication singleInstance;
    private ImageLoader imageLoader;

    @Override
    public void onCreate() {
        super.onCreate();

        //On garde une référence de l'instance courante
        //en tant que Singleton
        CocktailApplication.singleInstance = this;

        requestQueue = Volley.newRequestQueue(this);

        LruBitmapCache lruBitmapCache = new LruBitmapCache(8 * 1024 * 1024); //8Mo

        imageLoader = new ImageLoader(requestQueue, lruBitmapCache);
    }

    public static CocktailApplication getSingleInstance() {
        return singleInstance;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }
}
