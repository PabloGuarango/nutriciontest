package ec.edu.epn.nutricion.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;



/**
 * El archivo contiene diferentes funciones, ya sea matematicas, de fechas etc.
 * 
 * @FechaCreacion: 14/11/2015
 * @UsuarioCreacion: Pablo Guarango
 * @Version: 1.0
 */
public class FuncionesUtiles {
	private static final BigDecimal CIEN = new BigDecimal(100);

	/**
	 * Metodo que retorna el mes en formato nombre
	 * 
	 * @param entero
	 *            mes
	 * @return
	 */
	public static String Mes(int mes) {
		String[] meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo",
				"Junio", "Julio", "Agosto", "Septiembre", "Octubre",
				"Noviembre", "Diciemrbre" };
		String retornaMes = " ";
		retornaMes = meses[mes];
		return retornaMes;
	}

	/**
	 * Metodo que redondea un numero double
	 * 
	 * @param numero
	 * @return
	 */
	public static double Redondear(double numero) {
		int cifras = (int) Math.pow(10, 2);
		return Math.rint(numero * cifras) / cifras;
	}

	/**
	 * Metodo que suma n dias a una fecha dada
	 * 
	 * @param fecha
	 *            de tipo java.util.Date
	 * @param dias
	 * @return
	 */
	public static Date sumarFechaDiasMeses(Date fecha, int dias) {
		Calendar fechaCalendar = Calendar.getInstance();
		fechaCalendar.setTime(fecha);
		fechaCalendar.add(Calendar.DATE, dias);
		return fechaCalendar.getTime();
	}

	/**
	 * Metodo que suma n meses a una fecha dada
	 * 
	 * @param fecha
	 *            de tipo java.util.Date
	 * @param dias
	 * @return
	 */
	public static Date sumarFechaMeses(Date fecha, int mes) {
		Calendar fechaCalendar = Calendar.getInstance();
		fechaCalendar.setTime(fecha);
		fechaCalendar.add(Calendar.MONTH, mes);
		return fechaCalendar.getTime();
	}

	/**
	 * Metodo que suma n meses a una fecha dada
	 * 
	 * @param fecha
	 *            de tipo java.util.Date
	 * @param dias
	 * @return
	 */
	public static Date sumarFechaAnios(Date fecha, int anio) {
		Calendar fechaCalendar = Calendar.getInstance();
		fechaCalendar.setTime(fecha);
		fechaCalendar.add(Calendar.YEAR, anio);
		return fechaCalendar.getTime();
	}

	/**
	 * Metodo que suma n dias y n meses a una fecha dada
	 * 
	 * @param fecha
	 *            de tipo java.util.Date
	 * @param meses
	 * @param dias
	 * @return
	 */
	public static Date sumarFechaDiasMeses(Date fecha, int meses, int dias) {
		Calendar fechaCalendar = Calendar.getInstance();
		fechaCalendar.setTime(fecha);
		fechaCalendar.add(Calendar.DATE, dias);
		fechaCalendar.add(Calendar.MONTH, meses);
		return fechaCalendar.getTime();
	}

	/**
	 * Metodo que suma n dias, n meses y n anios a una fecha dada
	 * 
	 * @param fecha
	 *            de tipo java.util.Date
	 * @param meses
	 * @param dias
	 * @param anios
	 * @return
	 */
	public static Date sumarFechaDiasMeses(Date fecha, int meses, int dias,
			int anios) {
		Calendar fechaCalendar = Calendar.getInstance();
		fechaCalendar.setTime(fecha);
		fechaCalendar.add(Calendar.DATE, dias);
		fechaCalendar.add(Calendar.MONTH, meses);
		fechaCalendar.add(Calendar.YEAR, anios);
		return fechaCalendar.getTime();
	}

	/**
	 * Metodo que devuelve true si fecha1 es anterior a la fecha2
	 * 
	 * @param fecha1
	 * @param fecha2
	 * @return Devuelve true si fecha1 es anterior a fecha2, y devuelve false en
	 *         caso contrario
	 */
	public static boolean compararFechas(Date fecha1, Date fecha2) {
		return fecha1.before(fecha2);
	}

	/**
	 * Metodo que devuelve true si fecha1 es anterior o igual a fecha2
	 * 
	 * @param fecha1
	 * @param fecha2
	 * @return Devuelve true si fecha1 es anterior o igual a fecha2, y devuelve
	 *         false en caso contrario
	 */
	public static boolean compararFechaAnteriorOIgual(Date fecha1, Date fecha2) {
		return fecha1.before(fecha2) || fecha1.equals(fecha2);
	}

	/**
	 * Metodo que redondea un numero bigdecimal, al ser igual o mayor a 5 le
	 * suma al inmediato superior.
	 * 
	 * @param numero
	 * @return
	 */
	public static BigDecimal redondearBigDecimal(BigDecimal numero) {
		BigDecimal resp = null;
		resp = numero.setScale(2, RoundingMode.HALF_UP);
		return resp;
	}

	/**
	 * Metodo que redondea un numero bigdecimal, al ser igual o mayor a 5 le
	 * suma al inmediato superior. Recibe el numero de decimales que se requiera
	 * 
	 * @param numero
	 * @param numeroDecimales
	 * @return
	 */
	public static BigDecimal redondearBigDecimal(BigDecimal numero,
			int numeroDecimales) {
		BigDecimal resp = null;
		resp = numero.setScale(numeroDecimales, RoundingMode.HALF_UP);
		return resp;
	}

	/**
	 * Devuelve la fecha 9999/12/31
	 * 
	 * @return date
	 */
	public static Date obtenerFechaFinal() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(9999, 11, 31, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date date = calendar.getTime();
		return date;
	}

	/**
	 * Devuelve la fecha 1900/01/01
	 * 
	 * @return date
	 */
	public static Date obtenerFechaInicial() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1900, 00, 01);
		Date date = calendar.getTime();
		return date;
	}

	/**
	 * Devuelve un date dado el dia mes y anio
	 * 
	 * @param day
	 * @param month
	 *            Enero es 1 hasta Diciembre que es 12
	 * @param year
	 * @return devuelve un util.Date
	 */
	@Deprecated
	public static Date obtenerFechaCualquiera(int day, int month, int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		Date date = calendar.getTime();
		return date;
	}

	/**
	 * Devuelve un date dado el dia mes y anio
	 * 
	 * @param day
	 * @param month
	 *            Enero es 1 hasta Diciembre que es 12
	 * @param year
	 * @return devuelve un util.Date
	 */
	public static Date getFecha(int day, int month, int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date date = calendar.getTime();
		return date;
	}

	/**
	 * Obtiene el ultimo dia del mes dado el anio y el mes
	 * 
	 * @param year
	 * @param month
	 *            Enero es 1 hasta Diciembre que es 12
	 * @return
	 */
	@Deprecated
	public static Date obtenerUltimoDiaMes(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DATE, 1);

		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DATE, -1);

		return calendar.getTime();
	}

	/**
	 * Retorna el fin de mes
	 * 
	 * @param anio
	 * @param mes
	 * @return
	 */
	public static Date getFechaFinMes(int anio, int mes) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(anio, mes - 1, 1, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return getFechaFinMes(calendar.getTime());
	}

	/**
	 * Retorna el fin de mes
	 * 
	 * @param fecha
	 * @return
	 */
	public static Date getFechaFinMes(Date fecha) {
		Calendar fechaFinMes = Calendar.getInstance();
		fechaFinMes.setTime(fecha);

		int anio = fechaFinMes.get(Calendar.YEAR);
		int mes = fechaFinMes.get(Calendar.MONTH);

		/**
		 * Se construye la fecha del primer dia del mes dado la fecha
		 */
		fechaFinMes.set(anio, mes, 1, 0, 0, 0);
		fechaFinMes.set(Calendar.MILLISECOND, 0);

		/*
		 * Se suma un mes a la fecha
		 */
		fechaFinMes.add(Calendar.MONTH, 1);
		/**
		 * Resto un dia para obtener la fecha de fin de mes
		 */
		fechaFinMes.add(Calendar.DATE, -1);

		return fechaFinMes.getTime();
	}

	public static int getDiaFinMes(Calendar fecha) {
		Calendar fechaFinMes = (Calendar) fecha.clone();
		int anio = fechaFinMes.get(Calendar.YEAR);
		int mes = fechaFinMes.get(Calendar.MONTH);

		/**
		 * Se construye la fecha del primer dia del mes dado la fecha
		 */
		fechaFinMes.set(anio, mes, 1, 0, 0, 0);
		fechaFinMes.set(Calendar.MILLISECOND, 0);

		/*
		 * Se suma un mes a la fecha
		 */
		fechaFinMes.add(Calendar.MONTH, 1);
		/**
		 * Resto un dia para obtener la fecha de fin de mes
		 */
		fechaFinMes.add(Calendar.DAY_OF_MONTH, -1);

		return fechaFinMes.get(Calendar.DAY_OF_MONTH);
	}

	public static BigDecimal getProporcianalDiasAnterioresVSMes(Date fecha) {
		Calendar fechaCalendar = Calendar.getInstance();
		fechaCalendar.setTime(fecha);
		int diasAnteriores = fechaCalendar.get(Calendar.DAY_OF_MONTH);
		int mes = fechaCalendar.get(Calendar.MONTH);
		int diasMes = 30;
		if (mes == Calendar.FEBRUARY) {
			Date ultiDiaMes = getFechaFinMes(fecha);
			Calendar ultiDiaMesCalendar = Calendar.getInstance();
			;
			ultiDiaMesCalendar.setTime(ultiDiaMes);
			diasMes = ultiDiaMesCalendar.get(Calendar.DAY_OF_MONTH);
		}

		BigDecimal proporcional = new BigDecimal(diasAnteriores).divide(
				new BigDecimal(diasMes), 2, RoundingMode.HALF_UP);
		if (proporcional.compareTo(BigDecimal.ONE) == 1) {
			proporcional = BigDecimal.ONE;
		}
		return proporcional;
	}

	public static BigDecimal getProporcianalDiasPosterioresVSMes(Date fecha) {
		Calendar fechaCalendar = Calendar.getInstance();
		fechaCalendar.setTime(fecha);
		int diasAnteriores = fechaCalendar.get(Calendar.DAY_OF_MONTH);
		int mes = fechaCalendar.get(Calendar.MONTH);
		int diasMes = 30;
		if (mes == Calendar.FEBRUARY) {
			Date ultiDiaMes = getFechaFinMes(fecha);
			Calendar ultiDiaMesCalendar = Calendar.getInstance();
			;
			ultiDiaMesCalendar.setTime(ultiDiaMes);
			diasMes = ultiDiaMesCalendar.get(Calendar.DAY_OF_MONTH);
		}
		int diasPosteriores = diasMes - diasAnteriores + 1;

		BigDecimal proporcional = new BigDecimal(diasPosteriores).divide(
				new BigDecimal(diasMes), 2, RoundingMode.HALF_UP);
		return proporcional;
	}

	public static Date getFechaInicioMes(Date fecha) {
		Calendar fechaInicioMes = Calendar.getInstance();
		fechaInicioMes.setTime(fecha);

		int anio = fechaInicioMes.get(Calendar.YEAR);
		int mes = fechaInicioMes.get(Calendar.MONTH);

		/**
		 * Se construye la fecha del primer dia del mes dado la fecha
		 */
		fechaInicioMes.set(anio, mes, 1, 0, 0, 0);
		fechaInicioMes.set(Calendar.MILLISECOND, 0);

		return fechaInicioMes.getTime();
	}

	/**
	 * Ordena una lista
	 * 
	 * @param lista
	 * @param propiedad
	 */
	@SuppressWarnings({ "rawtypes" })
	public static void ordenaLista(List lista, final String propiedad) {
		ordenaLista(lista, propiedad, true);
	}

	/**
	 * Ordena una lista
	 * 
	 * @param lista
	 * @param propiedad
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void ordenaLista(List lista, final String propiedad,
			final boolean order) {

		Collections.sort(lista, new Comparator() {

			public int compare(Object obj1, Object obj2) {

				Class clase = obj1.getClass();
				String getter = "get"
						+ Character.toUpperCase(propiedad.charAt(0))
						+ propiedad.substring(1);
				try {
					Method getPropiedad = clase.getMethod(getter);

					Object propiedad1 = getPropiedad.invoke(obj1);
					Object propiedad2 = getPropiedad.invoke(obj2);

					if (propiedad1 instanceof Comparable
							&& propiedad2 instanceof Comparable) {
						Comparable prop1 = (Comparable) propiedad1;
						Comparable prop2 = (Comparable) propiedad2;
						if (order) {
							return prop1.compareTo(prop2);
						} else {
							return prop2.compareTo(prop1);
						}
					}// CASO DE QUE NO SEA COMPARABLE
					else {
						if (propiedad1.equals(propiedad2))
							return 0;
						else
							return 1;

					}

				} catch (Exception e) {
					// e.printStackTrace();
				}
				return 0;
			}
		});

	}

	/**
	 * Nombres de los meses
	 * 
	 * @param numeroMes
	 * @return
	 */
	public static String nombreMes(int numeroMes) {
		String nombreMes = null;
		switch (numeroMes) {
		case 0:
			nombreMes = "Enero";
			break;
		case 1:
			nombreMes = "Febrero";
			break;
		case 2:
			nombreMes = "Marzo";
			break;
		case 3:
			nombreMes = "Abril";
			break;
		case 4:
			nombreMes = "Mayo";
			break;
		case 5:
			nombreMes = "Junio";
			break;
		case 6:
			nombreMes = "Julio";
			break;
		case 7:
			nombreMes = "Agosto";
			break;
		case 8:
			nombreMes = "Septiembre";
			break;
		case 9:
			nombreMes = "Octubre";
			break;
		case 10:
			nombreMes = "Noviembre";
			break;
		case 11:
			nombreMes = "Diciembre";
			break;
		default:
			break;
		}
		return nombreMes;
	}

	/**
	 * Devuelve el numero de dias entre dos fechas
	 * 
	 * @param fechaInicial
	 * @param fechaFinal
	 * @return
	 */
	public static int diferenciasDeFechas(Date fechaInicial, Date fechaFinal) {
		long fechaInicialMs = fechaInicial.getTime();
		long fechaFinalMs = fechaFinal.getTime();
		long diferencia = fechaFinalMs - fechaInicialMs;
		double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
		return ((int) dias + 1);
	}

	/**
	 * Devuelve el numero de minutos entre dos fechas, devuelve resultados
	 * negativos si la fechaInicial es mayor que fechaFinal
	 * 
	 * @param fechaInicial
	 * @param fechaFinal
	 * @return
	 */
	public static int getMinutosEntreFechas(Date fechaInicial, Date fechaFinal) {
		long fechaInicialMs = fechaInicial.getTime();
		long fechaFinalMs = fechaFinal.getTime();
		long diferencia = fechaFinalMs - fechaInicialMs;
		double minutos = diferencia / 1000 / 60;
		return ((int) minutos);
	}

	/**
	 * Convierte un String en un Date
	 * 
	 * @param fecha
	 * @return Devuelve una fecha en formato del sistema
	 */
	public static Date stringToDate(String fecha, String strFormatoFecha) {
		Date fecha_salida = null;
		SimpleDateFormat formatoFecha = new SimpleDateFormat(strFormatoFecha);

		try {
			fecha_salida = formatoFecha.parse(fecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return fecha_salida;
	}

	/**
	 * Devuelve una subcadena contenida en una cadena dado el valor inicial y
	 * final
	 * 
	 * @param cadena
	 * @param valorInicial
	 * @param valorFinal
	 * @return
	 */
	public static String obtenerSubcadena(String cadena, int valorInicial,
			int valorFinal) {
		return cadena.substring(valorInicial, valorFinal);
	}

	/**
	 * Convertidor Fecha a Letras Espa�ol
	 * 
	 * @param fecha
	 *            Fecha a convertir
	 * @return Fecha convertida a letras
	 */
	public static String convertidorFechaALetras(Date fecha) {
		SimpleDateFormat formateador = new SimpleDateFormat(
				"dd 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
		String fechaLetras = formateador.format(fecha);
		return fechaLetras;
	}

	/**
	 * Convertidor Fecha a Letras Espa�ol
	 * 
	 * @param fecha
	 *            Fecha a convertir
	 * @return Fecha convertida a letras
	 */
	public static String convertidorFechaALetrasHoras(Date fecha) {
		SimpleDateFormat formateador = new SimpleDateFormat(
				"dd 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
		String fechaLetras = formateador.format(fecha);
		return fechaLetras.concat(" " + fecha.getHours() + ":"
				+ fecha.getMinutes() + ":" + fecha.getSeconds());
	}

	/************************************************************************************************************************
	 *************************************** Inicio Funcion Cambiar de numero a
	 * Letras***************************************
	 ***********************************************************************************************************************/

	private final String[] UNIDADES = { "", "un ", "dos ", "tres ", "cuatro ",
			"cinco ", "seis ", "siete ", "ocho ", "nueve " };
	private final String[] DECENAS = { "diez ", "once ", "doce ", "trece ",
			"catorce ", "quince ", "dieciseis ", "diecisiete ", "dieciocho ",
			"diecinueve", "veinte ", "treinta ", "cuarenta ", "cincuenta ",
			"sesenta ", "setenta ", "ochenta ", "noventa " };
	private final String[] CENTENAS = { "", "ciento ", "doscientos ",
			"trescientos ", "cuatrocientos ", "quinientos ", "seiscientos ",
			"setecientos ", "ochocientos ", "novecientos " };

	/**
	 * Convertidor de numero a Letras
	 * 
	 * @param numero
	 *            Numero a convertir
	 * @param mayusculas
	 *            True para mayusculas, False para minusculas
	 * @return numero convertido a letras
	 */
	/**
	 * TODO: Agregar descripcion del metodo
	 * 
	 * @param numero1
	 * @param mayusculas
	 * @return
	 */
	public String convertidorNumeroALetras(BigDecimal numero, boolean mayusculas) {

		String strNnumero = "" + numero.doubleValue();

		String literal = "";
		String parte_decimal;
		// si el numero utiliza (.) en lugar de (,) -> se reemplaza
		strNnumero = strNnumero.replace(".", ",");
		// si el numero no tiene parte decimal, se le agrega ,00
		if (strNnumero.indexOf(",") == -1) {
			strNnumero = strNnumero + ",00";
		}
		// se valida formato de entrada -> 0,00 y 999 999 999,00
		if (Pattern.matches("\\d{1,9},\\d{1,2}", strNnumero)) {
			// se divide el numero 0000000,00 -> entero y decimal
			String Num[] = strNnumero.split(",");
			// de da formato al numero decimal
			if (Num[1].length() == 1) {
				parte_decimal = " con " + Num[1] + "0" + "/100";
			} else {
				parte_decimal = " con " + Num[1] + "/100";
			}
			// se convierte el numero a literal
			if (Integer.parseInt(Num[0]) == 0) {// si el valor es cero
				literal = "cero ";
			} else if (Integer.parseInt(Num[0]) > 999999) {
				// si es millon
				literal = getMillones(Num[0]);
			} else if (Integer.parseInt(Num[0]) > 999) {
				// si es miles
				literal = getMiles(Num[0]);
			} else if (Integer.parseInt(Num[0]) > 99) {
				// si es centena
				literal = getCentenas(Num[0]);
			} else if (Integer.parseInt(Num[0]) > 9) {
				// si es decena
				literal = getDecenas(Num[0]);
			} else {
				// sino unidades -> 9
				literal = getUnidades(Num[0]);
			}
			// devuelve el resultado en mayusculas o minusculas
			if (mayusculas) {
				return (literal + parte_decimal).toUpperCase();
			} else {
				return (literal + parte_decimal);
			}
		} else {
			// error, no se puede convertir
			return literal = null;
		}
	}

	/* funciones para convertir los numeros a literales */
	private String getUnidades(String numero) {// 1 - 9
		// si tuviera algun 0 antes se lo quita -> 09 = 9 o 009=9
		String num = numero.substring(numero.length() - 1);
		return UNIDADES[Integer.parseInt(num)];
	}

	private String getDecenas(String num) {
		// 99
		int n = Integer.parseInt(num);
		if (n < 10) {
			// para casos como -> 01 - 09
			return getUnidades(num);
		} else if (n > 19) {
			// para 20...99
			String u = getUnidades(num);
			if (u.equals("")) {
				// para 20,30,40,50,60,70,80,90
				return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8];
			} else {
				return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8]
						+ "y " + u;
			}
		} else {
			// numeros entre 11 y 19
			return DECENAS[n - 10];
		}
	}

	private String getCentenas(String num) {
		// 999 o 099
		if (Integer.parseInt(num) > 99) {
			// es centena
			if (Integer.parseInt(num) == 100) {
				// caso especial
				return " cien ";
			} else {
				return CENTENAS[Integer.parseInt(num.substring(0, 1))]
						+ getDecenas(num.substring(1));
			}
		} else {
			// por Ej. 099
			// se quita el 0 antes de convertir a decenas
			return getDecenas(Integer.parseInt(num) + "");
		}
	}

	private String getMiles(String numero) {
		// 999 999
		// obtiene las centenas
		String c = numero.substring(numero.length() - 3);
		// obtiene los miles
		String m = numero.substring(0, numero.length() - 3);
		String n = "";
		// se comprueba que miles tenga valor entero
		if (Integer.parseInt(m) > 0) {
			n = getCentenas(m);
			return n + "mil " + getCentenas(c);
		} else {
			return "" + getCentenas(c);
		}

	}

	private String getMillones(String numero) {
		// 000 000 000
		// se obtiene los miles
		String miles = numero.substring(numero.length() - 6);
		// se obtiene los millones
		String millon = numero.substring(0, numero.length() - 6);
		String n = "";
		if (millon.length() > 1) {
			n = getCentenas(millon) + "millones ";
		} else {
			n = getUnidades(millon) + "millon ";
		}
		return n + getMiles(miles);
	}

	/************************************************************************************************************************
	 ******************************************** Fin Funcion Cambiar de numero a
	 * Letras*************************************
	 ***********************************************************************************************************************/

	/**
	 * Metodo que devuelve el anio actual
	 * 
	 * @return el anio actual
	 */
	public static int obtenerAnioActual() {
		Calendar fechaCalendar = Calendar.getInstance();
		return fechaCalendar.get(Calendar.YEAR);
	}

	/**
	 * Metodo que devuelve el mes actual
	 * 
	 * @return el mes actual
	 */
	public static int obtenerMesActual() {
		Calendar fechaCalendar = Calendar.getInstance();
		return fechaCalendar.get(Calendar.MONTH);
	}

	/**
	 * Metodo que devuelve anio de una fecha especifica
	 * 
	 * @param fecha
	 * @return
	 */
	@Deprecated
	public static int obtenerAnioFecha(Date fecha) {
		Calendar fechaCalendar = Calendar.getInstance();
		fechaCalendar.setTime(fecha);
		return fechaCalendar.get(Calendar.YEAR);
	}

	/**
	 * Metodo que devuelve anio de una fecha especifica
	 * 
	 * @param fecha
	 * @return
	 */
	public static int getAnio(Date fecha) {
		Calendar fechaCalendar = Calendar.getInstance();
		fechaCalendar.setTime(fecha);
		return fechaCalendar.get(Calendar.YEAR);
	}

	/**
	 * Metodo que devuelve mes de una fecha especifica
	 * 
	 * @param fecha
	 * @return
	 */
	@Deprecated
	public static int obtenerMesFecha(Date fecha) {
		Calendar fechaCalendar = Calendar.getInstance();
		fechaCalendar.setTime(fecha);
		return fechaCalendar.get(Calendar.MONTH);
	}

	public static int getMes(Date fecha) {
		Calendar fechaCalendar = Calendar.getInstance();
		fechaCalendar.setTime(fecha);
		return fechaCalendar.get(Calendar.MONTH) + 1;
	}

	public static int getDiaFecha(Date fecha) {
		Calendar fechaCalendar = Calendar.getInstance();
		fechaCalendar.setTime(fecha);
		return fechaCalendar.get(Calendar.DATE);
	}

	/**
	 * Metodo que devuelve dia de una fecha especifica
	 * 
	 * @param fecha
	 * @return
	 */
	public static int obtenerDiaFecha(Date fecha) {
		Calendar fechaCalendar = Calendar.getInstance();
		fechaCalendar.setTime(fecha);
		return fechaCalendar.get(Calendar.DATE);
	}

	public static Date obtenerMaximaFechaLista(List<Date> fechas) {
		Date fechaMaxima = fechas.get(0);
		for (int i = 1; i < fechas.size(); i++) {
			fechaMaxima = obtenerFechaMaxima(fechaMaxima, fechas.get(i));
		}
		return fechaMaxima;
	}

	public static Date obtenerFechaMaxima(Date d1, Date d2) {
		if (d1 == null && d2 == null)
			return null;
		if (d1 == null)
			return d2;
		if (d2 == null)
			return d1;
		return (d1.after(d2)) ? d1 : d2;
	}

	public static Date obtenerMinimaFechaLista(List<Date> fechas) {
		Date fechaMinima = fechas.get(0);
		for (int i = 1; i < fechas.size(); i++) {
			fechaMinima = obtenerFechaMinima(fechaMinima, fechas.get(i));
		}
		return fechaMinima;
	}

	public static Date obtenerFechaMinima(Date d1, Date d2) {
		if (d1 == null && d2 == null)
			return null;
		if (d1 == null)
			return d2;
		if (d2 == null)
			return d1;
		return (d1.before(d2)) ? d1 : d2;
	}

	/**
	 * Metodo que calcula la edad
	 * 
	 * @param fechaNac
	 * @return
	 */
	public static int obtenerEdad(Date fechaNac) {

		Calendar fechaActual = Calendar.getInstance();
		fechaActual.setTime(new Date());

		Calendar fechaNacimiento = Calendar.getInstance();
		fechaNacimiento.setTime(fechaNac);

		int dif_anios = fechaActual.get(Calendar.YEAR)
				- fechaNacimiento.get(Calendar.YEAR);
		int dif_meses = fechaActual.get(Calendar.MONTH)
				- fechaNacimiento.get(Calendar.MONTH);
		int dif_dias = fechaActual.get(Calendar.DAY_OF_MONTH)
				- fechaNacimiento.get(Calendar.DAY_OF_MONTH);

		// Si est� en ese anio pero todav�a no los ha cumplido
		if (dif_meses < 0 || (dif_meses == 0 && dif_dias < 0)) {
			dif_anios--;
		}
		return dif_anios;

	}

	public static long DiasEntreFechas(Date fecha1, Date fecha2) {

		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(fecha1);
		cal1.set(Calendar.HOUR, 0);
		cal1.set(Calendar.MINUTE, 0);
		cal1.set(Calendar.SECOND, 0);
		cal1.set(Calendar.MILLISECOND, 0);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(fecha2);
		cal2.set(Calendar.HOUR, 0);
		cal2.set(Calendar.MINUTE, 0);
		cal2.set(Calendar.SECOND, 0);
		cal2.set(Calendar.MILLISECOND, 0);

		// conseguir la representacion de la fecha en milisegundos
		long milis1 = cal1.getTimeInMillis();
		long milis2 = cal2.getTimeInMillis();

		// calcular la diferencia en milisengundos
		long diff = milis2 - milis1;
		diff = diff / (24 * 60 * 60 * 1000);

		return diff + 1;
	}

	/**
	 * Metodo devuelve fecha setada con anio, mes,
	 * dia(hora=0,minuto=0,segundo=0,milisegundo=0)
	 * 
	 * @param anio
	 *            Anio a tomar la fecha
	 * @param mes
	 *            Mes a tomar la fecha
	 * @param dia
	 *            Dia a tomar la fecha
	 * @return fecha seteada
	 */
	public static Date setAtributoFecha(int anio, int mes, int dia) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(anio, mes, dia, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * Metodo devuelve fecha setada con anio, mes,
	 * dia(hora=0,minuto=0,segundo=0,milisegundo=0) recibe como parametro una
	 * fecha
	 * 
	 * @param fecha
	 * @return
	 */
	public static Date setAtributoFecha(Date fecha) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		int anio = calendar.get(Calendar.YEAR);
		int mes = calendar.get(Calendar.MONTH);
		int dia = calendar.get(Calendar.DATE);
		return setAtributoFecha(anio, mes, dia);
	}

	/**
	 * TODO: Devuelve la extencion de un archivo
	 * 
	 * @param nombreArchivo
	 *            Nombre del archivo
	 * @return
	 */
	public static String recuperaExtencion(String nombreArchivo) {
		int mid = nombreArchivo.lastIndexOf(".");
		return "." + nombreArchivo.substring(mid + 1, nombreArchivo.length());
	}

	/**
	 * 
	 * Carga una hoja de Excel en un matriz
	 * 
	 * @param fileName
	 * @param imInputStream
	 * @param filaInicial
	 *            >0
	 * @param hoja
	 * @return
	 * @throws IOException
	 */

	public static String completarALaIzquierda(char caracterDeRepeticion,
			int numeroDeCaracteres, String cadenaOriginal) {
		return replicar(caracterDeRepeticion, numeroDeCaracteres
				- cadenaOriginal.length())
				+ cadenaOriginal;
	}

	public static String completarALaDerecha(char caracterDeRepeticion,
			int numeroDeCaracteres, String cadenaOriginal) {
		return cadenaOriginal
				+ replicar(caracterDeRepeticion, numeroDeCaracteres
						- cadenaOriginal.length());
	}

	public static String replicar(char caracterDeRepeticion,
			int numeroDeCaracteres) {
		String r = "";
		for (int j = 0; j < numeroDeCaracteres; j++) {
			r += caracterDeRepeticion;
		}
		return r;
	}

	public static String completarNumeroConCerosIzquierda(int numero,
			int numeroCeros) {
		String format = String.format("%%0%dd", numeroCeros);
		String result = String.format(format, numero);

		return new String(result);
	}

	
	public static BigDecimal porcentaje(BigDecimal base, BigDecimal pct,
			int decimales) {
		return redondearBigDecimal(base.multiply(pct).divide(CIEN), decimales);
	}

	public static BigDecimal porcentaje(BigDecimal base, double pct,
			int decimales) {
		return porcentaje(base, new BigDecimal(pct), decimales);
	}

	public static BigDecimal porcentaje(BigDecimal base, BigDecimal pct) {

		return porcentaje(base, pct, 2);
	}

	public static BigDecimal porcentaje(BigDecimal base, double pct) {

		return porcentaje(base, pct, 2);
	}

	/**
	 * Obtiene la edad en a�os a partir de dos fechas
	 * 
	 * @param fechaNacimiento
	 * @param fechaCalculo
	 * @return
	 */
	public static int calcularEdad(Date fechaNacimiento, Date fechaCalculo) {

		Calendar birth = new GregorianCalendar();
		Calendar today = new GregorianCalendar();
		int age = 0;
		int factor = 0;
		Date birthDate = fechaNacimiento;
		Date currentDate = fechaCalculo; // new Date(); // current date
		birth.setTime(birthDate);
		today.setTime(currentDate);

		int diaHoy = today.get(Calendar.DATE);
		int diaCumple = birth.get(Calendar.DATE);
		int mesHoy = today.get(Calendar.MONTH) + 1;
		int mesCumple = birth.get(Calendar.MONTH) + 1;

		if (mesHoy <= mesCumple) {
			if (mesHoy == mesCumple) {
				if (diaHoy < diaCumple) {
					factor = -1; // Aun no celebra su cumpleaños
				}
			} else {
				factor = -1; // Aun no celebra su cumpleaños
			}
		}
		age = (today.get(Calendar.YEAR) - birth.get(Calendar.YEAR)) + factor;
		return age;
	}

	
	/**
	 * Crea una hoja Excel con el contenido especificado.
	 * 
	 * @param v
	 *            Vector con los datos a escribir en la hoja.
	 * @param namesheet
	 *            nombre de la hoja.
	 * @param filename
	 *            path del fichero donde se escribe.
	 */
		/**
	 * Devuelve el numero de horas entre dos horas
	 * 
	 * @param horaInicial
	 * @param horaFinal
	 * @return
	 */
	public static BigDecimal diferenciasDeHoras(Date horaInicial, Date horaFinal) {
		double horaInicialMs = horaInicial.getTime();
		double horaFinalMs = horaFinal.getTime();
		double diferencia = horaFinalMs - horaInicialMs;
		double horas = Redondear(diferencia / (1000 * 60 * 60));
		return BigDecimal.valueOf(horas);
	}

	/**
	 * Metodo que suma n dias y n meses a una fecha dada
	 * 
	 * @param fecha
	 *            de tipo java.util.Date
	 * @param meses
	 * @param dias
	 * @return
	 */
	public static Date SumarHoras(Date hora, double numeroHoras) {
		int horas = (int) numeroHoras;
		int minutos = (int) ((numeroHoras - horas) * 60);
		Calendar fechaCalendar = Calendar.getInstance();
		fechaCalendar.setTime(hora);
		fechaCalendar.add(Calendar.HOUR, horas);
		fechaCalendar.add(Calendar.MINUTE, minutos);
		return fechaCalendar.getTime();
	}

	/**
	 * Devuelve el inverso de un numero dado
	 * 
	 * @param valor
	 * @param numeroDecimales
	 * @return
	 */
	public static BigDecimal inverso(BigDecimal valor, int numeroDecimales) {
		return BigDecimal.valueOf(1).divide(valor, numeroDecimales,
				RoundingMode.HALF_UP);
	}

	/**
	 * Valida que una cadena contenga solo numeros
	 * 
	 * @param cadena
	 * @return true si son numeros
	 */
	public static boolean validaSoloNumero(String cadena) {
		boolean resp = true;
		for (int i = 0; i < cadena.length(); i++) {
			if (!Character.isDigit(cadena.charAt(i))) {
				resp = false;
			}
		}
		return resp;
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static List<String> leerArchivoTexto(InputStream imInputStream)
			throws IOException {

		BufferedReader entrada = null;
		List<String> lista = new ArrayList<String>();
		try {

			entrada = new BufferedReader(new InputStreamReader(imInputStream));
			String linea;
			while (entrada.ready()) {
				linea = entrada.readLine();
				if (linea != null && !linea.trim().isEmpty()) {
					lista.add(linea);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			entrada.close();
		}
		return lista;
	}

	/**
	 * Quita los caracteres especiales a una cadena de texto
	 * 
	 * @param input
	 * @return
	 */
	public static String removerCaracteresEspeciales(String input) {
		// Descomposici�n can�nica
		String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
		// Nos quedamos �nicamente con los caracteres ASCII
		Pattern pattern = Pattern.compile("\\P{ASCII}+");
		return pattern.matcher(normalized).replaceAll("");
	}

	public static String getTipoIdentificacionClienteSRI(
			String codigoTipoIdentificacion) {
		String tipoIdentificacionSRI = codigoTipoIdentificacion;
		if ("R".equals(codigoTipoIdentificacion)) {
			tipoIdentificacionSRI = "04";
		} else if ("C".equals(codigoTipoIdentificacion)) {
			tipoIdentificacionSRI = "05";
		} else if ("P".equals(codigoTipoIdentificacion)) {
			tipoIdentificacionSRI = "06";
		}

		return tipoIdentificacionSRI;
	}

	/**
	 * Devuelve la cantidad de meses que han pasado
	 * 
	 * @param fechaInicial
	 * @param fechaFinal
	 * @return
	 */
	public static int cantidadMeses(Date fechaInicial, Date fechaFinal) {
		Date inicio = getFechaInicioMes(fechaInicial);
		Date fin = getFechaFinMes(fechaFinal);
		int cantidad = 0;

		while (inicio.before(fin)) {
			cantidad = cantidad + 1;
			inicio = sumarFechaMeses(inicio, 1);
		}
		return cantidad;
	}

	/**
	 * Devuelve la cantidad de meses exactos con decimales
	 * 
	 * @param fechaInicial
	 * @param fechaFinal
	 * @return
	 */
	public static BigDecimal cantidadMesesDecimales(Date fechaInicial,
			Date fechaFinal) {
		int cantidadMeses = cantidadMeses(fechaInicial, fechaFinal);
		BigDecimal proporcionalPrimero = getProporcianalDiasPosterioresVSMes(fechaInicial);
		BigDecimal proporcionalUltimo = getProporcianalDiasAnterioresVSMes(fechaFinal);
		BigDecimal totalMeses = new BigDecimal(cantidadMeses - 2).add(
				proporcionalPrimero).add(proporcionalUltimo);
		return totalMeses;
	}

	/**
	 * Devuelve la una lista con todas las fechas dentro de un rango de fechas
	 * 
	 * @param fechaInicial
	 * @param fechaFinal
	 * @return
	 */
	public static List<Date> getListaEntreFechas(Date fechaInicio, Date fechaFin) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(fechaInicio);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(fechaFin);
		List<Date> listaFechas = new ArrayList<Date>();
		while (!c1.after(c2)) {
			listaFechas.add(c1.getTime());
			c1.add(Calendar.DAY_OF_MONTH, 1);
		}
		return listaFechas;
	}

	public static Date getHoraCero(Date fecha) {
		Calendar calFecha = Calendar.getInstance();
		calFecha.setTime(fecha);
		calFecha.set(Calendar.HOUR_OF_DAY, 00);
		calFecha.set(Calendar.MINUTE, 00);
		calFecha.set(Calendar.SECOND, 00);
		calFecha.set(Calendar.MILLISECOND, 0);

		return calFecha.getTime();
	}

	public static Date copiarFechaManteniendoHoras(Date fechaOrigen,
			Date fechaDestino) {
		if (fechaDestino == null) {
			return null;
		}
		Calendar calFechaOrigen = Calendar.getInstance();
		calFechaOrigen.setTime(fechaOrigen);
		int anio = calFechaOrigen.get(Calendar.YEAR);
		int mes = calFechaOrigen.get(Calendar.MONTH);
		int dia = calFechaOrigen.get(Calendar.DAY_OF_MONTH);

		Calendar calFechaDestino = Calendar.getInstance();
		calFechaDestino.setTime(fechaDestino);
		calFechaDestino.set(Calendar.YEAR, anio);
		calFechaDestino.set(Calendar.MONTH, mes);
		calFechaDestino.set(Calendar.DAY_OF_MONTH, dia);

		return calFechaDestino.getTime();
	}

	public static String LeeFicheroHTMLToEmail(String direcion) {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		String resultado = "";

		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File(direcion);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			// Lectura del fichero
			String linea;
			boolean primeraImagen = true;
			while ((linea = br.readLine()) != null) {
				if (linea.startsWith("<img src=\"")) {
					String[] cadenas = linea.split(" style=");
					if (primeraImagen) {
						linea = "<img src='cid:cidLogoEmpresa' style="
								+ cadenas[1];
						primeraImagen = false;
					} else {
						linea = "<img src='cid:cidCodigoBarras' style="
								+ cadenas[1];
					}

				}
				resultado += linea;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta
			// una excepcion.
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return resultado;
	}

}
