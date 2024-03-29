package com.sejjoh.ebooks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.sejjoh.ebooks.Adapters.BooksAdapter;
import com.sejjoh.ebooks.Api.ApiUtils;
import com.sejjoh.ebooks.Api.PreferenceKeys;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private static final String TAG ="MainActivity" ;
    private ProgressBar mLoadingProgress;
    private RecyclerView rvBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvBooks = (RecyclerView) findViewById(R.id.recycler);
        mLoadingProgress = (ProgressBar) findViewById(R.id.pb_loading);
        isFirstLogin();
        Intent intent = getIntent();
        String query = intent.getStringExtra("query");

        try {
            URL bookUrl;
            if (query == null  || query.isEmpty()) {
                bookUrl = ApiUtils.buildUrl("cooking");
            }
            else {
                bookUrl = new URL(query);
            }
            new BooksQueryTask().execute(bookUrl);

        }
        catch (Exception e) {
            Log.d("error", e.getMessage());
        }

        //create the layoutManager for the books (linear in this case, scrolling vertically
        LinearLayoutManager booksLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvBooks.setLayoutManager(booksLayoutManager);


    }
    private  void  isFirstLogin(){
        Log.d(TAG, "isFirstLogin: checking if this is the first Login ");
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstLogin =preferences.getBoolean(PreferenceKeys.FIRST_TIME_LOGIN, true);
        if (isFirstLogin){
            Log.d(TAG, "isFirstLogin: Launching alert dialog.");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("welcome and start accessing books from the amazing google books Api.We are digitizing every book " +
                    "content around the world");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Log.d(TAG, "onClick: Closing dialog.");
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean(PreferenceKeys.FIRST_TIME_LOGIN, false);
                    editor.commit();
                    dialogInterface.dismiss();

                }
            });
            builder.setIcon(R.drawable.ic_baseline_menu_book_24);
            builder.setTitle("");
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.book_list_menu, menu);
        final MenuItem searchItem=menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        //recent searches
        ArrayList<String> recentList = SpUtils.getQueryList(getApplicationContext());
        int itemNum = recentList.size();
        MenuItem recentMenu;
        for (int i = 0; i<itemNum; i++) {
            recentMenu = menu.add(Menu.NONE, i, Menu.NONE, recentList.get(i));
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.advanced_search:
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                return true;
            default:
                int position = item.getItemId() + 1 ;
                String preferenceName = SpUtils.QUERY + String.valueOf(position);
                String query = SpUtils.getPreferenceString(getApplicationContext(), preferenceName);
                String[] prefParams = query.split("\\,");
                String[] queryParams = new String[4];

                for (int i=0; i<prefParams.length;i++) {
                    queryParams[i] = prefParams[i];
                }


                URL bookUrl = ApiUtils.buildUrl(
                        (queryParams[0] == null) ? "" : queryParams[0],
                        (queryParams[1] == null) ? "" : queryParams[1],
                        (queryParams[2] == null) ? "" : queryParams[2],
                        (queryParams[3] == null) ? "" : queryParams[3]
                );
                new BooksQueryTask().execute(bookUrl);
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        try {
            URL bookUrl = ApiUtils.buildUrl(query);
            new BooksQueryTask().execute(bookUrl);
        }
        catch (Exception e) {
            Log.d("error", e.getMessage());
        }


        return false;
    }
    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    public class BooksQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL searchURL = urls[0];
            String result = null;
            try {
                result = ApiUtils.getJson(searchURL);
            }
            catch (IOException e) {
                Log.e("Error", e.getMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {

            TextView tvError = (TextView) findViewById(R.id.tv_error);
            mLoadingProgress.setVisibility(View.INVISIBLE);
            if (result == null) {
                rvBooks.setVisibility(View.INVISIBLE);
                tvError.setVisibility(View.VISIBLE);
            }
            else {
                rvBooks.setVisibility(View.VISIBLE);
                tvError.setVisibility(View.INVISIBLE);
                ArrayList<Books> books = ApiUtils.getBooksFromJson(result);
                String resultString = "";

                BooksAdapter adapter = new BooksAdapter(books);
                rvBooks.setAdapter(adapter);
            }

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingProgress.setVisibility(View.VISIBLE);
        }
    }
}
