package com.company.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "publisher")
public class Publisher implements Serializable {

    @Id
    //@Column(name = "publisher_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer publisherId;

    @OneToMany(mappedBy ="publisherId" ,cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    //@JoinColumn(name = "publisher_id")
    private Set<Book> books = new HashSet<>();

    private String name;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String phone;
    private String email;

    public Publisher() {}

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }


    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getStreet() {return street;}
    public void setStreet(String street) {this.street = street;}

    public String getCity() {return city;}
    public void setCity(String city) {this.city = city;}

    public String getState() {return state;}
    public void setState(String state) {this.state = state;}

    public String getPostalCode() {return postalCode;}
    public void setPostalCode(String postalCode) {this.postalCode = postalCode;}

    public String getPhone() {return phone;}
    public void setPhone(String phone) {this.phone = phone;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Publisher)) return false;
        Publisher that = (Publisher) o;
        return  Objects.equals(getPublisherId(), that.getPublisherId()) &&
                Objects.equals(getName(),that.getName()) &&
                Objects.equals(getStreet(), that.getStreet()) &&
                Objects.equals(getCity(), that.getCity()) &&
                Objects.equals(getState(), that.getState()) &&
                Objects.equals(getPostalCode(), that.getPostalCode()) &&
                Objects.equals(getPhone(), that.getPhone()) &&
                Objects.equals(getEmail(), that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPublisherId(), getName(), getStreet(), getCity(), getState(), getPostalCode(), getPhone(), getEmail());
    }
}