import static org.junit.Assert.*;

import org.junit.Test;

public class DecideTest {

  @Test
  public void testLIC0() {
    Decide decide = new Decide();

    // No input points should be false
    assertFalse(decide.LIC0());

    // Testing two points with a distance of sqrt(2).
    decide.numpoints = 2;

    decide.X[0] = 0;
    decide.Y[0] = 0;

    decide.X[1] = 1;
    decide.Y[1] = 1;

    // Should be true because sqrt(2) > 1
    decide.parameters.LENGTH1 = 1;
    assertTrue(decide.LIC0());

    // Should be false because sqrt(2) < 2
    decide.parameters.LENGTH1 = 2;
    assertFalse(decide.LIC0());

    // Adding another point
    decide.numpoints = 3;
    decide.X[2] = 3;
    decide.Y[2] = 1;

    // Should be false because the distance is equal to 2 but not greater than 2
    decide.parameters.LENGTH1 = 2;
    assertFalse(decide.LIC0());

    // But now that we add a consecutive point with a distance of 3...
    decide.numpoints = 4;
    decide.X[2] = 6;
    decide.Y[2] = 1;

    // ...it should be true!
    assertTrue(decide.LIC0());
  }

  @Test
  public void testLIC1() {
    Decide decide = new Decide();
    // No input points
    assertFalse(decide.LIC1());

    decide.parameters.RADIUS1 = 1;
    decide.numpoints = 3;

    // Test with three points that are all the same
    decide.X[0] = 1;
    decide.X[1] = 1;
    decide.X[2] = 1;
    decide.Y[0] = 1;
    decide.Y[1] = 1;
    decide.Y[2] = 1;
    assertFalse(decide.LIC1());

    // Test valid input with radius bigger than 1
    decide.X[0] = -1;
    decide.X[1] = 0;
    decide.X[2] = 1;
    decide.Y[0] = 0;
    decide.Y[1] = 0;
    decide.Y[2] = 1;
    assertTrue(decide.LIC1());

    // ... and lets assume it is on the line
    decide.parameters.RADIUS1 = 1.581139;
    assertFalse(decide.LIC1());

    // Test invalid input
    decide.X[0] = -0.5;
    decide.X[1] = 0;
    decide.X[2] = 0.25;
    decide.Y[0] = 0;
    decide.Y[1] = 0;
    decide.Y[2] = 0.25;
    assertFalse(decide.LIC1());
  }

  @Test
  public void testLIC2() {
    Decide decide = new Decide();

    // No inputs
    assertFalse(decide.LIC2());

    // Test three points
    decide.parameters.EPSILON1 = 4;
    decide.numpoints = 3;
    decide.X[0] = 1;
    decide.Y[0] = 2;
    decide.X[1] = 2;
    decide.Y[1] = 2;
    decide.X[2] = 2;
    decide.Y[2] = 3;
    assertFalse(decide.LIC2());

    // Test four points
    decide.parameters.EPSILON1 = 2;
    decide.numpoints = 4;
    decide.X[0] = 2;
    decide.Y[0] = 2;
    decide.X[1] = 2;
    decide.Y[1] = 2;
    decide.X[2] = 2;
    decide.Y[2] = 3;
    decide.X[3] = 4;
    decide.Y[3] = 4;
    assertFalse(decide.LIC2());

    decide.parameters.EPSILON1 = 0;
    decide.X[0] = 1;
    decide.Y[0] = 2;
    decide.X[1] = 2;
    decide.Y[1] = 2;
    decide.X[2] = 2;
    decide.Y[2] = 3;
    decide.X[3] = 4;
    decide.Y[3] = 4;
    assertTrue(decide.LIC2());

    decide.parameters.EPSILON1 = 0;
    decide.X[0] = 1.24;
    decide.Y[0] = 2.56;
    decide.X[1] = 2.01;
    decide.Y[1] = 2.10;
    decide.X[2] = 2.389;
    decide.Y[2] = 3.2123;
    decide.X[3] = 4.456;
    decide.Y[3] = 5.7960;
    assertTrue(decide.LIC2());

    decide.parameters.EPSILON1 = 1.5;
    assertTrue(decide.LIC2());
  }

