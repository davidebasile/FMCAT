package contractAutomata.automaton.transition;

import java.util.Objects;

import contractAutomata.automaton.Ranked;
import contractAutomata.automaton.label.Matchable;
import contractAutomata.automaton.state.State;

/**
 * Transition of a contract automaton
 * 
 * @author Davide Basile
 *
 */
public class Transition<U, S extends State<U>,L extends Ranked & Matchable<? super L>> { 
	final private S source;
	final private S target;
	final private L label;

	public Transition(S source, L label, S target){
		if (source==null || label==null || target==null)
			throw new IllegalArgumentException("source, label or target null");
		if (!(source.getRank()==target.getRank()&&label.getRank()==source.getRank()))
			throw new IllegalArgumentException("source, label or target with different ranks");
		this.source=source;
		this.target=target;
		this.label=label;
	}

	public S getSource()
	{
		return this.source;
	}

	public S getTarget()
	{
		return target;
	}

	public L getLabel()
	{
		return label;
	}

	public Integer getRank()
	{
		return label.getRank();
	}

	@Override
	public int hashCode() {
		return Objects.hash(source.hashCode(),label.hashCode(),target.hashCode());
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transition<?,? extends Ranked, ? extends Ranked> other = (Transition<?,?, ?>) obj;
		return label.equals(other.getLabel())&&source.equals(other.getSource())&&target.equals(other.getTarget());
	}

	@Override
	public String toString() {
		return "("+source+","+label+","+target+")";
	}
	
}
