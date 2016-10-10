package net.yesiltas.sample.common.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Abstract base class for all the Entity classes
 * 
 * @author Meltem
 *
 */
@MappedSuperclass
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * Two entities will be equal if they have same type and id
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (id == null || obj == null || getClass() != obj.getClass())
			return false;
		BaseEntity that = (BaseEntity) obj;
		return id.equals(that.id);
	}

	/**
	 * Hashcode is implemented to support equals() method
	 */
	@Override
	public int hashCode() {
		return id == null ? 0 : id.hashCode();
	}

	/**
	 * Get id of the entity
	 * 
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set id of the entity
	 * 
	 * @param id
	 *            id of the entity
	 */
	public void setId(Long id) {
		this.id = id;
	}

}