  @Test
  public void testLIC3() {
    Decide decide = new Decide();

    // No input points
    assertFalse(decide.LIC3());

    // Test with three points that are all the same
    decide.parameters.AREA1 = 1;
    decide.X[0] = 1;
    decide.X[1] = 1;
    decide.X[2] = 1;
    decide.Y[0] = 1;
    decide.Y[1] = 1;
    decide.Y[2] = 1;
    decide.numpoints = 3;
    assertFalse(decide.LIC3());

    // Test with four points
    decide.parameters.AREA1 = 0.5;
    decide.X[0] = 0;
    decide.X[1] = 1;
    decide.X[2] = 1;
    decide.X[3] = 0;
    decide.Y[0] = 0;
    decide.Y[1] = 1;
    decide.Y[2] = 0;
    decide.Y[3] = 1;
    decide.numpoints = 4;
    assertFalse(decide.LIC3());

    decide.parameters.AREA1 = 0.49;
    assertTrue(decide.LIC3());
  }

  @Test
  public void testLIC4() {
    Decide decide = new Decide();

    // No input points
    assertFalse(decide.LIC4());

    // Test with one point
    decide.parameters.Q_PTS = 1;
    decide.parameters.QUADS = 1;
    decide.numpoints = 1;
    decide.X[0] = 1;
    decide.Y[0] = 1;
    assertFalse(decide.LIC4());

    // Test with three points
    decide.parameters.Q_PTS = 3;
    decide.parameters.QUADS = 3;
    decide.numpoints = 3;
    decide.X[0] = 1;
    decide.Y[0] = 1;
    decide.X[1] = -1;
    decide.Y[1] = 1;
    decide.X[2] = -1;
    decide.Y[2] = -1;
    assertFalse(decide.LIC4());

    // Test with four points
    decide.parameters.QUADS = 2;
    decide.numpoints = 4;
    decide.X[0] = 1;
    decide.Y[0] = 1;
    decide.X[1] = -2;
    decide.Y[1] = 3;
    decide.X[2] = -1;
    decide.Y[2] = -1;
    decide.X[3] = 5;
    decide.Y[3] = 0;
    assertTrue(decide.LIC4());

    decide.X[0] = 1;
    decide.Y[0] = 1;
    decide.X[1] = 2;
    decide.Y[1] = 3;
    decide.X[2] = 1;
    decide.Y[2] = 1;
    decide.X[3] = 5;
    decide.Y[3] = 0;
    assertFalse(decide.LIC4());
  }

  @Test
  public void testLIC5() {
    Decide decide = new Decide();

    // No input points should return false
    assertFalse(decide.LIC5());

    // X[i+1] - X[i] > 0: LIC5 should return false
    decide.numpoints = 2;
    decide.X[0] = 0;
    decide.X[1] = 1;
    decide.Y[0] = 0;
    decide.Y[1] = 1;
    assertFalse(decide.LIC5());

    // X[i+1] - X[i] = 0: LIC5 should return false
    decide.X[0] = 1;
    decide.X[1] = 1;
    decide.Y[0] = 1;
    decide.Y[1] = 1;
    assertFalse(decide.LIC5());

    // X[i+1] - X[i] < 0: LIC5 should return true
    decide.X[0] = 1;
    decide.X[1] = 0;
    decide.Y[0] = 0;
    decide.Y[1] = 1;
    assertTrue(decide.LIC5());

    // Any consecutive points should work
    decide.numpoints = 4;
    decide.X[0] = 1;
    decide.X[1] = 2;
    decide.X[2] = 3;
    decide.X[3] = 0;
    decide.Y[0] = 0;
    decide.Y[1] = 1;
    decide.Y[2] = 1;
    decide.Y[3] = 0;
    assertTrue(decide.LIC5());
  }

  @Test
  public void testLIC6() {
    Decide decide = new Decide();
    decide.numpoints = 3;

    // Need to find 3 consecutive points where at least one point has a distance of >5.5
    // to the line joining the first and last of these points
    decide.parameters.N_PTS = 3;
    decide.parameters.DIST = 5.5;

    // line going through (0,0) and (1,0)
    decide.X[0] = 0;
    decide.Y[0] = 0;
    decide.X[2] = 1;
    decide.Y[2] = 0;
    // point at (0,6) should give a distance >5.5
    decide.X[1] = 0;
    decide.Y[1] = 6;
    assertTrue(decide.LIC6());

    // point at (0,5) should give a distance <5.5
    decide.X[1] = 0;
    decide.Y[1] = 5;
    assertFalse(decide.LIC6());

    // the line joining (0,0) and (1,0) shouldn't continue along the x-axis
    // so a point at (100, 1) should have a distance >5.5
    decide.X[1] = 100;
    decide.Y[1] = 1;
    assertTrue(decide.LIC6());

    // all consecutive points do not need to have a distance >5.5
    // and it can be any N consecutive points among all data points
    decide.numpoints = 5;
    decide.parameters.N_PTS = 4;

    decide.X[0] = 0;
    decide.Y[0] = 0;
    decide.X[1] = 0;
    decide.Y[1] = 0;
    decide.X[2] = 1;
    decide.Y[2] = 0;
    decide.X[3] = 1;
    decide.Y[3] = 0;
    decide.X[4] = 0;
    decide.Y[4] = 1;
    assertFalse(decide.LIC6());
    decide.X[2] = 10;
    decide.Y[2] = 0;
    assertTrue(decide.LIC6());
  }

