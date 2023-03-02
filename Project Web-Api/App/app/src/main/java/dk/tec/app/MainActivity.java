package dk.tec.app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PersonService personService = ServiceBuilder.buildService(PersonService.class);
        Call<Person> request = personService.getPersonById(1);
        request.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response)
            {
                Person person =  response.body();
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t)
            {

            }
        });
    }
}