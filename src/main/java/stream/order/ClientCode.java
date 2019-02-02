package stream.order;

class ClientCode {
	public static void main(String[] args) throws Exception {
		OrderRepo orderRepo = new OrderRepoImpl();
		UserRepo userRepo = new UserRepoImpl();
		new OrderExporter(orderRepo).exportFile("order.txt");
		new UserExporter(userRepo).exportFile("user.txt");
	}
}