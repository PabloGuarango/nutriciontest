package ec.edu.epn.nutricion.entities;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class EntidadBase implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -839173439692434205L;

	@Transient
	protected int id;

	@Transient
	protected int idHashCode;

	@Transient
	protected boolean editado = false;

	/**
	 * Bandera que indica si el registro fue eliminado
	 */
	@Transient
	protected boolean eliminado = false;

	/**
	 * Get del atributo id
	 * 
	 * @return el valor del atributo id
	 */
	public abstract int getId();

	/**
	 * Set del atributo id
	 * 
	 * @param id
	 *            valor para asignar al atributo id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get del atributo editado
	 * 
	 * @return el valor del atributo editado
	 */
	public boolean isEditado() {
		return editado;
	}

	/**
	 * Set del atributo editado
	 * 
	 * @param editado
	 *            valor para asignar al atributo editado
	 */
	public void setEditado(boolean editado) {
		this.editado = editado;
	}

	/**
	 * Get del atributo eliminado
	 * 
	 * @return el valor del atributo eliminado
	 */
	public boolean isEliminado() {
		return eliminado;
	}

	/**
	 * Set del atributo eliminado
	 * 
	 * @param eliminado
	 *            valor para asignar al atributo eliminado
	 */
	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}

	public String getClavePrimaria() {
		return "id";
	}

	@Override
	public boolean equals(Object entidad) {

		if (this == entidad) {
			return true;

		} else {
			return (entidad instanceof EntidadBase) ? getId() == ((EntidadBase) entidad).getId() : false;
		}
	}

	@Override
	public int hashCode() {
		return getId() > 0 ? getId() : super.hashCode();
	}

	public int getIdHashCode() {
		return hashCode();
	}

	public int getRowKey() {
		return getId() == 0 ? super.hashCode() : this.getId();
	}
}
