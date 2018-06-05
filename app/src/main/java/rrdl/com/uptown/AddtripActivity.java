package rrdl.com.uptown;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddtripActivity extends AppCompatActivity {
    EditText destination,start,place,price,date;
    Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtrip);
        final SharedPreferences prefs = this.getSharedPreferences("GLOBAL", this.MODE_PRIVATE);
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://serene-escarpment-65486.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        final apiservice apiservice = retrofit.create(apiservice.class);

        destination=findViewById(R.id.destination);
        start=findViewById(R.id.start);
        place=findViewById(R.id.places);
        price=findViewById(R.id.price);
        date=findViewById(R.id.date);
        confirm=findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Trip buff=new Trip();
                buff.setTitle(destination.getText().toString());
                buff.setStartadress(start.getText().toString());
                buff.setPlaces(Integer.parseInt(place.getText().toString()));
                buff.setPrice(Integer.parseInt(price.getText().toString()));
                buff.setDate(date.getText().toString());
                Call<Trip>posttrip=apiservice.posttrip(prefs.getString("AUTH",""),buff);
                posttrip.enqueue(new Callback<Trip>() {
                    @Override
                    public void onResponse(Call<Trip> call, Response<Trip> response) {
                        Toast.makeText(AddtripActivity.this, "Offer Added !", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Trip> call, Throwable t) {
                        Toast.makeText(AddtripActivity.this, "Woops , something went very wrong", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });
    }
}
