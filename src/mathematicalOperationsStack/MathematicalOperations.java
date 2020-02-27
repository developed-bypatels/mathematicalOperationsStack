/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathematicalOperationsStack;

/**
 *
 * @author I am Prerak Patel and my student number is 000736376 and I haven't shared my program
 */
public class MathematicalOperations {

    // public class variable is created so that we can use it directly from the static main context
    public final char operand;      // variable for storing operand
    public final double digits;     // variable for storing value for calculation

    // default constructor with no arguments to handle error
    MathematicalOperations() {
        this.operand = 'e';
        this.digits = 0;
    }

    // constructor overloaded with two arguments accepting operator and value
    MathematicalOperations(char operator, double value) {
        this.operand = operator;
        this.digits = value;
    }

    // Overriding toString method to make the program and ouptut neat and clean
    @Override
    public String toString() {
        return this.operand + " " + this.digits;
    }

}
