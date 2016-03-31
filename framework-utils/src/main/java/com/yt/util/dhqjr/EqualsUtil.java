package com.yt.util.dhqjr;

public class EqualsUtil implements java.io.Serializable {

	private static final long serialVersionUID = 5614846018094755355L;

	public static boolean equest(Integer val, Integer val1) {
		if (EmptyUtil.isNotEmpty(val) && EmptyUtil.isNotEmpty(val1)) {
			if (val.intValue() == val1.intValue()) {
				return true;
			}
		}
		return false;
	}

	public static boolean noEquest(Integer val, Integer val1) {
		if (EmptyUtil.isNotEmpty(val) && EmptyUtil.isNotEmpty(val1)) {
			if (val.intValue() != val1.intValue()) {
				return true;
			}
		}
		return false;
	}

	public static boolean equest(Object val, Object val1) {
		if (EmptyUtil.isNotEmpty(val) && EmptyUtil.isNotEmpty(val1)) {
			if (val.toString().equals(val1.toString())) {
				return true;
			}
		}
		return false;
	}
}
