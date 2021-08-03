package de.cas.labs.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.NumberFormat;
import java.text.NumberFormat.Style;
import java.util.Locale;

import org.junit.jupiter.api.Test;

public class NumberFormattingTest {

	@Test
	void englishFormattingExamples() {
		NumberFormat longFormat = NumberFormat.getCompactNumberInstance(Locale.ENGLISH, Style.LONG);
		NumberFormat shortFormat = NumberFormat.getCompactNumberInstance(Locale.ENGLISH, Style.SHORT);
		NumberFormat fractionalFormat = NumberFormat.getCompactNumberInstance(Locale.ENGLISH, Style.SHORT);
		fractionalFormat.setMaximumFractionDigits(3);

		assertEquals("10", longFormat.format(10));
		assertEquals("100", longFormat.format(100));
		assertEquals("1 thousand", longFormat.format(1000));
		assertEquals("10 thousand", longFormat.format(10_000));

		assertEquals("10", shortFormat.format(10));
		assertEquals("100", shortFormat.format(100));
		assertEquals("1K", shortFormat.format(1000));
		assertEquals("10K", shortFormat.format(10_000));

		assertEquals("10", fractionalFormat.format(10));
		assertEquals("100", fractionalFormat.format(100));
		assertEquals("1.001K", fractionalFormat.format(1001));
		assertEquals("10.001K", fractionalFormat.format(10_001));
	}

	@Test
	void germanFormattingExamples() {
		NumberFormat longFormat = NumberFormat.getCompactNumberInstance(Locale.GERMAN, Style.LONG);
		NumberFormat shortFormat = NumberFormat.getCompactNumberInstance(Locale.GERMAN, Style.SHORT);

		assertEquals("10", longFormat.format(10));
		assertEquals("100", longFormat.format(100));
		assertEquals("1 Tausend", longFormat.format(1000));
		assertEquals("10 Tausend", longFormat.format(10_000));

		assertEquals("10", shortFormat.format(10));
		assertEquals("100", shortFormat.format(100));
		assertEquals("1.000", shortFormat.format(1000));
		assertEquals("10.000", shortFormat.format(10_000));
	}

	@Test
	void hungarianFormattingExamples() {
		Locale hungarianLocale = new Locale("hu", "HU");
		NumberFormat longFormat = NumberFormat.getCompactNumberInstance(hungarianLocale, Style.LONG);
		NumberFormat shortFormat = NumberFormat.getCompactNumberInstance(hungarianLocale, Style.SHORT);

		assertEquals("10", longFormat.format(10));
		assertEquals("100", longFormat.format(100));
		assertEquals("1 ezer", longFormat.format(1000));
		assertEquals("10 ezer", longFormat.format(10_000));

		assertEquals("10", shortFormat.format(10));
		assertEquals("100", shortFormat.format(100));
		assertEquals("1 E", shortFormat.format(1000));
		assertEquals("10 E", shortFormat.format(10_000));
	}

}
