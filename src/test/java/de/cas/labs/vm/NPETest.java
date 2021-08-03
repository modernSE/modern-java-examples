package de.cas.labs.vm;

import java.time.LocalDate;

public class NPETest {

	record Content(String title, String content) {}

	// To compare add -XX:-ShowCodeDetailsInExceptionMessages to the JVM arguments to get old non detailed behavior
	public static void main(String[] args) {
		LocalDate date = LocalDate.now();
		Content content = new Content("Title", null);

		execute(null, content);
	}

	private static void execute(LocalDate date, Content content) {
		System.out.println(content.content() + date.toEpochDay());
	}

}
