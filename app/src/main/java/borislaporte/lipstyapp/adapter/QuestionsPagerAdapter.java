package borislaporte.lipstyapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import borislaporte.lipstyapp.Fragment.LetsFlirtSubFragment.LetsFlirtSubFragment;
import borislaporte.lipstyapp.model.Choice;
import borislaporte.lipstyapp.model.Question;

/**
 * Created by moi on 09/06/16.
 */
public class QuestionsPagerAdapter extends FragmentPagerAdapter {

    private List<Choice> choices;
    private ArrayList<LetsFlirtSubFragment> questionFragment;

    public QuestionsPagerAdapter(FragmentManager fm, List<Choice> choices) {
        super(fm);
        this.choices = choices;
        CreateAllSubFragment(choices);
    }

    private void CreateAllSubFragment(List<Choice> choices){
        questionFragment = new ArrayList<>();
        for (Choice s : choices) {
            CreateASubFragement(s);
        }

    }

    private void CreateASubFragement(Choice choice){
        LetsFlirtSubFragment fragment = LetsFlirtSubFragment.newInstance(choice);
        questionFragment.add(fragment);
    }



    @Override
    public LetsFlirtSubFragment getItem(int position) {
        return questionFragment.get(position);
    }

    @Override
    public int getCount() {
        return questionFragment.size();
    }


}
