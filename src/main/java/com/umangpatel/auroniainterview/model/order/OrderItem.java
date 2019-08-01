package com.umangpatel.auroniainterview.model.order;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.umangpatel.auroniainterview.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.mapping.Field;

@JsonPropertyOrder({
        "_id",
        "Quantity",
        "UnitPriceInclTax",
        "PriceInclTax",
        "CreatedOnUtc",
        "Product"
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItem {

    @Field("_id")
    private String _id;
    @Field("Quantity")
    private long Quantity;
    @Field("UnitPriceInclTax")
    private long UnitPriceInclTax;
    @Field("PriceInclTax")
    private long PriceInclTax;
    @Field("CreatedOnUtc")
    private DateTime CreatedOnUtc;
    @Field("Product")
    private Product product;

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
