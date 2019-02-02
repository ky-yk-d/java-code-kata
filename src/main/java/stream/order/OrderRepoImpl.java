package stream.order;

import java.util.stream.Stream;

public class OrderRepoImpl implements OrderRepo {

	@Override
	public Stream<Order> findByActiveTrue() {
		return Stream.of(new Order("001", "20190201"),new Order("002", "20190202"), new Order("003", "20190203"));
	}

}
