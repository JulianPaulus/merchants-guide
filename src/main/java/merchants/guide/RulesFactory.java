package merchants.guide;

import merchants.guide.rules.IRule;
import merchants.guide.rules.MaxCountRule;
import merchants.guide.rules.NoSubtractionRule;
import merchants.guide.rules.OnlyOneSubtractionRule;
import merchants.guide.rules.RepeatMaxNTimesRule;
import merchants.guide.rules.SubtractionRule;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RulesFactory {

	public static Set<IRule> getRules() {
		Set<IRule> rules = new HashSet<>();

		// Repeat Rules
		rules.add(new RepeatMaxNTimesRule(RomanNumeral.I, 3));
		rules.add(new RepeatMaxNTimesRule(RomanNumeral.X, 3));
		rules.add(new RepeatMaxNTimesRule(RomanNumeral.C, 3));
		rules.add(new RepeatMaxNTimesRule(RomanNumeral.M, 3));

		// MaxCount Rules
		rules.add(new MaxCountRule(RomanNumeral.D, 1));
		rules.add(new MaxCountRule(RomanNumeral.L, 1));
		rules.add(new MaxCountRule(RomanNumeral.V, 1));

		// Subtraction Rules
		rules.add(new SubtractionRule(RomanNumeral.I, new HashSet<>(Arrays.asList(RomanNumeral.V, RomanNumeral.X))));
		rules.add(new SubtractionRule(RomanNumeral.X, new HashSet<>(Arrays.asList(RomanNumeral.L, RomanNumeral.C))));
		rules.add(new SubtractionRule(RomanNumeral.C, new HashSet<>(Arrays.asList(RomanNumeral.D, RomanNumeral.M))));

		// NoSubtraction Rules
		rules.add(new NoSubtractionRule(RomanNumeral.V));
		rules.add(new NoSubtractionRule(RomanNumeral.L));
		rules.add(new NoSubtractionRule(RomanNumeral.D));

		// Only one subtraction at a time
		rules.add(new OnlyOneSubtractionRule());

		return rules;
	}

}
