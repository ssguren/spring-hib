package com.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class RunTimeService extends Thread {

	private static final Logger log = Logger.getLogger(RunTimeService.class);

	private Object res;

	private Class<?> clazz;

	private String className;

	private String methodName;

	private Object[] params;

	public RunTimeService() {
	}

	public RunTimeService(Class<?> clazz, String methodName, Object[] params) {
		this.clazz = clazz;
		this.methodName = methodName;
		this.params = params;
	}

	public RunTimeService(String className, String methodName, Object[] params) {
		this.className = className;
		this.methodName = methodName;
		this.params = params;
	}

	@Override
	public void run() {
		try {
			Class<?>[] classes = null;
			if (params != null) {
				classes = new Class<?>[params.length];
				for (int i = 0; i < params.length; i++) {
					classes[i] = params[i].getClass();
				}
			}

			classes = castType(classes);

			Class<?> worker = null;
			if (clazz != null)
				worker = clazz;
			else if (className != null && !className.isEmpty())
				worker = Class.forName(className);
			else {
				log.error("no class define!");
				return;
			}
			Method method = worker.getMethod(methodName, classes);
			if (method != null)
				res = method.invoke(worker.newInstance(), params);
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			synchronized (this) {
				this.notifyAll();
			}
		}
	}

	private Class<?>[] castType(Class<?>[] classes) {
		if (classes != null) {
			List<Class<?>> list = new ArrayList<Class<?>>();
			for (Class<?> cla : classes) {
				if (Number.class.isAssignableFrom(cla)) {
					if (Byte.class.equals(cla))
						list.add(Byte.TYPE);
					if (Short.class.equals(cla))
						list.add(Short.TYPE);
					if (Integer.class.equals(cla))
						list.add(Integer.TYPE);
					if (Long.class.equals(cla))
						list.add(Long.TYPE);
					if (Float.class.equals(cla))
						list.add(Float.TYPE);
					if (Double.class.equals(cla))
						list.add(Double.TYPE);
				} else if (Character.class.isAssignableFrom(cla)) {
					list.add(Character.TYPE);
				} else if (Boolean.class.isAssignableFrom(cla)) {
					list.add(Boolean.TYPE);
				} else {
					list.add(cla);
				}
			}

			return list.toArray(classes);
		}

		return null;
	}

	public Object getRes() {
		return res;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}
}
