import java.util.*;

public class Main {
  private Parameters parameters;
  private double[] X = new double[100];
  private double[] Y = new double[100];
  private int numpoints = 0;
  private CONNECTORS[][] LICM = new CONNECTORS[15][15];
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
  public void caLICulateCMV() {
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

  public void caLICulatePUM() {
    // Matrix operations between CMV and LICM to get PUM
  }

  public void caLICulateFUV() {
    // PUM (***) PUV -> FUV
  }

  public boolean checkFUV() {
    // Check if all values are true,
    return false;
  }

  public void decide() {
    caLICulateCMV();
    caLICulatePUM();
    caLICulateFUV();

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