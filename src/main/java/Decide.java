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

  /*
   * There exists at least one set of three consecutive data points 
   * that cannot all be contained within or on a circle of radius RADIUS1.
   * (0 ≤ RADIUS1)
  */
  public boolean LIC1() {
    if (this.numpoints < 3 || !(0 <= this.parameters.RADIUS1)) {
      return false;
    }
    for (int i = 0; i < this.numpoints - 2; i++) {
      double radius = getRadiusOfCircleFrom3Points(i, i+1, i+2);
      if (doubleCompare(radius, this.parameters.RADIUS1) == COMPTYPE.GT ) { return true; }
    }
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
    if (this.numpoints < 3)
      return false;
    for (int i = 0; i < this.numpoints - 2; i++) {
      if (doubleCompare(getArea(i, i+1, i+2), this.parameters.AREA1) == COMPTYPE.GT)
        return true;
    }
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
  //There exists at least one set of three data points separated by exactly E PTS and F PTS consecutive
  //intervening points, respectively, that are the vertices of a triangle with area greater
  // than AREA1. The condition is not met when NUMPOINTS < 5.
  public boolean LIC10() {
    if (this.numpoints < 5)
      return false;
    int e = this.parameters.E_PTS;
    int f = this.parameters.F_PTS;
    for (int i = 0; i < this.numpoints - (2 + e + f); i++) {
      if (doubleCompare(getArea(i, i+e+1, i+e+1+f+1), this.parameters.AREA1) == COMPTYPE.GT)
        return true;
    }
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
<<<<<<< HEAD
  
  /****** HELPER METHODS ******/

  /*
   * Used in LIC1
   * See http://www.ambrsoft.com/TrigoCalc/Circle3D.htm for equation
   */
  private Double getRadiusOfCircleFrom3Points(int i, int j, int k) {
    double X1 = X[i], Y1 = Y[i];
    double X2 = X[j], Y2 = Y[j];
    double X3 = X[k], Y3 = Y[k];

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

    return Math.sqrt(Math.pow((x - X1), 2) + Math.pow((y - Y1), 2));
  }

=======

  private double getArea(int i, int j, int k) {
    double Ax, Bx, Cx, Ay, By, Cy;
    Ax = X[i]; Ay = Y[i];
    Bx = X[j]; By = Y[j];
    Cx = X[k]; Cy = Y[k];
    return Math.abs(Ax*(By-Cy) + Bx*(Cy-Ay) + Cx*(Ay-By))/2;
  }
>>>>>>> master
} 