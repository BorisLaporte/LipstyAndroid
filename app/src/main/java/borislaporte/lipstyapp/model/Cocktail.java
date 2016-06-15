package borislaporte.lipstyapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import borislaporte.lipstyapp.model.Skill;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * Created by moi on 09/06/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Cocktail implements Parcelable{

    private String name;

    private String color;

    @JsonProperty(value = "descriptionPlain")
    private String description;

    private String story;

    private Skill skill;

    private Videos[] videos;

    private Ingredients[] ingredients;

    @JsonProperty(value = "id")
    private String image_path;

    public Cocktail(){

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @JsonProperty(value = "descriptionPlain")
    public String getDescription() {
        return description;
    }

    @JsonProperty(value = "descriptionPlain")
    public void setDescription(String description) {
        this.description = description;
    }


    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }


    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Videos[] getVideos() {
        return videos;
    }

    public void setVideos(Videos[] videos) {
        this.videos = videos;
    }

    public Ingredients[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredients[] ingredients) {
        this.ingredients = ingredients;
    }

    @JsonProperty(value = "id")
    public String getImage_path() {
        return image_path;
    }

    @JsonProperty(value = "id")
    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }



    public Cocktail(Parcel in){
        this.name = in.readString();
        this.color = in.readString();
        this.description = in.readString();
        this.story = in.readString();
        this.skill = (Skill) in.readParcelable(Skill.class.getClassLoader());
        this.videos = (Videos[]) in.createTypedArray(Videos.CREATOR);
        this.ingredients = (Ingredients[]) in.createTypedArray(Ingredients.CREATOR);
        this.image_path = in.readString();
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.color);
        dest.writeString(this.description);
        dest.writeString(this.story);
        dest.writeParcelable(this.skill,0);
        dest.writeTypedArray(this.videos,0);
        dest.writeTypedArray(this.ingredients, 0);
        dest.writeString(this.image_path);
    }

    public static final Creator<Cocktail> CREATOR = new Creator<Cocktail>() {
        @Override
        public Cocktail createFromParcel(Parcel in) {
            return new Cocktail(in);
        }

        @Override
        public Cocktail[] newArray(int size) {
            return new Cocktail[size];
        }
    };

}
