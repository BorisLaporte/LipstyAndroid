package borislaporte.lipstyapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import borislaporte.lipstyapp.model.Choice;

/**
 * Created by moi on 05/06/16.
 */
public class Question implements Parcelable{
    private int id;
    private String key;
    private String value;
    private Choice[] choices;
    private boolean inRequest;
    private boolean inPunchline;
    private boolean isFinal;

    public Question(int id, String key, String value, Choice[] choices, boolean inRequest, Boolean inPunchline, boolean isFinal) {
        this.id = id;
        this.key = key;
        this.value = value;
        this.choices = choices;
        this.inRequest = inRequest;
        this.inPunchline = inPunchline;
        this.isFinal = isFinal;
    }

    public int getSize() {
        return this.choices.length;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Choice[] getChoices() {
        return choices;
    }

    public void setChoices(Choice[] choices) {
        this.choices = choices;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    public boolean isInRequest() {
        return inRequest;
    }

    public void setInRequest(boolean inRequest) {
        this.inRequest = inRequest;
    }

    public boolean isInPunchline() {
        return inPunchline;
    }

    public void setInPunchline(boolean inPunchline) {
        this.inPunchline = inPunchline;
    }

    public Question(Parcel in){
        this.id = in.readInt();
        this.key = in.readString();
        this.value = in.readString();
        this.choices = (Choice[]) in.createTypedArray(Choice.CREATOR);
        this.inRequest = ( in.readInt() == 1 );
        this.inPunchline = ( in.readInt() == 1 );
        this.isFinal = ( in.readInt() == 1 );
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.key);
        dest.writeString(this.value);
        dest.writeTypedArray(this.choices, 0);
        dest.writeInt(this.inRequest ? 1 :0 );
        dest.writeInt(this.inPunchline ? 1 :0 );
        dest.writeInt(this.isFinal ? 1 :0 );
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

}
