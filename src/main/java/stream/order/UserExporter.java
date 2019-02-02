package stream.order;

import java.io.IOException;
import java.io.Writer;

class UserExportContentWriter{
	private UserRepo repo;

	UserExportContentWriter(UserRepo repo) {
		this.repo = repo;
	}
	
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