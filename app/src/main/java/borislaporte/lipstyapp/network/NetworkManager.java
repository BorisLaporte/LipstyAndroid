package borislaporte.lipstyapp.network;

import android.os.Parcelable;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.spothero.volley.JacksonRequest;
import com.spothero.volley.JacksonRequestListener;

import borislaporte.lipstyapp.Application.CocktailApplication;
import borislaporte.lipstyapp.model.Answer;
import borislaporte.lipstyapp.model.AnswerTotal;
import borislaporte.lipstyapp.model.Cocktail;
import borislaporte.lipstyapp.model.CocktailResult;

/**
 * Created by moi on 09/06/16.
 */
public class NetworkManager {

    private static String apiKey = "554d49b3281d4991ab23bcf65f7e5151";
    private static String baseUrl = "http://addb.absolutdrinks.com/drinks/rating/lt100/";
    private static String baseQueryUrl = "http://addb.absolutdrinks.com/quickSearch/drinks/";


    public interface CocktailResultListener {
        void onCocktailsFind(Cocktail[] cocktails);
        void onFail();
    }

    public static void letsFlirt(AnswerTotal answers, final CocktailResultListener listener){
        String urlArgs = parserAnswers(answers);
        String url = baseUrl+urlArgs+"?pageSize=3&apiKey="+apiKey;
        findCocktails(url, listener);
    }

    public static void queryCocktail(String query,final CocktailResultListener listener){
        String url = baseUrl+"?pageSize=25&apiKey="+apiKey;
        if ( query != "" ){
            url = baseQueryUrl + query + "/?pageSize=25&apiKey=" + apiKey;
        }
        findCocktails(url, listener);

    }

    public static String parserAnswers(AnswerTotal answers){
        String args = "";
        for (Answer _answer : answers.getAnswers()) {
            if ( _answer.getInRequest() && !_answer.getValue().equalsIgnoreCase("null")  ){
                args += _answer.getKey()+"/"+_answer.getValue()+"/";
            }
        }
        return args;
    }

    public static void findCocktails(String url, final CocktailResultListener listener) {


        JacksonRequest<CocktailResult> request =
                new JacksonRequest<CocktailResult>(Request.Method.GET, url,
                        new JacksonRequestListener<CocktailResult>() {
                            @Override
                            public void onResponse(CocktailResult response, int statusCode, VolleyError error) {

                                if(error!=null) {
                                    if(listener!=null) {
                                        listener.onFail();
                                    }
                                } else {
                                    if(response!=null) {
                                        if(listener!=null) {
                                            listener.
                                                    onCocktailsFind(response.getResult());
                                        }
                                    }
                                }
                            }

                            @Override
                            public JavaType getReturnType() {
                                return SimpleType.construct(CocktailResult.class);
                            }
                        });


        CocktailApplication.getSingleInstance()
                .getRequestQueue().add(request);

    }
}
