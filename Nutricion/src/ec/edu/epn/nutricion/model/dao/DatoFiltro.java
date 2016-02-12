package ec.edu.epn.nutricion.model.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.From;

import ec.edu.epn.nutricion.enumeraciones.OPERACION_ENUM;

/**
 * 
 * Clase que permiter almacenar un tipo de dato especifico para el filtrado en el AbstractDao
 * 
 * La busqueda de textos se realizan por defecto con LIKE
 * 
 * @author: Pablo Andrade
 * @version: 1.0
 * @param <T>
 */
public final class DatoFiltro<T extends Comparable<T>> {

	public static final String FORMATO_FECHA = "dd/MM/yyyy";

	private String propiedadFiltro;
	private OPERACION_ENUM operacion;
	private String strValorFiltro;
	private Object valor;
	private Class<?> tipoDato;
	private Expression<T> expresion;
	From<?, ?> from;

	public DatoFiltro(Class<?> tipoDato, From<?, ?> from, String propiedadFiltro, String strValorFiltro) {
		super();
		this.propiedadFiltro = propiedadFiltro;
		this.strValorFiltro = strValorFiltro;
		this.tipoDato = tipoDato;
		this.from = from;
		this.expresion = from.get(propiedadFiltro);
		setearValores();
	}

	public Expression<T> getExpression() {
		return expresion;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setearValores() {

		// Quita los espacios en blanco al inicio del texto previo a extraer el
		// operador
		strValorFiltro = strValorFiltro.trim().replaceAll("^ +| +$|( )+", "$1");

		if (strValorFiltro.startsWith("!=") || strValorFiltro.startsWith("<>")) {
			this.operacion = OPERACION_ENUM.DIFERENTE;
			this.strValorFiltro = strValorFiltro.substring(2);

		} else if (strValorFiltro.startsWith("<>")) {
			this.operacion = OPERACION_ENUM.DIFERENTE;
			this.strValorFiltro = strValorFiltro.substring(2);

		} else if (strValorFiltro.startsWith(">=")) {
			this.operacion = OPERACION_ENUM.MAYOR_IGUAL;
			this.strValorFiltro = strValorFiltro.substring(2);

		} else if (strValorFiltro.startsWith("<=")) {
			this.operacion = OPERACION_ENUM.MENOR_IGUAL;
			this.strValorFiltro = strValorFiltro.substring(2);

		} else if (strValorFiltro.startsWith(">")) {
			this.operacion = OPERACION_ENUM.MAYOR;
			this.strValorFiltro = strValorFiltro.substring(1);

		} else if (strValorFiltro.startsWith("<")) {
			this.operacion = OPERACION_ENUM.MENOR;
			this.strValorFiltro = strValorFiltro.substring(1);

		} else if (strValorFiltro.startsWith("=")) {
			this.operacion = OPERACION_ENUM.IGUAL;
			this.strValorFiltro = strValorFiltro.substring(1);

		} else if ("IS_NOT_NULL".equalsIgnoreCase(strValorFiltro)) {
			this.operacion = OPERACION_ENUM.IS_NOT_NULL;
			this.strValorFiltro = strValorFiltro.substring(1);

		} else if ("IS_NULL".equalsIgnoreCase(strValorFiltro)) {
			this.operacion = OPERACION_ENUM.IS_NULL;

		} else if (strValorFiltro.startsWith("%")) {
			this.operacion = OPERACION_ENUM.LIKE;

		} else if (strValorFiltro.endsWith("%")) {
			this.strValorFiltro = strValorFiltro.substring(0, strValorFiltro.length() - 1);
			this.operacion = OPERACION_ENUM.LIKE;

		} else {
			if (tipoDato == String.class) {
				this.operacion = OPERACION_ENUM.LIKE;
				this.strValorFiltro = "%" + this.strValorFiltro;
			} else {
				this.operacion = OPERACION_ENUM.IGUAL;
			}
		}

		// Quita los espacios en blanco al inicio del texto posterior a extraer
		// el operador
		strValorFiltro = strValorFiltro.replaceAll("^ +| +$|( )+", "$1");

		if (tipoDato == String.class) {
			valor = strValorFiltro;

		} else if (tipoDato == Integer.class || tipoDato.toString().equals("int")) {
			valor = new Integer(Integer.parseInt(strValorFiltro));

		} else if (tipoDato == Long.class || tipoDato.toString().equals("long")) {
			valor = new Long(Long.parseLong(strValorFiltro));

		} else if (tipoDato == BigDecimal.class) {
			valor = new BigDecimal(strValorFiltro);

		} else if (tipoDato == Boolean.class || tipoDato.toString().equals("boolean")) {
			valor = Boolean.valueOf(strValorFiltro);

		} else if (tipoDato == Date.class) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat(FORMATO_FECHA);
				valor = dateFormat.parse(strValorFiltro);
			} catch (ParseException e) {
				valor = new Date();
			}

		} else if (tipoDato.isEnum()) {
			valor = Enum.valueOf((Class) tipoDato, strValorFiltro);
		}
	}

	public Expression<?> getExpresion(CriteriaBuilder criteriaBuilder, DatoFiltro<T> datoFiltro) {

		if (datoFiltro == null) {
			return null;
		}

		switch (datoFiltro.getOperacion()) {

		case IGUAL:
			return criteriaBuilder.equal(datoFiltro.getExpression(), datoFiltro.getValor());

		case DIFERENTE:
			return criteriaBuilder.notEqual(datoFiltro.getExpression(), datoFiltro.getValor());

		case LIKE:
			//return criteriaBuilder.like(datoFiltro.getExpression().as(String.class), datoFiltro.getValor() + "%");
			return criteriaBuilder.like(criteriaBuilder.lower(datoFiltro.getExpression().as(String.class)), String.valueOf(datoFiltro.getValor())
					.toLowerCase() + "%");

		case MENOR:
			return criteriaBuilder.lessThan(datoFiltro.getExpression(), datoFiltro.getValor());

		case MENOR_IGUAL:
			return criteriaBuilder.lessThanOrEqualTo(datoFiltro.getExpression(), datoFiltro.getValor());

		case MAYOR:
			return criteriaBuilder.greaterThan(datoFiltro.getExpression(), datoFiltro.getValor());

		case MAYOR_IGUAL:
			return criteriaBuilder.greaterThanOrEqualTo(datoFiltro.getExpression(), datoFiltro.getValor());

		case IS_NULL:
			return criteriaBuilder.isNull(datoFiltro.getExpression());

		case IS_NOT_NULL:
			return criteriaBuilder.isNotNull(datoFiltro.getExpression());

		default:
			return null;

		}
	}

	@SuppressWarnings("unchecked")
	public T getValor() {
		return (T) valor;
	}

	public OPERACION_ENUM getOperacion() {
		return operacion;
	}

	public String getPropiedadFiltro() {
		return propiedadFiltro;
	}
}