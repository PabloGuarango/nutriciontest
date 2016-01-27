package ec.edu.epn.nutricion.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;



/**
 * The persistent class for the antecedentes_alimentarios database table.
 * 
 */
@Entity
@Table(name="antecedentes_alimentarios")
@NamedQuery(name="AntecedenteAlimentario.findAll", query="SELECT a FROM AntecedenteAlimentario a")
public class AntecedenteAlimentario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_ANTECEDENTE_ALIMENTARIO")
	private int idAntecedenteAlimentario;

	@Column(name="AGUA_SOLA")
	private int aguaSola;

	@Column(name="ENTRECOMIDAS")
	private String entrecomidas;

	private String apetito;

	@Column(name="CON_QUIEN_VIVE")
	private String conQuienVive;

	@Column(name="DIFICULTAD_MASTICAR_TRAGAR")
	private byte dificultadMasticarTragar;

	@Column(name="DONDE_ALIMENTA")
	private String dondeAlimenta;

	private String habitos;

	private int liquidos;

	@Column(name="QUIEN_PREPARA_ALIMENTO")
	private String quienPreparaAlimento;

	@Column(name="VECES_ALIMENTA")
	private int vecesAlimenta;

	//bi-directional many-to-one association to Alimento
	@OneToMany(mappedBy="antecedenteAlimentario")
	private List<Alimento> alimentos;

	//bi-directional many-to-one association to IntoleranciaAlergica
	@OneToMany(mappedBy="antecedenteAlimentario")
	private List<IntoleranciaAlergica> intoleranciaAlergicas;

	//bi-directional many-to-one association to ProblemaGastrointestinal
	@OneToMany(mappedBy="antecedenteAlimentario")
	private List<ProblemaGastrointestinal> problemaGastrointestinals;

	public AntecedenteAlimentario() {
	}

	public int getIdAntecedentesAlimentarios() {
		return this.idAntecedenteAlimentario;
	}

	public void setIdAntecedentesAlimentarios(int idAntecedentesAlimentarios) {
		this.idAntecedenteAlimentario= idAntecedentesAlimentarios;
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

	public List<Alimento> getAlimentos() {
		if(this.alimentos==null)
			this.alimentos=new ArrayList<Alimento>();
		return this.alimentos;
	}

	public void setAlimentos(List<Alimento> alimentos) {
		this.alimentos = alimentos;
	}

	public Alimento addAlimento(Alimento alimento) {
		getAlimentos().add(alimento);
		alimento.setAntecedentesAlimentario(this);
		return alimento;
	}

	public Alimento removeAlimento(Alimento alimento) {
		getAlimentos().remove(alimento);
		alimento.setAntecedentesAlimentario(null);

		return alimento;
	}

	public List<IntoleranciaAlergica> getIntoleranciaAlergicas() {
		if(this.intoleranciaAlergicas==null)
			this.intoleranciaAlergicas=new ArrayList<IntoleranciaAlergica>();
		return this.intoleranciaAlergicas;
	}

	public void setIntoleranciaAlergicas(List<IntoleranciaAlergica> intoleranciaAlergicas) {
		this.intoleranciaAlergicas = intoleranciaAlergicas;
	}

	public IntoleranciaAlergica addIntoleranciaAlergica(IntoleranciaAlergica intoleranciaAlergica) {
		getIntoleranciaAlergicas().add(intoleranciaAlergica);
		intoleranciaAlergica.setAntecedentesAlimentario(this);

		return intoleranciaAlergica;
	}

	public IntoleranciaAlergica removeIntoleranciaAlergica(IntoleranciaAlergica intoleranciaAlergica) {
		getIntoleranciaAlergicas().remove(intoleranciaAlergica);
		intoleranciaAlergica.setAntecedentesAlimentario(null);

		return intoleranciaAlergica;
	}

	public List<ProblemaGastrointestinal> getProblemaGastrointestinals() {
		if(this.problemaGastrointestinals==null)
			this.problemaGastrointestinals=new ArrayList<ProblemaGastrointestinal>();
		return this.problemaGastrointestinals;
	}

	public void setProblemaGastrointestinals(List<ProblemaGastrointestinal> problemaGastrointestinals) {
		this.problemaGastrointestinals = problemaGastrointestinals;
	}

	public ProblemaGastrointestinal addProblemaGastrointestinal(ProblemaGastrointestinal problemaGastrointestinal) {
		getProblemaGastrointestinals().add(problemaGastrointestinal);
		problemaGastrointestinal.setAntecedentesAlimentario(this);

		return problemaGastrointestinal;
	}

	public ProblemaGastrointestinal removeProblemaGastrointestinal(ProblemaGastrointestinal problemaGastrointestinal) {
		getProblemaGastrointestinals().remove(problemaGastrointestinal);
		problemaGastrointestinal.setAntecedentesAlimentario(null);

		return problemaGastrointestinal;
	}

}