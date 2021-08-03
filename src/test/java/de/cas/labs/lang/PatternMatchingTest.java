package de.cas.labs.lang;

public class PatternMatchingTest {

	interface Root {
	}

	static final class A implements Root {
		void foo() {
		}
	}

	static final class B implements Root {
		void bar() {
		}
	}

	void classicHandling(Root root) {
		if (root instanceof A) {
			((A) root).foo();
		} else if (root instanceof B) {
			((B) root).bar();
		}
	}

	void modernHandling(Root root) {
		if(root instanceof A a) {
			a.foo();
		} else if(root instanceof B b) {
			b.bar();
		}
	}
}
