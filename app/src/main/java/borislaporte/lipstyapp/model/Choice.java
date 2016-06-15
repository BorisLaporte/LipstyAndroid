package borislaporte.lipstyapp.model;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by moi on 05/06/16.
 */
public class Choice implements Parcelable{
    private String key;
    private String value;
    private int imageId;

    public Choice(String key, String value, int imageId) {
        this.key = key;
        this.value = value;
        this.imageId = imageId;
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

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public Choice(Parcel in){
        this.key = in.readString();
        this.value = in.readString();
        this.imageId = in.readInt();
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.key);
        dest.writeString(this.value);
        dest.writeInt(this.imageId);
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Choice createFromParcel(Parcel in) {
            return new Choice(in);
        }

        public Choice[] newArray(int size) {
            return new Choice[size];
        }
    };
}