  @Test
  public void testLIC7() {
    Decide decide = new Decide();

    // No input points
    assertFalse(decide.LIC7());

    // Testing two points with a distance of sqrt(2).
    decide.numpoints = 2;
    decide.parameters.LENGTH1 = 1;
    decide.parameters.K_PTS = 1;

    decide.X[0] = 0;
    decide.Y[0] = 0;

    decide.X[1] = 1;
    decide.Y[1] = 1;

    // Two data points shouldn't be enough
    assertFalse(decide.LIC7());

    decide.numpoints = 3;

    decide.X[2] = 2;
    decide.Y[2] = 2;

    // Should be true because sqrt(2) > 1
    assertTrue(decide.LIC7());

    // But if we se K_PTS to 2 instead
    decide.parameters.K_PTS = 2;

    // ...it should fail!
    assertFalse(decide.LIC7());
  }

  @Test
  public void testLIC8() {
    Decide decide = new Decide();

    // No points
    assertFalse(decide.LIC8());

    decide.parameters.RADIUS1 = 1;
    decide.parameters.A_PTS = 1;
    decide.parameters.B_PTS = 1;

    // All points are the same and at origin
    decide.numpoints = 5;
    assertFalse(decide.LIC8());

    // Test valid input with radius bigger than 1
    decide.X[0] = 0;
    decide.X[1] = 0;
    decide.X[2] = 1;
    decide.X[3] = 0;
    decide.X[4] = 0;
    decide.Y[0] = 0;
    decide.Y[1] = 0;
    decide.Y[2] = 1;
    decide.Y[3] = 0;
    decide.X[4] = -1;
    assertTrue(decide.LIC8());

    // ... and lets assume it is on the line
    decide.parameters.RADIUS1 = 1.581139;
    assertFalse(decide.LIC1());

    // Test with valid input that has a radius < 1
    decide.X[0] = 0;
    decide.X[1] = 0;
    decide.X[2] = 0.25;
    decide.X[3] = 0;
    decide.X[4] = -0.5;
    decide.Y[0] = 0;
    decide.Y[1] = 0;
    decide.Y[2] = 0.25;
    decide.Y[3] = 0;
    decide.X[4] = 0;
    assertFalse(decide.LIC8());
  }

  @Test
  public void testLIC9() {
    Decide decide = new Decide();

    // No inputs
    assertFalse(decide.LIC9());

    // Test with two points
    decide.parameters.EPSILON1 = 1;
    decide.numpoints = 2;
    decide.parameters.D_PTS = 20;
    decide.parameters.C_PTS = 10;
    assertFalse(decide.LIC9());

    // Test with six points
    decide.parameters.D_PTS = 2;
    decide.parameters.C_PTS = 2;
    decide.numpoints = 7;
    decide.X[0] = 1;
    decide.Y[0] = 2;
    decide.X[1] = 5;
    decide.Y[2] = 2;
    decide.X[2] = 5;
    decide.Y[2] = 2;
    decide.X[3] = 2;
    decide.Y[3] = 2;
    decide.X[4] = 1;
    decide.Y[4] = 0;
    decide.X[5] = 2;
    decide.Y[5] = 7;
    decide.X[6] = 2;
    decide.Y[6] = 3;
    assertTrue(decide.LIC9());

    decide.X[0] = 1;
    decide.Y[0] = 1;
    decide.X[3] = 1;
    decide.Y[3] = 1;
    decide.X[6] = 1;
    decide.Y[6] = 1;
    assertFalse(decide.LIC9());

    decide.numpoints = 4;
    assertFalse(decide.LIC9());
  }

