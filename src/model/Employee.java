package model;

import java.io.*;

public class Employee implements Serializable {

    private int code;
    private String fn;
    private String name;
    private String ln;
    private String sex;
    private String bd;
    private String height;
    private String nationality;

    public Employee(int code, String name, String ln, String sex, String bd, String height, String nationality) {
        this.code = code;
        this.name = name;
        this.ln = ln;
        this.sex = sex;
        this.bd = bd;
        this.height = height;
        this.nationality = nationality;
        this.fn = name + " " + ln;
    }


    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLn() {
        return ln;
    }

    public void setLn(String ln) {
        this.ln = ln;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

}
