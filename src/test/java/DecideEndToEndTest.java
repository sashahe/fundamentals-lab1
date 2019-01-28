import static org.junit.Assert.*;

import java.io.*;
import org.junit.*;

public class DecideEndToEndTest {

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;

  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @After
  public void restoreStreams() {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  // Test with three coordinates should result in "no" if we require that all
  // LICS are true.

  @Test
  public void testDecide1() {
    Decide d = new Decide();

    d.parameters.LENGTH1 = 0.5;
    d.parameters.RADIUS1 = 0.1;
    d.parameters.EPSILON1 = 1;
    d.parameters.AREA1 = 1;
    d.parameters.Q_PTS = 2;
    d.parameters.QUADS = 2;
    d.parameters.DIST = 1;
    d.parameters.N_PTS = 3;
    d.parameters.K_PTS = 1;
    d.parameters.A_PTS = 1;
    d.parameters.B_PTS = 1;
    d.parameters.C_PTS = 1;
    d.parameters.D_PTS = 1;
    d.parameters.E_PTS = 1;
    d.parameters.F_PTS = 1;
    d.parameters.G_PTS = 1;
    d.parameters.LENGTH2 = 0.5;
    d.parameters.RADIUS2 = 1;
    d.parameters.AREA2 = 1;

    d.numpoints = 3;

    d.X[0] = 0;
    d.Y[0] = 0;

    d.X[1] = 1;
    d.Y[1] = 1;

    d.X[2] = 2;
    d.Y[2] = 2;

    assertTrue(d.LIC0());
    assertTrue(d.LIC1());
    assertTrue(d.LIC2());
    assertFalse(d.LIC3()); // False: Area = 0, required = 1
    assertFalse(d.LIC4()); // False: All points in one quadrand, required = 2
    assertFalse(d.LIC5()); // False: Coordinates are increasing
    assertFalse(d.LIC6()); // False: all points are on one line
    assertTrue(d.LIC7()); // True: Lenght = 0.5, distance between coords = 2
    assertFalse(d.LIC8()); // False: numpoints = 3, required 5
    assertFalse(d.LIC9()); // False: numpoints = 3, required 5
    assertFalse(d.LIC10()); // False: numpoints = 3, required 5
    assertFalse(d.LIC11()); // False: distance between points is increasing
    assertFalse(d.LIC12()); // False: Lenght2 = 0.5, required > 2
    assertFalse(d.LIC13()); // False: numpoints = 3, required 5
    assertFalse(d.LIC14()); // False: numpoints = 3, required 5

    // Test a requirement where all conditions must be true
    for (int i = 0; i < 15; i++) {
      for (int j = 0; j < 15; j++) {
        d.LCM[i][j] = Decide.CONNECTORS.ANDD;
      }
    }

    for (int i = 0; i < 15; i++) {
      d.PUV[i] = true;
    }

    d.decide();

    assertEquals("no", outContent.toString().trim());
  }

  @Test
  public void testDecide2() {
    Decide d = new Decide();

    d.parameters.LENGTH1 = 0.5;
    d.parameters.RADIUS1 = 0.1;
    d.parameters.EPSILON1 = 1;
    d.parameters.AREA1 = 1;
    d.parameters.Q_PTS = 2;
    d.parameters.QUADS = 2;
    d.parameters.DIST = 1;
    d.parameters.N_PTS = 3;
    d.parameters.K_PTS = 1;
    d.parameters.A_PTS = 1;
    d.parameters.B_PTS = 1;
    d.parameters.C_PTS = 1;
    d.parameters.D_PTS = 1;
    d.parameters.E_PTS = 1;
    d.parameters.F_PTS = 1;
    d.parameters.G_PTS = 1;
    d.parameters.LENGTH2 = 0.5;
    d.parameters.RADIUS2 = 1;
    d.parameters.AREA2 = 1;

    d.numpoints = 3;

    d.X[0] = 0;
    d.Y[0] = 0;

    d.X[1] = 1;
    d.Y[1] = 1;

    d.X[2] = 2;
    d.Y[2] = 2;

    assertTrue(d.LIC0());
    assertTrue(d.LIC1());
    assertTrue(d.LIC2());
    assertFalse(d.LIC3()); // False: Area = 0, required = 1
    assertFalse(d.LIC4()); // False: All points in one quadrand, required = 2
    assertFalse(d.LIC5()); // False: Coordinates are increasing
    assertFalse(d.LIC6()); // False: all points are on one line
    assertTrue(d.LIC7()); // True: Lenght = 0.5, distance between coords = 2
    assertFalse(d.LIC8()); // False: numpoints = 3, required 5
    assertFalse(d.LIC9()); // False: numpoints = 3, required 5
    assertFalse(d.LIC10()); // False: numpoints = 3, required 5
    assertFalse(d.LIC11()); // False: distance between points is increasing
    assertFalse(d.LIC12()); // False: Lenght2 = 0.5, required > 2
    assertFalse(d.LIC13()); // False: numpoints = 3, required 5
    assertFalse(d.LIC14()); // False: numpoints = 3, required 5

    // Test a requirement where all conditions must be true
    for (int i = 0; i < 15; i++) {
      for (int j = 0; j < 15; j++) {
        if ((i < 3 || i == 7) && (j < 3 || j == 7)) {
          d.LCM[i][j] = Decide.CONNECTORS.ANDD;
        } else {
          d.LCM[i][j] = Decide.CONNECTORS.NOTUSED;
        }
      }
    }

    for (int i = 0; i < 15; i++) {
      d.PUV[i] = true;
    }

    d.decide();
    assertEquals("yes", outContent.toString().trim());
  }

  @Test
  public void testDecide3() {
    Decide d = new Decide();

    d.parameters.LENGTH1 = 0.5;
    d.parameters.RADIUS1 = 0.1;
    d.parameters.EPSILON1 = 1;
    d.parameters.AREA1 = 1;
    d.parameters.Q_PTS = 2;
    d.parameters.QUADS = 2;
    d.parameters.DIST = 1;
    d.parameters.N_PTS = 3;
    d.parameters.K_PTS = 1;
    d.parameters.A_PTS = 1;
    d.parameters.B_PTS = 1;
    d.parameters.C_PTS = 1;
    d.parameters.D_PTS = 1;
    d.parameters.E_PTS = 1;
    d.parameters.F_PTS = 1;
    d.parameters.G_PTS = 1;
    d.parameters.LENGTH2 = 0.5;
    d.parameters.RADIUS2 = 1;
    d.parameters.AREA2 = 1;

    d.numpoints = 3;

    d.X[0] = 0;
    d.Y[0] = 0;

    d.X[1] = 1;
    d.Y[1] = 1;

    d.X[2] = 2;
    d.Y[2] = 2;

    assertTrue(d.LIC0());
    assertTrue(d.LIC1());
    assertTrue(d.LIC2());
    assertFalse(d.LIC3()); // False: Area = 0, required = 1
    assertFalse(d.LIC4()); // False: All points in one quadrand, required = 2
    assertFalse(d.LIC5()); // False: Coordinates are increasing
    assertFalse(d.LIC6()); // False: all points are on one line
    assertTrue(d.LIC7()); // True: Lenght = 0.5, distance between coords = 2
    assertFalse(d.LIC8()); // False: numpoints = 3, required 5
    assertFalse(d.LIC9()); // False: numpoints = 3, required 5
    assertFalse(d.LIC10()); // False: numpoints = 3, required 5
    assertFalse(d.LIC11()); // False: distance between points is increasing
    assertFalse(d.LIC12()); // False: Lenght2 = 0.5, required > 2
    assertFalse(d.LIC13()); // False: numpoints = 3, required 5
    assertFalse(d.LIC14()); // False: numpoints = 3, required 5

    // Test a requirement where all conditions must be true
    for (int i = 0; i < 15; i++) {
      for (int j = 0; j < 15; j++) {
        if ((i < 3 || i == 7) || (j < 3 || j == 7)) {
          d.LCM[i][j] = Decide.CONNECTORS.ORR;
        } else {
          d.LCM[i][j] = Decide.CONNECTORS.ANDD;
        }
      }
    }

    for (int i = 0; i < 15; i++) {
      if ((i < 3 || i == 7)) {
        d.PUV[i] = true;
      } else {
        d.PUV[i] = false;
      }
    }

    d.decide();

    assertEquals("yes", outContent.toString().trim());
  }
}
