package borislaporte.lipstyapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.os.Parcelable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.viewpagerindicator.CirclePageIndicator;


import java.util.Arrays;
import java.util.List;

import borislaporte.lipstyapp.Fragment.LetsFlirtResultFragment;
import borislaporte.lipstyapp.Fragment.LoadingResultFragment;
import borislaporte.lipstyapp.adapter.ResultsAdapter;
import borislaporte.lipstyapp.model.Answer;
import borislaporte.lipstyapp.model.AnswerTotal;
import borislaporte.lipstyapp.model.Cocktail;
import borislaporte.lipstyapp.model.ThePunchlines;
import borislaporte.lipstyapp.network.NetworkManager;

public class LetsFlirtResultActivity extends ToolbarStarterActivity implements LetsFlirtResultFragment.OnCocktailSelectedInterface{
    private static final String EXTRA_PREVIOUS = "extra_previous";
    private static final String EXTRA_COLOR = "extra_color";
    private AnswerTotal answers;
    private FragmentManager fragmentManager;
    private ViewPager viewPager;
    private String EXTRA_COCKTAIL = "extra_cocktail";
    private final Boolean toolbar_back = false;
    private final Boolean toolbar_home = true;
    private FragmentTransaction fragmentTransaction;
    private LoadingResultFragment loadingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        answers = (AnswerTotal) bundle.getParcelable("EXTRA_ANSWERS");
        setContentView(R.layout.activity_lets_flirt_result);

        super.StartToolBar(toolbar_back);

        viewPager = (ViewPager) findViewById(R.id.results_viewpager);


        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        loadingFragment = LoadingResultFragment.newInstance();
        fragmentTransaction.add(R.id.container_viewpager, loadingFragment);
        fragmentTransaction.commit();

        NetworkManager.letsFlirt( answers ,new NetworkManager.CocktailResultListener() {
            @Override
            public void onCocktailsFind(Cocktail[] cocktails) {
                if ( cocktails != null ){
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.remove(loadingFragment);
                    fragmentTransaction.commit();
                    List<Cocktail> cocktailsList = Arrays.asList(cocktails);
                    ResultsAdapter questionsPagerAdapter = new ResultsAdapter( getSupportFragmentManager(), cocktailsList);

                    viewPager.setAdapter(questionsPagerAdapter);

                    CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.dot);
                    indicator.setViewPager(viewPager);
                }
            }

            @Override
            public void onFail() {
                int a = 10;
            }
        });
    }



    @Override
    public void letsgo(Cocktail data) {
        Intent intent = new Intent(this, DetailCocktailActivity.class );
        intent.putExtra(EXTRA_COCKTAIL, data);
        intent.putExtra(EXTRA_PREVIOUS, "result");
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu, toolbar_home);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item, toolbar_back, toolbar_home);
    }
}
