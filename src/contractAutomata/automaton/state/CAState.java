package contractAutomata.automaton.state;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class representing a state of a Contract Automaton
 * 
 * @author Davide Basile
 * 
 */
public class CAState extends State<List<BasicState>> {
	
	/**
	 * The list of states of principal
	 */
	//private final List<BasicState> state;
	
	private final float x;
	private final float y;


	public CAState(List<BasicState> lstate, float x, float y)
	{
		super(lstate);
		if (lstate==null||lstate.isEmpty())
			throw new IllegalArgumentException();
		//this.state = lstate;
		this.x=x;
		this.y=y;
	}

	public CAState(List<CAState> states)
	{
		super(states.stream()
		.map(CAState::getState)
		.reduce(new ArrayList<BasicState>(), (x,y)->{x.addAll(y); return x;}));
		
		this.x=0;
		this.y=0;
	}


	@Override
	public Integer getRank() {
		return this.getState().size();
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	@Override
	public boolean isInitial() {
		return this.getState().stream().allMatch(BasicState::isInitial);
	}
	
	@Override
	public void setInitial(boolean initial) {
		this.getState().forEach(s->s.setInitial(initial));
	}

	@Override
	public boolean isFinalstate() {
		return this.getState().stream().allMatch(BasicState::isFinalstate);
	}
	
	public String toCSV()
	{
		return "[state=["+this.getState().stream()
		.map(bs->bs.toCSV())
		.collect(Collectors.joining())+"]]";
	}


	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		if (this.isInitial())
			sb.append(" Initial ");
		if (this.isFinalstate())
			sb.append(" Final ");

		sb.append(this.getState().toString());

		return sb.toString();
	}


	
//	public boolean hasSameBasicStateLabelsOf(CAState s) {
//		if (s.getState().size()!=this.state.size())
//				return false;
//		return IntStream.range(0, this.state.size())
//		.allMatch(i->state.get(i).getLabel().equals(s.getState().get(i).getLabel()));
//	}
// equals could cause errors of duplication of states in transitions to go undetected. 

}
