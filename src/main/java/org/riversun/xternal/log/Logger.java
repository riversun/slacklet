package org.riversun.xternal.log;

public class Logger {

	private static boolean sEnabled = false;

	public static void setEnalbed(boolean enabled) {
		sEnabled = enabled;
	}

	private Class<?> mClazz;

	public Logger(Class<?> clazz) {
		mClazz = clazz;
	}

	public void debug(String string) {
		if (sEnabled) {
			System.err.println(mClazz.getSimpleName() + " " + string);
		}
	}

	public void info(String string) {
		if (sEnabled) {
			System.err.println(mClazz.getSimpleName() + " " + string);
		}
	}

	public void error(String string) {
		if (sEnabled) {
			System.err.println(mClazz.getSimpleName() + " " + string);
		}
	}

	public void error(String string, Throwable e) {
		if (sEnabled) {
			System.err.println(mClazz.getSimpleName() + " " + string + " e=" + e);
		}
	}

	public void warn(String string) {
		if (sEnabled) {
			System.err.println(mClazz.getSimpleName() + " " + string);
		}
	}

	public void warn(String string, Throwable e) {
		if (sEnabled) {
			System.err.println(mClazz.getSimpleName() + " " + string + " e=" + e);
		}
	}

}
