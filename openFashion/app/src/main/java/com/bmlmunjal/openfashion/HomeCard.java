package com.bmlmunjal.openfashion;

public class HomeCard {
    String item1NameString, item2NameString, item1PriceString, item2PriceString;
    int item1Image,item2Image;
    public HomeCard(String item1NameString,String item2NameString,String item1PriceString,String item2PriceString,int item1Image,int item2Image){
        this.item1NameString=item1NameString;
        this.item2NameString=item2NameString;
        this.item1PriceString= item1PriceString;
        this.item2PriceString= item2PriceString;
        this.item1Image= item1Image;
        this.item2Image=item2Image;
    }
}
