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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConversionStatementTest {

	private static Stream<Arguments> matchesTestCases() {
		return Stream.of(
			Arguments.of("glob is I", false),
			Arguments.of("prok is V", false),
			Arguments.of("glob glob Silver is 34 Credits", true),
			Arguments.of("how much is glob glob ?", false),
			Arguments.of("how many Credits is glob prok Silver ?", false)
		);
	}

	@ParameterizedTest
	@MethodSource("matchesTestCases")
	void conversionStatementMatchesTest(final String input, final boolean expected) {
		final IStatement statement = new ConversionStatement();

		assertEquals(expected, statement.matches(input));
	}

	@Test
	void conversionStatementShouldRegisterUnitConverter() throws StatementParsingException {
		final IStatement statement = new ConversionStatement();
		final ParsingRegistry parsingRegistry = new ParsingRegistry(new RomanToDecimalConverter(RulesFactory.getRules()));
		parsingRegistry.registerAliasMapper(new AliasMapper("glob", "X"));

		statement.parse(parsingRegistry, "glob glob Iron is 40 Credits");

		assertEquals(1, parsingRegistry.getUnitConverters().size());

		final UnitConverter result = parsingRegistry.getUnitConverters().get(Unit.IRON);
		assertNotNull(result);
		assertEquals(20, result.convert(10));
	}

	@Test
	void conversionStatementShouldThrowWhenGivenNonMatchingStatement() {
		final IStatement statement = new ConversionStatement();
		final ParsingRegistry parsingRegistry = new ParsingRegistry(new RomanToDecimalConverter(RulesFactory.getRules()));
		parsingRegistry.registerAliasMapper(new AliasMapper("glob", "X"));

		final StatementParsingException exception = assertThrows(
			StatementParsingException.class,
			() -> statement.parse(parsingRegistry, "glob is 0")
		);
		assertTrue(exception.getMessage().contains("must match"));
	}

	@Test
	void conversionStatementShouldThrowWhenNoMappersArePresent() {
		final IStatement statement = new ConversionStatement();
		final ParsingRegistry parsingRegistry = new ParsingRegistry(new RomanToDecimalConverter(RulesFactory.getRules()));

		final StatementParsingException exception = assertThrows(
			StatementParsingException.class,
			() -> statement.parse(parsingRegistry, "glob glob Silver is 40 Credits")
		);
		assertTrue(exception.getMessage().contains("requires mappers"));
	}

}
