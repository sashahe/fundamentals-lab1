import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class DecideTest {
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

  @ Test
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
}
