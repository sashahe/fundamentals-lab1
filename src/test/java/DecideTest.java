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
  public void testLIC10(){
    Decide decide = new Decide();
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
}