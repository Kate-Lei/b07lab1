import java.io.File;
import java.io.IOException;
public class Driver {
    public static void main(String [] args) {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        double [] c1 = {6,-2,5};
        int [] e1 = {0,1,3};
        Polynomial p1 = new Polynomial(c1,e1);
        double [] c2 = {3,-5,1};
        int [] e2 = {2,0,1};
        Polynomial p2 = new Polynomial(c2,e2);
        Polynomial s1 = p1.add(p2);
        Polynomial s2 = p2.add(p1);
        System.out.println("s1(0.1) = " + s1.evaluate(0.1));
        System.out.println("s2(0.1) = " + s2.evaluate(0.1));
        if(s1.hasRoot(1))
            System.out.println("1 is a root of s1");
        else
            System.out.println("1 is not a root of s1");
        Polynomial t1 = p1.multiply(p2);
        System.out.println("t1(0.1) = " + t1.evaluate(0.1));
        Polynomial t2 = p2.multiply(p1);
        System.out.println("t2(0.1) = " + t2.evaluate(0.1));

        System.out.println("\n=== Testing Polynomial Save to File ===");
        try {
            s1.saveToFile("s1_output.txt");
            System.out.println("Polynomial s1 has been saved to the file 's1_output.txt'");

            Polynomial filePoly = new Polynomial(new File("s1_output.txt"));
            System.out.println("Polynomial read from file s1(0.1) = " + filePoly.evaluate(0.1));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== Testing File Format (1x1) ===");
        try {
            Polynomial polyFromFile = new Polynomial(new File("/Users/katelei/fileforpoly.txt"));  // 1x1+3x2
            System.out.println("polyFromFile(1) = " + polyFromFile.evaluate(1));  // Testing polynomial read from the file
            polyFromFile.saveToFile("poly_test_output.txt");
            System.out.println("Polynomial polyFromFile has been saved to the file 'poly_test_output.txt'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}