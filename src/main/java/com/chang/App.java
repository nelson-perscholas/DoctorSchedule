package com.chang;

import com.chang.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
public class App {
    public static void main(String[] args) {
        //manyToOne();
        //oneToMany();
        oneToOne();

        //manyToMany();

    }

    public static void manyToOne(){
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        //creating hospital
        Hospital hospital1 = new Hospital("Saint Joshep", "123 Mapple St.");
        Hospital hospital2 = new Hospital("Christiana Hospital", "987 Wilmington");

        Doctors doctor1 = new Doctors("MHaseeb",hospital1);
        Doctors doctor2 = new Doctors("Shahparan",hospital2);
        Doctors doctor3 = new Doctors("James",hospital1);
        Doctors doctor4 = new Doctors("Joseph",hospital1);


        session.persist(hospital1);
        session.persist(hospital2);

        session.persist(doctor1);
        session.persist(doctor2);
        session.persist(doctor3);
        session.persist(doctor4);
        transaction.commit();  }

    public static void oneToMany(){
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        //creating Doctor
        Doctors doctor1 = new Doctors("MHaseeb");
        Doctors doctor2 = new Doctors("James");
        Doctors doctor3 = new Doctors("Joseph");
        Doctors doctor4 = new Doctors("Ali");

        //Add Doctors entity object to the list
        ArrayList<Doctors> doctorsList = new ArrayList<>();
        doctorsList.add(doctor1);
        doctorsList.add(doctor2);
        doctorsList.add(doctor3);
        doctorsList.add(doctor4);


        session.persist(doctor1);
        session.persist(doctor2);
        session.persist(doctor3);
        session.persist(doctor4);
        //Creating Specialty
        Specialty specialty = new Specialty();
        specialty.setSpecialty_name("Orthopedic");
        specialty.setDoctorsList(doctorsList);
        //Storing Specialty
        session.persist(specialty);
        t.commit();    }

    public static void oneToOne(){
        System.out.println("Maven + Hibernate + SQL One to One Mapping Annotations");

        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        Transaction t = session.beginTransaction();
        DoctorSchedule a1 = new DoctorSchedule("Day Shift");
        DoctorSchedule a2 = new DoctorSchedule("Night Shift");

        Doctors doctor1 = new Doctors("MHaseeb");
        Doctors doctor2 = new Doctors("Shahparan");
        doctor1.setDoctorSchedule(a1);
        doctor2.setDoctorSchedule(a2);



        session.persist(a1);
        session.persist(a2);
        session.persist(doctor1);
        session.persist(doctor2);

        t.commit();
    }

    public static void manyToMany() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        Doctors doctor1 = new Doctors("MHaseeb");
        Doctors doctor2 = new Doctors("Shahparan");
        Doctors doctor3 = new Doctors("Ali");
        //------  Store Doctors  / Class  --------
        session.persist(doctor1);
        session.persist(doctor2);
        session.persist(doctor3);


        //-----Create Doctors one / patient one --------
        Set<Doctors> doctorsSet = new HashSet<Doctors>();
        doctorsSet.add(doctor1);
        doctorsSet.add(doctor2);
        doctorsSet.add(doctor3);
        //-----Create Doctors two / patient two --------
        Set<Doctors> doctorSet2 = new HashSet<Doctors>();
        doctorSet2.add(doctor2);
        doctorSet2.add(doctor3);
        doctorSet2.add(doctor1);
        //-----Create Doctors Three / patient Three --------
        Set<Doctors> doctorSet3 = new HashSet<Doctors>();
        doctorSet3.add(doctor3);
        doctorSet3.add(doctor1);
        doctorSet3.add(doctor2);

        Patients patient1 = new Patients("Haseeb", "1234 Kipling st", doctorsSet);
        Patients patient2 = new Patients("Jenny", "256 Jenny dr", doctorSet2);
        Patients patient3 = new Patients("Charlie", "897 Martin Luther blv", doctorSet3);

        session.persist(patient1);
        session.persist(patient2);
        session.persist(patient3);
        t.commit();
    }
}
