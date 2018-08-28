/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nasofx;

/**
 *
 * @author AMW Design PC 2
 */
public class Measurement {
    
    public double posStart,posEnd,startSecond,endSecond,startY;
    
    
    void setLastSecond(double LastSecond) {
       endSecond=LastSecond; //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setStartSecond(double StartSecond) {
       startSecond=StartSecond;// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     double getLastSecond() {
       return endSecond; //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    double getStartSecond() {
       return startSecond;// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
