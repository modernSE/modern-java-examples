package de.cas.labs.lang;

import org.junit.jupiter.api.Test;

public class SwitchExpressionTest {

	enum Day {
		MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
	}

	@Test
	void classicSwitch() {
		var day = Day.MONDAY;
		switch (day) {
		case MONDAY:
		case FRIDAY:
		case SUNDAY:
			System.out.println(6);
			break;
		case TUESDAY:
			System.out.println(7);
			break;
		case THURSDAY:
		case SATURDAY:
			System.out.println(8);
			break;
		case WEDNESDAY:
			System.out.println(9);
			break;
		}
	}

	@Test
	void newSwitchVariant1() {
		var day = Day.MONDAY;
		int result = switch (day) {
		case MONDAY:
		case SUNDAY:
			System.out.println();
			int i = 0;
		case TUESDAY:
			yield 7;
		case THURSDAY:
		case SATURDAY:
			yield 8;
		case WEDNESDAY:
			yield 9;
		default:
			yield 0;
		};
	}

	@Test
	void newSwitchVariant2() {
		var day = Day.MONDAY;
		switch (day) {
		case MONDAY, FRIDAY, SUNDAY -> {
			int i = 0;
			System.out.println(6);
		}
		case TUESDAY -> {
			int i = 0;
			System.out.println(7);
		}
		case THURSDAY, SATURDAY -> System.out.println(8);
		case WEDNESDAY -> System.out.println(9);
		}
	}

	@Test
	void newSwitchVariant3() {
		var day = Day.MONDAY;
		int result = switch (day) {
			case MONDAY, FRIDAY, SUNDAY ->  {
				System.out.println("");
				yield 6;
			}
			case TUESDAY -> 7;
			case THURSDAY, SATURDAY -> 8;
			case WEDNESDAY -> 9;
		};
	}

	@Test
	void newSwitchVariant4() {
		Day day = Day.MONDAY;
		int result = switch (day) {
			case MONDAY, FRIDAY -> 6;
			case TUESDAY -> 7;
			case THURSDAY -> 8;
			case WEDNESDAY -> 9;
			default -> throw new IllegalArgumentException("no");
		};
	}

	@Test
	void modernSwitchOnNumbers() {
		int number = 1;
		int result = switch(number) {
			case 1, 3, 5 -> 12;
			default -> 0;
		};
	}
}
