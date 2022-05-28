package AdvancedCalculator;

public class MissingRequiredValueException extends Exception {
	public MissingRequiredValueException(String errorName) {
		super(errorName);
	}
}
