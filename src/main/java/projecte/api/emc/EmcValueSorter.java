package projecte.api.emc;

import java.util.Comparator;

public class EmcValueSorter implements Comparator<EmcValue> {

	@Override
	public int compare(EmcValue o1, EmcValue o2) {
		
		return (int)((double)((o2.getValue() - o1.getValue()) * 1000000));
	}
	
}
