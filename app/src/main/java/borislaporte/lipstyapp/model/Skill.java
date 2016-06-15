package borislaporte.lipstyapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by moi on 13/06/16.
 */
public class Skill implements Parcelable{
    private String id;
    private String name;
    private int value;

    public Skill() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    public Skill(Parcel in){
        this.id = in.readString();
        this.name = in.readString();
        this.value = in.readInt();
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.value);
    }

    public static final Creator<Skill> CREATOR = new Creator<Skill>() {
        @Override
        public Skill createFromParcel(Parcel in) {
            return new Skill(in);
        }

        @Override
        public Skill[] newArray(int size) {
            return new Skill[size];
        }
    };
}
