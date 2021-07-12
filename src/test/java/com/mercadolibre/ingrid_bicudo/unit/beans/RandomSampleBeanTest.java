package com.mercadolibre.ingrid_bicudo.unit.beans;

import com.mercadolibre.ingrid_bicudo.dtos.SampleDTO;
import com.mercadolibre.ingrid_bicudo.beans.RandomSampleBean;

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
