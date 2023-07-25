package merchants.guide.parsing;

import merchants.guide.RomanToDecimalConverter;
import merchants.guide.RulesFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StatementParserTest {

	@Test
	void shouldParseStatements() {
		final StatementParser statementParser = new StatementParser(StatementFactory.getStatements());
		final RomanToDecimalConverter romanToDecimalConverter = new RomanToDecimalConverter(RulesFactory.getRules());
		final String[] input = {"glob is X"};

		final ParsingRegistry result = statementParser.parse(romanToDecimalConverter, input);

		assertTrue(result.getAnswers().isEmpty()); // statement was understood -> no "I have no idea what you are talking about"
		assertEquals(1, result.getAliasMappers().size()); // statement was parsed correctly
	}

	@Test
	void shouldAnswerIfStatementIsGarbage() {
		final StatementParser statementParser = new StatementParser(StatementFactory.getStatements());
		final RomanToDecimalConverter romanToDecimalConverter = new RomanToDecimalConverter(RulesFactory.getRules());
		final String[] input = {"how much wood could a woodchuck chuck if a woodchuck could chuck wood ?"};

		final ParsingRegistry result = statementParser.parse(romanToDecimalConverter, input);

		assertEquals(1, result.getAnswers().size());
		assertEquals("I have no idea what you are talking about", result.getAnswers().stream().findFirst().get());
	}

	@Test
	void shouldAnswerIfExceptionOccurs() {
		final StatementParser statementParser = new StatementParser(StatementFactory.getStatements());
		final RomanToDecimalConverter romanToDecimalConverter = new RomanToDecimalConverter(RulesFactory.getRules());
		final String[] input = {"how much is glob glob ?"};

		final ParsingRegistry result = statementParser.parse(romanToDecimalConverter, input);

		assertEquals(1, result.getAnswers().size());

		final String answer = result.getAnswers().stream().findFirst().get();
		assertNotEquals("I have no idea what you are talking about", answer);
		assertTrue(answer.contains("requires mappers"));
	}

}
