import static org.junit.Assert.*;
import org.junit.Before;
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
  public void testLIC2() {
    Decide decide = new Decide();

    //No inputs
    assertFalse(decide.LIC2());

    //Test three points
    decide.parameters.EPSILON1 = 4;
    decide.numpoints = 3;
    decide.X[0] = 1; decide.Y[0] = 2;
    decide.X[1] = 2; decide.Y[1] = 2;
    decide.X[2] = 2; decide.Y[2] = 3;
    assertFalse(decide.LIC2());

    decide.parameters.EPSILON1 = -1;
    assertFalse(decide.LIC2());

    //Test four points
    decide.parameters.EPSILON1 = 2;
    decide.numpoints = 4;
    decide.X[0] = 2; decide.Y[0] = 2;
    decide.X[1] = 2; decide.Y[1] = 2;
    decide.X[2] = 2; decide.Y[2] = 3;
    decide.X[3] = 4; decide.Y[3] = 4;
    assertFalse(decide.LIC2());

    decide.parameters.EPSILON1 = 0;
    decide.X[0] = 1; decide.Y[0] = 2;
    decide.X[1] = 2; decide.Y[1] = 2;
    decide.X[2] = 2; decide.Y[2] = 3;
    decide.X[3] = 4; decide.Y[3] = 4;
    assertTrue(decide.LIC2());

    decide.parameters.EPSILON1 = 0;
    decide.X[0] = 1.24; decide.Y[0] = 2.56;
    decide.X[1] = 2.01; decide.Y[1] = 2.10;
    decide.X[2] = 2.389; decide.Y[2] = 3.2123;
    decide.X[3] = 4.456; decide.Y[3] = 5.7960;
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
    decide.X[0] = 1; decide.X[1] = 1; decide.X[2] = 1;
    decide.Y[0] = 1; decide.Y[1] = 1; decide.Y[2] = 1;
    decide.numpoints = 3;
    assertFalse(decide.LIC3());

    // // Test with four points
    decide.parameters.AREA1 = 0.5;
    decide.X[0] = 0; decide.X[1] = 1; decide.X[2] = 1; decide.X[3] = 0;
    decide.Y[0] = 0; decide.Y[1] = 1; decide.Y[2] = 0; decide.Y[3] = 1;
    decide.numpoints = 4;
    assertFalse(decide.LIC3());

    decide.parameters.AREA1 = 0.49;
    assertTrue(decide.LIC3());
  }

  @Test
  public void testLIC5() {
    Decide decide = new Decide();

    // No input points should return false
    assertFalse(decide.LIC5());

    // X[i+1] - X[i] > 0: LIC5 should return false
    decide.numpoints = 2;
    decide.X[0] = 0; decide.X[1] = 1;
    decide.Y[0] = 0; decide.Y[1] = 1;
    assertFalse(decide.LIC5());

    // X[i+1] - X[i] = 0: LIC5 should return false
    decide.X[0] = 1; decide.X[1] = 1;
    decide.Y[0] = 1; decide.Y[1] = 1;
    assertFalse(decide.LIC5());

    // X[i+1] - X[i] < 0: LIC5 should return true
    decide.X[0] = 1; decide.X[1] = 0;
    decide.Y[0] = 0; decide.Y[1] = 1;
    assertTrue(decide.LIC5());

    // Any consecutive points should work
    decide.numpoints = 4;
    decide.X[0] = 1; decide.X[1] = 2; decide.X[2] = 3; decide.X[3] = 0;
    decide.Y[0] = 0; decide.Y[1] = 1; decide.Y[2] = 1; decide.Y[3] = 0;
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
    decide.X[0] = 0; decide.Y[0] = 0;
    decide.X[2] = 1; decide.Y[2] = 0;
    // point at (0,6) should give a distance >5.5
    decide.X[1] = 0; decide.Y[1] = 6;
    assertTrue(decide.LIC6());

    // point at (0,5) should give a distance <5.5
    decide.X[1] = 0; decide.Y[1] = 5;
    assertFalse(decide.LIC6());

    // the line joining (0,0) and (1,0) shouldn't continue along the x-axis 
    // so a point at (100, 1) should have a distance >5.5
    decide.X[1] = 100; decide.Y[1] = 1;
    assertTrue(decide.LIC6());

    // all consecutive points do not need to have a distance >5.5
    // and it can be any N consecutive points among all data points
    decide.numpoints = 5;
    decide.parameters.N_PTS = 4;

    decide.X[0] = 0; decide.Y[0] = 0;
    decide.X[1] = 0; decide.Y[1] = 0;
    decide.X[2] = 1; decide.Y[2] = 0;
    decide.X[3] = 1; decide.Y[3] = 0;
    decide.X[4] = 0; decide.Y[4] = 1;
    assertFalse(decide.LIC6());
    decide.X[2] = 10; decide.Y[2] = 0;
    assertTrue(decide.LIC6());
  }

  @Test
  public void testLIC10(){
    Decide decide = new Decide();
    decide.parameters.E_PTS = 1;
    decide.parameters.F_PTS = 1;
    // No points
    assertFalse(decide.LIC10());

    // All points are at origin
    decide.numpoints = 5;
    assertFalse(decide.LIC10());

    // Area of point 0, 2, 4 is 0.5
    decide.X[0] = 0; decide.X[1] = 1; decide.X[2] = 1; decide.X[3] = 0; decide.X[4] = 0;
    decide.Y[0] = 0; decide.Y[1] = 1; decide.Y[2] = 1; decide.Y[3] = 1; decide.X[4] = 1;
  
    assertTrue(decide.LIC10());
    decide.parameters.AREA1 = 1;
    assertFalse(decide.LIC10());
    decide.parameters.AREA1 = 0.49;
    assertTrue(decide.LIC10());
  }

  @Test
  public void testLIC14() {
    Decide decide = new Decide();
    decide.parameters.E_PTS = 1;
    decide.parameters.F_PTS = 1;
    decide.numpoints = 5;

    // rea of point 0, 2, 4 is 0.5
    decide.X[0] = 0; decide.Y[0] = 0;
    decide.X[1] = 1; decide.Y[1] = 1;
    decide.X[2] = 1; decide.Y[2] = 1;
    decide.X[3] = 0; decide.Y[3] = 1;
    decide.X[4] = 0; decide.X[4] = 1;

    decide.parameters.AREA1 = 0.49;
    decide.parameters.AREA2 = 0.5;
    assertFalse(decide.LIC14());

    decide.parameters.AREA2 = 0.51;
    assertTrue(decide.LIC14());
  }
}
