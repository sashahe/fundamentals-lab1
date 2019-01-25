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
  // There exists at least one set of two consecutive data points that are a distance greater than
  // the length, LENGTH1, apart.
  public boolean LIC0() {
    for (int i = 0; i < numpoints - 1; i++) {
      double distance = calculateDistance(i, i + 1);

      if (doubleCompare(distance, this.parameters.LENGTH1) == COMPTYPE.GT)
        return true;
    }
    return false;
  }

  /*
   * There exists at least one set of three consecutive data points 
   * that cannot all be contained within or on a circle of radius RADIUS1.
   * (0 ≤ RADIUS1)
  */
  public boolean LIC1() {
    if (this.numpoints < 3) { 
      return false; 
    }
    for (int i = 0; i < this.numpoints - 2; i++) {
      double radius = getRadiusOfCircleFrom3Points(i, i+1, i+2);
      if (!(doubleCompare(radius, this.parameters.RADIUS1) == COMPTYPE.LT)){
        return true; 
      }
    }
    return false;
  }

  // Returns true if LIC2 is true
  //There exists at least one set of three consecutive data points
  //that forms an angle such that angle < (PI - EPSILON1) or angle > (PI + EPSILON1)
  public boolean LIC2() {
    if((0 <= parameters.EPSILON1) && (parameters.EPSILON1 < PI) && (3 <= numpoints)) {
      double X1, X2, X3, Y1, Y2, Y3;
      double angle;
      for(int i = 0; i < numpoints - 2; i++) {
        X1 = X[i];    Y1 = Y[i];
        X2 = X[i+1];  Y2 = Y[i+1];
        X3 = X[i+2];  Y3 = Y[i+2];
        //The first and last point should not coincide with the vertex (second point)
        if(((X1 == X2) && (Y1 == Y2)) || ((X3 == X2) && (Y3 == Y2))) {
          return false;
        }
        angle = calculateAngle(i, i+1, i+2);
        if((doubleCompare(angle, (PI + parameters.EPSILON1)) == COMPTYPE.GT) || (doubleCompare(angle, (PI - parameters.EPSILON1)) == COMPTYPE.LT))
          return true;
      }
    }
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
  }

  public Decide() {
    decide();
  }

  public static void main(String args[]) {
    Decide decide = new Decide();
  }

  /****** HELPER METHODS ******/
  private COMPTYPE doubleCompare(double a, double b) {
    if (Math.abs(a - b) < 0.000001)
      return COMPTYPE.EQ;
    if (a < b)
      return COMPTYPE.LT;
    return COMPTYPE.GT;
  }

  //Calculates the distance between two coordinates.
  private double calculateDistance (int i, int j) {
    double Ax, Bx, Ay, By;
    Ax = X[i];	Ay = Y[i];
    Bx = X[j];  By = Y[j];
    double powX = Math.pow((Bx - Ax), 2);
    double powY = Math.pow((By - Ay), 2);
    double distance = Math.sqrt(powX + powY);
    return distance;
  }

  //Calculates the angle between three coordinates.
  private double calculateAngle (int i, int j, int k) {
    double Ax, Bx, Cx, Ay, By, Cy;
    Ax = X[i];	Ay = Y[i];
    Bx = X[j];	By = Y[j];
    Cx = X[k]; 	Cy = Y[k];

    double A = calculateDistance(i, j);
    double B = calculateDistance(j, k);
    double C = calculateDistance(k, i);
    double sqrtA = Math.sqrt(A);
    double sqrtB = Math.sqrt(B);
    double sqrtC = Math.sqrt(C);
    double numerator = Math.sqrt(sqrtA + sqrtB - sqrtC);
    double denominator = 2*A*B;
    double angle =  Math.acos(numerator/denominator);
    return angle;
  }

  private double getArea(int i, int j, int k) {
    double Ax, Bx, Cx, Ay, By, Cy;
    Ax = X[i]; Ay = Y[i];
    Bx = X[j]; By = Y[j];
    Cx = X[k]; Cy = Y[k];
    return Math.abs(Ax*(By-Cy) + Bx*(Cy-Ay) + Cx*(Ay-By))/2;
  }

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
    
    double radius = Math.sqrt(Math.pow((x - X1), 2) + Math.pow((y - Y1), 2));
    if (Double.isNaN(radius)) { return 0.0; }
    return radius;
  }
} 