package rainer_sieberer;

public abstract class CustomEvent<T>
{

	protected Object source;
	protected T target;

	public CustomEvent ( Object newSource, T newTarget )
	{
		this.source = newSource;
		this.target = newTarget;
	}

	public Object getSource () { return this.source; }

	public T getTarget () { return this.target; }

}
