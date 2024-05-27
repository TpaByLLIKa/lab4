package com.labs.lab4.dto.response;


public class BarcodeResponse {

    private String barcode;

    public BarcodeResponse(String barcode) {
        this.barcode = barcode;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
