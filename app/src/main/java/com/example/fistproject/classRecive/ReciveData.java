package com.example.fistproject.classRecive;

public class ReciveData {
    private String namedata;
    private String imageLink;
    private String discription;
    private String id_item;


    public ReciveData(String namedata, String imageLink, String discription, String id_item) {
        this.namedata = namedata;
        this.imageLink = imageLink;
        this.discription = discription;
        this.id_item = id_item;
    }

    public ReciveData(String namedata, String imageLink, String discription) {
        this.namedata = namedata;
        this.imageLink = imageLink;
        this.discription = discription;
    }

    public ReciveData() {
    }

    public String getId_item() {
        return id_item;
    }

    public void setId_item(String id_item) {
        this.id_item = id_item;
    }

    public String getNamedata() {
        return namedata;
    }

    public void setNamedata(String namedata) {
        this.namedata = namedata;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}
