package borislaporte.lipstyapp.Fragment;


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
public class LoadingCheckAllFragment extends Fragment {


    private View view;
    private TextView textLoading;

    public LoadingCheckAllFragment() {
        // Required empty public constructor
    }

    public static LoadingCheckAllFragment newInstance(){
        LoadingCheckAllFragment fragment = new LoadingCheckAllFragment();
        Bundle arguments = new Bundle();


        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_loading_check_all, container, false);

        textLoading = (TextView)view.findViewById(R.id.textLoading);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        Typeface lato = Typeface.createFromAsset(getContext().getAssets(), "Lato-Bold.ttf");
        textLoading.setTypeface(lato);
    }

}
