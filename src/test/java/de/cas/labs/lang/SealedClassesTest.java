package de.cas.labs.lang;

import java.awt.Point;

import de.cas.labs.lang.SealedClassesTest.AsyncReturn.Failure;
import de.cas.labs.lang.SealedClassesTest.AsyncReturn.Interrupted;
import de.cas.labs.lang.SealedClassesTest.AsyncReturn.Success;
import de.cas.labs.lang.SealedClassesTest.AsyncReturn.Timeout;

//  Related article https://www.infoq.com/articles/java-sealed-classes/
public class SealedClassesTest {

	//
	// EXAMPLE 1
	//

	// Sealed interfaces can define which implementations are permitted
	// If something is marked as sealed, it is required to have subtypes
	// note that permits is not strictly required as all implementations are in the same compilation unit
	public sealed interface Shape permits Circle, Rectangle, Dump, AnotherLevel{}
	// records are automatically final so they are leafs in the hierarchy without further work
	public record Circle(Point center, int radius) implements Shape {}
	public record Rectangle(Point lowerLeft, Point upperRight) implements Shape {}
	// a non-sealed class allows indefinite subclasses and opens ob the hierarchy
	public non-sealed static class Dump implements Shape {}
	// starting at that level no special modifiers are required
	public static class Bar extends Dump {}

	// classes can also be sealed and define allowed subclasses
	public static sealed class AnotherLevel implements Shape permits Level1, Level2 {}
	// instead of sealed and non-sealed final classes are also valid extensions
	public static final class Level1 extends AnotherLevel {}
	public static final class Level2 extends AnotherLevel  {}

	//
	// EXAMPLE 2
	//

	// Currently Future uses a generic return value + exceptions to communicate the result
	// Using sealed classes this could have been better solved by using defining all valid
	// outcomes of calling get as a fixed set of interfaces
	interface Future<V> {
		AsyncReturn<V> get();
	}

	// defining the types nested automatically infers permits as they are in the same compilation unit
	// you might know this modelling approach from Scala's Case Classes or
	// Tagged Unions in other Languages
	public sealed interface AsyncReturn<V> {
		record Success<V>(V result) implements AsyncReturn<V> { }
		record Failure<V>(Throwable cause) implements AsyncReturn<V> { }
		record Timeout<V>() implements AsyncReturn<V> { }
		record Interrupted<V>() implements AsyncReturn<V> { }
	}

	// This is only a _preview_ feature in Java 17 but makes an approach like the
	// alternative Future implementation much more interesting because having
	// the sealed hierarchy makes exhaustiveness checking possible
	static <V> boolean handleResult(AsyncReturn<V> result) {
		return switch(result) {
			case Success s -> true;
			case Failure f -> false;
			case Timeout t -> true;
			case Interrupted i -> true;
		};
	}

	public static void main(String[] args) {
		var result = new Success<String>("foo");
		System.out.println(handleResult(result));
	}

	//
	// EXAMPLE 3
	//

	// consider a command pattern where you want to only allow vertain kinds of commands with
	// different constraints applied to them
	sealed interface Command {
		final class LoginCommand implements Command {}
		final class LogoutCommand implements Command {}
		non-sealed class UserCommand implements Command {}
		sealed abstract class ExternalCommand {
			static final class HttpCommand extends ExternalCommand {}
		}
	}

	//
	// EXAMPLE 4
	//

	// See package sealed for an example where permits is required as the types are not in the same
	// compilation unit
}
