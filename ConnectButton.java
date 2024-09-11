
import javafx.scene.control.Button;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 *
 */
public class ConnectButton extends Button{
    private int row;
    private int column;
    
    /**
     * Assigns each variable to its value using the super class button to assign the label
     * @param label specifies the string to be represented by the button
     * @param row the row of the button
     * @param column the column of the button
     */
    public ConnectButton(String label, int row, int column){
        super(label);
        this.column = column;
        this.row = row;        
    }

    /**
     *
     * @return returns the row of the button
     */
    public int getRow(){
        return this.row;
    }

    /**
     *
     * @return returns the column of the button
     */
    public int getColumn(){
        return this.column;
    }
    /*
    * Returns a String represntation of the row and column of the button.
    */
    @Override
    public String toString(){
        return "(" + this.getRow() + " , " + this.getColumn() + " )";
    }
}
