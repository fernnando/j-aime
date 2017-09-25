package co.fddittmar.j_aime.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Recipe POJO.
 */

public class Recipe implements Serializable{
    @SerializedName("title") public String title;

    @SerializedName("href") public String href;

    @SerializedName("ingredients") public String ingredients;

    @SerializedName("thumbnail") public String picture;

}
