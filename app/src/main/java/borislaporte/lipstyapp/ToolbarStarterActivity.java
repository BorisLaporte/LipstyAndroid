package borislaporte.lipstyapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * Created by moi on 12/06/16.
 */
public class ToolbarStarterActivity extends AppCompatActivity {
    private Toolbar myToolbar;

    protected TextView StartToolBar(Boolean back) {

        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView title = (TextView) myToolbar.findViewById(R.id.custom_title);

        Typeface graphic = Typeface.createFromAsset(getAssets(), "Graphics.ttf");
        Typeface latoBold = Typeface.createFromAsset(getAssets(), "Lato-Bold.ttf");
        title.setTypeface(graphic);

        final Field staticField;
        try {
            staticField = Typeface.class.getDeclaredField("MONOSPACE");
            staticField.setAccessible(true);
            staticField.set(null, latoBold);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        if ( back ){
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        return title;
    }

    public boolean onOptionsItemSelected(MenuItem item, Boolean back, Boolean home) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if ( home && id == R.id.action_home_custom) {
            Intent intent = new Intent(this, MainActivity.class );
            startActivity(intent);
            return true;
        }

        if (back && id == android.R.id.home){
            this.onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu, Boolean home) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        if ( !home ){
            MenuItem homeItem = menu.findItem(R.id.action_home_custom);
            homeItem.setVisible(false);
        }
        return true;
    }
}
