package com.ecommerce.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Entity
@Table(name = "Customer")
@NamedQueries({ @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c ORDER BY c.registerDate DESC"),
        @NamedQuery(name = "Customer.findByEmail", query = "SELECT c FROM Customer c WHERE c.email = :email"),
        @NamedQuery(name = "Customer.countAll", query = "SELECT COUNT(c.email) FROM Customer c"),
        @NamedQuery(name = "Customer.findByEmailAndPassword", query = "SELECT c FROM Customer c WHERE c.email = :email AND c.password = :password") })
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer", nullable = false)
    private Integer id;

    @Size(max = 30)
    @NotNull
    @Column(name = "name_customer", nullable = false, length = 30)
    private String nameCustomer;

    @Size(max = 20)
    @NotNull
    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Size(max = 50)
    @NotNull
    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @Size(max = 50)
    @NotNull
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Size(max = 25)
    @NotNull
    @Column(name = "country", nullable = false, length = 25)
    private String country;

    @Size(max = 50)
    @NotNull
    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @NotNull
    @Column(name = "register_date", nullable = false)
    private Instant registerDate;

    @NotNull
    @Column(name = "active", nullable = false)
    private boolean active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Instant getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Instant registerDate) {
        this.registerDate = registerDate;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
//    //private Set<Review> reviews;// = new HashSet<>(0);
//    //private Set<Review> Reviews = new HashSet<>(0);
//    private Set<Review> reviews = new LinkedHashSet<>();
//
//    public Set<Review> getReviews() {
//        return this.reviews;
//    }
//    public void setReviews(Set<Review> reviews) {
//        this.reviews = reviews;
//    }
}