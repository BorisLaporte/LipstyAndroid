package borislaporte.lipstyapp.model;

/**
 * Created by moi on 15/06/16.
 */
public class ThePunchlines {
    private Punchline[] punchlines;

    public ThePunchlines(){
        this.punchlines = new Punchline[8];

        this.punchlines[0] = new Punchline("yellow", "I've made a bright yellow drink to thank you for illuminating my day.");
        this.punchlines[1] = new Punchline("red", "This cocktail is red. Red is the color of fire and there is a fire that lighted up the moment I met you.");
        this.punchlines[2] = new Punchline("brown", "has anyone told you're magnificent ? Will you let me offer this drink to you ? After all you stole my heart.");
        this.punchlines[3] = new Punchline("pink", "Pink and crazy is this cocktail, as my heart falling for you.");
        this.punchlines[4] = new Punchline("blue", "I turned as blue as this drink when I met you.");
        this.punchlines[5] = new Punchline("green", "I've always thought aliens where green and then I met you.\n(looks confused)\nWell you seem too beautiful to be human so you must be an alien right ? Do you want a green cocktail ?");
        this.punchlines[6] = new Punchline("clear", "I've made you a cocktail as clear as you shrine before me");
        this.punchlines[7] = new Punchline("null", "Please accept this gift, I've made it just for you.");
    }

    public String getByColor(String color){
        for (Punchline punchline : punchlines){
            if ( punchline.getColor().equalsIgnoreCase(color)  ){
                return punchline.getText();
            }
        }
        return "";
    }
}
