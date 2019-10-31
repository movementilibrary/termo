package br.com.dasa.api.termo.dao;

public class ParametroQuery {

	private Object value;
	private String key;

	public ParametroQuery(String key, Object value) {
		this.key = key;
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
