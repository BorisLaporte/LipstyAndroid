package borislaporte.lipstyapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by moi on 13/06/16.
 */
public class Ingredients implements Parcelable {
    private String type;
    private String id;
    private String text;
    private String textPlain;


    public Ingredients(){

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTextPlain() {
        return textPlain;
    }

    public void setTextPlain(String textPlain) {
        this.textPlain = textPlain;
    }

    public Ingredients(Parcel in){
        this.type = in.readString();
        this.id = in.readString();
        this.text = in.readString();
        this.textPlain = in.readString();
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeString(this.id);
        dest.writeString(this.text);
        dest.writeString(this.textPlain);
    }

    public static final Creator<Ingredients> CREATOR = new Creator<Ingredients>() {
        @Override
        public Ingredients createFromParcel(Parcel in) {
            return new Ingredients(in);
        }

        @Override
        public Ingredients[] newArray(int size) {
            return new Ingredients[size];
        }
    };
}
