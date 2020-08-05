package study.jpa.domain;

import javax.persistence.Embeddable;

import lombok.Getter;

@Embeddable
public class Address {

    private String state;
    
    private String city;

    private String street;

    private String zipCode;
    
}