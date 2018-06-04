package rrdl.com.uptown;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trip {@SerializedName("Title")
@Expose
private String title;
    @SerializedName("Startadress")
    @Expose
    private String startadress;
    @SerializedName("phonenumber")
    @Expose
    private String phonenumber;
    @SerializedName("places")
    @Expose
    private Integer places;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("id")
    @Expose
    private String id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartadress() {
        return startadress;
    }

    public void setStartadress(String startadress) {
        this.startadress = startadress;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Integer getPlaces() {
        return places;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}