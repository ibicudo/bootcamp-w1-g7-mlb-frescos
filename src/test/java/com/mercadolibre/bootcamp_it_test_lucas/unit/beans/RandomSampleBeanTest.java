package com.mercadolibre.bootcamp_it_test_lucas.unit.beans;

import com.mercadolibre.bootcamp_it_test_lucas.dtos.SampleDTO;
import com.mercadolibre.bootcamp_it_test_lucas.beans.RandomSampleBean;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomSampleBeanTest {

	@Test
	public void randomPositiveTestOK() {
		RandomSampleBean randomSample = new RandomSampleBean();

		SampleDTO sample = randomSample.random();
		
		assertTrue(sample.getRandom() >= 0);
	}
}
