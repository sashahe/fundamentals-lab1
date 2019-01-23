import java.util.*;

public class Main {
  private Parameters parameters;
  private double[] X = new double[100];
  private double[] Y = new double[100];
  private int numpoints = 0;
  private CONNECTORS[][] LCM = new CONNECTORS[15][15];
  private boolean[] PUV = new boolean[15];

  private boolean[] CVM = new boolean[15];
  private boolean[][] PUM = new boolean[15][15];
  private boolean[] FUV = new boolean[15];

  private boolean launch = false;

  private static final double PI = 3.1415926535;

  private enum CONNECTORS {
    NOTUSED, ORR, ANDD
  };

  private enum COMPTYPE {
    LT, EQ, GQ
  };

  // Returns true if LC0 is true
  public boolean LC0() {
    return false;
  }

  // Returns true if LC1 is true
  public boolean LC1() {
    return false;
  }

  // Returns true if LC2 is true
  public boolean LC2() {
    return false;
  }

  // Returns true if LC3 is true
  public boolean LC3() {
    return false;
  }

  // Returns true if LC4 is true
  public boolean LC4() {
    return false;
  }

  // Returns true if LC5 is true
  public boolean LC5() {
    return false;
  }

  // Returns true if LC6 is true
  public boolean LC6() {
    return false;
  }

  // Returns true if LC7 is true
  public boolean LC7() {
    return false;
  }

  // Returns true if LC8 is true
  public boolean LC8() {
    return false;
  }

  // Returns true if LC9 is true
  public boolean LC9() {
    return false;
  }

  // Returns true if LC10 is true
  public boolean LC10() {
    return false;
  }

  // Returns true if LC11 is true
  public boolean LC11() {
    return false;
  }

  // Returns true if LC12 is true
  public boolean LC12() {
    return false;
  }

  // Returns true if LC13 is true
  public boolean LC13() {
    return false;
  }

  // Returns true if LC14 is true
  public boolean LC14() {
    return false;
  }

  // Set CMV[i] = true if LC i == true
  public void calculateCMV() {
    CMV[0] = LC0();
    CMV[1] = LC1();
    CMV[2] = LC2();
    CMV[3] = LC3();
    CMV[4] = LC4();
    CMV[5] = LC5();
    CMV[6] = LC6();
    CMV[7] = LC7();
    CMV[8] = LC8();
    CMV[9] = LC9();
    CMV[10] = LC10();
    CMV[11] = LC11();
    CMV[12] = LC12();
    CMV[13] = LC13();
    CMV[14] = LC14();
  };

  public void calculatePUM() {
    // Matrix operations between CMV and LCM to get PUM
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

  public Main() {
    this.parameters = new Parameters();
    decide();
  }

  public static void main(String args[]) {
    Main main = new Main();
  }

  private COMPTYPE doubleCompare(double a, double b) {
    if (Math.abs(a - b) < 0.000001)
      return COMPTYPE.EQ;
    if (a < b)
      return COMPTYPE.LT;
    return COMPTYPE.GT;
  }
}