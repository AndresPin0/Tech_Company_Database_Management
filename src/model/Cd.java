package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import javafx.stage.FileChooser;
import model.Structures.TreeAVL;
import model.Structures.TreeBST;
import model.Structures.TreeRBT;
import threads.FileUpload;

public class Cd implements Serializable {

    int totalPeople = 0;
    private TreeRBT<String, Employee> RBTPersonName;
    private TreeAVL<Integer, Employee> AVLPersonCode;
    private TreeBST<String, Employee> BSTPersonLastName;
    private TreeRBT<String, Employee> RBTPersonFullName;
    private static final long serialVersionUID = 1;

    public Cd() {
        RBTPersonName = new TreeRBT<>();
        RBTPersonFullName = new TreeRBT<>();
        BSTPersonLastName = new TreeBST<>();
        AVLPersonCode = new TreeAVL<>();
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
            BufferedReader br1 = new BufferedReader(new FileReader(names));
            BufferedReader br2 = new BufferedReader(new FileReader(lastNames));
            FileUpload fup = new FileUpload(br1, br2, fb, amount);
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

    public int getTotalPeople() {
        return totalPeople;
    }

    public void setTotalPeople(int totalPeople) {
        this.totalPeople = totalPeople;
    }

    public RBT<String, Person> getRBTPersonName() {
        return RBTPersonName;
    }

    public void setRBTPersonName(RBT<String, Person> BSTPersonName) {
        this.RBTPersonName = BSTPersonName;
    }

    public AVL<Integer, Person> getAVLPersonCode() {
        return AVLPersonCode;
    }

    public void setAVLPersonCode(AVL<Integer, Person> RBTPersonCode) {
        this.AVLPersonCode = RBTPersonCode;
    }

    public BST<String, Person> getBSTPersonLastName() {
        return BSTPersonLastName;
    }

    public void setBSTPersonLastName(BST<String, Person> BSTPersonLastName) {
        this.BSTPersonLastName = BSTPersonLastName;
    }

    public RBT<String, Person> getRBTPersonFullName() {
        return RBTPersonFullName;
    }

    public void setRBTPersonFullName(RBT<String, Person> AVLPersonFullName) {
        this.RBTPersonFullName = RBTPersonFullName;
    }

    public void addEmployee(Employee employee) {
        setTotalPeople(getTotalPeople() + 1);
        getRBTPersonFullName().insert(employee.getFullName(), employee);
        getBSTPersonLastName().insert(employee.getLastName(), employee);
        getRBTPersonName().insert(employee.getName(), employee);
        getAVLPersonCode().insert(employee.getCode(), employee);
    }

    public void removePerson(Employee person) {
        getAVLPersonCode().delete(person.getCode());
        getRBTPersonName().remove(person.getName());
        getBSTPersonLastName().delete(person.getLastName());
        getRBTPersonFullName().remove(person.getFullName());
        setTotalPeople(getTotalPeople() - 1);
    }

}

