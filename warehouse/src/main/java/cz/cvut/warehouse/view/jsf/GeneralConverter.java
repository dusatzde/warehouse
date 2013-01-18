package cz.cvut.warehouse.view.jsf;

import cz.cvut.warehouse.model.EntityObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javassist.util.proxy.ProxyObject;
import javax.el.ValueExpression;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@RequestScoped
@FacesConverter(value = "generalConverter")
public class GeneralConverter implements Converter {

	private static final String NULL_ENTITY = "null";
	
	@Inject
	private EntityManager entityManager;

	@Inject
	private Logger log;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Class<?> entityClass = getEntityClass(context, component);
		if (NULL_ENTITY.equals(value) || value.isEmpty() || entityClass == null) {
			return null;
		} else if ("Object".equals(entityClass.getSimpleName())) {
			return null;
		} else {
			Long id = null;
			try {
				id = Long.parseLong(value);
				return entityManager.find(entityClass, id);
			} catch (Exception e) {
				log.log(Level.SEVERE, e.getLocalizedMessage(), e);
				return null;
			}
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return NULL_ENTITY;
		}
		if (!(value instanceof EntityObject)) {
			throw new ConverterException(String.format("Class %s doesn't extend IdGeneratedEntityObject class",
					value.getClass()));
		}
		Object id = ((EntityObject) value).getId();
		return id == null ? "" : id.toString();
	}

	protected Class<?> getEntityClass(FacesContext context, UIComponent component) {
		Class<?> clazz = component.getValueExpression("value").getType(context.getELContext());
		if ("Object".equals(clazz.getSimpleName())) {
			ValueExpression ve = component.getValueExpression("value");
			Object instanceObject = ve.getValue(context.getELContext());
			if (instanceObject == null) {
				return null;
			} else if (isAProxy(instanceObject)) {
				clazz = instanceObject.getClass().getSuperclass();
			} else {
				clazz = instanceObject.getClass();
			}

		}
		return clazz;
	}

	protected static boolean isAProxy(Object proxySuspect) {
		return proxySuspect instanceof ProxyObject;
	}
}
