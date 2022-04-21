package game.card;

public class Gawi extends Hand {

	@Override
	public int compare(Hand otherHand) {
		int result = 0;
		
		if(otherHand instanceof Gawi) {
			result = 0;
		}else if(otherHand instanceof Bawi) {
			result = -1;			
		}else {
			result = 1;
		}
		return result;
	}

	@Override
	public Hand cheat(Hand otherHand) {
		Hand newHand = null;
		
		if(otherHand instanceof Gawi) {
			newHand = new Bawi();
		}else if(otherHand instanceof Bawi) {
			newHand = new Bo();			
		}else {
			newHand = new Gawi();
		}
		return newHand;

	}

}
