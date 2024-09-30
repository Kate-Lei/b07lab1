import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class Polynomial{
	// fields
	double[] coefficient;
	int[] exponent;
	
	// methods
	public Polynomial() {
		this.coefficient = new double[0];
		this.exponent = new int[0];
	}
	
	public Polynomial(double[] coefficients, int[] exponents) {
        this.coefficient = coefficients;
        this.exponent = exponents;
    }
	
	public Polynomial add(Polynomial toadd) {
		int maxlen = this.coefficient.length + toadd.coefficient.length;
	    double[] newCos = new double[maxlen];
	    int[] newExs = new int[maxlen];

	    int index = 0;

	    for (int i = 0; i < this.coefficient.length; i++) {
	        newCos[index] = this.coefficient[i];
	        newExs[index] = this.exponent[i];
	        index++;
	    }

	    for (int i = 0; i < toadd.coefficient.length; i++) {
	        boolean found = false;

	        for (int j = 0; j < index; j++) {
	            if (newExs[j] == toadd.exponent[i]) {
	                newCos[j] += toadd.coefficient[i];
	                found = true;
	                break;
	            }
	        }

	        if (!found) {
	            newCos[index] = toadd.coefficient[i];
	            newExs[index] = toadd.exponent[i];
	            index++;
	        }
	    }

	    int nonZeroCount = 0;

	    for (int i = 0; i < index; i++) {
	        if (newCos[i] != 0) {
	            nonZeroCount++;
	        }
	    }

	    double[] finalCos = new double[nonZeroCount];
	    int[] finalExs = new int[nonZeroCount];

	    int finalIndex = 0;

	    for (int i = 0; i < index; i++) {
	        if (newCos[i] != 0) {
	            finalCos[finalIndex] = newCos[i];
	            finalExs[finalIndex] = newExs[i];
	            finalIndex++;
	        }
	    }

	    // Return the resulting polynomial
	    return new Polynomial(finalCos, finalExs);
				
	}
	
	
	
	public double evaluate(double x) {
		double result = 0;
		for(int i = 0; i < this.coefficient.length; i++)
			result += this.coefficient[i] * Math.pow(x, this.exponent[i]);
		return result;
	}
	
	public boolean hasRoot(double x) {
		return this.evaluate(x) == 0;
	}
	

	public Polynomial multiply(Polynomial tomul) {
		double[] thisco = this.coefficient;
	    int[] thisex = this.exponent;
	    double[] otherco = tomul.coefficient;
	    int[] otherex = tomul.exponent;
	        
	    Map<Integer, Double> resultMap = new HashMap<>();
	        
	    for (int i = 0; i < thisco.length; i++) {
	        for (int j = 0; j < otherco.length; j++) {
	            double newCo = thisco[i] * otherco[j];
	            int newEx = thisex[i] + otherex[j];
	            resultMap.put(newEx, resultMap.getOrDefault(newEx, 0.0) + newCo);
	        }
	    }

	    int size = resultMap.size();
	    double[] resultco = new double[size];
	    int[] resultex = new int[size];

	    int index = 0;
	    for (Map.Entry<Integer, Double> entry : resultMap.entrySet()) {
	        resultex[index] = entry.getKey();
	        resultco[index] = entry.getValue();
	        index++;
	    }

	    return new Polynomial(resultco, resultex);
    }
		
	public Polynomial(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        reader.close();

        parsePolynomial(line);
    }

    private void parsePolynomial(String line) {
        line = line.replace("-", " -").replace("+", " +");
        String[] terms = line.split("\\s");
        int termslen = terms.length;

        ArrayList<Double> coefList = new ArrayList<>();
        ArrayList<Integer> expList = new ArrayList<>();

        for (int i = 0; i < termslen; i++) {
        	String term = terms[i];
        	double coefficients;
            int exponents;
            if (term.contains("x")) {
                String[] parts = term.split("x");
                coefficients = Double.parseDouble(parts[0]);
                exponents = Integer.parseInt(parts[1]);

                coefList.add(coefficients);
                expList.add(exponents);
            }
            else {
                coefList.add(Double.parseDouble(term));
                expList.add(0);
            }
        }

        // 将 ArrayList 转换为数组并赋值给 fields
        this.coefficient = new double[coefList.size()];
        this.exponent = new int[expList.size()];

        for (int i = 0; i < coefList.size(); i++) {
            this.coefficient[i] = coefList.get(i);
            this.exponent[i] = expList.get(i);
        }
    }
    public void saveToFile(String filepath)throws IOException {
    	FileWriter writer = new FileWriter(filepath);
    	for (int i = 0; i < this.coefficient.length; i++) {
    		if(this.exponent[i] != 0) {
    		    if(this.coefficient[i] > 0 && i != 0) {
    			    writer.write("+" + String.valueOf(this.coefficient[i]) + "x" + String.valueOf(this.exponent[i]));
    		    }
    		    else {
    		    	writer.write(String.valueOf(this.coefficient[i]) + "x" + String.valueOf(this.exponent[i]));
    		    }
            }
    		else {
    			if(this.coefficient[i] > 0 && i != 0) {
    			    writer.write("+" + String.valueOf(this.coefficient[i]));
    		    }
    		    else {
    		    	writer.write(String.valueOf(this.coefficient[i]));
    		    }
    		}
    	}
        writer.close();
    }
}