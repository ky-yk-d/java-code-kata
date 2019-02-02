package stream.order;

import java.io.IOException;
import java.io.Writer;

class UserExporter extends AbstractExporter{
	private UserRepo repo;

	UserExporter(UserRepo repo) {
		this.repo = repo;
	}
	
	@Override
	public void writeContent(Writer writer) throws IOException {
		writer.write("Username\n");
		repo.findAll().stream()
			.map(u -> u.getUsername() + "\n")
			.forEach(t -> {
				try {
					writer.write(t);
				} catch(IOException e) {
					throw new RuntimeException(e);
				}
			});
	}	
	
}