package com.ibsvalleyn.outlet.models;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryListModel implements Serializable, Parcelable
{

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("AllowsBilling")
    @Expose
    private Boolean allowsBilling;
    @SerializedName("AllowsShipping")
    @Expose
    private Boolean allowsShipping;
    @SerializedName("TwoLetterIsoCode")
    @Expose
    private String twoLetterIsoCode;
    @SerializedName("ThreeLetterIsoCode")
    @Expose
    private String threeLetterIsoCode;
    @SerializedName("NumericIsoCode")
    @Expose
    private Integer numericIsoCode;
    @SerializedName("SubjectToVat")
    @Expose
    private Boolean subjectToVat;
    @SerializedName("Published")
    @Expose
    private Boolean published;
    @SerializedName("DisplayOrder")
    @Expose
    private Integer displayOrder;
    @SerializedName("LimitedToStores")
    @Expose
    private Boolean limitedToStores;
    public final static Parcelable.Creator<CountryListModel> CREATOR = new Creator<CountryListModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CountryListModel createFromParcel(Parcel in) {
            return new CountryListModel(in);
        }

        public CountryListModel[] newArray(int size) {
            return (new CountryListModel[size]);
        }

    }
            ;
    private final static long serialVersionUID = -3003213836625025181L;

    protected CountryListModel(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.allowsBilling = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.allowsShipping = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.twoLetterIsoCode = ((String) in.readValue((String.class.getClassLoader())));
        this.threeLetterIsoCode = ((String) in.readValue((String.class.getClassLoader())));
        this.numericIsoCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.subjectToVat = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.published = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.displayOrder = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.limitedToStores = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
    }

    public CountryListModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAllowsBilling() {
        return allowsBilling;
    }

    public void setAllowsBilling(Boolean allowsBilling) {
        this.allowsBilling = allowsBilling;
    }

    public Boolean getAllowsShipping() {
        return allowsShipping;
    }

    public void setAllowsShipping(Boolean allowsShipping) {
        this.allowsShipping = allowsShipping;
    }

    public String getTwoLetterIsoCode() {
        return twoLetterIsoCode;
    }

    public void setTwoLetterIsoCode(String twoLetterIsoCode) {
        this.twoLetterIsoCode = twoLetterIsoCode;
    }

    public String getThreeLetterIsoCode() {
        return threeLetterIsoCode;
    }

    public void setThreeLetterIsoCode(String threeLetterIsoCode) {
        this.threeLetterIsoCode = threeLetterIsoCode;
    }

    public Integer getNumericIsoCode() {
        return numericIsoCode;
    }

    public void setNumericIsoCode(Integer numericIsoCode) {
        this.numericIsoCode = numericIsoCode;
    }

    public Boolean getSubjectToVat() {
        return subjectToVat;
    }

    public void setSubjectToVat(Boolean subjectToVat) {
        this.subjectToVat = subjectToVat;
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

    public Boolean getLimitedToStores() {
        return limitedToStores;
    }

    public void setLimitedToStores(Boolean limitedToStores) {
        this.limitedToStores = limitedToStores;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(allowsBilling);
        dest.writeValue(allowsShipping);
        dest.writeValue(twoLetterIsoCode);
        dest.writeValue(threeLetterIsoCode);
        dest.writeValue(numericIsoCode);
        dest.writeValue(subjectToVat);
        dest.writeValue(published);
        dest.writeValue(displayOrder);
        dest.writeValue(limitedToStores);
    }

    public int describeContents() {
        return 0;
    }

}

