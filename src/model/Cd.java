package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import javafx.stage.FileChooser;
import model.Structures.TreeAVL;
import model.Structures.TreeBST;
import model.Structures.TreeRBT;
import model.Structures.FileUpload;

public class Cd implements Serializable {

    int totalEmployees = 0;
    private TreeRBT<String, Employee> RBTEmployeeName;
    private TreeAVL<Integer, Employee> AVLEmployeeCode;
    private TreeBST<String, Employee> BSTEmployeeLastName;
    private TreeRBT<String, Employee> RBTEmployeeFullName;
    private static final long serialVersionUID = 1;

    public Cd() {
        RBTEmployeeName = new TreeRBT<>();
        RBTEmployeeFullName = new TreeRBT<>();
        BSTEmployeeLastName = new TreeBST<>();
        AVLEmployeeCode = new TreeAVL<>();
    }

    public File fileChooser() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Open File Client");
        return fc.showOpenDialog(null);
    }

    public boolean importData(model.Cd fb, int amount) {
        try {
            String names = fileChooser().getAbsolutePath();
            String lastNames = fileChooser().getAbsolutePath();
            BufferedReader br = new BufferedReader(new FileReader(names));
            BufferedReader rb = new BufferedReader(new FileReader(lastNames));
            FileUpload fup = new FileUpload(br, rb, fb, amount);
            fup.start();
            int num = 0;
            while (fup.isAlive()) {
                num++;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(int totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public TreeRBT<String, Employee> getRBTEmployeeName() {
        return RBTEmployeeName;
    }

    public void setRBTEmployeeName(TreeRBT<String, Employee> BSTEmployeeName) {
        this.RBTEmployeeName = BSTEmployeeName;
    }

    public TreeAVL<Integer, Employee> getAVLEmployeeCode() {
        return AVLEmployeeCode;
    }

    public void setAVLEmployeeCode(TreeAVL<Integer, Employee> RBTEmployeeCode) {
        this.AVLEmployeeCode = RBTEmployeeCode;
    }

    public TreeBST<String, Employee> getBSTEmployeeLastName() {
        return BSTEmployeeLastName;
    }

    public void setBSTEmployeeLastName(TreeBST<String, Employee> BSTEmployeeLastName) {
        this.BSTEmployeeLastName = BSTEmployeeLastName;
    }

    public TreeRBT<String, Employee> getRBTEmployeeFullName() {
        return RBTEmployeeFullName;
    }

    public void setRBTEmployeeFullName(TreeRBT<String, Employee> AVLPersonFullName) {
        this.RBTEmployeeFullName = RBTEmployeeFullName;
    }

    public void addEmployee(Employee employee) {
        setTotalEmployees(getTotalEmployees() + 1);
        getRBTEmployeeFullName().insert(employee.getFn(), employee);
        getBSTEmployeeLastName().insert(employee.getLn(), employee);
        getRBTEmployeeName().insert(employee.getName(), employee);
        getAVLEmployeeCode().insert(employee.getCode(), employee);
    }

    public void removePerson(Employee person) {
        getAVLEmployeeCode().delete(person.getCode());
        getRBTEmployeeName().remove(person.getName());
        getBSTEmployeeLastName().delete(person.getLn());
        getRBTEmployeeFullName().remove(person.getFn());
        setTotalEmployees(getTotalEmployees() - 1);
    }

}

