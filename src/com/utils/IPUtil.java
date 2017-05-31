package com.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.log4j.Logger;

public class IPUtil {

	private static final Logger log = Logger.getLogger(IPUtil.class);

	public static boolean isWindowsOS() {
		return SystemUtil.getOSName().indexOf("windows") != -1;
	}

	public static List<InetAddress> getLocalIP() {
		List<InetAddress> list = new ArrayList<InetAddress>();
		InetAddress ip = null;
		try {
			Enumeration<NetworkInterface> netInterfaces = NetworkInterface
					.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface ni = netInterfaces.nextElement();
				Enumeration<InetAddress> ips = ni.getInetAddresses();
				while (ips.hasMoreElements()) {
					ip = ips.nextElement();
					if (log.isDebugEnabled())
						log.debug(ip.getHostAddress());
					if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() // 127.��ͷ�Ķ���lookback��ַ
							&& ip.getHostAddress().indexOf(":") == -1) {
						list.add(ip);
					}
				}
			}
		} catch (Exception e) {
			log.error(MiscUtil.traceInfo(e));
		}

		return list;
	}

	public static void main(String[] args) {
		List<InetAddress> list = getLocalIP();
		for (InetAddress add : list) {
			System.out.println("serverIP:::" + add.getHostAddress());
		}
	}
}