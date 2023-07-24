package merchants.guide;

import merchants.guide.rules.IRule;

import java.util.Set;
import java.util.stream.Collectors;

public class RomanToDecimalConverter {

	private final Set<IRule> rules;

	public RomanToDecimalConverter(final Set<IRule> rules) {
		this.rules = rules;
	}

	public int convert(final String input) {
		return romanToDecimal(input.toUpperCase());
	}

	private int romanToDecimal(final String romanNumber) {

		Set<IRule> failedRules = rules.stream().filter(rule -> !rule.check(romanNumber)).collect(Collectors.toSet());
		if (!failedRules.isEmpty()) {
			StringBuilder builder = new StringBuilder("provided roman number fails to match rules: ");
			failedRules.forEach(rule -> builder.append(rule.getName()).append(" "));
			throw new IllegalArgumentException(builder.toString());
		}

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
