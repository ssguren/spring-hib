package com.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class ShellUtil {

	private static final Logger log = Logger.getLogger(ShellUtil.class);

	public static final boolean ExecCommand(String command) {
		if (!isValidOS())
			return false;

		Process pcs = null;
		String line = null;
		BufferedReader br = null;
		boolean res = false;

		try {
			String str[] = { "/bin/sh", "-c", command };
			pcs = Runtime.getRuntime().exec(str);
			if (pcs != null) {
				log.info("PID: " + pcs);
				br = new BufferedReader(new InputStreamReader(pcs
						.getInputStream()));
				while ((line = br.readLine()) != null) {
					log.info(line);
				}

				int wait = pcs.waitFor();
				log.info("wait: " + wait);
				int exit = pcs.exitValue();
				log.info("exit: " + exit);

				res = wait == 0 && exit == 0;
			} else {
				log.error("Can't exec: " + command);
			}
		} catch (IOException e) {
			log.error(MiscUtil.traceInfo(e));
		} catch (InterruptedException e) {
			log.error(MiscUtil.traceInfo(e));
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (pcs != null) {
					pcs.getErrorStream().close();
					pcs.getInputStream().close();
					pcs.getOutputStream().close();
					pcs.destroy();
				}
			} catch (IOException e) {
				log.error(MiscUtil.traceInfo(e));
			}
		}

		return res;
	}

	private static boolean isValidOS() {
		String os = System.getProperty("os.name").toUpperCase();
		return os.startsWith("LINUX") || os.startsWith("UNIX");
	}
}
