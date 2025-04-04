//Text based paint program that Your prograwill allow the user to enter a series of drawing commands to “paint” text onto a virtual canvas and then display the result of that painting in the output console. 
import java.util.Scanner;

class Paint {
  public static void main(String[] args) {
    Scanner scnr = new Scanner(System.in);

    System.out.println("Welcome to TextPaint!");
    System.out.println("Available commands are:");
    System.out.println("  COLOR c");
    System.out.println("  POINT x1 y1");
    System.out.println("  HLINE x1 y1 len");
    System.out.println("  VLINE x1 y1 len");
    System.out.println("  BOX x1 y1 x2 y2");
    System.out.println("  EXIT");
    System.out.println("What size canvas would you like?");

    int theValue = scnr.nextInt(); // user input for canvas size
    final char ERASER = ' ';

    int i;
    int j;
    char[][] canvas = new char[theValue][theValue]; // Initialize the canvas to ERASER value so that it is an empty
                                                    // canvas
    //Time: O(n^2) Space:O(n^2)
    for (i = 0; i < theValue; ++i) {
      for (j = 0; j < theValue; ++j) {
        canvas[i][j] = ERASER;
      }
    }

    // Initializations
    String userCommand = ("None");
    int x = 0; // X value for Point
    int y = 0; // Y value for Point
    int hX = 0; // X value for Hline
    int hY = 0; // Y value for Hline
    int hLength = 0; // Length for Hline
    int vX = 0; // X value for Vline
    int vY = 0; // Y value for Vline
    int vLength = 0; // Length value for Vline
    int bX1 = 0;// First X value for Box
    int bY1 = 0;// First Y value for Box
    int bX2 = 0;// Second X value for Box
    int bY2 = 0;// Second Y value for Box
    char theColor = 'X';// Default Color

    
    while (!userCommand.equals("EXIT")) { // Checks what command the user calls for and loops for user to write command
                                          // until they write EXIT
      System.out.println("Enter the next drawing command:");
      userCommand = scnr.next();
      if (userCommand.equals("COLOR")) {
        theColor = scnr.next().charAt(0); // Takes in user char or paint color
        if (theColor == 'E') {
          theColor = ERASER;
        }
      } 
      else if (userCommand.equals("POINT")) {
        x = scnr.nextInt();
        y = scnr.nextInt();
        canvas = Point(canvas, x, y, theColor); // Calls POINT command and then alters the canvas if Point method is
                                                // called
      } 
      else if (userCommand.equals("HLINE")) {
        hX = scnr.nextInt();
        hY = scnr.nextInt();
        hLength = scnr.nextInt();
        canvas = Hline(canvas, hX, hY, hLength, theColor); // Calls HLINE command and then alters the canvas if Hline
                                                           // method is called
      } 
      else if (userCommand.equals("VLINE")) {
        vX = scnr.nextInt();
        vY = scnr.nextInt();
        vLength = scnr.nextInt();
        canvas = Vline(canvas, vX, vY, vLength, theColor); // Calls VLINE command and then alters the canvas if Vline
                                                           // method is called
      } 
      else if (userCommand.equals("BOX")) {
        bX1 = scnr.nextInt();
        bY1 = scnr.nextInt();
        bX2 = scnr.nextInt();
        bY2 = scnr.nextInt();
        canvas = Box(canvas, bX1, bY1, bX2, bY2, theColor); // Calls BOX command and then alters the canvas if Box
                                                            // method is called
      }

    }
    System.out.println("Here is your painting:");
    printCanvas(canvas); // Prints out canvas after exit and based on user input
    System.out.println("Goodbye!");
  }

  
  public static char[][] drawPoint(char[][] canvas, int x, int y, char userColor) { // Method draws a point at potition
                                                                                    // x, y unless there is already a +
    char permanent = '+';
    if (canvas[x][y] != permanent) {
      canvas[x][y] = userColor;
    }
    return canvas;
  }
  
  
  public static char[][] Hline(char[][] hCanvas, int x, int y, int length, char currentColor) { // Returns the altered
                                                                                                // canvas
    for (int j = 0; j < length; ++j) {
      drawPoint(hCanvas, y, x, currentColor);// Calls drawPoint to check if elements in canvas array is equal to +HLI
      x++;
    }
    return hCanvas;
  }

  
  public static char[][] Vline(char[][] vCanvas, int x, int y, int length, char currentColor) { // Returns the altered
                                                                                                // canvas
                                                                                                /*
                                                                                                 * int i; i = y; int j; j = x
                                                                                                 */
    for (int i = 0; i < length; ++i) {
      drawPoint(vCanvas, y, x, currentColor);
      ++y;
    }
    return vCanvas;
  }
  
  
  //Fills box with color starting from (x1,y1) and stops at (x2,y2)

  public static char[][] Box(char[][] bCanvas, int x1, int y1, int x2, int y2, char theCurrentColor) { 
    int i;
    int j;
    if (x2 < x1 || y2 < y1) {
      System.out.println("x2 must be greater than or equal to x1 and y2 must be greater than or equal to y1");
      return bCanvas;
    }
    for (i = y1; i <= y2; ++i) {
      for (j = x1; j <= x2; ++j) {
        drawPoint(bCanvas, i, j, theCurrentColor); // Calls drawPoint to check if elements in canvas array is equal to +
      }
    }
    return bCanvas;
  }

  
  // Returns canvas painted at coordinate x,y
  public static char[][] Point(char[][] pCanvas, int x, int y, char yourColor) { 
    drawPoint(pCanvas, y, x, yourColor); 
    return pCanvas;
  }

  
  // Takes in the altered canvas and prints it out to main
  public static void printCanvas(char[][] paintedCanvas) { 
    int i;
    int j;
    for (i = 0; i < paintedCanvas.length; ++i) {
      for (j = 0; j < paintedCanvas.length; ++j) {
        System.out.print(paintedCanvas[i][j]);
      }
      System.out.println();
    }
  }
}
