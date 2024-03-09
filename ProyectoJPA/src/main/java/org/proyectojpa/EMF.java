package org.proyectojpa;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EMF {
    private static volatile EntityManagerFactory emfInstance;

    private EMF() {
    }

    public static EntityManagerFactory get() {
        if (emfInstance == null) {
            synchronized (EMF.class) {
                if (emfInstance == null) {
                    emfInstance = Persistence.createEntityManagerFactory("ProyectoADJPA");
                }
            }
        }

        return emfInstance;
    }
}
