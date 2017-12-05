package com.example.datasource.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Forev on 17/04/25.
 */

public class ItemQuestionModel implements Parcelable {

    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("title")
    public String title;
    @SerializedName("type")
    public int type;
    @SerializedName("stt_question")
    public int order_rank;
    @SerializedName("total_submeta")
    public int totalSubMeta;

    public ItemQuestionModel(){}

    protected ItemQuestionModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        title = in.readString();
        type = in.readInt();
        order_rank = in.readInt();
    }

    public static final Creator<ItemQuestionModel> CREATOR = new Creator<ItemQuestionModel>() {
        @Override
        public ItemQuestionModel createFromParcel(Parcel in) {
            return new ItemQuestionModel(in);
        }

        @Override
        public ItemQuestionModel[] newArray(int size) {
            return new ItemQuestionModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(title);
        dest.writeInt(type);
        dest.writeInt(order_rank);
    }
}
