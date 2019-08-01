package com.umangpatel.auroniainterview.restcontroller;

import com.umangpatel.auroniainterview.model.order.Order;
import com.umangpatel.auroniainterview.model.order.OrderItem;
import com.umangpatel.auroniainterview.model.response.Response;
import com.umangpatel.auroniainterview.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orderAPI")
public class OrderController {

    private OrderRepository orderRepository;

    private final MongoTemplate mongoTemplate;

    @Autowired
    public OrderController(OrderRepository orderRepository, MongoTemplate mongoTemplate) {
        this.orderRepository = orderRepository;
        this.mongoTemplate = mongoTemplate;
    }

    /*
     * Used to get all orders in the database
     */
    @GetMapping()
    public List<Order> getAllOrders() {

        //Return all the orders
        return orderRepository.findAll();
    }

    /*
     * Used to get the specific order by Id
     * need to provide Id of the order to get order details
     * if no order found then return null
     */
    @GetMapping(value = "orderById/{id}")
    public Response getOrderById(@PathVariable("id") String id) {

        if (orderRepository.findBy_id(id) != null)
            //return the order by specific id
            return new Response("Order details by Id.", true, Collections.singletonList(orderRepository.findBy_id(id)));

        else
            return new Response("No Order found by given Id.", false, Collections.singletonList(orderRepository.findBy_id(id)));


    }

    /*
     * Used to add new Order to database
     * Need to use POST mapping
     * return added order details
     */
    @PostMapping("/addOrder")
    public Response addNewOrder(@RequestBody Order order) {

        //Check Order is new Or Existing.
        if (order.get_id() != null || order.getOrderGuid() != null)
            return new Response("Trying to Update Order using POST Request.Use PUT Request to Update Order !", false, new ArrayList<>());

        else if (order.get_id() == null || order.getOrderGuid() == null) {
            //Check First name
            if (order.getFirstName() == null)
                return new Response("Please add First name !", false, new ArrayList<>());

            //Check Last name
            if (order.getLastName() == null)
                return new Response("Please add Last name !", false, new ArrayList<>());

            //Check Email Address
            if (order.getCustomerEmail() == null)
                return new Response("Please add Email address !", false, new ArrayList<>());

            //Check Shipping address
            if (order.getShippingAddress() == null ||
                    order.getShippingAddress().getFirstName() == null ||
                    order.getShippingAddress().getLastName() == null ||
                    order.getShippingAddress().getEmail() == null ||
                    order.getShippingAddress().getPhoneNumber() == null ||
                    order.getShippingAddress().getAddress1() == null ||
                    order.getShippingAddress().getAddress2() == null ||
                    order.getShippingAddress().getCity() == null ||
                    order.getShippingAddress().getZipPostalCode() == null
            )
                return new Response("Please fill-up all shipping address details!", false, new ArrayList<>());

            if (order.getOrderItems() == null)
                return new Response("Please add atleast one Order Item !", false, new ArrayList<>());

            order.set_id("");
            order.setOrderGuid(UUID.randomUUID());
            long price = 0;

            for (OrderItem orderItem : order.getOrderItems()) {

                //Check product details
                if (orderItem.getProduct() == null ||
                        orderItem.getProduct().getName() == null ||
                        orderItem.getProduct().getPrice() <= 0)
                    return new Response("Please add proper product details !", false, new ArrayList<>());

                //Check Order Item Quantity
                if (orderItem.getQuantity() <= 0)
                    return new Response("Please add atleast 1 quantity !", false, new ArrayList<>());

                orderItem.set_id("");
                orderItem.setCreatedOnUtc("");

                orderItem.setUnitPriceInclTax(orderItem.getProduct().getPrice());
                orderItem.setPriceInclTax(orderItem.getQuantity() * orderItem.getUnitPriceInclTax());
                //Check Order item price details
                if (orderItem.getUnitPriceInclTax() <= 0 ||
                        orderItem.getPriceInclTax() <= 0)

                    return new Response("Please add proper price details of Order Items !", false, new ArrayList<>());

                price += (orderItem.getPriceInclTax());

            }
            order.setCreatedOnUtc("");
            //total price of order
            order.setOrderTotal(price);

            //add new order
            mongoTemplate.insert(order);

            Query query = new Query();
            query.addCriteria(Criteria.where("OrderGuid").is(order.getOrderGuid()));
            Order newOrder = mongoTemplate.findOne(query, Order.class);

            if (newOrder != null)
                return new Response("Order added successfully", true, Collections.singletonList(newOrder));
            else
                return new Response("Adding order failed !", false, Collections.singletonList(newOrder));

        }
        return null;
    }

