package com.gistlabs.util.options;

public class Options {

	public interface When<T, X> {
		public X some(T some);
		public X none();
	}
	
	private Options() {
	}
	
	/**
	 * An Option can be either full (Some<T>) or empty (None). Instead of
	 * passing/returning nulls use an Option
	 */
	public abstract static class Option<T> {
		public final boolean some;
		public final boolean none;

		private Option(boolean some) {
			this.some = some;
			this.none = !some;
		}

		abstract public T val();

		public T or(T defaultResult) {
			if (some)
				return val();
			else
				return defaultResult;
		}
		
		public <X> X when(When<T,X> when) {
			if (some) {
				return when.some(val());
			} else {
				return when.none();
			}
		}
	}

	/**
	 * Placeholder for empty
	 */
	@SuppressWarnings("unchecked")
	public static class None<T> extends Option {
		private None() {
			super(false);
		}

		public T val() {
			throw new NullPointerException("Can't dereference a None value");
		}
	}

	/**
	 * A Holder for some value (never null)
	 */
	@SuppressWarnings("unchecked")
	public static class Some<T> extends Option {
		private final T val;

		private Some(T val) {
			super(true);
			if (val==null)
				throw new NullPointerException("Can't create a Some with null");
			this.val = val;
		}

		public T val() {
			return val;
		}
	}

	/**
	 * Use this when not sure if value is present or null
	 */
	@SuppressWarnings("unchecked")
	public static <T> Option<T> option(T value) {
		if (value == null)
			return none();
		else
			return some(value);
	}

	/**
	 * Use this to wrap a non-null value
	 */
	public static <T> Some<T> some(T value) {
		return new Some<T>(value);
	}

	@SuppressWarnings("unchecked")
	private final static None NONE = new None();

	/**
	 * Use this when you don't have a value
	 */
	@SuppressWarnings("unchecked")
	public static <T> Option<T> none() {
		return NONE;
	}
}