package merchants.guide.rules;

import merchants.guide.RomanNumeral;

public class MaxCountRule implements IRule {

	private final char testChar;
	private final int maxCount;

	public MaxCountRule(final RomanNumeral numeral, final int maxCount) {
		this.testChar = numeral.toString().charAt(0);
		this.maxCount = maxCount;
	}

	@Override
	public boolean check(final String input) {
		return input.length() < maxCount || input.chars().filter(c -> c == testChar).count() <= maxCount;
	}
}
