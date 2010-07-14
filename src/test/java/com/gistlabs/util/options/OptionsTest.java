package com.gistlabs.util.options;

import static com.gistlabs.util.options.Options.*;

import org.junit.Assert;
import org.junit.Test;

import com.gistlabs.util.options.Options.Option;


public class OptionsTest extends Assert {

	@SuppressWarnings("unchecked")
	@Test
	public void checkSome() {
		checkSome(some("foo"));
		checkSome(option("foo"));
	}

	private void checkSome(Option<String> option) {
		assertEquals("foo", option.val());
		assertEquals("foo", option.or("bar"));
		assertTrue(option.some);
		assertFalse(option.none);
	}

	@Test(expected=NullPointerException.class)
	public void expectNullPointerFromNullSome() {
		some(null);
	}

	@Test(expected=NullPointerException.class)
	public void expectNullPointerFromNone() {
		none().val();
	}

	@Test(expected=NullPointerException.class)
	public void expectNullPointerFromNullOption() {
		option(null).val();
	}
	
	@Test
	public void checkNone() {
		Option<String> option = none();
		
		checkNone(option);

		Option<String> option2 = option(null);
		checkNone(option2);
	}

	private void checkNone(Option<String> option) {
		//		assertEquals(null, option.val()); // This will throw NullPointerException
				assertEquals("bar", option.or("bar"));
				assertFalse(option.some);
				assertTrue(option.none);
	}
}
