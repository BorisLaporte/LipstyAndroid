package borislaporte.lipstyapp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;

import borislaporte.lipstyapp.R;
import borislaporte.lipstyapp.adapter.QuestionsListAdapter;
import borislaporte.lipstyapp.model.Choice;
import borislaporte.lipstyapp.model.Question;

/**
 * A simple {@link Fragment} subclass.
 */
public class LetsFlirtListFormFragment extends Fragment implements AdapterView.OnItemClickListener {

    private static final String QUESTION_ARGUMENTS = "question_arguments";
    private ListView choice_list_view;
    private View view;
    private QuestionsListAdapter adapter;
    private OnAnswerSelectedInterface onAnswerSelectedInterface;


    public LetsFlirtListFormFragment() {
        // Required empty public constructor
    }

    public interface OnAnswerSelectedInterface{
        void onListAnswerSelected(String data);
    }

    public static LetsFlirtListFormFragment newInstance(Question question){
        LetsFlirtListFormFragment letsFlirtListFormFragment = new LetsFlirtListFormFragment();

        Bundle arguments = new Bundle();
        arguments.putParcelable(QUESTION_ARGUMENTS, question);
        letsFlirtListFormFragment.setArguments(arguments);


        return letsFlirtListFormFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        onAnswerSelectedInterface = (OnAnswerSelectedInterface) getActivity();
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_list_lets_flirt_form,container,false);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle arguments = getArguments();


        if(arguments!=null) {
            Question question = arguments.getParcelable(QUESTION_ARGUMENTS);


            startListView(question);
        }
    }

    private void startListView(Question question){
        choice_list_view = (ListView) view.findViewById(R.id.choice_list_view);

        choice_list_view.setOnItemClickListener(this);

        adapter = new QuestionsListAdapter(this.getContext(), Arrays.asList(question.getChoices()));
        choice_list_view.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Choice choice = (Choice) adapter.getItem(position);
        String value = choice.getValue();
        onAnswerSelectedInterface.onListAnswerSelected(value);
    }

}
