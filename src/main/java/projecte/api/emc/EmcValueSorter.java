package projecte.api.emc;

import java.util.Comparator;

public class EmcValueSorter implements Comparator<EmcData> {

	@Override
	public int compare(EmcData o1, EmcData o2) {
		
		return (int)((double)((o2.getValue() - o1.getValue()) * 1000000));
	}
	
}
