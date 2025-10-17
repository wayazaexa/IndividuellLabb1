package org.example.Repositories;

import org.example.Models.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class OrderRepository {
    private final Logger log = LoggerFactory.getLogger(OrderRepository.class);

    /* I chose a LinkedList to store the list of all orders, because the order might be important (though that could
     * instead be tracked by including date and time), it's a list that I expect to not be accessed via index,
     * and it's a list that will generally only have things added to the end. The only exception I can think of is if
     * a customer wants to cancel an order, but that is probably best saved in the system anyway with an additional
     * flag to indicate that an order has been cancelled.
     */
    private final List<Order> allOrders = new LinkedList<>();

    /* I chose to not include a Map of all orders per customer because this can just as easily be done with the
     * list of all orders and a filter via stream(), which I feel reduces the risk of messing up any potential changes
     * to an order and also saves storage space.
     */

    public List<Order> getAllOrders() {
        return allOrders;
    }

    public void addOrder(Order order) {
        // Special case if someone ignores the warnings and send in null
        if (order == null) {
            log.error("Order is null");
        }
        else if (!order.equals(new Order())) {
            allOrders.add(order);
        }
    }
}
