package com.dev.objects;

import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import javax.persistence.*;
import javax.swing.*;


@Entity
@Table(name = "tenders")
public class Tender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String productName;

    @Column
    private ImageIcon productImage;

    @Column
    private String dateOpenTender;

    @Column
    private int amountOfOffering;

    @Column
    private int getAmountOfOfferingForUser;

    @Column
    private boolean isOpen;

    public Tender(){

    }

    public Tender(String productName, ImageIcon productImage, String dateOpenTender, int amountOfOffering, int getAmountOfOfferingForUser, boolean isOpen) {
        this.productName = productName;
        this.productImage = productImage;
        this.dateOpenTender = dateOpenTender;
        this.amountOfOffering = amountOfOffering;
        this.getAmountOfOfferingForUser = getAmountOfOfferingForUser;
        this.isOpen = isOpen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ImageIcon getProductImage() {
        return productImage;
    }

    public void setProductImage(ImageIcon productImage) {
        this.productImage = productImage;
    }

    public String getDateOpenTender() {
        return dateOpenTender;
    }

    public void setDateOpenTender(String dateOpenTender) {
        this.dateOpenTender = dateOpenTender;
    }

    public int getAmountOfOffering() {
        return amountOfOffering;
    }

    public void setAmountOfOffering(int amountOfOffering) {
        this.amountOfOffering = amountOfOffering;
    }

    public int getGetAmountOfOfferingForUser() {
        return getAmountOfOfferingForUser;
    }

    public void setGetAmountOfOfferingForUser(int getAmountOfOfferingForUser) {
        this.getAmountOfOfferingForUser = getAmountOfOfferingForUser;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
