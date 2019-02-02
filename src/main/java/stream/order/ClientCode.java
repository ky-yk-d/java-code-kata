package stream.order;

import java.io.IOException;

class ClientCode {
	public static void main(String[] args) throws Exception {
		OrderRepo orderRepo = new OrderRepoImpl();
		UserRepo userRepo = new UserRepoImpl();
		Exporter exporter = new Exporter();
		OrderExportContentWriter orderContent = new OrderExportContentWriter(orderRepo);
		exporter.exportFile("order.txt",(t)->{
			try {
				orderContent.writeContent(t);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		UserExportContentWriter userContent = new UserExportContentWriter(userRepo);
		exporter.exportFile("user.txt", (t)->{
			try {
				userContent.writeContent(t);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
	}
}