package com.css.aq.base.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 操作bean属性的工具类，继承自BeanUtils
 * @author zhengzc
 */
public class BeanUtil extends BeanUtils {

	public BeanUtil() {
		super();
	}
	/**
	 * 对象拷贝 数据对象为Null不拷贝到目标对象
	 * @param source 数据源
	 * @param target 目的地
	 */
	public static void copyBeanNotNullToBean(Object source, Object target) {
		if ((source == null) || (target == null)) {
			return;
		}
		PropertyDescriptor[] targetPds = getPropertyDescriptors(target.getClass());
		for (int i = 0; i < targetPds.length; i++) {
			PropertyDescriptor targetPd = targetPds[i];
			Method writeMethod = targetPd.getWriteMethod();
			if ("class".equals(targetPd.getName())) {
				continue; // No point in trying to set an object's class
			}
			PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
			if (sourcePd != null) {
				Method readMethod = sourcePd.getReadMethod();
				if (readMethod != null && ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
					try {
						if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
							readMethod.setAccessible(true);
						}

						Object value = readMethod.invoke(source);
						// 只拷贝不为Null的属性
						if (value != null){
							if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
								writeMethod.setAccessible(true);
							}
							writeMethod.invoke(target, value);
						}
					} catch (Throwable var15) {
						throw new FatalBeanException("Could not copy property '" + targetPd.getName() + "' from source to target", var15);
					}
				}
			}
		}
	}

	/**
	 * 对象拷贝 数据对象空值不拷贝到目标对象
	 * @param source 数据源
	 * @param target 目的源
	 */
	public static void copyBeanNotEmptyToBean(Object source, Object target) {
		if ((source == null) || (target == null)) {
			return;
		}
		PropertyDescriptor[] targetPds = getPropertyDescriptors(target.getClass());
		for (int i = 0; i < targetPds.length; i++) {
			PropertyDescriptor targetPd = targetPds[i];
			Method writeMethod = targetPd.getWriteMethod();
			if ("class".equals(targetPd.getName())) {
				continue; // No point in trying to set an object's class
			}
			PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
			if (sourcePd != null) {
				Method readMethod = sourcePd.getReadMethod();
				if (readMethod != null && ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
					try {
						if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
							readMethod.setAccessible(true);
						}

						Object value = readMethod.invoke(source);
						// 只拷贝不为Null 且 不是空字符串 的属性
						if (value != null && !StringUtils.isEmpty(value)){
							if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
								writeMethod.setAccessible(true);
							}
							writeMethod.invoke(target, value);
						}
					} catch (Throwable var15) {
						throw new FatalBeanException("Could not copy property '" + targetPd.getName() + "' from source to target", var15);
					}
				}
			}
		}
	}
}