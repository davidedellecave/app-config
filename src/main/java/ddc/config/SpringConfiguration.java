package ddc.config;

import java.nio.file.Path;

import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 * @author davide 2013
 */
public abstract class SpringConfiguration {
//	private static GenericApplicationContext context;
	
	public static GenericApplicationContext getApplicationContext(Class<?> clazz) throws ConfigurationException {
		Path path = ConfigurationPath.getPath(new ConfigurationName(clazz));
		return getApplicationContext(path, false);
	}
	
	public static GenericApplicationContext getApplicationContext(Path path, boolean validating) {
		System.out.println("Loading spring configuration - path:[" + path + "]");
		GenericXmlApplicationContext gac = new GenericXmlApplicationContext();
		gac.setValidating(validating);
		Resource res = new FileSystemResource(path.toFile());
		gac.load(res);
		gac.refresh();
		return gac;
	}

}
