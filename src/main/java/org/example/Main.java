package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


//capacity -> int albo long a nie double
public class Main {
    public static void main(String[] args) {

        // Ustawienie fabryki sesji Hibernate
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Teacher.class)
                .addAnnotatedClass(ClassTeacher.class)
                .addAnnotatedClass(ClassContainer.class)
                .buildSessionFactory();

        // Utworzenie sesji
        Session session = factory.getCurrentSession();

        try {
            // Tworzenie instancji obiektów
            Teacher teacher1 = new Teacher("Martyna", "Kindrat", TeacherCondition.present, 2003, 5500.0);
            Teacher teacher2 = new Teacher("Anna", "Kowalska", TeacherCondition.sick, 1999, 3500.0);

            // Rozpoczęcie transakcji
            session.beginTransaction();

            // Zapis do bazy danych
            session.save(teacher1);
            session.save(teacher2);

            // Zatwierdzenie transakcji
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }
}