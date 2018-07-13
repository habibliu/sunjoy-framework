package com.sunjoy.framework.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapperImpl;

import com.sunjoy.framework.constants.ExceptionConstant;
import com.sunjoy.framework.exception.CommonException;


public class BeanUtils {
	private static final Logger logger = LoggerFactory.getLogger(BeanUtils.class);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Method getDeclareMethod(Object obj, String methodName) {
		Method method = null;
		Class clazz = obj.getClass();

		while (clazz != Object.class) {
			try {
				method = clazz.getDeclaredMethod(methodName, new Class[0]);
				break;
			} catch (Exception arg4) {
				logger.trace("反射调用 getDeclareMethod:" + methodName + "异常");
				clazz = clazz.getSuperclass();
			}
		}

		return method;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Method getDeclareMethod(Object obj, String methodName, Class<?>[] types) {
		Method method = null;
		Class clazz = obj.getClass();

		while (clazz != Object.class) {
			try {
				method = clazz.getDeclaredMethod(methodName, types);
				break;
			} catch (Exception arg5) {
				logger.trace("反射调用 getDeclareMethod:" + methodName + "异常");
				clazz = clazz.getSuperclass();
			}
		}

		return method;
	}

	@SuppressWarnings("rawtypes")
	private static Field getDeclareField(Object obj, String fieldName) {
		Field field = null;
		Class clazz = obj.getClass();

		while (clazz != Object.class) {
			try {
				field = clazz.getDeclaredField(fieldName);
				break;
			} catch (Exception arg4) {
				logger.trace("反射调用 getDeclareField:" + fieldName + "异常");
				clazz = clazz.getSuperclass();
			}
		}

		return field;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Field[] getDeclareFields(Object obj) {
		Class origClazz = obj.getClass();

		ArrayList fields;
		Field[] fs;
		for (fields = new ArrayList(); origClazz != Object.class; origClazz = origClazz.getSuperclass()) {
			try {
				fs = origClazz.getDeclaredFields();
				Field[] i = fs;
				int arg4 = fs.length;

				for (int f = 0; f < arg4; ++f) {
					Field field = i[f];
					fields.add(field);
				}
			} catch (Exception arg7) {
				logger.error(arg7.getMessage());
			}
		}

		fs = new Field[fields.size()];
		int arg8 = 0;

		Field arg10;
		for (Iterator arg9 = fields.iterator(); arg9.hasNext(); fs[arg8++] = arg10) {
			arg10 = (Field) arg9.next();
		}

		return fs;
	}

	public static void copyProperties(Object orig, Object dest) {
		Field[] fields = getDeclareFields(orig);
		Field[] arg2 = fields;
		int arg3 = fields.length;

		for (int arg4 = 0; arg4 < arg3; ++arg4) {
			Field field = arg2[arg4];

			try {
				copyProperty(dest, orig, field);
			} catch (SecurityException arg7) {
				logger.error(arg7.getMessage());
			} catch (Exception arg8) {
				logger.error(arg8.getMessage());
			}
		}

	}

	public static void copyProperties(Object orig, Object dest, String strFieldNames) {
		if (!StringUtils.isEmpty(strFieldNames)) {
			String[] fieldNames = strFieldNames.split(",");
			String[] arg3 = fieldNames;
			int arg4 = fieldNames.length;

			for (int arg5 = 0; arg5 < arg4; ++arg5) {
				String fieldName = arg3[arg5];

				try {
					Field e = getDeclareField(orig, fieldName);
					copyProperty(dest, orig, e);
				} catch (SecurityException arg8) {
					logger.error(arg8.getMessage());
				} catch (Exception arg9) {
					logger.error(arg9.getMessage());
				}
			}

		}
	}

	public static void copyExcludeProperties(Object orig, Object dest, String strFieldNames) {
		if (StringUtils.isEmpty(strFieldNames)) {
			copyProperties(dest, orig);
		} else {
			String[] fieldNames = strFieldNames.split(",");
			Field[] fields = getDeclareFields(orig);
			Field[] arg4 = fields;
			int arg5 = fields.length;

			for (int arg6 = 0; arg6 < arg5; ++arg6) {
				Field field = arg4[arg6];

				try {
					String e = field.getName();
					if (!exclude(fieldNames, e)) {
						copyProperty(dest, orig, field);
					}
				} catch (SecurityException arg9) {
					logger.error(arg9.getMessage());
				} catch (Exception arg10) {
					logger.error(arg10.getMessage());
				}
			}

		}
	}

	private static boolean exclude(String[] fieldNames, String fieldName) {
		String[] arg1 = fieldNames;
		int arg2 = fieldNames.length;

		for (int arg3 = 0; arg3 < arg2; ++arg3) {
			String _fieldName = arg1[arg3];
			if (_fieldName.equals(fieldName)) {
				return true;
			}
		}

		return false;
	}

	private static void copyProperty(Object dest, Object orig, Field field) {
		String fieldName = field.getName();
		String setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		String getMethodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		if (field.getType() == Boolean.class) {
			getMethodName = "is" + getMethodName;
		} else {
			getMethodName = "get" + getMethodName;
		}

		try {
			Method e = getDeclareMethod(dest, setMethodName, new Class[] { field.getType() });
			Method getMethod = getDeclareMethod(orig, getMethodName);
			if (getMethod != null) {
				Object value = getMethod.invoke(orig, new Object[0]);
				if (e != null) {
					e.invoke(dest, new Object[] { value });
				}
			}
		} catch (Exception arg8) {
			logger.error(arg8.getMessage());
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String[] getNullPropertyNames(Object source) {
		BeanWrapperImpl src = new BeanWrapperImpl(source);
		PropertyDescriptor[] pds = src.getPropertyDescriptors();
		HashSet emptyNames = new HashSet();
		PropertyDescriptor[] result = pds;
		int arg4 = pds.length;

		for (int arg5 = 0; arg5 < arg4; ++arg5) {
			PropertyDescriptor pd = result[arg5];
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null) {
				emptyNames.add(pd.getName());
			}
		}

		String[] arg8 = new String[emptyNames.size()];
		return (String[]) emptyNames.toArray(arg8);
	}

	public static void copyPropertiesIgnoreNull(Object src, Object target) {
		org.springframework.beans.BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
	}

	public static Object getPropertyValue(Object srcObj, String propertyName) {
		try {
			PropertyDescriptor e = org.springframework.beans.BeanUtils.getPropertyDescriptor(srcObj.getClass(),
					propertyName);
			Method readMethod = e.getReadMethod();
			return readMethod.invoke(srcObj);
		} catch (Exception arg4) {
			logger.error(arg4.getMessage());
			return null;
		}
	}

	public static void setPropertyValue(Object srcObj, String propertyName, Object propertyValue) {
		try {
			PropertyDescriptor e = org.springframework.beans.BeanUtils.getPropertyDescriptor(srcObj.getClass(),
					propertyName);
			Method writeMethod = e.getWriteMethod();
			writeMethod.invoke(srcObj, new Object[] { propertyValue });
		} catch (Exception arg4) {
			logger.error(arg4.getMessage());
		}

	}

	public static boolean isEmpty(Object obj) {
		boolean flag = false;
		if (obj == null) {
			return true;
		} else if (obj instanceof String && "".equals((String) obj)) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 对象非空字段检查
	 * @param bean POJO对象
	 * @param strings 对象属性集
	 */
	public static void checkEmptyFields(Object bean, String... strings) {
		if (bean == null) {
			throw new CommonException(ExceptionConstant.EXCEPION_FIELD_IS_EMPTY);
		}
		for (String propertyName : strings) {
			Object value=getPropertyValue(bean, propertyName);
			if (isEmpty(value)) {
				throw new CommonException(ExceptionConstant.EXCEPION_FIELD_IS_EMPTY, propertyName);
			}
		}
		
	}

	
}