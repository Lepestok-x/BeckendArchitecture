package com.example.Spring.database;

import com.example.Spring.dto.CustomersDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "contactname")
    private String contactName;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "postalcode")
    private String postalCode;
    @Column(name = "country")
    private String country;

    public Customers(String contactName, String address, String city, String postalCode, String country) {
          this.contactName = contactName;
          this.address = address;
          this.city = city;
          this.postalCode = postalCode;
          this.country = country;
    }
    // Тут сразу передаем дто класс, не плодя аргументами.
    public Customers(CustomersDTO dto) {
        this.contactName = dto.contactName();
        this.address = dto.address();
        this.city = dto.city();
        this.postalCode = dto.postalCode();
        this.country = dto.country();
    }
    public Customers() {} // No args

}
