package rrdl.com.uptown;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final android.widget.SearchView sv=findViewById(R.id.sv);
        final RecyclerView rv=findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        final SharedPreferences prefs = this.getSharedPreferences("GLOBAL", this.MODE_PRIVATE);
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://serene-escarpment-65486.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        final apiservice apiservice = retrofit.create(apiservice.class);
        Call<List<Trip>>getall=apiservice.gettrips(prefs.getString("AUTH",""));
        getall.enqueue(new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                Toast.makeText(MainActivity.this, response.errorBody().toString(), Toast.LENGTH_SHORT).show();

                if(response.isSuccessful()){

                  adapter adapter=new adapter(getApplicationContext(),response.body(),sv);
                  rv.setAdapter(adapter);
              }
            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {

            }
        });






        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
