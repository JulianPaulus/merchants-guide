package merchants.guide;

import merchants.guide.parsing.StatementFactory;
import merchants.guide.parsing.ParsingRegistry;
import merchants.guide.parsing.StatementParser;


public class Main {

	public static void main(String[] args) {
		String[] input = {
			"glob is I",
			"prok is V",
			"pish is X",
			"tegj is L",
			"glob glob Silver is 34 Credits",
			"glob prok Gold is 57800 Credits",
			"pish pish Iron is 3910 Credits",
			"how much is pish tegj glob glob ?",
			"how many Credits is glob prok Silver ?",
			"how many Credits is glob prok Gold ?",
			"how many Credits is glob prok Iron ?",
			"how much wood could a woodchuck chuck if a woodchuck could chuck wood ?"
		};


		final StatementParser parser = new StatementParser(StatementFactory.getStatements());
		final RomanToDecimalConverter romanToDecimalConverter = new RomanToDecimalConverter(RulesFactory.getRules());

		final ParsingRegistry registry = parser.parse(romanToDecimalConverter, input);

		registry.getAnswers().forEach(System.out::println);
	}
}
