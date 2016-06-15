package borislaporte.lipstyapp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.Arrays;

import borislaporte.lipstyapp.R;
import borislaporte.lipstyapp.adapter.QuestionsGridAdapter;
import borislaporte.lipstyapp.model.Choice;
import borislaporte.lipstyapp.model.Question;

/**
 * A simple {@link Fragment} subclass.
 */
public class LetsFlirtGridFormFragment extends Fragment implements AdapterView.OnItemClickListener{


    private static final String QUESTION_ARGUMENTS = "question_arguments";
    private GridView gridView;
    private View view;
    private QuestionsGridAdapter adapter;
    private OnAnswerSelectedInterface onAnswerSelectedInterface;


    public LetsFlirtGridFormFragment() {
        // Required empty public constructor
    }

    public interface OnAnswerSelectedInterface{
        void onGridAnswerSelected(String data);
    }

    public static LetsFlirtGridFormFragment newInstance(Question question){
        LetsFlirtGridFormFragment letsFlirtGridFormFragment = new LetsFlirtGridFormFragment();

        Bundle arguments = new Bundle();
        arguments.putParcelable(QUESTION_ARGUMENTS, question);

        letsFlirtGridFormFragment.setArguments(arguments);

        return letsFlirtGridFormFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        onAnswerSelectedInterface = (OnAnswerSelectedInterface) getActivity();
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_grid_lets_flirt_form,container,false);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle arguments = getArguments();


        if(arguments!=null) {
            Question question = arguments.getParcelable(QUESTION_ARGUMENTS);


            startGridView(question);
        }
    }

    private void startGridView(Question question){
        gridView = (GridView) view.findViewById(R.id.gridview);

        gridView.setOnItemClickListener(this);

        adapter = new QuestionsGridAdapter(this.getContext(), Arrays.asList(question.getChoices()));
        gridView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Choice choice = (Choice) adapter.getItem(position);
        String value = choice.getValue();
        onAnswerSelectedInterface.onGridAnswerSelected(value);
    }

}
