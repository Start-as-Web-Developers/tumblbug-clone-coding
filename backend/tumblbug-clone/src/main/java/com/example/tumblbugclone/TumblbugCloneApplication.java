package com.example.tumblbugclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TumblbugCloneApplication {

	public static void main(String[] args) {
		/*
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("tumblbug");

		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		try{
			transaction.begin();


			TempDBData tempData = new TempDBData();
			tempData.setId(3);
			tempData.setName("장혁수");
			em.persist(tempData);

			TempDBData tempDBData = em.find(TempDBData.class, 3);
			System.out.println(tempDBData.getName());


			transaction.commit();
		}catch (Exception e){
			e.printStackTrace();
			transaction.rollback();
		}finally {
			em.close();
		}

		emf.close();
*/
		SpringApplication.run(TumblbugCloneApplication.class, args);
	}

}
