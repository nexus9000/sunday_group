package edu.itstep.runapp;


import java.io.Serializable;



public class Student extends Person implements Serializable,Comparable {


    private long facNum;
    public Student(String name, String lastName, int age, long facNum){
        super(name,lastName,age);
        this.facNum = facNum;

   }

    public long getFacNum() {
        return facNum;
    }

    public void setFacNum(long facNum) {
        this.facNum = facNum;
    }

    @Override
    public int compareTo(Object s) {
        return ((Student) s ).getAge()- this.getAge();
    }

    @Override
    public String toString() {
        return "Student{" + super.toString()+
                "facNum=" + facNum +
                '}';
    }
}
