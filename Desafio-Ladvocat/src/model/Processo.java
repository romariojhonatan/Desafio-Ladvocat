package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "processo")
public class Processo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String numeroPublicacao;
	
	private String numeroPedidoInternacional;
	
	private String dataPublicacao;
	
	private String requerentes;
	
	private String titulo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumeroPublicacao() {
		return numeroPublicacao;
	}

	public void setNumeroPublicacao(String numeroPublicacao) {
		this.numeroPublicacao = numeroPublicacao;
	}

	public String getNumeroPedidoInternacional() {
		return numeroPedidoInternacional;
	}

	public void setNumeroPedidoInternacional(String numeroPedidoInternacional) {
		this.numeroPedidoInternacional = numeroPedidoInternacional;
	}

	public String getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(String dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public String getRequerentes() {
		return requerentes;
	}

	public void setRequerentes(String requerentes) {
		this.requerentes = requerentes;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public boolean isValid() {
		if(null == this.dataPublicacao && null == this.id && null == this.numeroPedidoInternacional &&
				null == this.numeroPublicacao && null == this.requerentes && null == this.titulo) {
			return false;
		} else {
			return true;
		}
	}
}
