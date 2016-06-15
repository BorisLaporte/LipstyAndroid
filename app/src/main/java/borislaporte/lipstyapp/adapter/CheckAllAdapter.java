package borislaporte.lipstyapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import borislaporte.lipstyapp.R;
import borislaporte.lipstyapp.model.Cocktail;

/**
 * Created by moi on 11/06/16.
 */
public class CheckAllAdapter extends BaseAdapter {

    private List<Cocktail> cocktails;
    private final LayoutInflater layoutInflater;

    public CheckAllAdapter(Context context){
        cocktails = new ArrayList<Cocktail>();
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cocktails.size();
    }

    @Override
    public Object getItem(int position) {
        return cocktails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null) {
            boolean attachToParent = false;

            convertView = layoutInflater.inflate(R.layout.check_all_row, parent, attachToParent);

            convertView.setTag(new CheckAllRowHolder(convertView));
        }

        Cocktail cocktail = cocktails.get(position);

        CheckAllRowHolder checkAllRowHolder = (CheckAllRowHolder) convertView.getTag();
        checkAllRowHolder.refreshWithCocktails(cocktail);

        return convertView;
    }

    public void refreshWithCocktails(List<Cocktail> movies) {
        this.cocktails.clear();
        this.cocktails.addAll(movies);

        notifyDataSetChanged();
    }
}
