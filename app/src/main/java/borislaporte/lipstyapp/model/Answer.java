package borislaporte.lipstyapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by moi on 05/06/16.
 */
public class Answer implements Parcelable{
    private String key;
    private String value;
    private Boolean inRequest;
    private Boolean inPunchline;

    public Answer(String key, String value, Boolean inRequest, boolean inPunchline) {
        this.key = key;
        this.value = value;
        this.inRequest = inRequest;
        this.inPunchline = inPunchline;
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

    public Boolean getInRequest() {
        return inRequest;
    }

    public void setInRequest(Boolean inRequest) {
        this.inRequest = inRequest;
    }

    public Boolean getInPunchline() {
        return inPunchline;
    }

    public void setInPunchline(Boolean inPunchline) {
        this.inPunchline = inPunchline;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Answer(Parcel in){
        this.key = in.readString();
        this.value = in.readString();
        this.inRequest = ( in.readInt() == 1 );
        this.inPunchline = ( in.readInt() == 1 );
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.key);
        dest.writeString(this.value);
        dest.writeInt(this.inRequest ? 1 :0 );
        dest.writeInt(this.inPunchline ? 1 :0 );
    }
    public static final Creator<Answer> CREATOR = new Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel in) {
            return new Answer(in);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };
}
