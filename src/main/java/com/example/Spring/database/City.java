package com.example.Spring.database;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "city")
    private String city;
    @Column(name = "isreal")
    private boolean isReal;

    public City(String city, boolean isReal) {
        this.city = city;
        this.isReal = isReal;
    }
}
