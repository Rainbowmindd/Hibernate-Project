package org.example;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Collections;

@Entity
public class ClassTeacher {

    @Id
    @GeneratedValue
    private int id;

    private String teacherGroupName;
    private int maxTeacherNumber;

    @ManyToOne
    @JoinColumn(name = "class_container_id")
    private ClassContainer classContainer;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "class_teacher_id")
    private List<Teacher> listTeachers;

    public ClassTeacher(String teacherGroupName, int maxTeacherNumber) {
        this.teacherGroupName = teacherGroupName;
        this.maxTeacherNumber = maxTeacherNumber;
        this.listTeachers = new ArrayList<>();
    }

    public void addTeacher(Teacher teacher) {
        if (listTeachers.size() >= maxTeacherNumber) {
            System.out.println("GROUP IS FULL, cannot add new teacher");
            return;
        }


        Comparator<Teacher> teacherComparator = Comparator
                .comparing(Teacher::getSurname) //jesli takie same then
                .thenComparing(Teacher::getName); //porownywanie imion

        boolean exists = listTeachers.stream()
                .anyMatch(t -> teacherComparator.compare(t, teacher) == 0);

        if (exists) {
            System.out.println("This teacher ALREADY EXISTS in this group, please try another teacher");
        } else {
            listTeachers.add(teacher);
            System.out.println("Teacher added successfully");
        }
    }

    public void addSalary(Teacher teacher, double bonus ){
        teacher.setSalary(teacher.getSalary() + bonus);

        System.out.println("CONGRATULATIONS! YOU'VE EARNED A BONUS TO YOUR SALARY! Current salary: "+teacher.getSalary());

    }

    public void removeTeacher(Teacher teacher){
        listTeachers.remove(teacher);
        System.out.println("Teacher removed successfully");
    }
    public void changeCondition(Teacher teacher, TeacherCondition condition){
        teacher.setCondition(condition);
    }

    public Teacher search(String surnames) {
        Comparator<Teacher> surnameComp = Comparator.comparing(Teacher::getSurname);
        return listTeachers.stream()
                .filter(teacher -> surnameComp.compare(teacher, new Teacher("", surnames, null, 0, 0.0)) == 0)
                .findFirst()
                .orElse(null);
    }

    public List<Teacher> searchPartial(String stringFragment){
        List<Teacher> result=new ArrayList<>();
        for(Teacher teacher : listTeachers){
            if (teacher.getSurname().contains(stringFragment)) {
                result.add(teacher);
            }
        }
        return result;
    }

    public long countByCondition(TeacherCondition condition){
        return listTeachers.stream().filter(t->t.getCondition()==condition).count();
    }

    public void summary(){
        listTeachers.forEach(Teacher::printing);
    }

    public List<Teacher> sortByName(){
        Collections.sort(listTeachers, Comparator.comparing(Teacher::getName));
        return listTeachers;
    }
    public List<Teacher> sortBySalary(){
        Collections.sort(listTeachers, Comparator.comparing(Teacher::getSalary).reversed());
        return listTeachers;
    }
    public Teacher max(){
        return Collections.max(listTeachers, Comparator.comparing(Teacher::getSalary));
    }

    //for ClassContainer
    public int getNumberOfTeachers(){
        return listTeachers.size();
    }
    public int getMaxTeacherNumber(){
        return maxTeacherNumber;
    }

}