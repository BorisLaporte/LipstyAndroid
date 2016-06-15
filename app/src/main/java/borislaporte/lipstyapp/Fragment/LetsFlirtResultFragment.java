package borislaporte.lipstyapp.Fragment;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import borislaporte.lipstyapp.Application.CocktailApplication;
import borislaporte.lipstyapp.R;
import borislaporte.lipstyapp.model.Cocktail;
import borislaporte.lipstyapp.model.Ingredients;
import borislaporte.lipstyapp.model.Skill;

/**
 * A simple {@link Fragment} subclass.
 */
public class LetsFlirtResultFragment extends Fragment implements View.OnClickListener {
    private static final String COCKTAILS_ARGUMENTS = "cocktails_arguments";
    private View view;
    private OnCocktailSelectedInterface onCocktailSelectedInterface;
    private Cocktail cocktail;
    private NetworkImageView cocktailImageView;
    private TextView nameCocktail;
    private String urlImage = "http://assets.absolutdrinks.com/drinks/200x270/";
    private String extensionImg = ".jpg";
    private Button buttonDetail;
    private String id;
    private TextView labelCocktail;
    private TextView ingredientsCocktail;
    private ImageView[] skillImageView;


    public interface OnCocktailSelectedInterface{
        void letsgo(Cocktail data);
    }


    public LetsFlirtResultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_lets_flirt_result, container, false);

        cocktailImageView = (NetworkImageView)view.findViewById(R.id.cocktail_result_imageview);
        nameCocktail = (TextView)view.findViewById(R.id.cocktail_name);
        labelCocktail = (TextView) view.findViewById(R.id.cocktail_label);
        ingredientsCocktail = (TextView)view.findViewById(R.id.cocktail_ingredients);
        skillImageView = new ImageView[]{(ImageView) view.findViewById(R.id.skill_1),
                (ImageView) view.findViewById(R.id.skill_2),
                (ImageView) view.findViewById(R.id.skill_3)};
        buttonDetail = (Button)view.findViewById(R.id.detail_button);


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        onCocktailSelectedInterface = (OnCocktailSelectedInterface) context;
    }

    public static LetsFlirtResultFragment newInstance(Cocktail cocktail){
        LetsFlirtResultFragment fragment = new LetsFlirtResultFragment();
        Bundle arguments = new Bundle();
        arguments.putParcelable(COCKTAILS_ARGUMENTS, cocktail);
        fragment.setArguments(arguments);


        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle arguments = getArguments();

        if(arguments!=null) {
            cocktail = arguments.getParcelable(COCKTAILS_ARGUMENTS);
            ImageLoader imageLoader = CocktailApplication.getSingleInstance()
                    .getImageLoader();
            String imageUrl = urlImage+cocktail.getImage_path()+extensionImg;

            Typeface lato = Typeface.createFromAsset(getContext().getAssets(), "Lato-Bold.ttf");
            nameCocktail.setTypeface(lato);
            ingredientsCocktail.setTypeface(lato);
            buttonDetail.setTypeface(lato);

            Typeface latoBolditalic = Typeface.createFromAsset(getContext().getAssets(), "Lato-BoldItalic.ttf");
            labelCocktail.setTypeface(latoBolditalic);


            cocktailImageView.setImageUrl(imageUrl, imageLoader);
            nameCocktail.setText(cocktail.getName());
            String txtIng = ingredientsParser(cocktail.getIngredients());
            ingredientsCocktail.setText(txtIng);

            setSkill(cocktail.getSkill());

            buttonDetail.setOnClickListener(this);



        }
    }


    private String ingredientsParser(Ingredients[] ingredients){
        String textIngredients = "";
        Boolean secondLine = false;
        for(int i = 0; i < ingredients.length; i++) {
            String theText = ingredients[i].getText();
            textIngredients += theText.substring(theText.indexOf("[") + 1,theText.indexOf("]"));
            if ( textIngredients.length() >= 25 && !secondLine ){
                textIngredients += "\n";
                secondLine = true;
            }
            else if ( i < ingredients.length - 1 ){
                textIngredients += " - ";
            }
        }
        return textIngredients;
    }

    private void setSkill(Skill skill){
        int value = skill.getValue();
        for(int i = 0; i < 3; i++){
            if ( i < value ){
                skillImageView[i].setImageResource(R.drawable.skill_star_on);
            } else {
                skillImageView[i].setImageResource(R.drawable.skill_star_off);
            }
        }
    }

    @Override
    public void onClick(View v) {
        onCocktailSelectedInterface.letsgo(cocktail);
    }

}
