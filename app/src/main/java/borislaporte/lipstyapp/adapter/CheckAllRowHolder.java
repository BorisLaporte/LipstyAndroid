package borislaporte.lipstyapp.adapter;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import borislaporte.lipstyapp.Application.CocktailApplication;
import borislaporte.lipstyapp.R;
import borislaporte.lipstyapp.model.Cocktail;

/**
 * Created by moi on 11/06/16.
 */
public class CheckAllRowHolder {

    private final TextView textViewCocktailName;
    private final NetworkImageView imageViewCocktail;
    private String urlImage = "http://assets.absolutdrinks.com/drinks/100x140/";
//    private String urlImage = "http://assets.absolutdrinks.com/drinks/transparent-background-white/70x100/";
//    private String extensionImg = ".png";
    private String extensionImg = ".jpg";

    public CheckAllRowHolder(View view) {
        textViewCocktailName = (TextView) view.
                findViewById(R.id.row_name);
        imageViewCocktail = (NetworkImageView) view.
                findViewById(R.id.row_imageview);


        Typeface font = Typeface.createFromAsset(view.getContext().getAssets(), "Lato-Bold.ttf");
        textViewCocktailName.setTypeface(font);
    }

    public void refreshWithCocktails(Cocktail cocktail) {
        textViewCocktailName.setText(cocktail.getName());
        String imageUrl = urlImage+cocktail.getImage_path()+extensionImg;

        ImageLoader imageLoader =
                CocktailApplication.
                        getSingleInstance().
                        getImageLoader();

        imageViewCocktail.setImageUrl(imageUrl, imageLoader);

    }
}
