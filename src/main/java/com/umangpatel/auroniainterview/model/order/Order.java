package com.umangpatel.auroniainterview.model.order;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.umangpatel.auroniainterview.model.address.ShippingAddress;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.UUID;

@JsonPropertyOrder({
        "_id",
        "OrderGuid",
        "FirstName",
        "LastName",
        "CustomerEmail",
        "OrderTotal",
        "CreatedOnUtc",
        "ShippingAddress",
        "OrderItems"

})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "Order")
public class Order {

    @Field("_id")
    private String _id;
    @Field("OrderGuid")
    private UUID OrderGuid;
    @Field("CustomerEmail")
    private String CustomerEmail;
    @Field("FirstName")
    private String FirstName;
    @Field("LastName")
    private String LastName;
    @Field("OrderTotal")
    private long OrderTotal;
    @Field("ShippingAddress")
    private com.umangpatel.auroniainterview.model.address.ShippingAddress ShippingAddress;
    @Field("CreatedOnUtc")
    private DateTime CreatedOnUtc;
    @Field("OrderItems")
    private List<OrderItem> orderItems;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        if (_id.isEmpty())
            this._id = new ObjectId().toString();
        else
            this._id = _id;
    }


    public String getCreatedOnUtc() {
        if (CreatedOnUtc != null)
            return String.valueOf(CreatedOnUtc);
        return null;
    }

    public void setCreatedOnUtc(String createdOnUtc) {
        CreatedOnUtc = DateTime.now();
    }

}
