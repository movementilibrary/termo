package br.com.dasa.api.termo.exceptions.enumsExceptions;

import br.com.dasa.api.termo.exceptions.ApiException;

public class ExceptionsAceite extends ApiException {

	private static final long serialVersionUID = 1L;

	public ExceptionsAceite(String msg, Exception e, String msg1, AceiteTermoEnums termoEnums) {
		super(msg, e);
		this.msg = msg1;
		this.termoEnums = termoEnums;
	}

	public ExceptionsAceite(String msg, String msg1, AceiteTermoEnums termoEnums) {
		super(msg);
		this.msg = msg1;
		this.termoEnums = termoEnums;
	}

	private String msg;
	private AceiteTermoEnums termoEnums;

	@Override
	public String getErro() {
		return this.termoEnums.getMsg();
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
