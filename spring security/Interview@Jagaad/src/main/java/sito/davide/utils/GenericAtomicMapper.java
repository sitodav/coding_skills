package sito.davide.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*Author :
 * d.sito : sitodav@gmail.com
 */
@SuppressWarnings("unchecked")
public class GenericAtomicMapper {
	
	private static final Logger logger = LoggerFactory.getLogger(GenericAtomicMapper.class);
	
	 
	
 
	
	public static <DEST_TYPE,SRC_TYPE> DEST_TYPE 
		copyObject(SRC_TYPE objectToTransform, Class<DEST_TYPE> targetClass) throws Exception
	{
		  
		
		DEST_TYPE transformed = (DEST_TYPE) createInstance(targetClass);
		cycleOnReflectionSameNameFields(objectToTransform,transformed);
		
		return transformed;
				
	}
	
	private static Object createInstance(Class<?> targetClass) throws Exception
	{
		return targetClass.newInstance();
	}
	
	private static void cycleOnReflectionSameNameFields(Object src, Object target) throws Exception
	{
		for(Field fieldOnA : src.getClass().getDeclaredFields())
		{
			fieldOnA.setAccessible(true);
			try
			{

				Field fieldOnB = target.getClass().getDeclaredField(fieldOnA.getName());
				fieldOnB.setAccessible(true);
				if(isDirectlyCloneable(fieldOnA) && isDirectlyCloneable(fieldOnB))
				{ 
					fieldOnB.set(target,fieldOnA.get(src)); //copy value from field on A (from src) to field on B (target)
				}  
				else if(  fieldOnA.getType().isArray() && fieldOnB.getType().isArray()  )
				{
					 
					    int lengthA = Array.getLength(fieldOnA);
					    Array.newInstance(fieldOnB.getType(), lengthA); 
					     
					    for (int i = 0; i < lengthA; i ++) {
					        Object arrayElement = Array.get(fieldOnA, i);
					        Array.set(fieldOnB,i,arrayElement);
					    }
				}
				 
			}
			catch(Exception ex) {
				continue;
			}
			 
		}
	}
	
	
	 
	
	private static boolean isDirectlyCloneable(Field field)
	{
		return Integer.class.isAssignableFrom(field.getType())
				|| Long.class.isAssignableFrom(field.getType())
				|| String.class.isAssignableFrom(field.getType())
				|| Double.class.isAssignableFrom(field.getType())
				|| Boolean.class.isAssignableFrom(field.getType())
				|| Float.class.isAssignableFrom(field.getType()) 
				|| Date.class.isAssignableFrom(field.getType())  ;
				
	}
	
 
	
	
}
