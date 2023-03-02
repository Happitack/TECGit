package dk.tec.app;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PersonService {
    @GET("Person/")
    Call<List<Person>> getAllPersons();
    @GET("Person/{id}")
    Call<Person> getPersonById(@Path("id") int id);

    @POST("Person/")
    Call<Void> addPerson(@Body Person e);

    @DELETE("Person/{id}")
    Call<Void> deletePersonById(@Path("id") int id);

    @PUT("Person")
    Call<Void> updatePerson(@Body Person e);
}
