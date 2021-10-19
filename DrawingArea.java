//Gautham Sathish - Recursion Assigment Part B

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

class Point2D {//Creates class Point2D that updates value of coordinates

    double x;//Creates double x
    double y;//Creates double y

    Point2D(double x, double y) {//Method with paramters of double x and y
        this.x = x;//Set this x to the previous x
        this.y = y;//Set this y to the previous y
    }
}

public class DrawingArea extends javax.swing.JPanel {

    Timer t1;//Creates timer
    static public ArrayList<Point2D> coords;//Initiates global ArrayList coords
    static public int i = -1;//Sets i to -1(this variable sees to whether the start button has been pushed or not)
    String bounceSound = "boing.wav";//Sets bounceSound to the sound file
    SoundPlayer theSound = new SoundPlayer();//Creates a SoundPlayer
    static int wallDistance = 638;//Global variable of the distance of the wall
    static int speed;

    public static void main(String[] args) {
        
        /*Part 3/4 
        ArrayList<Point2D>finalCoordinates = coordinates(wallDistance,new ArrayList<Point2D>(), 0, 20);
        for(Point2D p: finalCoordinates){
            System.out.println("("+p.x+","+p.y+")");
        }
         */

        /*Part 5
        ArrayList<Point2D> finalCoordinates = coordinates(wallDistance, new ArrayList<Point2D>(), 0, 20, 0.2);//Call method
        */
    }

    /**
     * Creates new form DrawingArea
     */
    public DrawingArea() {
        initComponents();
        t1 = new Timer(speed, new DrawingArea.TimerListener());
        t1.start();
    }

    //PART 1
    /**
     * 
     * @param pixels
     * @return 
     */
    public static int wallDistance(int pixels) {//wallDistance method which calculates how much distance till the wall
        if (pixels == 0) {//if pixels =0
            return 0;//return 0
        }
        System.out.println(pixels / 2);//Divide pixels by 2
        return wallDistance(pixels / 2);//Return wallDistance method with pixels divided by 2 as method
    }

    //PART 2
    /**
     * 
     * @param originalDistance
     * @param x
     * @param numberOfPointsPerJump
     * @return 
     */ 
    /*
    public static int coordinates(double originalDistance, double x, int numberOfPointsPerJump){//coordinates method that calculates points for parabola with 20 x-values between 0 and the current distance 
        if(x==(originalDistance/2)){//Base case
            return 0;
        }
        System.out.println(-1*(x-0)*(x-(originalDistance/2)));//Y value
        //System.out.println("x"+x);
        x = x + ((originalDistance/2)/numberOfPointsPerJump);//Update x value
        return coordinates(originalDistance, x, numberOfPointsPerJump);//return value
    }
    */ 
    
    //Part 3
    /**
     * 
     * @param originalDistance
     * @param coords
     * @param x
     * @param numberOfPointsPerJump
     * @return 
     */
    /*
    public static ArrayList<Point2D> coordinates(double originalDistance, ArrayList<Point2D>coords, double x, int numberOfPointsPerJump) {//Receives the current remaining distances and an ArrayList returns the current ArrayList of coordinates  for the current parabola interval for the current remaining distance.
        if (x == (originalDistance / 2)) {//Base case
            return coords;
        }
        x = x + ((originalDistance / 2) / numberOfPointsPerJump);//Update x value
        ArrayList<Point2D>array = coordinates(originalDistance,coords, x, numberOfPointsPerJump);//Set array ArrayList to coordinates 
        Point2D point = new Point2D(x,(-1 * (x - 0) * (x - (originalDistance / 2))));//Add point
        array.add(0, point);//Add new point to array
        return array;//return the array
    }
    */ 
    
    //Part 5
    /**
     * 
     * @param originalDistance
     * @param coords
     * @param xCoordinate
     * @param numberOfPointsPerJump
     * @param percentDistanceJumped
     * @return 
     */
    public static ArrayList<Point2D> coordinates(double originalDistance, ArrayList<Point2D> coords, double xCoordinate, int numberOfPointsPerJump, double percentDistanceJumped) {
        if (xCoordinate > (wallDistance - 38) - 1) {
            return coords;
        } else if (xCoordinate >= ((wallDistance - 38) - originalDistance * percentDistanceJumped)) {//Base case
            originalDistance *= percentDistanceJumped;
        }
        xCoordinate = xCoordinate + ((originalDistance * percentDistanceJumped) / numberOfPointsPerJump);
        Point2D point = new Point2D(xCoordinate, (-0.01 * (xCoordinate - ((wallDistance - 38) - originalDistance)) * (xCoordinate - ((wallDistance - 38) - (originalDistance * percentDistanceJumped)))));
        
        //Part 5 Printing
        //System.out.println("("+point.x+","+point.y+")");

        /*Part 6
        for (int i = 0; i <= point.y; i++)//Dividing based on how low the scale should be("Ask what the scale should be?")//Counts through the y values
        {
            System.out.print(".");//Prints a dot per a value of 1 in the y
        }
                
        System.out.println("");
         */
        
        ArrayList<Point2D> array = coordinates(originalDistance, coords, xCoordinate, numberOfPointsPerJump, percentDistanceJumped);
        array.add(0, point);
        return array;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        ///*Part 8
        //To make sure Part 8 operates, make sure part 7 is commented out and g.drawImage(img, 0, 400, this);, and g.drawImage(img, (int)coords.get(i).x, 400-(int)coords.get(i).y, this); are not commented out
        Image img = Toolkit.getDefaultToolkit().getImage("BugsBunny.png");//Imports character
        Image bkg = Toolkit.getDefaultToolkit().getImage("Background.png");//Imports background
        g.drawImage(bkg, 0, 0, this);//Draws background

        if (i == -1) {//If the start button has not been pushed
            g.drawImage(img, 0, 400, this);//Keep the image in its starting location

        } else {//If start has been pushed
            g.drawImage(img, (int)coords.get(i).x, 400-(int)coords.get(i).y, this);//Change the image location to the points of the ArrayList

            /*Part 7
            //To make part 7 operate lines comment out super.paintComponent(g);, g.drawImage(bkg, 0, 0, this);, and g.drawImage(img, 0, 400, this);
            for(int u = 0; u<coords.size()-1;u++){//For loop that counts to the size of the ArrayList
                g.drawOval((int)coords.get(u).x, 400-(int)coords.get(u).y, 10, 10);//Prints circle at each location
            }
             */
            
            if (400 - (int) coords.get(i).y == 398 || i == 0) {//If the location of the y value is greater than 398 or if i is equal to 0
                theSound.play(bounceSound);//Play the sound
            }
            i++;//Set i back to 0
            if (i == coords.size()) {//If i is equal to the size of the ArrayList
                i = -1;//Set i back to -1
            }
        }
        
        g.setColor(Color.MAGENTA);//Sets wall color to magenta
        g.drawLine(wallDistance, 20, wallDistance, 700);//Draw the wall
        Stroke stroke = new BasicStroke(7f);//Set thickness
        
    }

    private class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            repaint();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 583, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
