package merchants.guide.parsing.statements;

import merchants.guide.RomanToDecimalConverter;
import merchants.guide.RulesFactory;
import merchants.guide.parsing.AliasMapper;
import merchants.guide.parsing.ParsingRegistry;
import merchants.guide.parsing.StatementParsingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AliasStatementTest {

	private static Stream<Arguments> matchesTestCases() {
		return Stream.of(
			Arguments.of("glob is I", true),
			Arguments.of("prok is V", true),
			Arguments.of("glob glob Silver is 34 Credits", false),
			Arguments.of("how much is glob glob ?", false)
		);
	}

	@ParameterizedTest
	@MethodSource("matchesTestCases")
	void aliasStatementMatchesTest(final String input, final boolean expected) {
		final IStatement statement = new AliasStatement();

		assertEquals(expected, statement.matches(input));
	}

	@Test
	void aliasStatementShouldCreateAlias() throws StatementParsingException {
		final IStatement statement = new AliasStatement();
		final ParsingRegistry parsingRegistry = new ParsingRegistry(new RomanToDecimalConverter(RulesFactory.getRules()));

		statement.parse(parsingRegistry, "glob is X");

		assertEquals(1, parsingRegistry.getAliasMappers().size());

		final AliasMapper result = parsingRegistry.getAliasMappers().stream().findFirst().get();
		assertEquals("\\Qglob\\E", result.getFrom());
		assertEquals("X", result.getTo());
	}

	@Test
	void aliasStatementShouldThrowWhenGivenNonMatchingStatement() {
		final IStatement statement = new AliasStatement();
		final ParsingRegistry parsingRegistry = new ParsingRegistry(new RomanToDecimalConverter(RulesFactory.getRules()));

		assertThrows(
			StatementParsingException.class,
			() -> statement.parse(parsingRegistry, "glob is ")
		);
	}

	@Test
	void aliasStatementShouldThrowWhenGivenTooManyParameters() {
		final IStatement statement = new AliasStatement();
		final ParsingRegistry parsingRegistry = new ParsingRegistry(new RomanToDecimalConverter(RulesFactory.getRules()));

		assertThrows(
			StatementParsingException.class,
			() -> statement.parse(parsingRegistry, "glob is X is Y")
		);
	}

}
