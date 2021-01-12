package com.example.minim2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    APIInterface APIIface;
    MyRecyclerViewAdapter adapter;
    ProgressBar progressBar;
    private String username;
    private GithubUserClass user;
    private List<GithubRepoClass> repositories = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        final Intent getUser_Intent = getIntent();
        username = getUser_Intent.getExtras().getString("username");


        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(layoutManager);

        APIIface = APIClient.getClient().create(APIInterface.class);

        getUser(username);
        getRepositorios(username);
    }

    public void getUser(final String username){
        Call<GithubUserClass> call = APIIface.getUser(username);

        call.enqueue(new Callback<GithubUserClass>() {
            @Override
            public void onResponse(Call<GithubUserClass> call, Response<GithubUserClass> response) {
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);

                    TextView username = findViewById(R.id.UserText);
                    TextView followers = findViewById(R.id.FollowersText);
                    TextView following = findViewById(R.id.FollowingText);

                    ImageView pic = findViewById(R.id.imageView);

                    user = response.body();

                    username.setText(user.getLogin());
                    followers.setText("Number of Followers: "+user.getFollowers());
                    following.setText("Number of Following: "+user.getFollowing());

                    Picasso.get().load(user.getAvatar_url()).into(pic);
                    Log.d("error", "Salgo de la llamada");
                }
            }

            @Override
            public void onFailure(Call<GithubUserClass> call, Throwable throwable) {
                call.cancel();
                Toast.makeText(getApplicationContext(), "El usuario: ," + username + "no se puede encontrar", Toast.LENGTH_LONG).show();

            }
        });

    }

    public void getRepositorios(final String u) {
        Call<ArrayList<GithubRepoClass>> call = APIIface.getRepos(u);
        Log.d("ERROR IDIOTA", "Hago la llamada");
        call.enqueue(new Callback<ArrayList<GithubRepoClass>>() {
            @Override
            public void onResponse(Call<ArrayList<GithubRepoClass>> call, Response<ArrayList<GithubRepoClass>> response) {
                if (!response.isSuccessful()) {

                    progressBar.setVisibility(View.GONE);
                    Log.d("error", getString(response.code()));
                    Toast.makeText(getApplicationContext(), "El usuario: ," + u + "no se puede encontrar", Toast.LENGTH_LONG).show();
                }
                if (response.isSuccessful()) {

                    repositories = response.body();
                    TextView repositoriesText = findViewById(R.id.RepositoriesText);
                    repositoriesText.setText("Number of Repositories: " + repositories.size());
                    mAdapter = new MyRecyclerViewAdapter(repositories);
                    recyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<GithubRepoClass>> call, Throwable throwable) {
                Log.d("minim2TAG", throwable.getMessage());
                call.cancel();

                Toast.makeText(getApplicationContext(),  u + "No tiene repositorios", Toast.LENGTH_LONG).show();
            }
        });
    }


    /*public void getRepositorios(final String username){
        Call<GithubRepos> call = apiIface.getRepos(username);
        call.enqueue(new Callback<GithubRepos>() {
            @Override
            public void onResponse(Call<GithubRepos> call, Response<GithubRepos> response) {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<GithubRepos> call, Throwable throwable) {

            }
        });
    }
*/
}