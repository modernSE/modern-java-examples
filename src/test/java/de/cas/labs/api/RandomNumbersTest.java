package de.cas.labs.api;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.SecureRandom;
import java.util.Random;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RandomNumbersTest {

	@Test
	void classic() {
		int randomInt = new Random().nextInt(0, 10);
		assertTrue(randomInt > 0 && randomInt < 10);
	}

	@Test
	void secureClassic() {
		int randomInt = new SecureRandom().nextInt(0, 10);
		assertTrue(randomInt > 0 && randomInt < 10);
	}

	@Test
	void modern() {
		RandomGenerator defaultRandomGenerator = RandomGeneratorFactory.getDefault().create();
		RandomGenerator xoshiro256PlusPlus = RandomGeneratorFactory.of("Xoshiro256PlusPlus").create(System.currentTimeMillis());
		RandomGenerator l64X128StarStarRandom = RandomGeneratorFactory.of("L64X128StarStarRandom").create();

		int defaultRandom = defaultRandomGenerator.nextInt(0, 10);
		int xoshiroRandom = xoshiro256PlusPlus.nextInt(0, 10);
		int starStarRandom = l64X128StarStarRandom.nextInt(0, 10);

		assertTrue(defaultRandom >= 0 && defaultRandom < 10);
		assertTrue(xoshiroRandom >= 0 && xoshiroRandom < 10);
		assertTrue(starStarRandom >= 0 && starStarRandom < 10);
	}
}
