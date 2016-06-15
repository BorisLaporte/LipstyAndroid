package borislaporte.lipstyapp.Fragment.LetsFlirtSubFragment;


import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import borislaporte.lipstyapp.R;
import borislaporte.lipstyapp.model.Choice;

/**
 * A simple {@link Fragment} subclass.
 */
public class LetsFlirtSubFragment extends Fragment implements View.OnClickListener {
    private static final String CHOICE_ARGUMENTS = "choice_arguments";
    private Choice choice;
    private TextView choice_key;
    private View view;
    private OnColorSelectedInterface onColorSelectedInterface;
    private ImageView image;


    public interface OnColorSelectedInterface{
        void onColorSelected(String data);
    }


    public LetsFlirtSubFragment() {
        // Required empty public constructor
    }

    public static LetsFlirtSubFragment newInstance(Choice choice){
        LetsFlirtSubFragment letsFlirtSubFragment = new LetsFlirtSubFragment();
        Bundle arguments = new Bundle();
        arguments.putParcelable(CHOICE_ARGUMENTS, choice);
        letsFlirtSubFragment.setArguments(arguments);


        return letsFlirtSubFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_lets_flirt_sub,container,false);
        choice_key = (TextView) view.findViewById(R.id.key);
        image = (ImageView) view.findViewById(R.id.image);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        onColorSelectedInterface = (OnColorSelectedInterface) context;
    }


    @Override
    public void onStart() {
        super.onStart();

        Bundle arguments = getArguments();

        if(arguments!=null) {
            choice = arguments.getParcelable(CHOICE_ARGUMENTS);

            Typeface lato = Typeface.createFromAsset(getContext().getAssets(), "Lato-BoldItalic.ttf");
            choice_key.setTypeface(lato);

            choice_key.setText(choice.getKey());

            image.setImageResource(choice.getImageId());
            view.setOnClickListener(this);



        }
    }

    @Override
    public void onClick(View v) {
        String value = choice.getValue();
        onColorSelectedInterface.onColorSelected(value);
    }

}
