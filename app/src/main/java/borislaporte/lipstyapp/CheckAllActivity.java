package borislaporte.lipstyapp;

import android.content.Intent;
import android.graphics.Movie;
import android.graphics.Typeface;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import borislaporte.lipstyapp.Fragment.LoadingCheckAllFragment;
import borislaporte.lipstyapp.adapter.CheckAllAdapter;
import borislaporte.lipstyapp.model.Cocktail;
import borislaporte.lipstyapp.network.NetworkManager;

import android.widget.TextView;
import android.widget.Toast;

public class CheckAllActivity extends ToolbarStarterActivity implements AdapterView.OnItemClickListener{

    private static final String EXTRA_COCKTAIL = "extra_cocktail";
    private static final String EXTRA_PREVIOUS = "extra_previous";
    private CheckAllAdapter checkAllAdapter;
    private final Boolean toolbar_back = true;
    private final Boolean toolbar_home = true;
    private EditText search;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private LoadingCheckAllFragment loadingFragment;
    private TextView label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_all);

        super.StartToolBar(toolbar_back);

        List<Cocktail> cocktails= new ArrayList<Cocktail>();

        checkAllAdapter = new CheckAllAdapter(this);
        checkAllAdapter.refreshWithCocktails(cocktails);

        search = (EditText) findViewById(R.id.check_all_search);
        label = (TextView) findViewById(R.id.label_editText);

        Typeface latoReg = Typeface.createFromAsset(getAssets(), "Lato-Regular.ttf");
        label.setTypeface(latoReg);

        Typeface latoBold = Typeface.createFromAsset(getAssets(), "Lato-Bold.ttf");
        search.setTypeface(latoBold);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        loadingFragment = LoadingCheckAllFragment.newInstance();
        fragmentTransaction.add(R.id.container_list, loadingFragment);
        fragmentTransaction.commit();

        ListView listView = (ListView) findViewById(R.id.check_all_list_view);

        listView.setOnItemClickListener(this);

        listView.setAdapter(checkAllAdapter);

        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                queryCocktail(v.getText().toString().replace(" ", "%20"), false);
                return false;
            }
        });

        queryCocktail("", true);
    }

    private void queryCocktail(String query, final Boolean removeFragment){
        NetworkManager.queryCocktail(query, new NetworkManager.CocktailResultListener() {

            @Override
            public void onCocktailsFind(Cocktail[] cocktails) {
                if ( removeFragment ){
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.remove(loadingFragment);
                    fragmentTransaction.commit();
                }
                List<Cocktail> cocktailsList = Arrays.asList(cocktails);
                checkAllAdapter.refreshWithCocktails(cocktailsList);
            }

            @Override
            public void onFail() {
                int a = 10;
            }
        });
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, DetailCocktailActivity.class );
        Cocktail cocktail = (Cocktail) checkAllAdapter.getItem(position);
        intent.putExtra(EXTRA_COCKTAIL, cocktail);
        intent.putExtra(EXTRA_PREVIOUS, "check_all");
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
