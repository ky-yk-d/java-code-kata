package stream.order;

import java.util.stream.Stream;

interface OrderRepo {

	Stream<Order> findByActiveTrue();

}
