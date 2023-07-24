package merchants.guide;

public class RomanUtil {

	public static int romanToDecimal(final String romanNumber) {
		int sum = 0;
		for (int i = 0; i < romanNumber.length(); i++) {
			RomanNumeral current = RomanNumeral.fromChar(romanNumber.charAt(i));

			RomanNumeral next = null;
			if (i < romanNumber.length() - 1) {
				next = RomanNumeral.fromChar(romanNumber.charAt(i + 1));
			}

			if (next != null && current.smallerThan(next)) {
				sum += next.getValue() - current.getValue();
				i++;
			} else {
				sum += current.getValue();
			}

		}

		return sum;
	}

}
