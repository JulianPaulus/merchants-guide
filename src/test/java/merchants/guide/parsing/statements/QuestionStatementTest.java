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

public class QuestionStatementTest {

	private static Stream<Arguments> matchesTestCases() {
		return Stream.of(
			Arguments.of("glob is I", false),
			Arguments.of("prok is V", false),
			Arguments.of("glob glob Silver is 34 Credits", false),
			Arguments.of("how much is glob glob ?", true),
			Arguments.of("how many Credits is glob prok Silver ?", false)
		);
	}

	@ParameterizedTest
	@MethodSource("matchesTestCases")
	void questionStatementMatchesTest(final String input, final boolean expected) {
		final IStatement statement = new QuestionStatement();

		assertEquals(expected, statement.matches(input));
	}

	@Test
	void questionStatementShouldCreateAnswer() throws StatementParsingException {
		final IStatement statement = new QuestionStatement();
		final ParsingRegistry parsingRegistry = new ParsingRegistry(new RomanToDecimalConverter(RulesFactory.getRules()));
		parsingRegistry.registerAliasMapper(new AliasMapper("glob", "X"));

		statement.parse(parsingRegistry, "how much is glob glob ?");

		assertEquals(1, parsingRegistry.getAnswers().size());

		final String answer = parsingRegistry.getAnswers().stream().findFirst().get();
		assertEquals("glob glob is 20", answer);
	}

	@Test
	void questionStatementShouldThrowWhenGivenNonMatchingStatement() {
		final IStatement statement = new QuestionStatement();
		final ParsingRegistry parsingRegistry = new ParsingRegistry(new RomanToDecimalConverter(RulesFactory.getRules()));
		parsingRegistry.registerAliasMapper(new AliasMapper("glob", "X"));

		final StatementParsingException exception = assertThrows(
			StatementParsingException.class,
			() -> statement.parse(parsingRegistry, "how much is")
		);
		assertTrue(exception.getMessage().contains("must match"));
	}

	@Test
	void questionStatementShouldThrowWhenNoMappersArePresent() {
		final IStatement statement = new QuestionStatement();
		final ParsingRegistry parsingRegistry = new ParsingRegistry(new RomanToDecimalConverter(RulesFactory.getRules()));

		final StatementParsingException exception = assertThrows(
			StatementParsingException.class,
			() -> statement.parse(parsingRegistry, "how much is glob glob ?")
		);
		assertTrue(exception.getMessage().contains("requires mappers"));
	}

}
