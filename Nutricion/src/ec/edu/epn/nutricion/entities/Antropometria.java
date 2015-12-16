package ec.edu.epn.nutricion.entities;

import java.io.Serializable;

import javax.persistence.*;


import java.util.List;


/**
 * The persistent class for the antropometria database table.
 * 
 */
@Entity
@NamedQuery(name="Antropometria.findAll", query="SELECT a FROM Antropometria a")
public class Antropometria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_ANTROPOMETRIA")
	private int idAntropometria;

	@Column(name="CIRCUNFERENCIA_MUNECA")
	private float circunferenciaMuneca;

	@Column(name="CONSTITUCION_CORPORAL")
	private float constitucionCorporal;

	@Column(name="DIAMETRO_CADERA")
	private float diametroCadera;

	@Column(name="DIAMETRO_CINTURA")
	private float diametroCintura;

	@Column(name="EDAD_METABOLICA")
	private float edadMetabolica;

	@Column(name="GASTO_ENERGETICO_BASAL")
	private float gastoEnergeticoBasal;

	@Column(name="INDICE_CINTURA_CADERA")
	private float indiceCinturaCadera;

	@Column(name="INDICE_MASA_CORPORAL")
	private float indiceMasaCorporal;

	@Column(name="NIVEL_GRASA_VISCERAL")
	private float nivelGrasaVisceral;

	@Column(name="OBSERVACION_PLIEGUE")
	private String observacionPliegue;

	@Column(name="PERIMETRO_BRAQUIAL")
	private float perimetroBraquial;

	@Column(name="PESO_ACTUAL")
	private float pesoActual;

	@Column(name="PESO_IDEAL")
	private float pesoIdeal;

	@Column(name="PLIEGUE_TRICIPITAL")
	private float pliegueTricipital;

	@Column(name="PORCENTAJE_GRASA")
	private float porcentajeGrasa;

	@Column(name="PORCENTAJE_MUSCULO_ESQUILETO")
	private float porcentajeMusculoEsquileto;

	@Column(name="RESERVA_PROTEICA")
	private float reservaProteica;

	private float talla;

	//bi-directional many-to-one association to DatosMedico
	@ManyToOne
	@JoinColumn(name="ID_DATOS_MEDICOS")
	private DatosMedico datosMedico;

	//bi-directional many-to-one association to DatosMedico
	@OneToMany(mappedBy="antropometria")
	private List<DatosMedico> datosMedicos;

	public Antropometria() {
	}

	public int getIdAntropometria() {
		return this.idAntropometria;
	}

	public void setIdAntropometria(int idAntropometria) {
		this.idAntropometria = idAntropometria;
	}

	public float getCircunferenciaMuneca() {
		return this.circunferenciaMuneca;
	}

	public void setCircunferenciaMuneca(float circunferenciaMuneca) {
		this.circunferenciaMuneca = circunferenciaMuneca;
	}

	public float getConstitucionCorporal() {
		return this.constitucionCorporal;
	}

	public void setConstitucionCorporal(float constitucionCorporal) {
		this.constitucionCorporal = constitucionCorporal;
	}

	public float getDiametroCadera() {
		return this.diametroCadera;
	}

	public void setDiametroCadera(float diametroCadera) {
		this.diametroCadera = diametroCadera;
	}

	public float getDiametroCintura() {
		return this.diametroCintura;
	}

	public void setDiametroCintura(float diametroCintura) {
		this.diametroCintura = diametroCintura;
	}

	public float getEdadMetabolica() {
		return this.edadMetabolica;
	}

	public void setEdadMetabolica(float edadMetabolica) {
		this.edadMetabolica = edadMetabolica;
	}

	public float getGastoEnergeticoBasal() {
		return this.gastoEnergeticoBasal;
	}

	public void setGastoEnergeticoBasal(float gastoEnergeticoBasal) {
		this.gastoEnergeticoBasal = gastoEnergeticoBasal;
	}

	public float getIndiceCinturaCadera() {
		return this.indiceCinturaCadera;
	}

	public void setIndiceCinturaCadera(float indiceCinturaCadera) {
		this.indiceCinturaCadera = indiceCinturaCadera;
	}

	public float getIndiceMasaCorporal() {
		return this.indiceMasaCorporal;
	}

	public void setIndiceMasaCorporal(float indiceMasaCorporal) {
		this.indiceMasaCorporal = indiceMasaCorporal;
	}

	public float getNivelGrasaVisceral() {
		return this.nivelGrasaVisceral;
	}

	public void setNivelGrasaVisceral(float nivelGrasaVisceral) {
		this.nivelGrasaVisceral = nivelGrasaVisceral;
	}

	public String getObservacionPliegue() {
		return this.observacionPliegue;
	}

	public void setObservacionPliegue(String observacionPliegue) {
		this.observacionPliegue = observacionPliegue;
	}

	public float getPerimetroBraquial() {
		return this.perimetroBraquial;
	}

	public void setPerimetroBraquial(float perimetroBraquial) {
		this.perimetroBraquial = perimetroBraquial;
	}

	public float getPesoActual() {
		return this.pesoActual;
	}

	public void setPesoActual(float pesoActual) {
		this.pesoActual = pesoActual;
	}

	public float getPesoIdeal() {
		return this.pesoIdeal;
	}

	public void setPesoIdeal(float pesoIdeal) {
		this.pesoIdeal = pesoIdeal;
	}

	public float getPliegueTricipital() {
		return this.pliegueTricipital;
	}

	public void setPliegueTricipital(float pliegueTricipital) {
		this.pliegueTricipital = pliegueTricipital;
	}

	public float getPorcentajeGrasa() {
		return this.porcentajeGrasa;
	}

	public void setPorcentajeGrasa(float porcentajeGrasa) {
		this.porcentajeGrasa = porcentajeGrasa;
	}

	public float getPorcentajeMusculoEsquileto() {
		return this.porcentajeMusculoEsquileto;
	}

	public void setPorcentajeMusculoEsquileto(float porcentajeMusculoEsquileto) {
		this.porcentajeMusculoEsquileto = porcentajeMusculoEsquileto;
	}

	public float getReservaProteica() {
		return this.reservaProteica;
	}

	public void setReservaProteica(float reservaProteica) {
		this.reservaProteica = reservaProteica;
	}

	public float getTalla() {
		return this.talla;
	}

	public void setTalla(float talla) {
		this.talla = talla;
	}

	public DatosMedico getDatosMedico() {
		return this.datosMedico;
	}

	public void setDatosMedico(DatosMedico datosMedico) {
		this.datosMedico = datosMedico;
	}

	public List<DatosMedico> getDatosMedicos() {
		return this.datosMedicos;
	}

	public void setDatosMedicos(List<DatosMedico> datosMedicos) {
		this.datosMedicos = datosMedicos;
	}

	public DatosMedico addDatosMedico(DatosMedico datosMedico) {
		getDatosMedicos().add(datosMedico);
		datosMedico.setAntropometria(this);

		return datosMedico;
	}

	public DatosMedico removeDatosMedico(DatosMedico datosMedico) {
		getDatosMedicos().remove(datosMedico);
		datosMedico.setAntropometria(null);

		return datosMedico;
	}

}