    /*
     * PUT Mapping
     * Used to update the Order details in the database
     * Need to pass id and Order Object in the API
     * return updated order details
     */
    @PutMapping("/updateOrder")
    @ResponseBody
    public Response updateOrder(@RequestBody Order order) {

        //Check order already exist or not
        if (order.get_id() == null && order.getOrderGuid() == null) {
            return new Response("Trying to add new Order using PUT Request.Use POST Request to add new Order !", false, new ArrayList<>());

        } else if (order.get_id() != null && order.getOrderGuid() != null) {

            //First order of Given id is exist or not
            Order existingOrder = orderRepository.findBy_id(order.get_id());

            if (existingOrder == null)
                return new Response("No order found by given order Id.", false, new ArrayList<>());

            else {
                //Check First name
                if (order.getFirstName() == null)
                    return new Response("Please add First name !", false, new ArrayList<>());

                //Check Last name
                if (order.getLastName() == null)
                    return new Response("Please add Last name !", false, new ArrayList<>());

                //Check Email Address
                if (order.getCustomerEmail() == null)
                    return new Response("Please add Email address !", false, new ArrayList<>());

                //Check Shipping address
                if (order.getShippingAddress() == null ||
                        order.getShippingAddress().getFirstName() == null ||
                        order.getShippingAddress().getLastName() == null ||
                        order.getShippingAddress().getEmail() == null ||
                        order.getShippingAddress().getPhoneNumber() == null ||
                        order.getShippingAddress().getAddress1() == null ||
                        order.getShippingAddress().getAddress2() == null ||
                        order.getShippingAddress().getCity() == null ||
                        order.getShippingAddress().getZipPostalCode() == null
                )
                    return new Response("Please fill-up all shipping address details!", false, new ArrayList<>());

                if (order.getOrderItems().size()==0)
                    return new Response("Please add atleast one Order Item !", false, new ArrayList<>());

                long price = 0;

                for (OrderItem orderItem : order.getOrderItems()) {

                    //Check product details
                    if (orderItem.getProduct() == null ||
                            orderItem.getProduct().getName() == null ||
                            orderItem.getProduct().getPrice() <= 0)
                        return new Response("Please add proper product details !", false, new ArrayList<>());

                    //Check Order Item Quantity
                    if (orderItem.getQuantity() <= 0)
                        return new Response("Please add atleast 1 quantity !", false, new ArrayList<>());

                    orderItem.set_id("");
                    orderItem.setCreatedOnUtc("");

                    orderItem.setUnitPriceInclTax(orderItem.getProduct().getPrice());
                    orderItem.setPriceInclTax(orderItem.getQuantity() * orderItem.getUnitPriceInclTax());
                    //Check Order item price details
                    if (orderItem.getUnitPriceInclTax() <= 0 ||
                            orderItem.getPriceInclTax() <= 0)

                        return new Response("Please add proper price details of Order Items !", false, new ArrayList<>());

                    price += (orderItem.getPriceInclTax());

                }
                //total price of order
                order.setOrderTotal(price);

                existingOrder.setOrderItems(order.getOrderItems());
                existingOrder.setOrderTotal(order.getOrderTotal());
                existingOrder.setFirstName(order.getFirstName());
                existingOrder.setLastName(order.getLastName());
                existingOrder.setCustomerEmail(order.getCustomerEmail());
                existingOrder.setOrderGuid(order.getOrderGuid());
                existingOrder.setShippingAddress(order.getShippingAddress());
                existingOrder.setCreatedOnUtc("");

                //save Updated order
                mongoTemplate.save(order);

                Query query = new Query();
                query.addCriteria(Criteria.where("OrderGuid").is(order.getOrderGuid()));
                Order updatedOrder = mongoTemplate.findOne(query, Order.class);

                if (updatedOrder != null)
                    return new Response("Order Updated successfully.", true, Collections.singletonList(updatedOrder));
                else
                    return new Response("Updating order failed !", false, Collections.singletonList(updatedOrder));

            }
        } else if (order.get_id() == null)
            return new Response("Order Id is empty !", false, new ArrayList<>());
        else if (order.getOrderGuid() == null)
            return new Response("Order GUID is empty !", false, new ArrayList<>());
        return null;

    }
    /*
     * DELETE Mapping
     * used to delete the order from the database
     * need to provide id of order
     */

    @DeleteMapping("deleteOrder/{id}")
    public Response DeleteOrder(@PathVariable("id") String id) {
        if (orderRepository.findBy_id(id) == null)
            return new Response("No Order found by given Id.", false, Collections.singletonList(orderRepository.findBy_id(id)));
        else {
            //Delete order by id
            orderRepository.deleteBy_id(id);
            Order order = orderRepository.findBy_id(id);
            if (order == null)
                return new Response("Order deleted successfully.", true, new ArrayList<>());
            else
                return new Response("Unable to delete order !", false, new ArrayList<>());
        }
    }
}