  @Test
  public void testLIC10() {
    Decide decide = new Decide();
    decide.parameters.E_PTS = 1;
    decide.parameters.F_PTS = 1;
    // No points
    assertFalse(decide.LIC10());

    // All points are at origin
    decide.numpoints = 5;
    assertFalse(decide.LIC10());

    // Area of point 0, 2, 4 is 0.5
    decide.X[0] = 0;
    decide.X[1] = 1;
    decide.X[2] = 1;
    decide.X[3] = 0;
    decide.X[4] = 0;
    decide.Y[0] = 0;
    decide.Y[1] = 1;
    decide.Y[2] = 1;
    decide.Y[3] = 1;
    decide.X[4] = 1;

    assertTrue(decide.LIC10());
    decide.parameters.AREA1 = 1;
    assertFalse(decide.LIC10());
    decide.parameters.AREA1 = 0.49;
    assertTrue(decide.LIC10());
  }

  @Test
  public void testLIC11() {
    Decide decide = new Decide();
    decide.numpoints = 5;

    // Need to find two points P1, P2  with G_PTS = 3 consecutive intervening points
    // such that P2x - P1x < 0 <=> P2x < P1x
    decide.parameters.G_PTS = 3;

    // X[4] > X[0]: LIC6 should fail
    decide.X[0] = 4;
    decide.X[1] = 0;
    decide.X[2] = 1;
    decide.X[3] = 2;
    decide.X[4] = 4.1;
    decide.Y[0] = 4;
    decide.Y[1] = 0;
    decide.Y[2] = 1;
    decide.Y[3] = 2;
    decide.Y[4] = -1;
    assertFalse(decide.LIC11());

    // X[4] < X[0]: LIC6 should pass
    decide.X[0] = -3.8;
    decide.X[1] = 0;
    decide.X[2] = 1;
    decide.X[3] = 2;
    decide.X[4] = -3.9;
    decide.Y[0] = 4;
    decide.Y[1] = 0;
    decide.Y[2] = 1;
    decide.Y[3] = 2;
    decide.Y[4] = -1;
    assertTrue(decide.LIC11());

    // X[4] = X[0]: LIC6 should fail
    decide.X[0] = 4;
    decide.X[1] = 0;
    decide.X[2] = 1;
    decide.X[3] = 2;
    decide.X[4] = 4;
    decide.Y[0] = 4;
    decide.Y[1] = 0;
    decide.Y[2] = 1;
    decide.Y[3] = 2;
    decide.Y[4] = -1;
    assertFalse(decide.LIC11());

    // any points separated by G_PTS fulfilling P2x < P1x should do
    decide.numpoints = 7;
    decide.X[0] = 4;
    decide.X[1] = 0;
    decide.X[2] = 1;
    decide.X[3] = 2;
    decide.X[4] = 5;
    decide.X[5] = -1;
    decide.X[6] = 5;
    decide.Y[0] = 4;
    decide.Y[1] = 0;
    decide.Y[2] = 1;
    decide.Y[3] = 2;
    decide.Y[4] = -1;
    decide.Y[5] = 4;
    decide.Y[6] = 0;
    assertTrue(decide.LIC11());
  }

  @Test
  public void testLIC12() {
    Decide decide = new Decide();

    // No input points
    assertFalse(decide.LIC12());

    // Testing two points with a distance of sqrt(2).
    decide.numpoints = 2;
    decide.parameters.LENGTH1 = 3;
    decide.parameters.LENGTH2 = 10;
    decide.parameters.K_PTS = 1;

    decide.X[0] = 0;
    decide.Y[0] = 0;

    decide.X[1] = 1;
    decide.Y[1] = 1;

    // Two data points shouldn't be enough
    assertFalse(decide.LIC12());

    decide.numpoints = 3;

    decide.X[2] = 2;
    decide.Y[2] = 2;

    // Should be false because both conditions must hold
    assertFalse(decide.LIC12());

    decide.parameters.LENGTH1 = 2;

    // Should be true now that both conditions hold
    assertTrue(decide.LIC12());
  }

  @Test
  public void testLIC13() {
    Decide decide = new Decide();

    // No points
    assertFalse(decide.LIC13());

    decide.parameters.RADIUS1 = 1;
    decide.parameters.RADIUS2 = 2;
    decide.parameters.A_PTS = 1;
    decide.parameters.B_PTS = 1;

    // All points are the same and at origin
    decide.numpoints = 5;
    assertFalse(decide.LIC13());

    // Test valid input with radius bigger than 1 and smaller than 2
    decide.X[0] = 0;
    decide.X[1] = 0;
    decide.X[2] = 1;
    decide.X[3] = 0;
    decide.X[4] = 0;
    decide.Y[0] = 0;
    decide.Y[1] = 0;
    decide.Y[2] = 1;
    decide.Y[3] = 0;
    decide.X[4] = -1;
    assertTrue(decide.LIC13());

    decide.parameters.RADIUS2 = 1.581138830084;
    assertTrue(decide.LIC13());
  }

