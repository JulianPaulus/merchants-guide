package merchants.guide.parsing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AliasMapperTest {

	@Test
	void aliasMapperShouldEscapeUserInput() {
		final AliasMapper aliasMapper = new AliasMapper(".*", "evil input");
		final String input = "how much is glob glob";

		final String result = aliasMapper.map(input);

		assertEquals(input, result);
	}

}
