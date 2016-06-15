package borislaporte.lipstyapp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.Arrays;

import borislaporte.lipstyapp.Fragment.LetsFlirtSubFragment.LetsFlirtSubFragment;
import borislaporte.lipstyapp.R;
import borislaporte.lipstyapp.adapter.QuestionsPagerAdapter;
import borislaporte.lipstyapp.model.Choice;
import borislaporte.lipstyapp.model.Question;

/**
 * A simple {@link Fragment} subclass.
 */
public class LetsFlirtPagerFormFragment extends Fragment{

    private static final String QUESTION_ARGUMENTS = "question_arguments";
    private ViewPager viewPager;
    private View view;
    private QuestionsPagerAdapter adapter;
    private View rootView;

    public LetsFlirtPagerFormFragment() {
        // Required empty public constructor
    }

    public static LetsFlirtPagerFormFragment newInstance(Question question){
        LetsFlirtPagerFormFragment letsFlirtPagerFormFragment = new LetsFlirtPagerFormFragment();

        Bundle arguments = new Bundle();
        arguments.putParcelable(QUESTION_ARGUMENTS, question);
        letsFlirtPagerFormFragment.setArguments(arguments);


        return letsFlirtPagerFormFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_lets_flirt_pager_form_fragement, container, false);

        viewPager = (ViewPager)rootView.findViewById(R.id.lets_flirt_viewpager);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle arguments = getArguments();


        if(arguments!=null) {
            Question question = arguments.getParcelable(QUESTION_ARGUMENTS);
            QuestionsPagerAdapter questionsPagerAdapter = new QuestionsPagerAdapter(getFragmentManager(), Arrays.asList(question.getChoices()));

            viewPager.setAdapter(questionsPagerAdapter);

            CirclePageIndicator indicator = (CirclePageIndicator) rootView.findViewById(R.id.dot);
            indicator.setViewPager(viewPager);
        }


    }
}
