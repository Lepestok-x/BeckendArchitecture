package com.example.Spring;

import net.datafaker.Faker;

public class Data {
    private final Faker faker = new Faker();

    public String contactName() {
        return faker.name().fullName();
    }
    public String address() {
        return faker.address().fullAddress();
    }
    public String city() {
        return faker.address().city();
    }
    public String postalCode() {
        return faker.address().postcode();
    }
    public String country() {
        return faker.address().country();
    }
}
