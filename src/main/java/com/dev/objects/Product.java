package com.dev.objects;

import javax.persistence.*;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

@Entity
@Table (name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String productName;

    @Column
    private String productDescription;

    @Column
    private int minCost;

    @Column
    private String ownerOfTheProduct;

    @Column
    private ImageIcon productImage;

    public Product() {
    }

    public Product(String productName, String productDescription, int minCost,String ownerOfTheProduct, ImageIcon productImage) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.minCost = minCost;
        this.ownerOfTheProduct=ownerOfTheProduct;
        this.productImage = productImage;
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

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getMinCost() {
        return minCost;
    }

    public void setMinCost(int minCost) {
        this.minCost = minCost;
    }

    public String getOwnerOfTheProduct() {
        return ownerOfTheProduct;
    }

    public void setOwnerOfTheProduct(String ownerOfTheProduct) {
        this.ownerOfTheProduct = ownerOfTheProduct;
    }

    public ImageIcon getProductImage() {
        return productImage;
    }

    public void setProductImage(ImageIcon productImage) {
        this.productImage = productImage;
    }

}
