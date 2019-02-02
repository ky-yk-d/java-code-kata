package stream.order;

import java.io.File;


import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.function.Consumer;

class Exporter {
	
	public File exportFile(String fileName, Consumer<Writer> contentWriter) throws Exception {
		File file = new File("../" + fileName);
		try (Writer writer = new FileWriter(file)){
			contentWriter.accept(writer);
			return file;
		} catch (Exception e) {
			System.err.println("Panic!" + e);
			throw e;
		}
	}
	
}