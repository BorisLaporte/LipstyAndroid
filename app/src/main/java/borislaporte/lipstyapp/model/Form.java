package borislaporte.lipstyapp.model;

import borislaporte.lipstyapp.R;

/**
 * Created by moi on 05/06/16.
 */
public class Form {
    private Question[] questions;

    public Form() {
        this.questions = new Question[6];

        this.questions[0] = new Question(
                    0,
                    "Who is the target ?",
                    "gender",
                    new Choice[]{
                        new Choice("An incredible god", "man", R.drawable.form_1_man),
                        new Choice("A magnificent godess", "woman", R.drawable.form_1_woman)
                    },
                    false,
                    false,
                    false
                );

        this.questions[1] = new Question(
                    1,
                    "How good are you at making cocktails ?",
                    "skill",
                    new Choice[]{
                        new Choice("I'm a noob", "easy/or/average", R.drawable.form_2_star1),
                        new Choice("I'm not a pro, but I'm not bad at them either", "easy/or/average/or/advanced", R.drawable.form_2_star2),
                        new Choice("I'm so good at them that even impress myself sometimes", "average/or/advanced", R.drawable.form_2_star3),
                        new Choice("I don't care I pay", "null",R.drawable.form_2_money)
                    },
                    true,
                    false,
                    false
                );

        this.questions[2] = new Question(
                    2,
                    "The target seems to be...",
                    "servedin",
                    new Choice[]{
                        new Choice("Fancy as hell", "cocktail-glass/or/wine-glass", R.drawable.form_3_fancy),
                        new Choice("Into sports", "highball-glass/or/cocktail-glass", R.drawable.form_3_sport),
                        new Choice("Mainstream Hipster", "wine-glass/or/rocks-glass", R.drawable.form_3_hipster),
                        new Choice("Basic as you can be", "rocks-glass/or/highball-glass", R.drawable.form_3_basic)
                    },
                    true,
                    false,
                    false
                );

        this.questions[3] = new Question(
                    3,
                    "What color is the target wearing ?",
                    "colored",
                    new Choice[]{
                            new Choice("Yellow, just like the sun", "yellow", R.drawable.form_4_yellow),
                            new Choice("Red because the target is full of passion", "red", R.drawable.form_4_red),
                            new Choice("Orange just like the fire burning in my heart", "brown", R.drawable.form_4_orange),
                            new Choice("Pink and joyfull", "pink", R.drawable.form_4_pink),
                            new Choice("Blue, because blue is cool", "blue", R.drawable.form_4_blue),
                            new Choice("Green because of the nature", "green", R.drawable.form_4_green),
                            new Choice("Black, the target is playing it safe", "clear", R.drawable.form_4_black),
                            new Choice("Something else because other colors exist", "null", R.drawable.form_4_other)
                    },
                    true,
                    true,
                    false
                );

        this.questions[4] = new Question(
                    4,
                    "What's the occasion ?",
                    "for",
                    new Choice[]{
                        new Choice("A nice afternoon drink", "afternoon/or/evening", R.drawable.form_5_afternoon),
                        new Choice("A pre-dinner “mise en bouche”", "pre-dinner/or/afternoon", R.drawable.form_5_amusebouche),
                        new Choice("A chill after dinner","after-dinner/or/pre-dinner", R.drawable.form_5_afterdinner),
                        new Choice("A “fun evening” AKA A party", "evening/or/after-dinner", R.drawable.form_5_party)
                    },
                    true,
                    false,
                    false
                );

        this.questions[5] = new Question(
                    5,
                    "What's the weather ?",
                    "weather",
                    new Choice[]{
                        new Choice("It's raining cats and dogs", "raining", R.drawable.form_6_rain),
                        new Choice("It's freezing and you want to warm the target's heart", "freezing", R.drawable.form_6_freeze),
                        new Choice("It's hot as the target", "hot", R.drawable.form_6_hot),
                        new Choice("You have no idea because you don't go out enough", "null", R.drawable.form_6_else)
                    },
                    false,
                    false,
                    true
                );
    }

    public int getSize(){
        return this.questions.length;
    }

    public Question getQuestionById(int id){
        return this.questions[id];
    }
}
