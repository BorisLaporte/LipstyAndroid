package borislaporte.lipstyapp.Fragment.LetsFlirtSubFragment;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import borislaporte.lipstyapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsStoryFragment extends Fragment implements View.OnClickListener {


    private static final String STORY_ARGUMENTS = "story_arguments";
    private View view;
    private TextView story;
    private TextView flirt;
    private OnTapStory onTapStory;


    public interface OnTapStory{
        void onTap();
    }

    public DetailsStoryFragment() {
        // Required empty public constructor
    }

    public static DetailsStoryFragment newInstance(String string){
        DetailsStoryFragment fragment = new DetailsStoryFragment();

        Bundle arguments = new Bundle();
        arguments.putString(STORY_ARGUMENTS, string);
        fragment.setArguments(arguments);


        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_details_story, container, false);
        story = (TextView) view.findViewById(R.id.story);
        flirt = (TextView) view.findViewById(R.id.flirt_fact);
        return view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        onTapStory = (OnTapStory) context;

    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle arguments = getArguments();


        if(arguments!=null) {
            String string = arguments.getString(STORY_ARGUMENTS);

            Typeface lato = Typeface.createFromAsset(getContext().getAssets(), "Lato-Bold.ttf");
            story.setTypeface(lato);
            flirt.setTypeface(lato);

            story.setText(string);

            view.setOnClickListener(this);

        }
    }

    @Override
    public void onClick(View v) {
        onTapStory.onTap();
    }

}
