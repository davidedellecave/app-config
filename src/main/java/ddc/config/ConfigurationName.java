package ddc.config;

public class ConfigurationName {
	String name = "app";

	public ConfigurationName(String name) {
		super();
		this.name = name;
	}
	
	public ConfigurationName(Class<?> clazz) {
		super();
		name = clazz.getPackage().getName();
	}

	public String getName() {
		return name + ".conf";
	}

	public void setName(String name) {
		this.name = name;
	}
}
