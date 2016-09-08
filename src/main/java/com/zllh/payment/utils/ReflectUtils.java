package com.zllh.payment.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

//import com.factoring.pojo.base.CommonBean;



	 
	/**
	 *
	 *@author Moon
	 *@since 2012.3.10
	 *@version 1.0
	 */
    
	public class ReflectUtils {
		
	    private static Logger logger = Logger.getLogger(ReflectUtils.class.getName());
	    
	    static {
	        DateConverter dc = new DateConverter();
	        dc.setUseLocaleFormat(true);
	        dc.setPatterns(new String[] {"yyyy-MM-dd HH:mm:ss"});
	        ConvertUtils.register(dc, Date.class);
	    }
	 
	    public static Object invokeGetterMethod(Object target, String propertyName) {
	    	
    		String getterMethodName = "get" + StringUtils.capitalize(propertyName);
    		return invokeMethod(target, getterMethodName, new Class[] {}, new Object[] {});
	    }
	 
	    public static void invokeSetterMethod(Object target, String propertyName, Object value) {
	        if(value == null ) return ;
	    	invokeSetterMethod(target, propertyName, value, null);
	    }
	 
	    public static void invokeSetterMethod(Object target, String propertyName, Object value, Class<?> propertyType) {
	    	Class<?> type = propertyType != null ? propertyType : ReflectUtils.getDeclaredField(target, propertyName).getType();
	        String setterMethodName = "set" + StringUtils.capitalize(propertyName);
	        Object[] parms = new Object[]{ReflectUtils.convertStringToObject(value, type)};
	        invokeMethod(target, setterMethodName, new Class[] { type }, parms);
	    }
	 
	    public static Object getFieldValue(final Object object, final String fieldName) {
	        Field field = getDeclaredField(object, fieldName);
	 
	        if (field == null) {
	            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
	        }
	 
	        makeAccessible(field);
	 
	        Object result = null;
	        try {
	            result = field.get(object);
	        } catch (IllegalAccessException e) {
	        	
	        }
	        return result;
	    }
	 
	    public static void setFieldValue(final Object object, final String fieldName, final Object value) {
	        Field field = getDeclaredField(object, fieldName);
	 
	        if (field == null) {
	            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
	        }
	 
	        makeAccessible(field);
	 
	        try {
	            field.set(object, value);
	        } catch (IllegalAccessException e) {
	        	
	        }
	    }
	    
	    public static Object invokeMethod(final Object object, final String methodName, final Class<?>[] parameterTypes,
	            final Object[] parameters) {
	        Method method = getDeclaredMethod(object, methodName, parameterTypes);
	        if (method == null) {
	            throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + object + "]");
	        }
	 
	        method.setAccessible(true);
	 
	        try {
	            return method.invoke(object, parameters);
	        } catch (Exception e) {
	            throw convertReflectionExceptionToUnchecked(e);
	        }
	    }
	 
	    public static Field getDeclaredField(final Object object, final String fieldName) {
	        Assert.notNull(object, "");
	        Assert.hasText(fieldName, "fieldName");
	        for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
	                .getSuperclass()) {
	            try {
	                return superClass.getDeclaredField(fieldName);
	            } catch (NoSuchFieldException e) {
	            	
	            }
	        }
	        return null;
	    }
	 
	    protected static void makeAccessible(final Field field) {
	        if (!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
	            field.setAccessible(true);
	        }
	    }
	 
	    protected static Method getDeclaredMethod(Object object, String methodName, Class<?>[] parameterTypes) {
	        Assert.notNull(object, "object");
	 
	        for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
	                .getSuperclass()) {
	            try {
	                return superClass.getDeclaredMethod(methodName, parameterTypes);
	            } catch (NoSuchMethodException e) {//NOSONAR
	            }
	        }
	        return null;
	    }
	 
	    @SuppressWarnings("unchecked")
	    public static <T> Class<T> getSuperClassGenricType(final Class<?> clazz) {
	        return (Class<T>) getSuperClassGenricType(clazz, 0);
	    }
	 
	    /**
	     *
	     * @param clazz clazz The class to introspect
	     * @param index the Index of the generic ddeclaration,start from 0.
	     * @return the index generic declaration, or Object.class if cannot be determined
	     */
	    public static Class<?> getSuperClassGenricType(final Class<?> clazz, final int index) {
	        Type genType = clazz.getGenericSuperclass();
	 
	        if (!(genType instanceof ParameterizedType)) {
	            logger.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
	            return Object.class;
	        }
	 
	        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
	 
	        if (index >= params.length || index < 0) {
	            logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: "
	                    + params.length);
	            return Object.class;
	        }
	        if (!(params[index] instanceof Class)) {
	            logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
	            return Object.class;
	        }
	 
	        return (Class<?>) params[index];
	    }
 
	    @SuppressWarnings({ "rawtypes", "unchecked" })
		public static List<?> convertElementPropertyToList(final Collection<?> collection, final String propertyName) {
			List list = new ArrayList();
	 
	        try {
	            for (Object obj : collection) {
	                list.add(PropertyUtils.getProperty(obj, propertyName));
	            }
	        } catch (Exception e) {
	            throw convertReflectionExceptionToUnchecked(e);
	        }
	 
	        return list;
	    }
	 
	    @SuppressWarnings("rawtypes")
		public static String convertElementPropertyToString(final Collection collection, final String propertyName,
	            final String separator) {
			List list = convertElementPropertyToList(collection, propertyName);
	        return StringUtils.join(list.iterator(), separator);
	    }
	 
    	@SuppressWarnings("unchecked")
	    public static <T> T convertStringToObject(Object value, Class<T> toType) {
	        try {
	            return (T) ConvertUtils.convert(value, toType);
	        } catch (Exception e) {
            	throw convertReflectionExceptionToUnchecked(e);
	        }
	    }
	 
	    public static RuntimeException convertReflectionExceptionToUnchecked(Exception e) {
	        return convertReflectionExceptionToUnchecked(null, e);
	    }
	 
	    public static RuntimeException convertReflectionExceptionToUnchecked(String desc, Exception e) {
	        desc = (desc == null) ? "Unexpected Checked Exception." : desc;
	        if (e instanceof IllegalAccessException || e instanceof IllegalArgumentException
	                || e instanceof NoSuchMethodException) {
	            return new IllegalArgumentException(desc, e);
	        } else if (e instanceof InvocationTargetException) {
	            return new RuntimeException(desc, ((InvocationTargetException) e).getTargetException());
	        } else if (e instanceof RuntimeException) {
	            return (RuntimeException) e;
	        }
	        return new RuntimeException(desc, e);
	    }
	    
