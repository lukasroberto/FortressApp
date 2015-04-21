package br.com.grupofortress.model;

import java.util.ArrayList;

/**
 *
 * @author informatica
 */
public class Evento {

	private Long eve_id;
	private String status;
	private String eve_data;
	private String eve_conta_grupo_receptor;
	private String eve_codigo_cliente;
	private String eve_protocolo;
	private String eve_codigo_evento;
	private String eve_particao;
	private String eve_usuario_zona;
	private String cli_nome;
	private String evento_descricao;

	private static Evento instancia;

	public static Evento getInstancia() {
		if (instancia == null) {
			instancia = new Evento();
		}
		return instancia;
	}

	public Long getEve_id() {
		return eve_id;
	}

	public void setEve_id(Long eve_id) {
		this.eve_id = eve_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEve_data() {
		return eve_data;
	}

	public void setEve_data(String eve_data) {
		this.eve_data = eve_data;
	}

	public String getEve_conta_grupo_receptor() {
		return eve_conta_grupo_receptor;
	}

	public void setEve_conta_grupo_receptor(String eve_conta_grupo_receptor) {
		this.eve_conta_grupo_receptor = eve_conta_grupo_receptor;
	}

	public String getEve_codigo_cliente() {
		return eve_codigo_cliente;
	}

	public void setEve_codigo_cliente(String eve_codigo_cliente) {
		this.eve_codigo_cliente = eve_codigo_cliente;
	}

	public String getEve_protocolo() {
		return eve_protocolo;
	}

	public void setEve_protocolo(String eve_protocolo) {
		this.eve_protocolo = eve_protocolo;
	}

	public String getEve_codigo_evento() {
		return eve_codigo_evento;
	}

	public void setEve_codigo_evento(String eve_codigo_evento) {
		this.eve_codigo_evento = eve_codigo_evento;
	}

	public String getEve_particao() {
		return eve_particao;
	}

	public void setEve_particao(String eve_particao) {
		this.eve_particao = eve_particao;
	}

	public String getEve_usuario_zona() {
		return eve_usuario_zona;
	}

	public void setEve_usuario_zona(String eve_usuario_zona) {
		this.eve_usuario_zona = eve_usuario_zona;
	}

	public String getCli_nome() {
		return cli_nome;
	}

	public void setCli_nome(String cli_nome) {
		this.cli_nome = cli_nome;
	}

	public String getEvento_descricao() {
		return evento_descricao;
	}

	public void setEvento_descricao(String evento_descricao) {
		this.evento_descricao = evento_descricao;
	}

}
