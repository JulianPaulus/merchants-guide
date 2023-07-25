package merchants.guide.parsing.statements;

import merchants.guide.RomanToDecimalConverter;
import merchants.guide.RulesFactory;
import merchants.guide.parsing.AliasMapper;
import merchants.guide.parsing.ParsingRegistry;
import merchants.guide.parsing.StatementParsingException;
import merchants.guide.parsing.Unit;
import merchants.guide.parsing.UnitConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConversionQuestionStatementTest {

	private static Stream<Arguments> matchesTestCases() {
		return Stream.of(
			Arguments.of("glob is I", false),
			Arguments.of("prok is V", false),
			Arguments.of("glob glob Silver is 34 Credits", false),
			Arguments.of("how much is glob glob ?", false),
			Arguments.of("how many Credits is glob prok Silver ?", true)
		);
	}

	@ParameterizedTest
	@MethodSource("matchesTestCases")
	void conversionQuestionStatementMatchesTest(final String input, final boolean expected) {
		final IStatement statement = new ConversionQuestionStatement();

		assertEquals(expected, statement.matches(input));
	}

	@Test
	void conversionQuestionStatementShouldCreateAnswer() throws StatementParsingException {
		final IStatement statement = new ConversionQuestionStatement();
		final ParsingRegistry parsingRegistry = new ParsingRegistry(new RomanToDecimalConverter(RulesFactory.getRules()));
		parsingRegistry.registerAliasMapper(new AliasMapper("glob", "X"));
		parsingRegistry.registerAliasMapper(new AliasMapper("prok", "I"));
		parsingRegistry.registerUnitConverters(new UnitConverter(Unit.SILVER, 2));

		statement.parse(parsingRegistry, "how many Credits is glob prok Silver ?");

		assertEquals(1, parsingRegistry.getAnswers().size());

		final String answer = parsingRegistry.getAnswers().stream().findFirst().get();
		assertEquals("glob prok Silver is 22.0 Credits", answer);
	}

	@Test
	void conversionQuestionStatementShouldThrowWhenGivenNonMatchingStatement() {
		final IStatement statement = new ConversionQuestionStatement();
		final ParsingRegistry parsingRegistry = new ParsingRegistry(new RomanToDecimalConverter(RulesFactory.getRules()));

		assertThrows(
			StatementParsingException.class,
			() -> statement.parse(parsingRegistry, "glob is ")
		);
	}

	@Test
	void conversionQuestionStatementShouldThrowWhenMissingConverter() {
		final IStatement statement = new ConversionQuestionStatement();
		final ParsingRegistry parsingRegistry = new ParsingRegistry(new RomanToDecimalConverter(RulesFactory.getRules()));
		parsingRegistry.registerAliasMapper(new AliasMapper("glob", "X"));
		parsingRegistry.registerAliasMapper(new AliasMapper("prok", "I"));

		final StatementParsingException exception = assertThrows(
			StatementParsingException.class,
			() -> statement.parse(parsingRegistry, "how many Credits is glob prok Gold ?")
		);
		assertTrue(exception.getMessage().contains(Unit.GOLD.toString()));
	}

}
