import java.util.*;

public class Decide {
  public Parameters parameters = new Parameters();
  public double[] X = new double[100];
  public double[] Y = new double[100];
  public int numpoints = 0;
  public CONNECTORS[][] LICM = new CONNECTORS[15][15];
  public boolean[] PUV = new boolean[15];

  public boolean[] CMV = new boolean[15];
  public boolean[][] PUM = new boolean[15][15];
  public boolean[] FUV = new boolean[15];

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

  // Returns true if LIC1 is true
  public boolean LIC1() {
    return false;
  }

  // Returns true if LIC2 is true
  public boolean LIC2() {
    return false;
  }

  // Returns true if LIC3 is true
  // There exists at least one set of three consecutive data points
  // that are the vertices of a triangle with area greater than AREA1. (0 ≤ AREA1)
  public boolean LIC3() {
    if (numpoints < 3)
      return false;
    double Ax, Bx, Cx, Ay, By, Cy;
    for (int i = 0; i < numpoints - 2; i++) {
      Ax = X[i];   Ay = Y[i];
      Bx = X[i+1]; By = Y[i+1];
      Cx = X[i+2]; Cy = Y[i+2];
      double area = Math.abs(Ax*(By-Cy) + Bx*(Cy-Ay) + Cx*(Ay-By))/2;
      if (doubleCompare(area,this.parameters.AREA1) == COMPTYPE.GT)
        return true;
    }
    return false;
  }

  // Returns true if LIC4 is true
  public boolean LIC4() {
    return false;
  }

  // Returns true if LIC5 is true
  // There exists at least one set of two consecutive data points,
  // (X[i],Y[i]) and (X[j],Y[j]) such that X[j] - X[i] < 0. (where i = j-1)
  public boolean LIC5() {
    if (this.numpoints < 2)
      return false;
    for (int i = 0; i < numpoints - 1; i++) {
      if (doubleCompare(X[i+1], X[i]) == COMPTYPE.LT)
        return true;
    }
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
