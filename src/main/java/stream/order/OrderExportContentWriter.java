package stream.order;

import java.io.IOException;
import java.io.Writer;

class OrderExportContentWriter{
	
	private OrderRepo repo;
	
	OrderExportContentWriter(OrderRepo repo) {
		this.repo = repo;
	}

	protected void writeContent(Writer writer) throws IOException {
		writer.write("ID;Date\n");
		repo.findByActiveTrue()
			.map(o -> o.getId() + ";" + o.getCreationDate() + "\n")
			.forEach(t -> {
				try {
					writer.write(t);
				} catch(IOException e) {
					throw new RuntimeException(e);
				}
			});
	}
	
}