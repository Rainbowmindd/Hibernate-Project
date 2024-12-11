package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;


import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

@Entity
public class ClassContainer {

    @Id
    @GeneratedValue
    private int id;

    @OneToMany(mappedBy = "classContainer", cascade = CascadeType.ALL)
    private Map<String, ClassTeacher> teacherGroups;

    public ClassContainer() {
        this.teacherGroups = new HashMap<>();
    }

    public void addClass(String groupName, int maxTeacher) {
        if (!teacherGroups.containsKey(groupName)) {
            teacherGroups.put(groupName, new ClassTeacher(groupName, maxTeacher));
        } else {
            System.out.printf("Group: "+groupName+" already exists!\n");
        }
    }

    public void removeClass(String groupName){
        System.out.println("\nClass removed succesfully! ");
        teacherGroups.remove(groupName);
    }
    public List<String> findEmpty(){
        List<String> emptyGroups=new ArrayList<>();
        for(Map.Entry<String, ClassTeacher> entry : teacherGroups.entrySet()){
            if(entry.getValue().getNumberOfTeachers()==0){
                emptyGroups.add(entry.getKey());
            }
        }
        return emptyGroups;
    }
    public void summary(){
        for(Map.Entry<String, ClassTeacher> entry : teacherGroups.entrySet()){
            String groupName = entry.getKey();
            ClassTeacher teacher = entry.getValue();

            int numberOfTeachers=teacher.getNumberOfTeachers();
            int maxNumberOfTeachers=teacher.getMaxTeacherNumber();

            double percentage = (maxNumberOfTeachers > 0) ? (double) numberOfTeachers / maxNumberOfTeachers * 100 : 0.0;
            System.out.printf("Group: %s, Filled: %.2f%% (%d/%d teachers)%n", groupName, percentage, numberOfTeachers, maxNumberOfTeachers);
        }
    }

}

