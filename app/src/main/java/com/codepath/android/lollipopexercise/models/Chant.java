package com.codepath.android.lollipopexercise.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

/**
 * Created by Guest 3 on 11/24/2017.
 */

@Parcel
public class Chant {
    public String title;
    public Long id;
    public String refrain;
    public String couplet;
    public String categorie;
    public String langue;
    public String description;

    public Chant(JSONObject object) throws JSONException {
        title = object.getString("titre");
        id = object.getLong("id");
        refrain = object.getString("refrain");
        couplet = object.getString("couplet");
        categorie = object.getString("nom");
        langue = object.getString("langue");
        // rate = object.getDouble("rate");
        description = object.getString("description");

    }
    // no-arg, empty constructor required for Parceler
    public Chant(){}

    public Chant(long id, String title) {
        super();
        this.title = title;
        this.id = id;
    }

    public Chant(Long id, String title, String refrain, String couplet, String categorie, String langue, String description) {
        super();
        this.title = title;
        this.id = id;
        this.refrain = refrain;
        this.couplet = couplet;
        this.categorie = categorie;
        this.langue = langue;
        //  this.rate = rate;
        this.description = description;
    }

    public String getRefrain() { return refrain;    }

    public String getCouplet() {  return couplet;  }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRefrain(String refrain) {
        this.refrain = refrain;
    }

    public void setCouplet(String couplet) {
        this.couplet = couplet;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
