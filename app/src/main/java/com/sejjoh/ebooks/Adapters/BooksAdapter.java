package com.sejjoh.ebooks.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sejjoh.ebooks.BookDetail;
import com.sejjoh.ebooks.Books;
import com.sejjoh.ebooks.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * created by joseph mulingwa kithome on
 * 3 oct 2020
 */
public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder>{
    ArrayList<Books> books;
    public BooksAdapter(ArrayList<Books> books) {
        this.books = books;
    }
    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.book_list_item, parent, false);


        return new BookViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        Books book = books.get(position);
        holder.bind(book);

    }

    @Override
    public int getItemCount() {
        return books.size();
    }


    public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvTitle;
        TextView tvAuthors;
        TextView tvDate;
        TextView tvPublisher;
        ImageView imageView;
        public BookViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.textTitle);
            tvAuthors= itemView.findViewById(R.id.txtAuthor);
            tvDate = itemView.findViewById(R.id.txtPDate);
            tvPublisher = itemView.findViewById(R.id.txtPublisher);
            imageView=itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);

        }
        public void bind (Books book) {
            tvTitle.setText(book.title);
            String authors="";
            int i=0;
            /*for (String author:book.authors.get()) {
                authors+=author;
                i++;
                if(i<book.authors.get().length) {
                    authors+=", ";
                }
            }*/

            tvAuthors.setText(book.authors.get());
            tvDate.setText(book.publishedDate.get());
            tvPublisher.setText(book.publisher.get());


        }


        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Log.d("Click", String.valueOf(position));
            //gets the book from the arrayList
            Books selectedBook = books.get(position);
            Intent intent = new Intent(view.getContext(), BookDetail.class);
            intent.putExtra("Book", selectedBook);
            view.getContext().startActivity(intent);
        }
    }
}
