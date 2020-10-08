//package com.sejjoh.ebooks.Adapters;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.sejjoh.ebooks.Books;
//import com.sejjoh.ebooks.R;
//
//import java.util.ArrayList;
//
///**
// * created by joseph mulingwa kithome on
// */
//public class BooksDetailAdapter extends RecyclerView.Adapter<BooksDetailAdapter.BookDetailViewHolder> {
//    ArrayList<Books> booksArrayList;
//    public BooksDetailAdapter (ArrayList<Books> books){
//        this.booksArrayList = books;
//
//    }
//    @NonNull
//    @Override
//    public BookDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Context context = parent.getContext();
//        View itemView = LayoutInflater.from(context)
//                .inflate(R.layout.book_detail_list_items,parent,false);
//        return new BookDetailViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull BookDetailViewHolder holder, int position) {
//        Books books = booksArrayList.get(position);
//        holder.bind(books);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return booksArrayList.size();
//    }
//
//    public  class BookDetailViewHolder extends RecyclerView.ViewHolder {
//        TextView txtTitle;
//        TextView txtSubTitle;
//        TextView txtAuthor;
//        TextView txtPublisher;
//        TextView txtPublishDate;
//        TextView txtDescription;
//        public BookDetailViewHolder(@NonNull View itemView) {
//            super(itemView);
//            txtTitle = itemView.findViewById(R.id.textView);
//            txtSubTitle = itemView.findViewById(R.id.tvSubtitle);
//            txtAuthor = itemView.findViewById(R.id.tvAuthors);
//            txtPublisher = itemView.findViewById(R.id.tvPublisher);
//            txtPublishDate = itemView.findViewById(R.id.description);
//            txtDescription = itemView.findViewById(R.id.tvPublishDate);
//
//        }
//        public void bind (Books books){
//            txtTitle.setText(books.title);
//            txtAuthor.setText(books.authors);
//            txtPublishDate.setText(books.publishedDate);
//            txtPublisher.setText(books.publisher);
//            txtSubTitle.setText(books.subTitle);
//            txtDescription.setText(books.description);
//
//        }
//    }
//}
