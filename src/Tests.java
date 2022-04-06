import java.util.ArrayList;

import geometric.*;
import lsystem.*;
import window.Cylinder3D;
import window.Sphere3D;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.geometry.Point3D;
public class Tests {
    public static void main(String[] args) {
        // Test 1
        Point p1 = new Point(0, 0, 0);
        Point p2 = new Point(1, 1, 1);
        Point3D p3 = new Point3D(0, 0, 0);
        Point3D p4 = new Point3D(1, 1, 1);
        if(!p1.equals(p2)) {
            System.out.println("Test Point: Passed");
        } else {
            System.out.println("Test 1: Failed");
        }
        // Test 2
        Line l1 = new Line(1, p1, 42, 23);
        Line l2 = new Line(2, p2, 42, 23);
        if(!l1.equals(l2)) {
            System.out.println("Test Line: Passed");
        } else {
            System.out.println("Test Line: Failed");
        }
        
        //Test 3
        String good1 = "F+F+[-F+F][-F+F]";
        String good2 = "FFFFF";
        String bad1 = "[F-F";
        String bad2 = "F+F[F-F]]";
        Parser parser1 = new Parser(good1, 12, 23);
        Parser parser2 = new Parser(good2,12,21);
        Parser parser3 = new Parser(bad1,12,12);
        Parser parser4 = new Parser(bad2,12,12);
        if(parser1.isValid() && parser2.isValid() && !parser3.isValid() && !parser4.isValid()) {
            System.out.println("Test analyse string: Passed");
        } else {
            System.out.println("Test analyse string: Failed");
        }

        //Test 4 
        Rule rule1 = new Rule("F","FF");
        String expectedResult = "FFFF+FFFF+[-FFFF+FFFF][-FFFF+FFFF]";
        ArrayList<Rule> rules = new ArrayList<>();
        rules.add(rule1);
        Rewrite rewrite1 = new Rewrite(rules, good1);
        String res = rewrite1.replacement(2);
        if(expectedResult.equals(res)) {
            System.out.println("Test rewrite : Passed");
        } else {
            System.out.println("Test rewrite : Failed");
        }
        
    }
}