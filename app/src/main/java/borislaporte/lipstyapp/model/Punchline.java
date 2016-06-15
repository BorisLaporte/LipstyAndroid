package borislaporte.lipstyapp.model;

/**
 * Created by moi on 15/06/16.
 */
public class Punchline {
    private String color;
    private String text;

    public Punchline(String color, String text) {
        this.color = color;
        this.text = text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
