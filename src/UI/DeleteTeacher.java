
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;
import BLL.Teacher;
import BUS.TeacherBUS;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 *
 * @author hoaip
 */
public class DeleteTeacher extends TeacherDetails {
    TeacherBUS bus = new TeacherBUS();
    int index;
    DeleteTeacher() {
        
//            index = table.getSelectedRow();
//            System.out.print(index);
//            int option = JOptionPane.showConfirmDialog(null, "Do you want to delete this teacher?");
//            if (option == JOptionPane.YES_OPTION) {
//                int check = bus.xoaTC(ID, index);
//                if(check == 1){ 
//                    JOptionPane.showMessageDialog(null, "Xóa thành công");
//                    setVisible(false);
//                }else{JOptionPane.showMessageDialog(null, "Xóa thất bại");
//                    setVisible(false);
//                }
//            } else {
//                // Xử lý khi người dùng chọn No hoặc Cancel
//            }           

    }
    
    public static void main(String[] args) {
        new DeleteTeacher();
    }
}