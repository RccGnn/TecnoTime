package it.unisa.model.Filters;

public interface Compatible <A,B> {
	public boolean isCompatible(A a, B b);
}
