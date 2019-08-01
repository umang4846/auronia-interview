package com.umangpatel.auroniainterview.repository;

import com.umangpatel.auroniainterview.model.order.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order,String> {

    Order findBy_id(String id);

    void  deleteBy_id(String id);
}
