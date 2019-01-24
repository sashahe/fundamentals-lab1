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

/*****************Additional Math Functions********************/
  //Calculates the distance between two coordinates.
  public double calculateDistance (double X1, double X2, double Y1, double Y2) {
    double powA = Math.pow((X2 - X1), 2);
    double powB = Math.pow((Y2 - Y1), 2);
    double distance = Math.sqrt(powA + powB);
    return distance;
  }

  //Calculates the angle between three coordinates.
  public double calculateAngle (double X1, double X2, double X3, double Y1, double Y2, double Y3) {
    double A = calculateDistance(X1, X2, Y1, Y2);
    double B = calculateDistance(X2, X3, Y2, Y3);
    double C = calculateDistance(X3, X1, Y3, Y1);
    double sqrtA = Math.sqrt(A);
    double sqrtB = Math.sqrt(B);
    double sqrtC = Math.sqrt(C);
    double numerator = Math.sqrt(sqrtA + sqrtB - sqrtC);
    double denominator = 2*A*B;
    double angle =  Math.acos(numerator/denominator);
    return angle;
  }
  /*************************************************************/


  // Returns true if LIC0 is true
  public boolean LIC0() {
    return false;
  }

  // Returns true if LIC1 is true
  public boolean LIC1() {
    return false;
  }

  // Returns true if LIC2 is true
  //There exists at least one set of three consecutive data points
  //that forms an angle such that angle < (PI - EPSILON1) or angle > (PI + EPSILON1)
  public boolean LIC2() {
    if((0 <= parameters.EPSILON1) & (parameters.EPSILON1 < PI) & (3 <= numpoints)) {
      double X1, X2, X3, Y1, Y2, Y3;
      double angle;
      for(int i = 0; i < numpoints - 2; i++) {
        X1 = X[i];    Y1 = Y[i];
        X2 = X[i+1];  Y2 = Y[i+1];
        X3 = X[i+2];  Y3 = Y[i+2];
        if(((X1 == X2) & (Y1 == Y2)) | ((X3 == X2) & (Y3 == Y2))) {
          return false;
        } else {
          angle = calculateAngle(X1, X2, X3, Y1, Y2, Y3);
          if((angle > (PI + parameters.EPSILON1) | (angle < (PI - parameters.EPSILON1)))) {
            return true;
          }
        }
      }
    }
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
