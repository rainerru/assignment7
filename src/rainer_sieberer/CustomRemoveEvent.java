package rainer_sieberer;

public class CustomRemoveEvent<T> extends CustomEvent<T>
{

	public CustomRemoveEvent ( Object newSource, T newTarget ) { super(newSource, newTarget); }

}
