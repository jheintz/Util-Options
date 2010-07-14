package com.gistlabs.util.options;

import static com.gistlabs.util.options.Options.*;

import org.junit.Assert;
import org.junit.Test;

public class OptionsWhenTest extends Assert {

	@SuppressWarnings("unchecked")
	@Test
	public void checkSome() {
		Some<String> some = some("foo");
		
		Integer length = (Integer) some.when(new When<String, Integer>() {
			public Integer none() {
				return 0;
			}
			public Integer some(String some) {
				return some.length();
			}
		});
		
		assertEquals((Integer)3, length);
	}
}
