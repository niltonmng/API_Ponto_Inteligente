package com.nilton.pontointeligente.api.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.nilton.pontointeligente.api.enums.PerfilEnum;


@Entity
@Table(name = "funcionario")
public class Funcionario implements Serializable {

	private static final long serialVersionUID = -1931498073221379134L;
	
	private long id;
	private String nome;
	private String email;
	private String senha;
	private String cpf;
	private BigDecimal valorHora;
	private Float qtdHorasTrabalhoDia;
	private Float qtdHorasAlmoco;
	private PerfilEnum perfil;
	private Date dataCriacao;
	private Date dataAtualizacao;
	private Empresa empresa;
	private List<Lancamento> Lancamentos;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "nome", nullable = false)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name = "email", nullable = false)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "senha", nullable = false)
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Column(name = "cpf", nullable = false)
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	@Column(name = "valor_hora", nullable = false)
	public BigDecimal getValorHora() {
		return valorHora;
	}
	@Transient // importante para o DTO, anotação transient, significa para o jpa que esse método nao está relacionado com a implementação do mapeamento com o banco de dados, ele é um método que está ai por alguma outra razão que nao seja relacionada diretamente com o bd
	public Optional<BigDecimal> getValorHoraOpt() {
		return Optional.ofNullable(valorHora);
	}
	public void setValorHora(BigDecimal valorHora) {
		this.valorHora = valorHora;
	}
	
	@Column(name = "qtd_horas_trabalho_dia", nullable = false)
	public Float getQtdHorasTrabalhoDia() {
		return qtdHorasTrabalhoDia;
	}
	@Transient
	public Optional<Float> getQtdHorasTrabalhoDiaOpt() {
		return Optional.ofNullable(qtdHorasTrabalhoDia);
	}
	public void setQtdHorasTrabalhoDia(Float qtdHorasTrabalhoDia) {
		this.qtdHorasTrabalhoDia = qtdHorasTrabalhoDia;
	}
	
	@Column(name = "qtd_horas_almoco", nullable = false)
	public Float getQtdHorasAlmoco() {
		return qtdHorasAlmoco;
	}
	public void setQtdHorasAlmoco(Float qtdHorasAlmoco) {
		this.qtdHorasAlmoco = qtdHorasAlmoco;
	}
	
	
	@Enumerated(EnumType.STRING) // JPA supports converting database data to and from Java enum types via the @javax.persistence.Enumerated annotation
	@Column(name = "perfil", nullable = false)
	public PerfilEnum getPerfil() {
		return perfil;
	}
	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
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

	// muitos eventos para uma categoria
	@ManyToOne(fetch = FetchType.EAGER)
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	// um evento para muitas categorias
	@OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Lancamento> getLancamentos() {
		return Lancamentos;
	}
	public void setLancamentos(List<Lancamento> lancamentos) {
		Lancamentos = lancamentos;
	}
	
	@PreUpdate
	public void preUpdate() {
		dataAtualizacao = new Date();
	}
	
	@PrePersist
	public void prePersist() {
		final Date atual = new Date();
		dataCriacao = atual;
		dataAtualizacao = atual;
	}
	
	@Override
	public String toString() {
		return "Funcionario [id=" + this.id +
				", nome=" + this.nome +
				", email=" + this.email +
				", senha=" + this.senha +
				", cpf=" + this.cpf +
				", valorHora=" + this.valorHora +
				", qtdHorasTrabalhoDia=" + this.qtdHorasTrabalhoDia+
				", qtdHorasAlmoco=" + this.qtdHorasAlmoco +
				", perfil=" + this.perfil +
				", dataCriacao=" + this.dataCriacao +
				", dataAtualizacao=" + this.dataAtualizacao + 
				", empresa=" + this.empresa +
				"]"; 
	}
	

}
