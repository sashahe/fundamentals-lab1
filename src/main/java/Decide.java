import java.util.*;

public class Decide {
  private Parameters parameters;
  private double[] X = new double[100];
  private double[] Y = new double[100];
  private int numpoints = 0;
  private CONNECTORS[][] LICM = new CONNECTORS[15][15];
  private boolean[] PUV = new boolean[15];

  private boolean[] CMV = new boolean[15];
  private boolean[][] PUM = new boolean[15][15];
  private boolean[] FUV = new boolean[15];

  private boolean launch = false;

  private static final double PI = 3.1415926535;

  private enum CONNECTORS {
    NOTUSED, ORR, ANDD
  };

  private enum COMPTYPE {
    LT, EQ, GT
  };

  // Returns true if LIC0 is true
  public boolean LIC0() {
    return false;
  }

  // There exists at least one set of three consecutive data points
  // that cannot all be contained within or on a circle of radius RADIUS1.
  // (0 ≤ RADIUS1)
  public boolean LIC1() {
    if (numpoints < 3 && 0 <= parameters.RADIUS1) {
      return false;
    }

    double X1, Y1, X2, Y2, X3, Y3;
    for (int i = 0; i < numpoints - 2; i++) {
      X1 = X[i];
      Y1 = Y[i];
      X2 = X[i + 1];
      Y2 = Y[i + 1];
      X3 = X[i + 2];
      Y3 = Y[i + 2];

      double dividePart =  2 * ((X1 * (Y2 - Y3)) - (Y1 * (X2 - X3)) + (X2 * Y3) - (X3 * Y2));
      double x = 
      (Math.pow(X1, 2) + Math.pow(Y1, 2)) * (Y2 - Y3) + 
      (Math.pow(X2, 2) + Math.pow(Y2, 2)) * (Y3 - Y1) + 
      (Math.pow(X3, 2) + Math.pow(Y3, 2)) * (Y1 - Y2);
      x /= dividePart;

      double y = 
      (Math.pow(X1, 2) + Math.pow(Y1, 2)) * (X3 - X2) + 
      (Math.pow(X2, 2) + Math.pow(Y2, 2)) * (X1 - X3) + 
      (Math.pow(X3, 2) + Math.pow(Y3, 2)) * (X2 - X1);
      y /= dividePart;

      double radius = Math.sqrt(Math.pow((x - X1), 2) + Math.pow((y - Y1), 2));

      if (radius > parameters.RADIUS1) {
        return true;
      }
    }
    return false;
  }

  // Returns true if LIC2 is true
  public boolean LIC2() {
    return false;
  }

  // Returns true if LIC3 is true
  public boolean LIC3() {
    return false;
  }

  // Returns true if LIC4 is true
  public boolean LIC4() {
    return false;
  }

  // Returns true if LIC5 is true
  public boolean LIC5() {
    return false;
  }

  // Returns true if LIC6 is true
  public boolean LIC6() {
    return false;
  }

  // Returns true if LIC7 is true
  public boolean LIC7() {
    return false;
  }

  // Returns true if LIC8 is true
  public boolean LIC8() {
    return false;
  }

  // Returns true if LIC9 is true
  public boolean LIC9() {
    return false;
  }

  // Returns true if LIC10 is true
  public boolean LIC10() {
    return false;
  }

  // Returns true if LIC11 is true
  public boolean LIC11() {
    return false;
  }

  // Returns true if LIC12 is true
  public boolean LIC12() {
    return false;
  }

  // Returns true if LIC13 is true
  public boolean LIC13() {
    return false;
  }

  // Returns true if LIC14 is true
  public boolean LIC14() {
    return false;
  }

  // Set CMV[i] = true if LIC i == true
  public void calculateCMV() {
    CMV[0] = LIC0();
    CMV[1] = LIC1();
    CMV[2] = LIC2();
    CMV[3] = LIC3();
    CMV[4] = LIC4();
    CMV[5] = LIC5();
    CMV[6] = LIC6();
    CMV[7] = LIC7();
    CMV[8] = LIC8();
    CMV[9] = LIC9();
    CMV[10] = LIC10();
    CMV[11] = LIC11();
    CMV[12] = LIC12();
    CMV[13] = LIC13();
    CMV[14] = LIC14();
  };

  public void calculatePUM() {
    // Matrix operations between CMV and LICM to get PUM
  }

  public void calculateFUV() {
    // PUM (***) PUV -> FUV
  }

  public boolean checkFUV() {
    // Check if all values are true,
    return false;
  }

  public void decide() {
    calculateCMV();
    calculatePUM();
    calculateFUV();

    if (checkFUV()) {
      System.out.println("yes");
    } else {
      System.out.println("no");
    }
  };

  public Decide() {
    this.parameters = new Parameters();
    decide();
  }

  public static void main(String args[]) {
    Decide decide = new Decide();
  }

  private COMPTYPE doubleCompare(double a, double b) {
    if (Math.abs(a - b) < 0.000001)
      return COMPTYPE.EQ;
    if (a < b)
      return COMPTYPE.LT;
    return COMPTYPE.GT;
  }
}