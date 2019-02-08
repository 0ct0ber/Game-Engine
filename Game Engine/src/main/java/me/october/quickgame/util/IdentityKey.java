package me.october.quickgame.util;

public class IdentityKey<E> {

	private E key;
	
	public IdentityKey(E key) {
		this.key = key;
	}
	
	public E get() {
		return key;
	}
	
	@Override
	public int hashCode() {
		return System.identityHashCode(key);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof IdentityKey)) return false;
		IdentityKey<?> other = ((IdentityKey<?>)obj);
		return this.key == other.key;
	}
}
