package com.bmlmunjal.openfashion;

public class CartData {
    private String itemName,itemPrice;
    private int noOfItems;
    private Integer itemImage;
    public CartData(String itemName,String itemPrice,String itemSize,String noOfItems,Integer itemImage){
        this.itemName=itemName;
        this.itemPrice=itemPrice;
//        this.itemSize=itemSize;
//        this.noOfItems=noOfItems;
        this.itemImage=itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

//    public String getItemSize() {
//        return itemSize;
//    }
//
//    public void setItemSize(String itemSize) {
//        this.itemSize = itemSize;
//    }
//
//    public String getNoOfItems() {
//        return noOfItems;
//    }
//
//    public void setNoOfItems(String noOfItems) {
//        this.noOfItems = noOfItems;
//    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getItemImage() {
        return itemImage;
    }

    public void setItemImage(Integer itemImage) {
        this.itemImage = itemImage;
    }
}
