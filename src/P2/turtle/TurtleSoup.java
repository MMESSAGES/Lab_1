/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P2.turtle;

import java.util.*;
public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
        for(int i=0;i < 4; i++){
            turtle.forward(sideLength);
            turtle.turn(90);
        }
//        throw new RuntimeException("implement me!");
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
        return (sides - 2)*180.0/sides;
//        throw new RuntimeException("implement me!");
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        double angles = TurtleSoup.calculateRegularPolygonAngle(sides);
        angles = 180 - angles;
        for(int i=0;i < sides; i++){
            turtle.forward(sideLength);
            turtle.turn(angles);
        }
//        throw new RuntimeException("implement me!");
    }

    /**
     * Given the current direction, current location, and a target location, calculate the Bearing
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentBearing. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentBearing current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to Bearing (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY,
                                                 int targetX, int targetY) {
        double angle = Math.atan2(targetX - currentX, targetY - currentY)*180/Math.PI - currentBearing;
        if(angle < 0)
            angle += 360;
        return angle;
//        throw new RuntimeException("implement me!");
    }

    /**
     * Given a sequence of points, calculate the Bearing adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateBearingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of Bearing adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords) {
        int length = xCoords.size();
        double angle;
        double bearings = 0;
        List<Double> angles = new ArrayList<>();
        for(int i=1; i < length; i++){
            angle = calculateBearingToPoint(bearings, xCoords.get(i-1), yCoords.get(i-1), xCoords.get(i), yCoords.get(i));
            angles.add(angle);
            bearings += angle;
            if (bearings > 360)
                bearings %= 360;
        }

        return angles;
//        throw new RuntimeException("implement me!");
    }
    
    /**
     * Given a set of points, compute the convex hull, the smallest convex set that contains all the points 
     * in a set of input points. The gift-wrapping algorithm is one simple approach to this problem, and 
     * there are other algorithms too.
     * 
     * @param points a set of points with xCoords and yCoords. It might be empty, contain only 1 point, two points or more.
     * @return minimal subset of the input points that form the vertices of the perimeter of the convex hull
     */
    public static Set<Point> convexHull(Set<Point> points){
        if(points.size() <= 3){
            return points;
        }//异常警告（
        Point lu = null;
        Set<Point> chpoints = new HashSet<>();

        for(Point p:points){
            if(lu == null){
                lu = p;
                continue;
            }
            if(lu.x() > p.x()){
                lu = p;
            }else if(lu.x() == p.x()){
                if(lu.y() < p.y())
                    lu = p;
            }//左上点
        }chpoints.add(lu);

        Point c_points = lu;
        Point ch_points = null;
        double angle, mini_angle, l_angles = 0;
        while(true){
            mini_angle = 360.0;
            for(Point p:points){

                if(p == c_points)
                    continue;
                angle = calculateBearingToPoint(l_angles, (int)c_points.x(), (int)c_points.y(), (int)p.x(), (int)p.y());
                if(angle < mini_angle){
                    ch_points = p;
                    mini_angle = angle;
                }else if(angle == mini_angle && ch_points != null){
                    double d0 = p.x()- c_points.x();
                    double d1 = p.y()- c_points.y();
                    double dx1 = d0*d0 + d1*d1;
                    d0 = ch_points.x() - c_points.x();
                    d1 = ch_points.y() - c_points.y();
                    double dx2 = d0*d0 + d1*d1;
                    if(dx1 > dx2){
                        ch_points = p;
                    }
                }

            }
            if(chpoints.contains(ch_points)){
                break;
            }else{
                chpoints.add(ch_points);
                c_points = ch_points;
                l_angles += mini_angle;
            }
        }
        return chpoints;


//        throw new RuntimeException("implement me!");
    }
    
    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
            int Size = 200, Step = 1, ColorNum = 2;
            for (int i = 1; i <= Size; i++) {
                turtle.forward( i);
                switch (i % ColorNum) {
                    case 0:
                        turtle.color(PenColor.CYAN);
                        break;
                    case 1:
                        turtle.color(PenColor.PINK);
                        break;
                }
                turtle.forward(Step * i);
                turtle.turn(175);
            }

//        throw new RuntimeException("implement me!");
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String[] args){
        DrawableTurtle turtle = new DrawableTurtle();
        int sides = 6;
        int sideLength = 40;

       drawSquare(turtle, sideLength);
        drawRegularPolygon(turtle,sides,sideLength);

        List<Integer> xCoords = Arrays.asList(0, 0, 1, 1);
        List<Integer> yCoords = Arrays.asList(1, 0, 0, 1);
        List<Double> angles = calculateBearings(xCoords, yCoords);
        System.out.println(angles);

        Set<Point> points = new HashSet<>();
        Point p1 = new Point(0, 0);
        Point p2 = new Point(2, 0);
        Point p3 = new Point(2, 2);
        Point p4 = new Point(2, -2);
        Point p5 = new Point(4, 1);
        Point p6 = new Point(4, 4);
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        points.add(p5);
        points.add(p6);
        for(Point p:points){
            System.out.print((int)p.x());
            System.out.print(' ');
            System.out.println((int)p.y());
        }
        Set<Point> ch ;
        ch = convexHull(points);
        for(Point p:ch){
            System.out.print((int)p.x());
            System.out.print(' ');
            System.out.println((int)p.y());
        }

        // draw the window
        drawPersonalArt(turtle);
        turtle.draw();
    }
}
