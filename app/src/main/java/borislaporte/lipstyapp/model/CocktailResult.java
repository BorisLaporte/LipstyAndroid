package borislaporte.lipstyapp.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by moi on 09/06/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CocktailResult {
    private Cocktail[] result;

    public Cocktail[] getResult() {
        return result;
    }

    public void setResults(Cocktail[] result) {
        this.result = result;
    }

}
