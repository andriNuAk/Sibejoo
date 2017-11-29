package api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by M on 10/11/2017.
 */

public class APIClient {
    public static final String BASE_URL = "http://192.168.42.183:3100"; // (vps)
    private static Retrofit retrofit = null;


    public static Retrofit getURL() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static APIInterface getApiService() {
        return getURL().create(APIInterface.class);
    }
}
