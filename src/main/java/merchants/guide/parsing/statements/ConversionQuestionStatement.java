package merchants.guide.parsing.statements;

import merchants.guide.parsing.AlienUtil;
import merchants.guide.parsing.ParsingRegistry;
import merchants.guide.parsing.StatementParsingException;
import merchants.guide.parsing.Unit;
import merchants.guide.parsing.UnitConverter;

import java.util.Arrays;

public class ConversionQuestionStatement extends TranslatingStatement {
	@Override
	public void parseInternal(final ParsingRegistry parsingRegistry, final String line) throws StatementParsingException{
		final String question = line.replace("how many ", "").replace(" ?", "");

		final String[] mainSplit = question.split("\\s+is\\s+");

		final String[] fromSplit = mainSplit[1].split("\\s+");
		final Unit fromUnit = Unit.valueOf(fromSplit[fromSplit.length - 1].toUpperCase());

		final double fromValue = AlienUtil.parseAlienNumber(parsingRegistry, fromSplit);
		final double convertedValue = convertToCredits(parsingRegistry, fromUnit, fromValue);

		parsingRegistry.registerAnswer(mainSplit[1] + " is " + convertedValue + " Credits");
	}

	private static double convertToCredits(final ParsingRegistry parsingRegistry, final Unit fromUnit,
											final double fromValue) throws StatementParsingException {
		final UnitConverter unitConverter = parsingRegistry.getUnitConverters().get(fromUnit);
		if (unitConverter == null) {
			throw new StatementParsingException("no UnitConverter for unit '" + fromUnit + "' registered!");
		}
		return unitConverter.convert(fromValue);
	}

	@Override
	public boolean matches(final String line) {
		return line.startsWith("how many ") && line.endsWith("?");
	}
}
