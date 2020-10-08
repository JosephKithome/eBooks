package com.sejjoh.ebooks;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;

import com.sejjoh.ebooks.databinding.ActivityBookDetailBinding;
public class BookDetail extends AppCompatActivity {
    @Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    ActivityBookDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_book_detail);
    Books book= getIntent().getParcelableExtra("Book");
    binding.setBooks(book);
}
}
