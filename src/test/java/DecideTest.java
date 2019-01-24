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
  public void testCalculatePUM() {
    Decide decide = new Decide();
    decide.CMV[3] = true;

    // Test a requirement where only LIC3 must be true
    for (int i = 0; i < 15; i++) {
      for (int j =0; j < 15; j++) {
        if (i  == 3 || j == 3) {
          decide.LCM[i][j] = Decide.CONNECTORS.ORR;
        } else {
         decide.LCM[i][j] = Decide.CONNECTORS.NOTUSED;
        }
      }
    }

    decide.calculatePUM();

    for (int i = 0; i < 15; i++) {
      for (int j =0; j < 15; j++) {
        assertTrue(decide.PUM[i][j]);
      }
    }

    // Test a requirement where everything must be true
    for (int i = 0; i < 15; i++) {
      decide.CMV[i] = true;
      for (int j =0; j < 15; j++) {
        decide.LCM[i][j] = Decide.CONNECTORS.ANDD;
      }
    }

    decide.calculatePUM();

    // All CMVs are false
    for (int i = 0; i < 15; i++) {
       decide.CMV[i] = false;
    }

    decide.calculatePUM();

    for (int i = 0; i < 15; i++) {
      for (int j =0; j < 15; j++) {
        assertFalse(decide.PUM[i][j]);
      }
    }
  }
}