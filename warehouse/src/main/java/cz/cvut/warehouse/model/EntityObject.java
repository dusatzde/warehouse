package cz.cvut.warehouse.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class EntityObject implements Serializable {

	private static final long serialVersionUID = 5553924130305731516L;
	protected Long id;
	protected Integer version = 0;

	public EntityObject() {
	}

	@Override
	public String toString() {
		return String.format("%s(%d)[%s]", getClass().getSimpleName(), getId(), super.toString());
	}

	@Version
	@Column(name = "version")
	public Integer getVersion() {
		return version;
	}

	protected void setVersion(Integer version) {
		this.version = version;
	}

	public static <T extends Enum<T>> List<Enum<T>> getEnumList(Class<T> clazz) {
		List<Enum<T>> types = new ArrayList<Enum<T>>(Arrays.asList(clazz.getEnumConstants()));

		return types;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Enum<T>> List<Enum<T>> getEnumList(Class<T> clazz, Enum<T>... filter) {
		List<Enum<T>> types = new ArrayList<Enum<T>>();
		for (T value : clazz.getEnumConstants()) {
			boolean filtered = false;
			for (Enum<T> filteredEnum : filter) {
				if (filteredEnum.toString().equals(value.toString())) {
					filtered = true;
					break;
				}
			}
			if (!filtered) {
				types.add(value);
			}
		}
		return types;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

