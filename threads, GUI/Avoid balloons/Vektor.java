package igra;

public class Vektor implements Cloneable{
	
	private double x,y;

	public Vektor(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	@Override
	public Vektor clone()  {
		Vektor copy=null;
		try {
			copy = (Vektor) super.clone();
		} catch (CloneNotSupportedException e) {}
		
		return copy;
	}
	
	public Vektor pomnozi(double vr) {
		return new Vektor(x*vr,y*vr);
	}
	
	public void saberi(Vektor v) {
		x+=v.x; y+=v.y;
	}
	
	
}