  @Test
  public void testLIC14() {
    Decide decide = new Decide();
    decide.parameters.E_PTS = 1;
    decide.parameters.F_PTS = 1;
    decide.numpoints = 5;

    // Area of point 0, 2, 4 is 0.5
    decide.X[0] = 0;
    decide.Y[0] = 0;
    decide.X[1] = 1;
    decide.Y[1] = 1;
    decide.X[2] = 1;
    decide.Y[2] = 1;
    decide.X[3] = 0;
    decide.Y[3] = 1;
    decide.X[4] = 0;
    decide.X[4] = 1;

    decide.parameters.AREA1 = 0.49;
    decide.parameters.AREA2 = 0.5;
    assertFalse(decide.LIC14());

    decide.parameters.AREA2 = 0.51;
    assertTrue(decide.LIC14());
  }

  @Test
  public void testCalculatePUM() {
    Decide decide = new Decide();
    decide.CMV[3] = true;

    // Test a requirement where only LIC3 must be true
    for (int i = 0; i < 15; i++) {
      for (int j = 0; j < 15; j++) {
        if (i == 3 || j == 3) {
          decide.LCM[i][j] = Decide.CONNECTORS.ORR;
        } else {
          decide.LCM[i][j] = Decide.CONNECTORS.NOTUSED;
        }
      }
    }

    decide.calculatePUM();

    for (int i = 0; i < 15; i++) {
      for (int j = 0; j < 15; j++) {
        assertTrue(decide.PUM[i][j]);
      }
    }

    // Test a requirement where all conditions must be true
    for (int i = 0; i < 15; i++) {
      decide.CMV[i] = true;
      for (int j = 0; j < 15; j++) {
        decide.LCM[i][j] = Decide.CONNECTORS.ANDD;
      }
    }

    decide.calculatePUM();

    for (int i = 0; i < 15; i++) {
      for (int j = 0; j < 15; j++) {
        assertTrue(decide.PUM[i][j]);
      }
    }

    // Setting all conditions to false
    for (int i = 0; i < 15; i++) {
      decide.CMV[i] = false;
    }

    decide.calculatePUM();

    for (int i = 0; i < 15; i++) {
      for (int j = 0; j < 15; j++) {
        assertFalse(decide.PUM[i][j]);
      }
    }
  }

  @Test
  public void testCalculateFUV() {
    Decide decide = new Decide();
    // PUV and PUM are default initialised to false
    // False PUV should yield all true values in FUV
    decide.calculateFUV();
    for (int i = 0; i < 15; i++) {
      assertTrue(decide.FUV[i]);
    }

    // True PUV[i] and all false PUM[i][x]: false FUV[i]
    decide.PUV[3] = true;
    decide.calculateFUV();
    assertTrue(decide.FUV[2]);
    assertFalse(decide.FUV[3]);
    assertTrue(decide.FUV[4]);

    // True PUV[i] and 1 false PUM[i][x]: false FUV[i]
    for (int i = 0; i < 15; i++) {
      for (int j = 0; j < 15; j++) {
        decide.PUM[i][j] = true;
      }
    }
    decide.PUM[3][6] = false;
    decide.calculateFUV();
    assertTrue(decide.FUV[2]);
    assertFalse(decide.FUV[3]);
    assertTrue(decide.FUV[4]);

    // True PUV[i] and all true PUM[i][x]: true FUV[i]
    decide.PUM[3][6] = true;
    decide.calculateFUV();
    for (int i = 0; i < 15; i++) {
      assertTrue(decide.FUV[i]);
    }
  }

  @Test
  public void testCheckFUV() {
    Decide decide = new Decide();

    // All true values in FUV: CheckFUV() should return true
    for (int i = 0; i < 15; i++) {
      decide.FUV[i] = true;
    }
    assertTrue(decide.checkFUV());

    // Some false value in FUV: CheckFUV() should return false
    decide.FUV[4] = false;
    assertFalse(decide.checkFUV());

    // All false values in FUV: CheckFUV() should return false
    for (int i = 0; i < 15; i++) {
      decide.FUV[i] = false;
    }
    assertFalse(decide.checkFUV());
  }
}
