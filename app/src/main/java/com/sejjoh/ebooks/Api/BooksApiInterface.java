package com.sejjoh.ebooks.Api;

import com.sejjoh.ebooks.Books;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * created by joseph mulingwa kithome on
 */
public interface BooksApiInterface {
    @GET("cooking")
    Call<List<Books>>getBooks();
}
