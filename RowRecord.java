/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplication9;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;

public class RowRecord extends Process {

    public JFXButton delBTN = new JFXButton("Delete");
     private ObservableList<RowRecord> data;

    public RowRecord(int processName, int arrivalTime, int burstTime) {
        super(processName, arrivalTime, burstTime);
        btnDecoration();
    }

    public JFXButton getDelBTN() {
        return delBTN;
    }

    public void setDelBTN(JFXButton delBTN) {
        this.delBTN = delBTN;
    }

    public final void btnDecoration() {
        delBTN.setStyle("-fx-background-color:#95ccff");
        delBTN.setCursor(Cursor.HAND);
                // Set the action event handler for the delete button
                        delBTN.setOnAction((e) -> {
            data.remove(this); // Remove the current row from the data list
        });

    }
}
