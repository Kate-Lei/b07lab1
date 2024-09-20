public class Polynomial{
	// fields
	double[] coEfficient;
	
	// methods
	public Polynomial() {
		this.coEfficient = new double[]{0};
	}
	
	public Polynomial(double[] coefficient) {
		this.coEfficient = coefficient;
	}
	
	public Polynomial add(Polynomial toadd) {
		int maxlen = Math.max(this.coEfficient.length, toadd.coEfficient.length);
		double[] result = new double[maxlen];
		for(int i = 0;i < maxlen;i++) {
			double thisco = 0;
		    double addco = 0;
			if(i < this.coEfficient.length)
				thisco = this.coEfficient[i];
			else
				thisco = 0;
			if(i < toadd.coEfficient.length)
				addco = toadd.coEfficient[i];
			else
				addco = 0;
			result[i] = thisco + addco;
		}
		return new Polynomial(result);
				
	}
	
	public double evaluate(double x) {
		double result = 0;
		for(int i = 0; i < this.coEfficient.length; i++)
			result += this.coEfficient[i] * Math.pow(x, i);
		return result;
	}
	
	public boolean hasRoot(double x) {
		return this.evaluate(x) == 0;
	}
}