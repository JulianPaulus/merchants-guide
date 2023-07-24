package merchants.guide.rules;

import merchants.guide.RomanNumeral;

public class MaxCountRule implements IRule {

	private final char testChar;
	private final int maxCount;

	private final String name;

	public MaxCountRule(final RomanNumeral numeral, final int maxCount) {
		this.testChar = numeral.toString().charAt(0);
		this.maxCount = maxCount;

		this.name = "MaxCountRule(" + numeral + ", " + maxCount + ")";
	}

	@Override
	public boolean check(final String input) {
		return input.length() < maxCount || input.chars().filter(c -> c == testChar).count() <= maxCount;
	}

	@Override
	public String getName() {
		return name;
	}
}