//		public static void copyProperties(Object target , Object source ) {
//			
//			if(target == null || source == null) return ;
//			
//			Class<? extends Object> sourceClazz = source.getClass();
//			
//			Set<Field> fieldSet = getFields(sourceClazz);
//			
//			Object val ;
//			for(Field f : fieldSet) {
//				if(f.toGenericString() != null &&  (f.toGenericString().contains("static") 
//						|| f.toGenericString().contains("final"))) continue;
//				if(f.getType().isAssignableFrom(List.class) || CommonBean.class.equals(f.getType().getSuperclass())) continue;
//				val = ReflectUtils.invokeGetterMethod(source, f.getName());
//				if(val == null ) continue;
//				ReflectUtils.invokeSetterMethod(target, f.getName(), val, f.getType());
//			}
//		}
		
		/*
		 * 递归获取Object对象属性 By zuoln 2014-09-03
		 */
		private static Set<Field> getFields(Class<? extends Object> sourceClazz){
			Set<Field> fieldSet = new HashSet<Field>();
			
			CollectionUtils.addAll(fieldSet, sourceClazz.getDeclaredFields());
			
			if( sourceClazz.getSuperclass() != null ) {
				Set<Field> pfieldSet = getFields(sourceClazz.getSuperclass());
				fieldSet.addAll(pfieldSet);
			}
			
			return fieldSet;
		}
		
		
		/*
		 * Map转Object对象 By zuoln 2014-09-03
		 */
//		public static void copyMap2Obj(List<String> target , List<String> source ,Object targetObj,Map<String,Object> sourceMap) {
//			
//			if(target == null || source == null || targetObj == null || sourceMap == null) return ;
//			
//			Class<? extends Object> sourceClazz = targetObj.getClass();
//			Set<Field> fieldSet = getFields(sourceClazz);
//			
//			Object val ;
//			for(Field f : fieldSet) {
//				if(f.toGenericString() != null &&  (f.toGenericString().contains("static") 
//						|| f.toGenericString().contains("final"))) continue;
//				if(f.getType().isAssignableFrom(List.class) || CommonBean.class.equals(f.getType().getSuperclass())) continue;
//				int index = target.indexOf(f.getName()); 
//				if(index>0){
//					val = sourceMap.get(source.get(index));
//					if(val == null ) continue;
//					ReflectUtils.invokeSetterMethod(targetObj, f.getName(), val, f.getType());
//				}
//			}
//		}
		
		/*
		 * Map转Object对象  2014-10-13
		 * 
		 * 根据targetObj的属性去查找map中是否有此键相应的值，如果有则set
		 */
		public static void copyMap2Obj(Object targetObj,Map<String,Object> sourceMap) {
			
			if(targetObj == null || sourceMap == null) return ;
			
			Class<? extends Object> targetClass = targetObj.getClass();
			Field[] fields = targetClass.getDeclaredFields();
			
			for(Field field:fields){
				
				String fieldName = field.toString();
				int begin = fieldName.lastIndexOf(".");
				Object obj = sourceMap.get(fieldName.substring(begin+1,fieldName.length()));
				invokeSetterMethod(targetObj,field.getName(),obj);
			}
			
			
		}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public static void copyMap2Objs(Map<String,String> relaMap,Map<String,Object> sourceMap,List<Object> targetList,Class thisClass,int n) throws InstantiationException, IllegalAccessException{
			
			if(relaMap==null||sourceMap==null||relaMap.size()==0||sourceMap.size()==0||thisClass==null){
				
				return;
			}
			for(int i=0;i<n;i++){
				
				Object obj = thisClass.newInstance();
				for(String key:relaMap.keySet()){
					
					invokeSetterMethod(obj,key,((List<Object>)sourceMap.get(relaMap.get(key))).get(i));
				}
				targetList.add(obj);
			}
		} 
		
		
		//测试
		public static void main(String[] args) throws InstantiationException, IllegalAccessException{
			/*System.out.println(Test1.class);
			
			Map<String,String> relaMap = new HashMap<String,String>();
			relaMap.put("name", "names");
			relaMap.put("pass", "passes");
			
			List<String> nameList= new ArrayList<String>();
			nameList.add("LiMing");
			nameList.add("WangMei");
			
			List<String> passList = new ArrayList<String>();
			passList.add("asd123");
			passList.add("zxc123");
			
			Map<String,Object> sourceMap = new HashMap<String,Object>();
			sourceMap.put("names", nameList);
			sourceMap.put("passes", passList);
			
			List<Object> testList = new ArrayList<Object>();
			ReflectUtils.copyMap2Objs(relaMap, sourceMap, testList, Test1.class, 2);*/
		}
}