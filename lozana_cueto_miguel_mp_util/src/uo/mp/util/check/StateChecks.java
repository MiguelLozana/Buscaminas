package uo.mp.util.check;

public class StateChecks {

	public static void isTrue(boolean condition) {
		isTrue(condition, "Condition not met" );
	}

	public static void isTrue(boolean condition, String msg) {
		if ( condition == false) { 
			throw new IllegalStateException( msg );
		}
	}

}
