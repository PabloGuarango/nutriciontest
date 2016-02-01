package ec.edu.epn.nutricion.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * The persistent class for the antecedentes_alimentarios database table.
 * 
 */
@Entity
@Table(name = "antecedentes_alimentarios")
@NamedQuery(name = "AntecedenteAlimentario.findAll", query = "SELECT a FROM AntecedenteAlimentario a")
public class AntecedenteAlimentario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ANTECEDENTE_ALIMENTARIO")
	private int idAntecedenteAlimentario;

	@Column(name = "AGUA_SOLA")
	private int aguaSola;

	@Column(name = "ENTRECOMIDAS")
	private String entrecomidas;

	private String apetito;

	@Column(name = "CON_QUIEN_VIVE")
	private String conQuienVive;

	@Column(name = "DIFICULTAD_MASTICAR_TRAGAR")
	private byte dificultadMasticarTragar;

	@Column(name = "DONDE_ALIMENTA")
	private String dondeAlimenta;

	private String habitos;

	private int liquidos;

	@Column(name = "QUIEN_PREPARA_ALIMENTO")
	private String quienPreparaAlimento;

	@Column(name = "VECES_ALIMENTA")
	private int vecesAlimenta;

	// bi-directional many-to-one association to Alimento
	@OneToMany(mappedBy = "antecedenteAlimentario")
	private List<Alimento> listaAlimentos;

	// bi-directional many-to-one association to IntoleranciaAlergica
	@OneToMany(mappedBy = "antecedenteAlimentario")
	private List<IntoleranciaAlergica> listaIntoleranciaAlergicas;

	// bi-directional many-to-one association to ProblemaGastrointestinal
	@OneToMany(mappedBy = "antecedenteAlimentario")
	private List<ProblemaGastrointestinal> listaProblemaGastrointestinals;
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Paciente paciente;
	public AntecedenteAlimentario() {
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public int getAguaSola() {
		return this.aguaSola;
	}

	public void setAguaSola(int aguaSola) {
		this.aguaSola = aguaSola;
	}

	public int getIdAntecedenteAlimentario() {
		return idAntecedenteAlimentario;
	}

	public void setIdAntecedenteAlimentario(int idAntecedenteAlimentario) {
		this.idAntecedenteAlimentario = idAntecedenteAlimentario;
	}

	public String getEntrecomidas() {
		return entrecomidas;
	}

	public void setEntrecomidas(String entrecomidas) {
		this.entrecomidas = entrecomidas;
	}

	public String getApetito() {
		return this.apetito;
	}

	public void setApetito(String apetito) {
		this.apetito = apetito;
	}

	public String getConQuienVive() {
		return this.conQuienVive;
	}

	public void setConQuienVive(String conQuienVive) {
		this.conQuienVive = conQuienVive;
	}

	public byte getDificultadMasticarTragar() {
		return this.dificultadMasticarTragar;
	}

	public void setDificultadMasticarTragar(byte dificultadMasticarTragar) {
		this.dificultadMasticarTragar = dificultadMasticarTragar;
	}

	public String getDondeAlimenta() {
		return this.dondeAlimenta;
	}

	public void setDondeAlimenta(String dondeAlimenta) {
		this.dondeAlimenta = dondeAlimenta;
	}

	public String getHabitos() {
		return this.habitos;
	}

	public void setHabitos(String habitos) {
		this.habitos = habitos;
	}

	public int getLiquidos() {
		return this.liquidos;
	}

	public void setLiquidos(int liquidos) {
		this.liquidos = liquidos;
	}

	public String getQuienPreparaAlimento() {
		return this.quienPreparaAlimento;
	}

	public void setQuienPreparaAlimento(String quienPreparaAlimento) {
		this.quienPreparaAlimento = quienPreparaAlimento;
	}

	public int getVecesAlimenta() {
		return this.vecesAlimenta;
	}

	public void setVecesAlimenta(int vecesAlimenta) {
		this.vecesAlimenta = vecesAlimenta;
	}

	public Alimento addAlimento(Alimento alimento) {
		getListaAlimentos().add(alimento);
		alimento.setAntecedentesAlimentario(this);
		return alimento;
	}

	public Alimento removeAlimento(Alimento alimento) {
		getListaAlimentos().remove(alimento);
		alimento.setAntecedentesAlimentario(null);

		return alimento;
	}

	public IntoleranciaAlergica addIntoleranciaAlergica(IntoleranciaAlergica intoleranciaAlergica) {
		getListaIntoleranciaAlergicas().add(intoleranciaAlergica);
		intoleranciaAlergica.setAntecedentesAlimentario(this);

		return intoleranciaAlergica;
	}

	public IntoleranciaAlergica removeIntoleranciaAlergica(IntoleranciaAlergica intoleranciaAlergica) {
		getListaIntoleranciaAlergicas().remove(intoleranciaAlergica);
		intoleranciaAlergica.setAntecedentesAlimentario(null);

		return intoleranciaAlergica;
	}

	public ProblemaGastrointestinal addProblemaGastrointestinal(ProblemaGastrointestinal problemaGastrointestinal) {
		getListaProblemaGastrointestinals().add(problemaGastrointestinal);
		problemaGastrointestinal.setAntecedentesAlimentario(this);

		return problemaGastrointestinal;
	}

	public ProblemaGastrointestinal removeProblemaGastrointestinal(ProblemaGastrointestinal problemaGastrointestinal) {
		getListaProblemaGastrointestinals().remove(problemaGastrointestinal);
		problemaGastrointestinal.setAntecedentesAlimentario(null);

		return problemaGastrointestinal;
	}

	public List<Alimento> getListaAlimentos() {
		return listaAlimentos;
	}

	public void setListaAlimentos(List<Alimento> listaAlimentos) {
		this.listaAlimentos = listaAlimentos;
	}

	public List<IntoleranciaAlergica> getListaIntoleranciaAlergicas() {
		return listaIntoleranciaAlergicas;
	}

	public void setListaIntoleranciaAlergicas(List<IntoleranciaAlergica> listaIntoleranciaAlergicas) {
		this.listaIntoleranciaAlergicas = listaIntoleranciaAlergicas;
	}

	public List<ProblemaGastrointestinal> getListaProblemaGastrointestinals() {
		return listaProblemaGastrointestinals;
	}

	public void setListaProblemaGastrointestinals(List<ProblemaGastrointestinal> listaProblemaGastrointestinals) {
		this.listaProblemaGastrointestinals = listaProblemaGastrointestinals;
	}
}