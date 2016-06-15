package borislaporte.lipstyapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ToolbarStarterActivity {

    private final Boolean toolbar_back = false;
    private final Boolean toolbar_home = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView title = super.StartToolBar(toolbar_back);
        title.setVisibility(View.VISIBLE);

        Button lets_flirt = (Button) findViewById(R.id.btn_lets_flirt);
        Button check_all = (Button) findViewById(R.id.btn_check_all);

        TextView flirty = (TextView) findViewById(R.id.absolut_flirty);
        TextView intro = (TextView) findViewById(R.id.text_intro);

        Typeface graphics = Typeface.createFromAsset(getAssets(), "Graphics.ttf");
        flirty.setTypeface(graphics);

        Typeface lato = Typeface.createFromAsset(getAssets(), "Lato-BoldItalic.ttf");
        intro.setTypeface(lato);

        Typeface latoBold = Typeface.createFromAsset(getAssets(), "Lato-Bold.ttf");
        lets_flirt.setTypeface(latoBold);
        check_all.setTypeface(latoBold);


        lets_flirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_lets_flirt();
            }
        });

        check_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_check_all();
            }
        });

    }

    private void start_check_all() {
        Intent intent = new Intent(this, CheckAllActivity.class );
        startActivity(intent);
    }

    private void start_lets_flirt() {
        Intent intent = new Intent(this, LetsFlirtFormActivity.class );
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu, toolbar_home);
    }
}
