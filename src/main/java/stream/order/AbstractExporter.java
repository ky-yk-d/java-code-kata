package stream.order;

import java.io.File;


import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

abstract class AbstractExporter {
	
	public File exportFile(String fileName) throws Exception {
		File file = new File("../" + fileName);
		try (Writer writer = new FileWriter(file)){
			writeContent(writer);
			return file;
		} catch (Exception e) {
			System.err.println("Panic!" + e);
			throw e;
		}
	}

	protected abstract void writeContent(Writer writer) throws IOException;
	
}