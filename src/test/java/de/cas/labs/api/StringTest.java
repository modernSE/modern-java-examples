package de.cas.labs.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class StringTest {

	@Test
	void transform() {
		assertEquals("woohoo!", "woohoo".transform(s -> s + "!"));

		var strings = List.of("woohoo", "haha", "hoohoo");
		assertIterableEquals(List.of("woohoo!", "haha!", "hoohoo!"), strings.stream().map(s -> s + "!").toList());

		assertEquals(new FancyNumber("SDDEV", "123"), "SDDEV:123".transform(this::toFancyNumber));
		assertIterableEquals(List.of(new FancyNumber("SDDEV", "123"), new FancyNumber("SDDEV", "234")), Stream.of("SDDEV:123", "SDDEV:234").map(this::toFancyNumber).toList());
	}

	private record FancyNumber(String prefix, String value) {}
	private FancyNumber toFancyNumber(String s) {
		String[] split = s.split(":");
		return new FancyNumber(split[0], split[1]);
	}

	@Test
	void indent() {
		String notIndented = "testing\n";
		String alreadyIndented = "    testing\n";
		String alreadyIndentedTabs = "\ttesting\n";

		assertEquals("    testing\n", notIndented.indent(4));
		assertEquals("        testing\n", alreadyIndented.indent(4));
		assertEquals("testing\n", notIndented.indent(-2));
		assertEquals("  testing\n", alreadyIndented.indent(-2));
		assertEquals("testing\n", alreadyIndentedTabs.indent(-2));
	}

	@Test
	void stripIndent() {
		var example = "  start\n" +
				"      line1\n" +
				"      line2\n" +
				"  end";

		assertEquals("""
				start
				    line1
				    line2
				end""", example.stripIndent());
	}

	@Test
	void translateEscapes() throws IOException {
		var compiled = "\ttest\nbla\r\n";
		var fromFile = getResourceFileAsString("translate-escapes.txt");

		assertNotEquals(compiled, fromFile);
		assertEquals(compiled, fromFile.translateEscapes());
	}

	@Test
	void formatted() {
		var template = "(%s) -> %s";

		assertEquals("(a) -> b", String.format(template, "a", "b"));
		assertEquals("(a) -> b", template.formatted("a", "b"));
	}

	private String getResourceFileAsString(String fileName) throws IOException {
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		try (InputStream is = classLoader.getResourceAsStream(fileName)) {
			if (is == null) return null;
			try (InputStreamReader isr = new InputStreamReader(is);
			     BufferedReader reader = new BufferedReader(isr)) {
				return reader.lines().collect(Collectors.joining(System.lineSeparator()));
			}
		}
	}

}
