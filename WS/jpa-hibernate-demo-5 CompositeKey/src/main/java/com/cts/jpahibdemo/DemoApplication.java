package com.cts.jpahibdemo;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.cts.jpahibdemo.entity.Trainee;
import com.cts.jpahibdemo.entity.TraineeId;

public class DemoApplication {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqlPU");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		
		em.persist(new Trainee(new TraineeId(1L, "JavaFSD"),"Vamsy Kiran", LocalDate.now()));
		em.persist(new Trainee(new TraineeId(2L, "JavaFSD"),"Vinay", LocalDate.now()));
		em.persist(new Trainee(new TraineeId(1L, "MERN-FSD"),"Suseela", LocalDate.now()));
		em.persist(new Trainee(new TraineeId(2L, "MERN-FSD"),"Sagar", LocalDate.now()));
		em.persist(new Trainee(new TraineeId(1L, "MEAN-FSD"),"Indhikaa", LocalDate.now()));
		
		txn.commit();
		em.close();
	}
}
