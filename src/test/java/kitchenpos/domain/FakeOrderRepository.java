package kitchenpos.domain;

import java.util.*;

public class FakeOrderRepository implements OrderRepository {
    private Map<UUID, Order> map = new HashMap<>();

    @Override
    public boolean existsByOrderTableAndStatusNot(OrderTable orderTable, OrderStatus status) {
        return map.values()
                .stream()
                .noneMatch(order -> (OrderStatus.COMPLETED.equals(order.getStatus()) &&
                        orderTable.getId().equals(order.getOrderTableId())));
    }

    @Override
    public Order save(Order order) {
        map.put(order.getId(), order);
        return order;
    }

    @Override
    public Optional<Order> findById(UUID id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public List<Order> findAll() {
        return null;
    }
}
