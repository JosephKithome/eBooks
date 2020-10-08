package com.sejjoh.ebooks;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;

import com.squareup.picasso.Picasso;

public class Books implements Parcelable {
    public ObservableField<String> id = new ObservableField<String>();
    public String title;
    public ObservableField<String> subTitle= new ObservableField<String>();
    public ObservableField<String> authors= new ObservableField<String>();
    public ObservableField<String> publisher= new ObservableField<String>();
    public ObservableField<String> publishedDate= new ObservableField<String>();
    public ObservableField<String> description= new ObservableField<String>();
    public ObservableField<String> thumbnail= new ObservableField<String>();


    public Books(String id, String title, String subTitle, String[] authors, String publisher,
                String publishedDate, String description, String thumbnail) {

        try {
            this.id.set(id);
            this.title=title;
            this.subTitle.set(subTitle);
            this.authors.set(TextUtils.join(", ", authors));
            this.publisher.set(publisher);
            this.publishedDate.set(publishedDate);
            this.description.set(description);
            this.thumbnail.set(thumbnail);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Books(Parcel in) {
        id.set(in.readString());
        title = (in.readString());
        subTitle.set(in.readString());
        authors.set(in.readString());
        publisher.set(in.readString());
        publishedDate.set(in.readString());
        description.set(in.readString());
        thumbnail.set(in.readString());
    }

    public static final Creator<Books> CREATOR = new Creator<Books>() {
        @Override
        public Books createFromParcel(Parcel in) {
            return new Books(in);
        }

        @Override
        public Books[] newArray(int size) {
            return new Books[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id.get());
        parcel.writeString(title);
        parcel.writeString(subTitle.get());
        parcel.writeString(authors.get());
        parcel.writeString(publisher.get());
        parcel.writeString(publishedDate.get());
        parcel.writeString(description.get());
        parcel.writeString(thumbnail.get());
    }

    @BindingAdapter({"android:imageUrl"})
    public static void loadImage(ImageView imageCover, String imageUrl) {
        if (!imageUrl.isEmpty()){

            Picasso.get().load(imageUrl).into(imageCover);
        }
        else{
            imageCover.setBackgroundResource(R.drawable.ic_baseline_menu_book_24);
        }
    }


}
