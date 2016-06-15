package borislaporte.lipstyapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by moi on 14/06/16.
 */
public class AnswerTotal implements Parcelable{

    private Answer[] answers;

    public AnswerTotal() {
    }

    public Answer[] getAnswers() {
        return answers;
    }

    public void setAnswers(Answer[] answers) {
        this.answers = answers;
    }


    public AnswerTotal(Parcel in){
        this.answers = (Answer[]) in.createTypedArray(Answer.CREATOR);
    }


    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedArray(this.answers, 0);
    }
    public static final Parcelable.Creator<AnswerTotal> CREATOR = new Parcelable.Creator<AnswerTotal>() {
        @Override
        public AnswerTotal createFromParcel(Parcel in) {
            return new AnswerTotal(in);
        }

        @Override
        public AnswerTotal[] newArray(int size) {
            return new AnswerTotal[size];
        }
    };
}
