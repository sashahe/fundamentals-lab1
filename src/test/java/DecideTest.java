import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class DecideTest {

  @ Test
  public void testLIC1() {
    Decide d = new Decide();
    
    // No input points
    assertFalse(d.LIC1());

    d.parameters.RADIUS1 = 1;
    d.numpoints = 3;

    // Test with three points that are all the same
    d.X[0] = 1; d.X[1] = 1; d.X[2] = 1;
    d.Y[0] = 1; d.Y[1] = 1; d.Y[2] = 1;
    assertFalse(d.LIC1());  

    // Test valid input
    d.X[0] = -1; d.X[1] = 0; d.X[2] = 1;
    d.Y[0] = 0;  d.Y[1] = 0; d.Y[2] = 1;
    assertTrue(d.LIC1());

    // Test invalid input
    d.X[0] = -0.5; d.X[1] = 0; d.X[2] = 0.25;
    d.Y[0] = 0;    d.Y[1] = 0; d.Y[2] = 0.25;
    assertFalse(d.LIC1());
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