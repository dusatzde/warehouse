package cz.cvut.warehouse.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericsUtils {
	
    @SuppressWarnings("rawtypes")
	public static Class getSuperClassGenericType(Class<?> clazz){
        return getSuperClassGenericType(clazz, 0);
    }
 
    @SuppressWarnings({"rawtypes" })
	public static Class getSuperClassGenericType (Class<?> clazz, int index){
        
        Type genericType = clazz.getGenericSuperclass();

        if (!(genericType instanceof ParameterizedType)){
            return Object.class;
        }
      
        Type[] params = ((ParameterizedType)genericType).getActualTypeArguments();
        if (index >= params.length || index < 0){
            throw new IllegalArgumentException("The index is invalid:"+index);
        }
        return (Class)params[index];
    }
}
