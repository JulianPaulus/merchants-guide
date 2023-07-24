package merchants.guide.rules;

import merchants.guide.RomanNumeral;

public class OnlyOneSubtractionRule implements IRule {
	@Override
	public boolean check(final String input) {
		if (input.length() < 3) {
			return true;
		}

		for (int i = 2; i < input.length(); i++) {
			final RomanNumeral minuend = RomanNumeral.fromChar(input.charAt(i));
			final RomanNumeral directSubtrahend = RomanNumeral.fromChar(input.charAt(i - 1));
			final RomanNumeral indirectSubtrahend = RomanNumeral.fromChar(input.charAt(i - 2));

			if (directSubtrahend.smallerThan(minuend) && indirectSubtrahend.smallerThan(minuend)) {
				return false;
			}
		}

		return true;
	}
}
