package borislaporte.lipstyapp;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import borislaporte.lipstyapp.Application.CocktailApplication;
import borislaporte.lipstyapp.Fragment.LetsFlirtSubFragment.DetailsStoryFragment;
import borislaporte.lipstyapp.model.Cocktail;
import borislaporte.lipstyapp.model.Ingredients;
import borislaporte.lipstyapp.model.Skill;
import borislaporte.lipstyapp.model.ThePunchlines;

public class DetailCocktailActivity extends ToolbarStarterActivity implements DetailsStoryFragment.OnTapStory{

    private static final String EXTRA_URI = "extra_uri";
    private final Boolean toolbar_back = true;
    private final Boolean toolbar_home = true;
    private NetworkImageView image;
    private String jpgUrlImage = "http://assets.absolutdrinks.com/drinks/200x270/";
    private String jpgExtensionImg = ".jpg";
    private DetailsStoryFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        final Cocktail cocktail = (Cocktail) bundle.getParcelable("extra_cocktail");

        String previous = (String) bundle.getString("extra_previous");
        setContentView(R.layout.activity_detail_cocktail);

        TextView name = (TextView) findViewById(R.id.cocktail_name);
        TextView description = (TextView) findViewById(R.id.cocktail_desc);
        TextView ingredients = (TextView) findViewById(R.id.cocktail_ingredients);
        TextView punchline = (TextView) findViewById(R.id.punchline);
        image = (NetworkImageView) findViewById(R.id.cocktail_result_imageview);
        ImageView[] skillImageView = new ImageView[]{(ImageView) findViewById(R.id.skill_1),
                (ImageView) findViewById(R.id.skill_2),
                (ImageView) findViewById(R.id.skill_3)};
        Button story = (Button) findViewById(R.id.btn_story_cocktail);
        Button video = (Button) findViewById(R.id.btn_cocktail_video);


        Typeface lato = Typeface.createFromAsset(getAssets(), "Lato-Bold.ttf");
        name.setTypeface(lato);
        description.setTypeface(lato);
        ingredients.setTypeface(lato);
        story.setTypeface(lato);
        video.setTypeface(lato);

        Typeface latoBolditalic = Typeface.createFromAsset(getAssets(), "Lato-BoldItalic.ttf");
        punchline.setTypeface(latoBolditalic);


        ImageLoader imageLoader = CocktailApplication.getSingleInstance()
                .getImageLoader();

        String imageUrl;


        imageUrl = jpgUrlImage+cocktail.getImage_path()+jpgExtensionImg;


        image.setImageUrl(imageUrl, imageLoader);

        setSkill(cocktail.getSkill(), skillImageView);
        String txtIng = ingredientsParser(cocktail.getIngredients());
        ingredients.setText(txtIng);
        if ( !cocktail.getColor().equalsIgnoreCase("")  ){
            punchline.setText(punchlineGenerator(cocktail.getColor().toLowerCase()));
        }

        name.setText(cocktail.getName());
        description.setText(cocktail.getDescription());

        if ( cocktail.getStory().equalsIgnoreCase("") ){
            story.setVisibility(View.GONE);
        }else{
            story.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragment = DetailsStoryFragment.newInstance(cocktail.getStory());
                    fragmentTransaction.add(R.id.container, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
        }
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), VideoActivity.class );
                intent.putExtra(EXTRA_URI, cocktail.getVideos()[0].getVideoPath());
                startActivity(intent);
            }
        });

        super.StartToolBar(toolbar_back);
    }


    private String ingredientsParser(Ingredients[] ingredients){
        String textIngredients = "";
        for(int i = 0; i < ingredients.length; i++) {
            String theText = ingredients[i].getTextPlain();
            textIngredients += theText;
            if ( i < ingredients.length - 1 ){
                textIngredients += "\n";
            }
        }
        return textIngredients;
    }


    private void setSkill(Skill skill, ImageView[] skillImageView){
        int value = skill.getValue();
        for(int i = 0; i < 3; i++){
            if ( i < value ){
                skillImageView[i].setImageResource(R.drawable.skill_star_on);
            } else {
                skillImageView[i].setImageResource(R.drawable.skill_star_off);
            }
        }
    }

    private String punchlineGenerator(String color){
        ThePunchlines thePunchlines = new ThePunchlines();
        return thePunchlines.getByColor(color);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu, toolbar_home);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item, toolbar_back, toolbar_home);
    }

    @Override
    public void onTap() {
        super.onBackPressed();
    }
}
