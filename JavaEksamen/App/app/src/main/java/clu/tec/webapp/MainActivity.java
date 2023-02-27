package clu.tec.webapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Goose> geese = new ArrayList<Goose>();
        geese.add(new Goose("Greg", "The dumb one", R.drawable.geese01));
        geese.add(new Goose("Dave", "The smart one", R.drawable.geese02));
        geese.add(new Goose("Bob", "The strong one", R.drawable.geese03));
        geese.add(new Goose("Lina", "The quick one", R.drawable.geese04));
        geese.add(new Goose("Diva", "The stoned one", R.drawable.geese05));
        geese.add(new Goose("Jiva", "The enlightened one", R.drawable.geese06));
        geese.add(new Goose("Bogo", "The thicc one", R.drawable.geese07));

        GooseAdapter gooseAdapter = new GooseAdapter(geese, this);
        ListView lstGoose = findViewById(R.id.lstGeese);
        lstGoose.setAdapter(gooseAdapter);
    }
}