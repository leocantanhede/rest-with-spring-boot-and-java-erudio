package br.com.erudio.services;

import org.springframework.stereotype.Service;

import br.com.erudio.exceptions.UnsupportedMathOperationException;

@Service
public class MathService {

	public Double sum(String numberOne, String numberTwo) {
		if (!isNumeric(numberOne) || !isNumeric(numberTwo))
			throw new UnsupportedMathOperationException("Please, set a numeric value!");
		return convertToDouble(numberOne) + convertToDouble(numberTwo);
	}
	
	public Double subtraction(String numberOne, String numberTwo) {
		if (!isNumeric(numberOne) || !isNumeric(numberTwo))
			throw new UnsupportedMathOperationException("Please, set a numeric value!");
		return convertToDouble(numberOne) - convertToDouble(numberTwo);
	}
	
	public Double multiplication(String numberOne, String numberTwo) {
		if (!isNumeric(numberOne) || !isNumeric(numberTwo))
			throw new UnsupportedMathOperationException("Please, set a numeric value!");
		return convertToDouble(numberOne) * convertToDouble(numberTwo);
	}
	
	public Double division(String numberOne, String numberTwo) {
		if (!isNumeric(numberOne) || !isNumeric(numberTwo))
			throw new UnsupportedMathOperationException("Please, set a numeric value!");
		if (convertToDouble(numberTwo) == 0)
			throw new UnsupportedMathOperationException("Division by zero!");
		return convertToDouble(numberOne) / convertToDouble(numberTwo);
	}
	
	public Double average(String numberOne, String numberTwo) {
		if (!isNumeric(numberOne) || !isNumeric(numberTwo))
			throw new UnsupportedMathOperationException("Please, set a numeric value!");
		return (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2;
	}
	
	public Double pow(String number) {
		if (!isNumeric(number))
			throw new UnsupportedMathOperationException("Please, set a numeric value!");
		return convertToDouble(number) * convertToDouble(number);
	}
	
	public Double square(String number) {
		if (!isNumeric(number))
			throw new UnsupportedMathOperationException("Please, set a numeric value!");
		return Math.sqrt(convertToDouble(number));
	}
	
	private boolean isNumeric(String strNumber) {
		String number = strNumber == null ? null : strNumber.replaceAll(",", ".");
		return number == null ? false : number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}
	
	private Double convertToDouble(String strNumber) {
		String number = strNumber == null ? null : strNumber.replaceAll(",", ".");
		return number != null && isNumeric(number) ? Double.parseDouble(number) : 0D;
	}
	
}