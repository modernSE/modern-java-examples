package de.cas.labs.api;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class StreamsTest {

	@Test
	void collectorsTeeing() {
		record Position(String name, long value, long amount) {}
		record Receipt(long total, List<String> positions) {}

		Receipt receipt = Stream.of(new Position("pos1", 10, 5),
				new Position("pos2", 20, 2),
				new Position("pos3", 30, 1),
				new Position("pos4", 40, 6)
		).collect(
				Collectors.teeing(
						Collectors.mapping(pos -> "%d x %s : %d".formatted(pos.amount, pos.name, pos.value), Collectors.toList()),
						Collectors.mapping(pos -> pos.amount() * pos.value(), Collectors.reducing(0L, (total, current) -> total + current)),
						(positions, total) -> new Receipt(total, positions)
				)
		);

		assertEquals(360, receipt.total());
		assertIterableEquals(receipt.positions(), List.of("5 x pos1 : 10", "2 x pos2 : 20", "1 x pos3 : 30", "6 x pos4 : 40"));
	}

	@Test
	void streamToList() {
		List<String> strings1 = Stream.of("1", "2").collect(Collectors.toList());

		assertDoesNotThrow(() -> {
			strings1.add("3");
		});

		List<String> strings2 = Stream.of("1", "2").collect(Collectors.toUnmodifiableList());

		assertThrows(UnsupportedOperationException.class, () -> {
			strings2.add("3");
		});

		List<String> strings3 = Stream.of("1", "2").toList();
		assertThrows(UnsupportedOperationException.class, () -> {
			strings3.add("3");
		});
	}
}
