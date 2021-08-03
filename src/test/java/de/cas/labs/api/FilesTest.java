package de.cas.labs.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

public class FilesTest {

	@Test
	void mismatch() throws IOException {
		Path file1 = File.createTempFile("labs-mismatch", null).toPath();
		Path file2 = File.createTempFile("labs-mismatch", null).toPath();

		Files.writeString(file1, """
				I'am the same.
				""");

		Files.writeString(file2, """
				I'am different.
				""");

		long difference = Files.mismatch(file1, file2);

		assertEquals(5, difference);
	}

}
