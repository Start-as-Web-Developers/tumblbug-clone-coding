package com.example.tumblbugclone.dbexample;

import com.example.tumblbugclone.dbexample.TempDBData;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class DBConnectClass {
    //DB 연결 이후 사용법 예시 입니다.
    public void logic() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tumblbug");

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();


            TempDBData tempData = new TempDBData();
            tempData.setId(3);
            tempData.setName("장혁수");
            em.persist(tempData);

            TempDBData tempDBData = em.find(TempDBData.class, 3);
            System.out.println(tempDBData.getName());


            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}