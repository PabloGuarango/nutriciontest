package ec.edu.epn.nutricion.entities;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the antropometria database table.
 * 
 */
@Entity
@NamedQuery(name="Antropometria.findAll", query="SELECT a FROM Antropometria a")
public class Antropometria extends EntidadBase {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_ANTROPOMETRIA")
	private int idAntropometria;

	@Column(name="CIRCUNFERENCIA_MUNECA")
	private double circunferenciaMuneca;

	@Column(name="CONSTITUCION_CORPORAL")
	private String constitucionCorporal;

	@Column(name="DIAMETRO_CADERA")
	private double diametroCadera;

	@Column(name="DIAMETRO_CINTURA")
	private double diametroCintura;

	@Column(name="EDAD_METABOLICA")
	private double edadMetabolica;

	@Column(name="GASTO_ENERGETICO_BASAL")
	private double gastoEnergeticoBasal;

	@Column(name="INDICE_CINTURA_CADERA")
	private double indiceCinturaCadera;

	@Column(name="INDICE_MASA_CORPORAL")
	private double indiceMasaCorporal;

	@Column(name="NIVEL_GRASA_VISCERAL")
	private double nivelGrasaVisceral;

	@Column(name="OBSERVACION_PLIEGUE")
	private String observacionPliegue="";

	@Column(name="PERIMETRO_BRAQUIAL")
	private double perimetroBraquial;

	@Column(name="PESO_ACTUAL")
	private double pesoActual;

	@Column(name="PESO_IDEAL")
	private double pesoIdeal;

	@Column(name="PLIEGUE_TRICIPITAL")
	private double pliegueTricipital;

	@Column(name="PORCENTAJE_GRASA")
	private double porcentajeGrasa;

	@Column(name="PORCENTAJE_MUSCULO_ESQUILETO")
	private double porcentajeMusculoEsquileto;

	@Column(name="RESERVA_PROTEICA")
	private double reservaProteica;

	private double talla;

	//bi-directional many-to-one association to InformacionMedica
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ID_INFORMACION_MEDICA")
	private InformacionMedica informacionMedica;

	//bi-directional many-to-one association to InformacionMedica
	@OneToMany(mappedBy="antropometria",cascade=CascadeType.ALL)
	private List<InformacionMedica> listaInformacionMedicas;

	public Antropometria() {
	}

	public int getIdAntropometria() {
		return this.idAntropometria;
	}

	public void setIdAntropometria(int idAntropometria) {
		this.idAntropometria = idAntropometria;
	}

	public double getCircunferenciaMuneca() {
		return this.circunferenciaMuneca;
	}

	public void setCircunferenciaMuneca(double circunferenciaMuneca) {
		this.circunferenciaMuneca = circunferenciaMuneca;
	}

	public String getConstitucionCorporal() {
		return constitucionCorporal;
	}

	public void setConstitucionCorporal(String constitucionCorporal) {
		this.constitucionCorporal = constitucionCorporal;
	}

	public double getDiametroCadera() {
		return this.diametroCadera;
	}

	public void setDiametroCadera(double diametroCadera) {
		this.diametroCadera = diametroCadera;
	}

	public double getDiametroCintura() {
		return this.diametroCintura;
	}

	public void setDiametroCintura(double diametroCintura) {
		this.diametroCintura = diametroCintura;
	}

	public double getEdadMetabolica() {
		return this.edadMetabolica;
	}

	public void setEdadMetabolica(double edadMetabolica) {
		this.edadMetabolica = edadMetabolica;
	}

	public double getGastoEnergeticoBasal() {
		return this.gastoEnergeticoBasal;
	}

	public void setGastoEnergeticoBasal(double gastoEnergeticoBasal) {
		this.gastoEnergeticoBasal = gastoEnergeticoBasal;
	}

	public double getIndiceCinturaCadera() {
		return this.indiceCinturaCadera;
	}

	public void setIndiceCinturaCadera(double indiceCinturaCadera) {
		this.indiceCinturaCadera = indiceCinturaCadera;
	}

	public double getIndiceMasaCorporal() {
		return this.indiceMasaCorporal;
	}

	public void setIndiceMasaCorporal(double indiceMasaCorporal) {
		this.indiceMasaCorporal = indiceMasaCorporal;
	}

	public double getNivelGrasaVisceral() {
		return this.nivelGrasaVisceral;
	}

	public void setNivelGrasaVisceral(double nivelGrasaVisceral) {
		this.nivelGrasaVisceral = nivelGrasaVisceral;
	}

	public String getObservacionPliegue() {
		return this.observacionPliegue;
	}

	public void setObservacionPliegue(String observacionPliegue) {
		this.observacionPliegue = observacionPliegue;
	}

	public double getPerimetroBraquial() {
		return this.perimetroBraquial;
	}

	public void setPerimetroBraquial(double perimetroBraquial) {
		this.perimetroBraquial = perimetroBraquial;
	}

	public double getPesoActual() {
		return this.pesoActual;
	}

	public void setPesoActual(double pesoActual) {
		this.pesoActual = pesoActual;
	}

	public double getPesoIdeal() {
		return this.pesoIdeal;
	}

	public void setPesoIdeal(double d) {
		this.pesoIdeal = d;
	}

	public double getPliegueTricipital() {
		return this.pliegueTricipital;
	}

	public void setPliegueTricipital(double pliegueTricipital) {
		this.pliegueTricipital = pliegueTricipital;
	}

	public double getPorcentajeGrasa() {
		return this.porcentajeGrasa;
	}

	public void setPorcentajeGrasa(double porcentajeGrasa) {
		this.porcentajeGrasa = porcentajeGrasa;
	}

	public double getPorcentajeMusculoEsquileto() {
		return this.porcentajeMusculoEsquileto;
	}

	public void setPorcentajeMusculoEsquileto(double porcentajeMusculoEsquileto) {
		this.porcentajeMusculoEsquileto = porcentajeMusculoEsquileto;
	}

	public double getReservaProteica() {
		return this.reservaProteica;
	}

	public void setReservaProteica(double reservaProteica) {
		this.reservaProteica = reservaProteica;
	}

	public double getTalla() {
		return this.talla;
	}

	public void setTalla(double talla) {
		this.talla = talla;
	}

	public InformacionMedica getDatosMedico() {
		return this.informacionMedica;
	}

	public void setDatosMedico(InformacionMedica informacionMedica) {
		this.informacionMedica = informacionMedica;
	}

	public InformacionMedica getInformacionMedica() {
		return informacionMedica;
	}

	public void setInformacionMedica(InformacionMedica informacionMedica) {
		this.informacionMedica = informacionMedica;
	}

	public List<InformacionMedica> getListaInformacionMedicas() {
		return listaInformacionMedicas;
	}

	public void setListaInformacionMedicas(List<InformacionMedica> listaInformacionMedicas) {
		this.listaInformacionMedicas = listaInformacionMedicas;
	}

	public InformacionMedica addInformacionMedica(InformacionMedica informacionMedica) {
		if(getListaInformacionMedicas()==null)
			listaInformacionMedicas=new ArrayList<InformacionMedica>();
		getListaInformacionMedicas().add(informacionMedica);
		informacionMedica.setAntropometria(this);

		return informacionMedica;
	}

	public InformacionMedica removeInformacionMedica(InformacionMedica informacionMedica) {
		if(getListaInformacionMedicas()==null)
			return null;
		getListaInformacionMedicas().remove(informacionMedica);
		informacionMedica.setAntropometria(null);

		return informacionMedica;
	}

	@Override
	public int getId() {
		return this.idAntropometria;
	}

}