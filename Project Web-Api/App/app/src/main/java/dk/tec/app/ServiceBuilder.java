package dk.tec.app;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ServiceBuilder {
    private static final String URL = "http://10.0.2.2:8080/WebApi/api/";
    private static Retrofit.Builder builder =
            new Retrofit.Builder().baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = builder.build();

    public static <S> S buildService(Class<S> serviceType)
    {
        return retrofit.create(serviceType);
    }
}