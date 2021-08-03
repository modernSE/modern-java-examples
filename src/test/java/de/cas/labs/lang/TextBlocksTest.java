package de.cas.labs.lang;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TextBlocksTest {

	@Test
	void classicVsModernStringUsageScenario1() {
		var multipleLines = "line1\n" +
				"line2\n" +
				"line3\n";

		var multipleLinesComparison = """
				line1
				line2
				line3
				""";

		assertEquals(multipleLines, multipleLinesComparison);
	}

	@Test
	void classicVsModernStringUsageScenario2() {
		var multipleLines = "{\n" +
				"\tline1\n" +
				"\t\tline11\n" +
				"\tline2";


		var multipleLinesComparison = """
				{
					line1
						line11
					line2""";

		assertEquals(multipleLines, multipleLinesComparison);
	}

	@Test
	void classicVsModernStringUsageScenario3() {
		var title = "This is a title";
		var content = "The content";

		var htmlTemplate = "<html>\n" +
				"    <head>\n" +
				"        <title>" + title + "</title>\n" +
				"    </head>\n" +
				"    <body>\n" +
				"        " + content + "\n" +
				"    </body>\n" +
				"</html>\n";

		// ignores trailing whitespace and first line break after opening """
		var htmlTemplateComparison = """
				<html>
				    <head>
				        <title>%s</title>
				    </head>
				    <body>
				        %s
				    </body>
				</html>      
				""".formatted(title, content);

		assertEquals(htmlTemplate, htmlTemplateComparison);
	}

	@Test
	void textBlockSpecialFeatures_IgnoreLineBreak() {
		var textBlockComparison = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.";

		var textBlock = """
				Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor \
				invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam \
				et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem \
				ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod \
				tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et \
				justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum \
				dolor sit amet.""";

		assertEquals(textBlockComparison, textBlock);
	}

	@Test
	void textBlockSpecialFeatures_KeepTrailingWhitespace() {
		var textBlockComparison = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.       ";

		var textBlock = """
				Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor \
				invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam \
				et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem \
				ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod \
				tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et \
				justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum \
				dolor sit amet.      \s""";

		assertEquals(textBlockComparison, textBlock);
	}

}
