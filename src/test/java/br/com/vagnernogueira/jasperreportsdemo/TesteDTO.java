package br.com.vagnernogueira.jasperreportsdemo;

import java.io.Serializable;

public class TesteDTO implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String nome;
	
	public TesteDTO(String nome)
	{
		super();
		this.nome = nome;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}
}
