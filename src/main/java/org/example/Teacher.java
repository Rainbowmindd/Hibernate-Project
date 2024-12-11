package org.example;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Teacher implements Comparable<Teacher> {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String surname;

    @Enumerated(EnumType.STRING)
    private TeacherCondition condition;

    private int birthYear;
    private double salary;

    public Teacher() {}

    public Teacher(String name, String surname, TeacherCondition condition, int birthYear, double salary) {
        this.name = name;
        this.surname = surname;
        this.condition = condition;
        this.birthYear = birthYear;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Teacher info: \n"+
                "name= " + name + "\n" +
                "surname='" + surname + "\n" +
                "condition=" + condition +
                "\nbirth year=" + birthYear +
                "\nsalary=" + salary;
    }
    void printing(){
        System.out.println("Teacher info: ");
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Condition: " + condition);
        System.out.println("Birth Year: " + birthYear);
        System.out.println("Salary: " + salary);
        System.out.println("\n");
    }

    @Override
    public int compareTo(Teacher teacher) {
        return this.surname.compareTo(teacher.surname);
    }

    // Getters and setters
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public double getSalary() { return salary; }
    public TeacherCondition getCondition() { return condition; }
    public void setSalary(double salary) { this.salary = salary; }
    public void setCondition(TeacherCondition condition) { this.condition = condition; }




}
