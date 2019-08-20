package com.nilton.pontointeligente.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity // informa ao JPS que se trata de uma entidade JPA
@Table(name = "empresa") // é opcional, permite definir o nome da tabela no bd para a entidade
public class Empresa implements Serializable {

	private static final long serialVersionUID = 5470572157038586483L;
	
	private long id;
	private String razaoSocial;
	private String cnpj;
	private Date dataCriacao;
	private Date dataAtualizacao;
	private List<Funcionario> funcionarios;
	
	public Empresa() {
		
	}
	
	@Id // chave primaria do bd
	@GeneratedValue(strategy = GenerationType.AUTO) // informa como ocorre o incremento da chave primaria, sendo que o modo automatico incrementará o valor em 1 a cada nova insercao
	private long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "razao_social", nullable = false) // define o nome para o campo no bd, no caso o nome é (name="<nome dado>")
	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	@Column(name = "cnpj", nullable = false)
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Column(name = "data_criacao", nullable = false)
	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Column(name = "data_atualizacao", nullable = false)
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	
	@OneToMany(mappedBy="empresa", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	@PreUpdate // é opcional e permite executar uma acao antes de uma atualizacao de registro
	public void preUpdate() {
		dataAtualizacao = new Date();
	}
	
	@PrePersist // é opcional e permite executar uma acao antes de uma insercao de registro
	public void prePersist() {
		final Date atual = new Date();
		dataCriacao = atual;
		dataAtualizacao = atual;
	}
	
	@Override
	public String toString() {
		return "Empresa [id=" + this.id +
				", razaoSocial=" + this.razaoSocial +
				", cnpj=" + this.cnpj +
				", dataCriacao=" + this.dataCriacao +
				", dataAtualizacao=" + this.dataAtualizacao + "]"; 
	}
	
}
