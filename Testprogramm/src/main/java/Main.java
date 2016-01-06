

public class Main
{
	int ergebnis;
	int zahl1;
	int zahl2;
	
	public Main() {
		
	}
	
	public Main(int zahl1, int zahl2)
	{
		this.zahl1 = zahl1;
		this.zahl2 = zahl2;
	}
	
	public int addition(int zahl1, int zahl2)
	{
		ergebnis = zahl1 + zahl2;
		
		return ergebnis;
	}

}
