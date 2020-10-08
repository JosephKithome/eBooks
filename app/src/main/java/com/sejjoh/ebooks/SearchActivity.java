package com.sejjoh.ebooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.sejjoh.ebooks.Api.ApiUtils;

import java.net.URL;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final TextInputEditText etTitle = findViewById(R.id.etTitle);
        final TextInputEditText etAuthor = findViewById(R.id.etAuthor);
        final TextInputEditText etPublisher = findViewById(R.id.etPublisher);
        final TextInputEditText etIsbn = findViewById(R.id.isbn);
        final MaterialButton btnSearch = findViewById(R.id.btnSerach);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString().trim();
                String author =etAuthor.getText().toString().trim();
                String publisher =etPublisher.getText().toString().trim();
                String isbn =etIsbn.getText().toString().trim();
                if(title.isEmpty() && author.isEmpty() && publisher.isEmpty() && isbn.isEmpty()) {
                    String message = getString(R.string.no_search_Data);
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }
                else {
                    URL queryURL = ApiUtils.buildUrl(title, author, publisher, isbn);
                    Context context = getApplicationContext();
                    //sharedPreferences
                    int position = SpUtils.getPreferenceInt(context, SpUtils.POSITION);
                    if (position == 0 || position == 5) {
                        position=1;
                    }
                    else {
                        position++;
                    }
                    String key = SpUtils.QUERY + String.valueOf(position);
                    String value = title + "," +  author +"," + publisher +"," + isbn;
                    SpUtils.setPreferenceString(context, key, value);
                    SpUtils.setPreferenceInt(context, SpUtils.POSITION, position);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("query", queryURL.toString());
                    startActivity(intent);
                }
            }
        });
    }
}
