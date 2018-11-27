/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Polygone;

/**
 *
 * @author Yan Revuz
 */
public class Line {
    
    private Point firstPoint;
    private Point lastPoint;
    
    public Line(Point firstPoint,Point lastPoint){
        this.firstPoint = firstPoint;
        this.lastPoint = lastPoint;
    }

    public Point getFirstPoint() {
        return firstPoint;
    }

    public void setFirstPoint(Point firstPoint) {
        this.firstPoint = firstPoint;
    }

    public Point getLastPoint() {
        return lastPoint;
    }

    public void setLastPoint(Point lastPoint) {
        this.lastPoint = lastPoint;
    }
    
    
}
