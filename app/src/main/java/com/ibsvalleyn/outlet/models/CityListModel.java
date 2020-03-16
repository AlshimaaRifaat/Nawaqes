package com.ibsvalleyn.outlet.models;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityListModel implements Serializable, Parcelable
{

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("CountryId")
    @Expose
    private Integer countryId;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Abbreviation")
    @Expose
    private Object abbreviation;
    @SerializedName("Published")
    @Expose
    private Boolean published;
    @SerializedName("DisplayOrder")
    @Expose
    private Integer displayOrder;
    public final static Parcelable.Creator<CityListModel> CREATOR = new Creator<CityListModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CityListModel createFromParcel(Parcel in) {
            return new CityListModel(in);
        }

        public CityListModel[] newArray(int size) {
            return (new CityListModel[size]);
        }

    }
            ;
    private final static long serialVersionUID = -1432041452684371435L;

    protected CityListModel(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.countryId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.abbreviation = ((Object) in.readValue((Object.class.getClassLoader())));
        this.published = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.displayOrder = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public CityListModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(Object abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(countryId);
        dest.writeValue(name);
        dest.writeValue(abbreviation);
        dest.writeValue(published);
        dest.writeValue(displayOrder);
    }

    public int describeContents() {
        return 0;
    }

}
