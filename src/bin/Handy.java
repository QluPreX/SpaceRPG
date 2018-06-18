package bin;

public class Handy {
	
	
	//returns the length of an integer
	public int getlenght(int n) {
		return (int)(Math.log10(n)+1);
	}
	//check if percent
	public boolean isPercent(int n) {
		boolean bool;
		return(bool = (n >= 0 || n <= 100) ? true : false);
		
		}
	}
