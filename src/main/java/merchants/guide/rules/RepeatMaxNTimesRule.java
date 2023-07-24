package merchants.guide.rules;

import merchants.guide.RomanNumeral;

public class RepeatMaxNTimesRule implements IRule {

	private final String testString;

	public RepeatMaxNTimesRule(final RomanNumeral numeral, final int n) {
		this.testString = numeral.toString().repeat(n + 1);
	}

	@Override
	public boolean check(final String input) {
		return !input.toUpperCase().contains(testString);
	}
}
