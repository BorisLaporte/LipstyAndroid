package borislaporte.lipstyapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import borislaporte.lipstyapp.Fragment.LetsFlirtResultFragment;
import borislaporte.lipstyapp.model.Cocktail;

/**
 * Created by moi on 09/06/16.
 */
public class ResultsAdapter extends FragmentPagerAdapter {

    private List<Cocktail> cocktails;
    private ArrayList<LetsFlirtResultFragment> resultFragments;

    public ResultsAdapter(FragmentManager fm, List<Cocktail> cocktails) {
        super(fm);
        this.cocktails = cocktails;
        CreateAllSubFragment( cocktails );
    }

    private void CreateAllSubFragment(List<Cocktail> cocktails){
        resultFragments = new ArrayList<>();
        for (Cocktail s : cocktails) {
            CreateASubFragement(s);
        }

    }

    private void CreateASubFragement(Cocktail cocktail){
        LetsFlirtResultFragment fragment = LetsFlirtResultFragment.newInstance(cocktail);
        resultFragments.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return resultFragments.get(position);
    }

    @Override
    public int getCount() {
        return resultFragments.size();
    }
}
