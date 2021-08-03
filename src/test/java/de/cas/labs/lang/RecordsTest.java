package de.cas.labs.lang;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RecordsTest {

	interface Content {
		String title();

		String content();
	}

	record Article(String title, String content) implements Content {}

	record BlogPost(String title, String text, List<String> tags) implements Content {

		// Use shortcut to define invariants
		BlogPost {
			requireNonNull(title);
			if (tags.isEmpty()) {
				tags = List.of("blogpost");
			}
		}

		@Override
		public String content() {
			return """
					%s
					                    
					Tags: %s
					""".formatted(text, String.join(", ", tags));
		}
	}

	record Tutorial(String title, List<String> parts) implements Content {

		@Override
		public String content() {
			return String.join("\n\n", parts);
		}
	}

	@Test
	void equalsHashCodeToString() {
		assertNotEquals(new Article("An Article Title", "The Content"), new Article("Another Article Title", "The Content"));
		assertEquals(new Article("An Article Title", "The Content"), new Article("An Article Title", "The Content"));

		assertNotEquals(new Article("An Article Title", "The Content").hashCode(), new Article("Another Article Title", "The Content").hashCode());
		assertEquals(new Article("An Article Title", "The Content").hashCode(), new Article("An Article Title", "The Content").hashCode());

		assertEquals("Article[title=An Article Title, content=The Content]", new Article("An Article Title", "The Content").toString());
		assertEquals("Article[title=Another Article Title, content=The Content]", new Article("Another Article Title", "The Content").toString());
	}
}
