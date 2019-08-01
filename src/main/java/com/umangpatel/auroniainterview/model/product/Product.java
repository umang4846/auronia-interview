package com.umangpatel.auroniainterview.model.product;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({
        "Name",
        "Price"

})
public class Product {
    @Field("Name")
    private String name;
    @Field("Price")
    private long price;
}
