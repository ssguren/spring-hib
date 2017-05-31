package com.utils;

import org.apache.log4j.Logger;

public class SystemUtil {

	private static final Logger log = Logger.getLogger(SystemUtil.class);

	public static String getOSName() {
		return System.getProperty("os.name").toLowerCase();
	}

	public static String getOSArch() {
		return System.getProperty("os.arch").toLowerCase();
	}

	public static String getOSVsersion() {
		return System.getProperty("os.version").toLowerCase();
	}

	public static String getUserName() {
		return System.getProperty("user.name").toLowerCase();
	}

	public static String getUserHome() {
		return System.getProperty("user.home").toLowerCase();
	}

	public static String getUserDir() {
		return System.getProperty("user.dir").toLowerCase();
	}

	public static String getJavaHome() {
		return System.getProperty("java.home").toLowerCase();
	}

	public static String getJavaVersion() {
		return System.getProperty("java.version").toLowerCase();
	}

	public static String getJavaClassVersion() {
		return System.getProperty("java.class.version").toLowerCase();
	}

	public static String getJavaClassPath() {
		return System.getProperty("java.class.path").toLowerCase();
	}

	public static String getJavaLibraryPath() {
		return System.getProperty("java.library.path").toLowerCase();
	}

	public static String getJavaIOTmpdir() {
		return System.getProperty("java.io.tmpdir").toLowerCase();
	}

	public static String getJavaExtDirs() {
		return System.getProperty("java.ext.dirs").toLowerCase();
	}

	public static String getJavaVendor() {
		return System.getProperty("java.vendor").toLowerCase();
	}

	public static String getJavaVendorUrl() {
		return System.getProperty("java.vendor.url").toLowerCase();
	}

	public static String getJavaSpecificationVersion() {
		return System.getProperty("java.specification.version").toLowerCase();
	}

	public static String getJavaSpecificationVendor() {
		return System.getProperty("java.specification.vendor").toLowerCase();
	}

	public static String getJavaSpecificationName() {
		return System.getProperty("java.specification.name").toLowerCase();
	}

	public static String getJavaVmVersion() {
		return System.getProperty("java.vm.version").toLowerCase();
	}

	public static String getJavaVmVendor() {
		return System.getProperty("java.vm.vendor").toLowerCase();
	}

	public static String getJavaVmName() {
		return System.getProperty("java.vm.name").toLowerCase();
	}

	public static String getJavaVmSpecificationVersion() {
		return System.getProperty("java.vm.specification.version")
				.toLowerCase();
	}

	public static String getJavaVmSpecificationVendor() {
		return System.getProperty("java.vm.specification.vendor").toLowerCase();
	}

	public static String getJavaVmSpecificationName() {
		return System.getProperty("java.vm.specification.name").toLowerCase();
	}

	public static void main(String[] args) {
		if (args != null && args.length == 1) {
			String property = args[0];
			StringBuffer sb = new StringBuffer(property);
			sb.append(":::");
			sb.append(System.getProperty(property));
			log.info(sb.toString());
		} else {
			log.error("input a property name pls.");
		}
	}
}