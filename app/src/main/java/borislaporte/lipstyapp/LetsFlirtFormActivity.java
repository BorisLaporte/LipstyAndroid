package borislaporte.lipstyapp;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


import borislaporte.lipstyapp.Fragment.LetsFlirtGridFormFragment;
import borislaporte.lipstyapp.Fragment.LetsFlirtListFormFragment;
import borislaporte.lipstyapp.Fragment.LetsFlirtPagerFormFragment;
import borislaporte.lipstyapp.Fragment.LetsFlirtSubFragment.LetsFlirtSubFragment;
import borislaporte.lipstyapp.model.Answer;
import borislaporte.lipstyapp.model.AnswerTotal;
import borislaporte.lipstyapp.model.Form;
import borislaporte.lipstyapp.model.Question;

public class LetsFlirtFormActivity extends ToolbarStarterActivity implements LetsFlirtGridFormFragment.OnAnswerSelectedInterface, LetsFlirtSubFragment.OnColorSelectedInterface, LetsFlirtListFormFragment.OnAnswerSelectedInterface{

    private Form form;
    private int index;
    private Question question;
    private Answer[] answers;
    private FragmentManager fragmentManager;
    private TextView text_step;
    private TextView text_question;
    private String EXTRA_ANSWER = "EXTRA_ANSWERS";
    private final Boolean toolbar_back = true;
    private final Boolean toolbar_home = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        form = new Form();
        index = 0;
        answers = new Answer[form.getSize()];

        setContentView(R.layout.activity_lets_flirt_form);

        super.StartToolBar(toolbar_back);


        text_step = (TextView) findViewById(R.id.step);
        text_question = (TextView) findViewById(R.id.question);

        Typeface lato = Typeface.createFromAsset(getAssets(), "Lato-BoldItalic.ttf");
        text_step.setTypeface(lato);

        Typeface latoBold = Typeface.createFromAsset(getAssets(), "Lato-Bold.ttf");
        text_question.setTypeface(latoBold);

        FirstdisplayFragment();


    }

    @Override
    public void onBackPressed() {

        if ( index > 0 ){
            index --;
        }

        question = form.getQuestionById(index);
        updateStep();

        int count = getSupportFragmentManager().getBackStackEntryCount();
        if ( count < 1 ){
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }

    }

    private void FirstdisplayFragment(){
        question = form.getQuestionById(0);

        fragmentManager = getSupportFragmentManager();


        if ( question.getSize() <= 2 ) {
            startListFragment(true);
        } else if ( question.getSize() <= 4 ) {
            startGridFragment(true);
        } else {
            startPagerFragment(true);

        }

    }

    private void displayFormFragment(int id) {

        question = form.getQuestionById(id);


        if ( question.getSize() <= 2 ) {
            startListFragment(false);
        } else if ( question.getSize() <= 4 ) {
            startGridFragment(false);
        } else {
            startPagerFragment(false);

        }
    }

    private void startGridFragment(boolean firstTime){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        LetsFlirtGridFormFragment fragment = LetsFlirtGridFormFragment.newInstance(question);

        if ( firstTime ){
            fragmentTransaction.add(R.id.placeholderForm, fragment);
        } else {
            fragmentTransaction.replace(R.id.placeholderForm, fragment);
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();

        updateStep();
    }

    private void startListFragment(boolean firstTime){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        LetsFlirtListFormFragment fragment = LetsFlirtListFormFragment.newInstance(question);

        if ( firstTime ){
            fragmentTransaction.add(R.id.placeholderForm, fragment);
        } else {
            fragmentTransaction.replace(R.id.placeholderForm, fragment);
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();

        updateStep();
    }

    private void startPagerFragment(boolean firstTime){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        LetsFlirtPagerFormFragment fragment = LetsFlirtPagerFormFragment.newInstance(question);

        if ( firstTime ){
            fragmentTransaction.add(R.id.placeholderForm, fragment);
        } else {
            fragmentTransaction.replace(R.id.placeholderForm, fragment);
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();

        updateStep();
    }

    @Override
    public void onGridAnswerSelected(String data) {
        onAnswerSelected(data);
    }

    @Override
    public void onListAnswerSelected(String data) {
        onAnswerSelected(data);
    }

    @Override
    public void onColorSelected(String data) {
        onAnswerSelected(data);
    }

    private void onAnswerSelected(String data){
        answers[index] = new Answer(question.getValue(), data, question.isInRequest(), question.isInPunchline());
        boolean isFinal = question.isFinal();

        if ( isFinal ){
            Intent intent = new Intent(this, LetsFlirtResultActivity.class );
            AnswerTotal answerTotal = new AnswerTotal();
            answerTotal.setAnswers(answers);
            intent.putExtra(EXTRA_ANSWER, (Parcelable) answerTotal);
            startActivity(intent);
        }
        else if ( index < form.getSize() - 1 ){
            index ++;
            displayFormFragment(index);
        }
    }

    private void updateStep(){
        text_question.setText(question.getKey());
        text_step.setText( (index + 1) + " / " + form.getSize());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu, toolbar_home);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       return super.onOptionsItemSelected(item, toolbar_back, toolbar_home);
    }


}
