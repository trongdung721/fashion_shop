package com.ecommerce.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;


@Entity
@Table(name = "Product")
@NamedQueries({ @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
        @NamedQuery(name = "Product.findByCategory", query = "SELECT p FROM Product p WHERE p.category.id = :categoryId"),
        @NamedQuery(name = "Product.search", query = "SELECT p FROM Product p WHERE p.nameProduct LIKE '%' || :keyword || '%' OR p.description LIKE '%' || :keyword || '%'"),
        @NamedQuery(name = "Product.findByTitle", query = "SELECT p FROM Product p WHERE p.nameProduct = :nameProduct"),
        @NamedQuery(name = "Product.countByCategory", query = "SELECT COUNT(p) FROM Product p WHERE p.category.id = :categoryId"),
        @NamedQuery(name = "Product.findNew", query = "SELECT p FROM Product p ORDER BY p.postDate DESC"),

        @NamedQuery(name = "Product.findByProductAndNewest", query = "SELECT p FROM Product p ORDER BY p.postDate DESC "),
        @NamedQuery(name = "Product.findByProductAndPriceDec", query = "SELECT p FROM Product p ORDER BY p.price DESC"),
        @NamedQuery(name = "Product.findByProductAndPriceInc", query = "SELECT p FROM Product p ORDER BY p.price ASC"),
        @NamedQuery(name = "Product.findByCategoryAndNewest", query = "SELECT p FROM Product p JOIN Category c ON p.category.id = c.id AND c.id = :categoryId ORDER BY p.postDate DESC "),
        @NamedQuery(name = "Product.findByCategoryAndPriceDec", query = "SELECT p FROM Product p JOIN Category c ON p.category.id = c.id AND c.id = :categoryId ORDER BY p.price DESC"),
        @NamedQuery(name = "Product.findByCategoryAndPriceInc", query = "SELECT p FROM Product p JOIN Category c ON p.category.id= c.id AND c.id = :categoryId ORDER BY p.price ASC")})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_category")
    private Category category;

    @Size(max = 100)
    @Column(name = "name_product", length = 100)
    private String nameProduct;



    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "image_product")
    private byte[] imageProduct;



    @Column(name = "price")
    private Float price;

    @Column(name = "post_date")
    private Instant postDate;

    @NotNull
    @Column(name = "update_date", nullable = false)
    private Instant updateDate;

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

//    private String base64Image;

    public Product() {
    }

    public Product(Integer productId) {
        super();
        this.id = productId;
    }

    public  Product(String nameProduct, byte[] image, String description, float price)
    {
        this.nameProduct = nameProduct;
        this.description = description;
        this.imageProduct = image;
        this.price = price;
    }
    public Product(Category category, String nameProduct, String description, byte[] image, float price, Instant postDate,
                   Instant updateDate) {
        this.category = category;
        this.nameProduct = nameProduct;
        this.description = description;
        this.imageProduct = image;
        this.price = price;
        this.postDate = postDate;
        this.updateDate = updateDate;

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public Category getIdCategory() {
//        return idCategory;
//    }
//
//    public void setIdCategory(Category idCategory) {
//        this.idCategory = idCategory;
//    }
    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {

        this.category = category;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(byte[] imageProduct) {
        this.imageProduct = imageProduct;
    }



    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Instant getPostDate() {
        return postDate;
    }

    public void setPostDate(Instant postDate) {
        this.postDate = postDate;
    }


    public String getBase64Image() {
        // Kiểm tra nếu imageProduct không phải là null và chuyển đổi nó thành chuỗi Base64
        return this.imageProduct != null ? Base64.getEncoder().encodeToString(this.imageProduct) : "";
    }

    /*@Transient
    public String getBase64Image() {
        this.base64Image = Base64.getEncoder().encodeToString(this.imageProduct);
        return this.base64Image;
    }

    @Transient
    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }*/

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + nameProduct + '\'' +
                ", price=" + price +
                // Thêm các trường khác bạn muốn hiển thị
                '}';
    }

}