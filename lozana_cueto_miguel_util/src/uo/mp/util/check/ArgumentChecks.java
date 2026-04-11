package uo.mp.util.check;

/**
 * This class assists in validating arguments. The validation methods throw an
 * InvalidArgumentException on every case. Therefore this class should be only
 * used to validate arguments and not states nor other preconditions.
 * <p>
 * 
 * @see https://github.com/apache/commons-lang/blob/master/src/main/java/org/apache/commons/lang3/Validate.java
 *
 * @author Miguel Lozana Cueto
 * @version 20_01_26
 */
public final class ArgumentChecks {
	private static final String MESSAGE = " is an invalid value for the argument.";

	/**
	 * Validate that the argument condition is {@code true}; otherwise throwing an
	 * exception with the specified message. This method is useful when validating
	 * according to an arbitrary boolean expression, such as validating a primitive
	 * number or using your own custom validation expression. This method throws an
	 * IllegalArgumentException with the given message.
	 * 
	 * @param expression the boolean expression to check.
	 * @param message    the message to throw within the exception.
	 * @throws IllegalArgumentException with the given message if the expression is
	 *                                  not true.
	 */
	public static void isTrue(final boolean expression, String message) {
		if ( expression == false ) {
			throw new IllegalArgumentException(message);
		}
	}
	
	public static void isTrue(final boolean expression) {
		isTrue(expression, "False" + MESSAGE);
	}
	/**
	 * Validate the argument condition is false; otherwise throws IllegalArgumentException
	 * with a given message
	 * @param expression the boolean expression to check
	 * @param message the message to show in the exception
	 * @throws IllegalArgumentException with the given message if the expression is true
	 */
	public static void isFalse(final boolean expression, String message) {
		isTrue(!expression, message);
	}
	
	
	/**
	 * This method overloads the isTrue method to check that the value of the object
	 * is not null. In case the value is null, an IllegalArgumentException is thrown
	 * with the message given as parameter.
	 * 
	 * @param object   to check whether is null or not.
	 * @param messsage is the message to throw within the exception if the object is
	 *                 null.
	 * @throws IllegalArgumentException if the validation is not fulfilled.
	 */
	public static void isNotNull(final Object object, String messsage) {
		isTrue(object != null, messsage);
	}

	public static void isNotNull(final Object object) {
		isNotNull(object, "null" + MESSAGE);
	}
	
	/**
	 * This method overloads the isTrue method to check that the value of the given
	 * string is not empty (any kind of characters, including blanks and escape
	 * characters, count). In case the value is empty, an IllegalArgumentException
	 * is thrown with the message given as parameter.
	 * 
	 * @param object   to check whether is empty or not.
	 * @param messsage is the message to throw within the exception if the object is
	 *                 empty.
	 * @throws IllegalArgumentException if the validation is not fulfilled.
	 */
	public static void isNotEmpty(final String string, String message) {
		isTrue( string != null && string.isEmpty() == false, message);
	}

	public static void isNotEmpty(final String string) {
		isNotEmpty(string, "Null or empty string" + MESSAGE);
	}

	/**
	 * This method overloads the isTrue method to check that the value of the given
	 * string is not blank. In case the value is blank, an IllegalArgumentException
	 * is thrown with the message given as parameter.
	 * 
	 * @param object   to check whether is blank or not.
	 * @param messsage is the message to throw within the exception if the object is
	 *                 blank.
	 * @throws IllegalArgumentException if the validation is not fulfilled.
	 */
	public static void isNotBlank(final String string, String message) {
		isTrue(string != null && string.isBlank() == false, message);
	}

	public static void isNotBlank(final String string) {
		isNotBlank(string, "Blank string" + MESSAGE);
	}

	
	
}
