import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class DecideTest {

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

}
