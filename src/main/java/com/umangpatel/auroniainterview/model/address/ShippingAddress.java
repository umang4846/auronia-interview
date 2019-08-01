package com.umangpatel.auroniainterview.model.address;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@JsonPropertyOrder({
        "FirstName",
        "LastName",
        "Email",
        "City",
        "Address1",
        "Address2",
        "ZipPostalCode",
        "PhoneNumber"
})
public class ShippingAddress {

    @Field("PhoneNumber")
    private String PhoneNumber;
    @Field("ZipPostalCode")
    private String ZipPostalCode;
    @Field("Address2")
    private String Address2;
    @Field("Address1")
    private String Address1;
    @Field("City")
    private String City;
    @Field("Email")
    private String Email;
    @Field("LastName")
    private String LastName;
    @Field("FirstName")
    private String FirstName;


}

