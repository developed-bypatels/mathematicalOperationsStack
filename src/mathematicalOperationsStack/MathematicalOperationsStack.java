/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathematicalOperationsStack;

import java.util.Scanner;

public class MathematicalOperationsStack {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        // Queue class is inititalized which only accpts MathematicalOperations Class objects
        MQueue<MathematicalOperations> queue = new MQueue<>();
        
        // Stack class is initialized for doing undo on successfull operations
        MStack<MathematicalOperations> undoStack = new MStack<>();
        
        // Stack class is initialized for doing redo on followed undo operation
        MStack<MathematicalOperations> redoStack = new MStack<>();
        
        // Scanner class is initialized for input
        Scanner scan = new Scanner(System.in);
        
        // variable is declared to store the total of value after successful operations
        double finalValue = 0;

        // Menu provided on the output screen
        System.out.println("Calculator using Queues and Stacks ... by _______________\n"
                + "Enter tokens. Legal tokens are integers, +, -, *, /, U[ndo], R[edo], E[valuate] and [e]X[it]");

        // while loop is called with never ending condition
        while (true) {
            
            // prompt the user to enter the operation and value to carry out calculations
            String input = scan.nextLine();

            // if user enters data as per these regular expression then the operation and value is stored into queue
            if (input.matches("[+-/*]{1}[' '][0-9]*\\.?[0-9]*")) 
            {
                // user input is splitted from between and stored into the variable
                String[] splitInput = input.split(" ");
                
                // MathematicalOperations class is declared with operation and value as a argument
                MathematicalOperations queuedoperations = new MathematicalOperations(splitInput[0].charAt(0), Double.parseDouble(splitInput[1]));
                
                // object created above is then passed into queue
                queue.enqueue(queuedoperations);
            }
            
            // if the user want to undo or redo operations
            else if (input.matches("^[UR]$"))
            {
                // MathematicalOperations class is declared with operation and value as a argument
                MathematicalOperations queuedoperations = new MathematicalOperations(input.charAt(0), 0.0);
                
                // object created above is then passed into queue
                queue.enqueue(queuedoperations);
            }
            
            // if user enters the "E" for evaluation
            else if ("E".equals(input))
            {
                // Calculation method is called with refrence of queue, undostack, redeostack, finalValue as a agrument to solve the operation and number 
                Calculate(queue, undoStack, redoStack, finalValue);
                
                // after the problem evaluted the final value is again initialized to 0 for next problem
                finalValue = 0;
                
                // empties queue for the next problem
                queue.removeAll();
                
                // empties undostack
                undoStack.removeAll();
                
                // empties reedostack
                redoStack.removeAll();
            }
            
            // if user enters "X" for exit
            else if ("X".equals(input))
            {
                // problem entered by the user is once evaluated
                Calculate(queue, undoStack, redoStack, finalValue);
                
                // while loop safely ends and the program is ended
                break;
            }
            
            // to handle the bad input by the user
            else
            {
                // MathematicalOperations class is declared with no argument
                MathematicalOperations error = new MathematicalOperations();
                
                // object created above is then passed into queue to give neat output to the user 
                queue.enqueue(error);
            }
        }
    }

    // Method for calculating the operation and value entered by the user with refrence of queue, undo stack, redo stack and finalValue
    public static void Calculate(MQueue<MathematicalOperations> queue, MStack<MathematicalOperations> undo, MStack<MathematicalOperations> redo, double finalValue) {

        // loops runs until queue is emptied
        while (!queue.isEmpty()) {
            
            // controlling the operators in order to perfrom right task
            switch (queue.peek().operand) {
                
                // if the operand is '+'
                case '+':
                    // then the method evaluateOperators is called with all refreneces and with '+' operator
                    finalValue = evaluateOperators(finalValue, queue, undo, redo, '+');
                    break;
                // if the operand is '-'
                case '-':
                    // then the method evaluateOperators is called with all refreneces and with '-' operator
                    finalValue = evaluateOperators(finalValue, queue, undo, redo, '-');
                    break;
                // if the operand is '+'
                case '*':
                    // then the method evaluateOperators is called with all refreneces and with '-' operator
                    finalValue = evaluateOperators(finalValue, queue, undo, redo, '*');
                    break;
                // if the operand is '/'
                case '/':
                    // then the method evaluateOperators is called with all refreneces and with '/' operator
                    finalValue = evaluateOperators(finalValue, queue, undo, redo, '/');
                    break;
                // if the user wants to undo last operation
                case 'U':
                    // then undo method is called with refreneces
                    finalValue = undoMethod(undo, finalValue, redo, queue);
                    break;
                // if the user wants to redo last undo operation
                case 'R':
                    // then redo method is called with refrences 
                    finalValue = redoMethod(redo, finalValue, undo, queue);
                    break;
                // bad input by user is handled here
                case 'e':
                    // statement is printed sefely to let the user knoe about the entered operation had bad parameters
                    System.out.println("ERROR --> Invalid Token - line ignored");
                    // and the operation is removed from the queue
                    queue.dequeue();
                    break;
                // to handle if any of the operator is not executed
                default:
                    break;
            }

        }
    }

    // Method for redo to retrieve last undo operation with refrences of queue, undo stack and redo stack
    public static double redoMethod(MStack<MathematicalOperations> redo, double finalValue, MStack<MathematicalOperations> undo, MQueue<MathematicalOperations> queue) {

        // enters if redo is not empty
        if (redo.isEmpty()) 
        {
            // handles safely call to redo printing statement
            System.out.println("ERROR --> Redo is empty - Can't Redo");
            
            // removes the last element on queue
            queue.dequeue();
        }
        
        // if the redo stack is not empty
        else
        {
            // controlling the operators in order to perfrom right task
            switch (redo.peek().operand) {
                // if the operand is '+'
                case '+':
                    // then the method evaluateRedo is called with all refreneces and with '+' operator
                    finalValue = evaluateRedo(finalValue, redo, undo, queue, '+');
                    break;
                // if the operand is '-'
                case '-':
                    // then the method evaluateRedo is called with all refreneces and with '-' operator
                    finalValue = evaluateRedo(finalValue, redo, undo, queue, '-');
                    break;
                // if the operand is '*'
                case '*':
                    // then the method evaluateRedo is called with all refreneces and with '*' operator
                    finalValue = evaluateRedo(finalValue, redo, undo, queue, '*');
                    break;
                // if the operand is '/'
                case '/':
                    // then the method evaluateRedo is called with all refreneces and with '/' operator
                    finalValue = evaluateRedo(finalValue, redo, undo, queue, '/');
                    break;
                // to handle if any of the operator is not executed
                default:
                    break;
            }
        }
        // returns with updated final value
        return finalValue;
    }

    // Method for carrying out REDO operation with refrences of queue, undo stack, redo stack , operator
    public static double evaluateRedo(double finalValue, MStack<MathematicalOperations> redo, MStack<MathematicalOperations> undo, MQueue<MathematicalOperations> queue, char operator) {

        // controlling the operators in order to perfrom right task
        switch (operator) {
            // if the operand is '+'
            case '+':
                // addition is carried out on the final value and added with number entered by the user
                finalValue += redo.peek().digits;
                break;
            // if the operand is '-'
            case '-':
                // substration is carried out on the final value and added with number entered by the user
                finalValue -= redo.peek().digits;
                break;
            // if the operand is '*'
            case '*':
                // multiplication is carried out on the final value and added with number entered by the user
                finalValue *= redo.peek().digits;
                break;
            // if the operand is '/'
            case '/':
                // division is carried out on the final value and added with number entered by the user
                finalValue /= redo.peek().digits;
                break;
            // to handle if any of the operator is not executed
            default:
                break;
        }
        
        // output for redo operation and final value
        System.out.printf("R" + "\nTotal : %.1f \n", finalValue);
        // element which was returned to the queue by redo is stored in undo stack
        undo.push(redo.peek());
        // after successfully operating redo operation last element in redo stack is removed
        redo.pop();
        // element is then deleted from queue after implementing that operation
        queue.dequeue();
        // final value is then returned
        return finalValue;
    }

    // Method for undo to remove the last operation carried out on final value from queue is inititated with refrence of unod stack, redo stack, finalValue, queue
    public static double undoMethod(MStack<MathematicalOperations> undo, double finalValue, MStack<MathematicalOperations> redo, MQueue<MathematicalOperations> queue) {

        // condiiton checks it the undo stack is empty
        if (undo.isEmpty())
        {
            // call to empty undo stack is handled safely and neat print statement is printed
            System.out.println("ERROR --> Undo is empty - Can't Undo");
            // operation is removed from the queue after succesfully handled
            queue.dequeue();
        }
        // if the undo stack is not empty
        else 
        {
            // controlling the operators in order to perfrom right task
            switch (undo.peek().operand) {
                // if the operand is '+'
                case '+':
                    // undo is carried out on the final value
                    finalValue = evaluateUndo(finalValue, undo, redo, queue, '+');
                    break;
                // if the operand is '-'
                case '-':
                    // undo is carried out on the final value
                    finalValue = evaluateUndo(finalValue, undo, redo, queue, '-');
                    break;
                // if the operand is '-'
                case '*':
                    // undo is carried out on the final value
                    finalValue = evaluateUndo(finalValue, undo, redo, queue, '*');
                    break;
                // if the operand is '/'
                case '/':
                    // undo is carried out on the final value
                    finalValue = evaluateUndo(finalValue, undo, redo, queue, '/');
                    break;
                
                // to handle if any of the operator is not executed
                default:
                    break;
            }
        }
        // final value is then returned
        return finalValue;
    }

    // Method for carrying out UNDO operation with refrences of queue, undo stack, redo stack , operator
    public static double evaluateUndo(double finalValue, MStack<MathematicalOperations> undo, MStack<MathematicalOperations> redo, MQueue<MathematicalOperations> queue, char operator) {

        // controlling the operators in order to perfrom right task
        switch (operator) 
        {
            // if the operand is '+'
            case '+':
                // substraction is carried out on the final value with number entered by the user
                finalValue -= undo.peek().digits;
                break;
            // if the operand is '-'
            case '-':
                // addition is carried out on the final value with number entered by the user
                finalValue += undo.peek().digits;
                break;
            // if the operand is '*'
            case '*':
                // division is carried out on the final value with number entered by the user
                finalValue /= undo.peek().digits;
                break;
            // if the operand is '/'
            case '/':
                // multiplication is carried out on the final value with number entered by the user
                finalValue *= undo.peek().digits;
                break;
                
            // to handle if any of the operator is not executed
            default:
                break;
        }
        // output for undo operation and final value
        System.out.printf('U' + "\nTotal : %.1f \n", finalValue);
        // element which was undo so the element is stored into redo Stack
        redo.push(undo.peek());
        // after successfully operating undo operation last element in undo stack is removed
        undo.pop();
        // element is then deleted from queue after implementing that operation
        queue.dequeue();
        // final value is then returned
        return finalValue;
    }

    // Method for evaluating operators with refrence of queue, undo stack, redo stack, final value
    public static double evaluateOperators(double finalValue, MQueue<MathematicalOperations> queue, MStack<MathematicalOperations> undo, MStack<MathematicalOperations> redo, char operator) {
        
        // controlling the operators in order to perfrom right task
        switch (operator) {
            
            // if the operand is '+'
            case '+':
                // addition is carried out on the final value with number entered by the user
                finalValue += queue.peek().digits;
                break;
            // if the operand is '-'
            case '-':
                // subtraction is carried out on the final value with number entered by the user
                finalValue -= queue.peek().digits;
                break;
            // if the operand is '+'
            case '*':
                // multiplication is carried out on the final value with number entered by the user
                finalValue *= queue.peek().digits;
                break;
           // if the operand is '/'
            case '/':
                // division is carried out on the final value with number entered by the user
                finalValue /= queue.peek().digits;
                break;
                
            // to handle if any of the operator is not executed
            default:
                break;
        }

        // output for evaluating operation called by overridng method toString() and final value
        System.out.printf(queue.peek().toString() + "\nTotal : %.1f \n", finalValue);
        // element is added at last in undo stack to carry out undo on the last element entered into the queue
        undo.push(queue.peek());
        // element is then deleted from queue after implementing that operation
        queue.dequeue();
        // removes all the redo stacks when any element is added into the queue to control redo safe
        redo.removeAll();
        // final value is then returned
        return finalValue;
    }
}
