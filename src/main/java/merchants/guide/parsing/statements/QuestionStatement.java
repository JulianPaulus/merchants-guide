package merchants.guide.parsing.statements;

import merchants.guide.parsing.AlienUtil;
import merchants.guide.parsing.ParsingRegistry;

public class QuestionStatement extends TranslatingStatement {
	@Override
	public void parseInternal(final ParsingRegistry parsingRegistry, final String line) {
		final String alienNumber = line.replace("how much is ", "").replace(" ?", "");
		final String romanNumber = AlienUtil.alienToRoman(parsingRegistry, alienNumber.replaceAll("\\s+", ""));

		final int decimalValue = parsingRegistry.getRomanToDecimalConverter().convert(romanNumber);

		parsingRegistry.registerAnswer(alienNumber + " is " + decimalValue);
	}

	@Override
	public boolean matches(final String line) {
		return line.startsWith("how much is ") && line.endsWith("?");
	}
}
