package ddc.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class XmlConfiguration<T> {

	public void write(Path path, Class<T> clazz) throws ConfigurationException {
		try {
			T o = clazz.newInstance();
			write(path, o);
		} catch (InstantiationException | IllegalAccessException | IOException e) {
			String info = "Configuration Writing - file:[" + path.toString() + "] exception:[" + e.getMessage() + "]";
			throw new ConfigurationException(info);
		}
	}

	public void write(Path path, T instance) throws IOException {
		XStream xstream = new XStream(new StaxDriver());
		String xml = xstream.toXML(instance);
		Files.write(path, xml.getBytes());
	}
	
	public void delete(Path path) throws ConfigurationException {
		try {
			Files.delete(path);
		} catch (IOException e) {
			String info = "Configuration Deleting - file:[" + path.toString() + "] exception:[" + e.getMessage() + "]";
			throw new ConfigurationException(info);
		}
	}

	@SuppressWarnings("unchecked")
	public T read(Path path) throws IOException {
		byte[] bytes = Files.readAllBytes(path);
		String xml = new String(bytes, "UTF-8");
		XStream xstream = new XStream(new StaxDriver());
		Object o = xstream.fromXML(xml);
		return (T)o;	
	}



}
