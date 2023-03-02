package dk.tec.app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity {

    private TextView txtName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = (TextView) findViewById(R.id.idName);


        PersonService personService = ServiceBuilder.buildService(PersonService.class);
        Call<Person> request = personService.getPersonById(2);
        request.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                Person person = response.body();
                String name = person.getPerName();
                txtName.setText(name);
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                Log.d("Failed", t.getMessage());
            }
        });
    }
}