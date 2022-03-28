package com.company.run;

import com.company.model.Book;
import com.company.model.Magazine;
import com.company.model.Resource;

import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Application implements Runnable {

    private final Operation operation = Operation.SAVE;
    private final String path = System.getProperty("user.home") + File.pathSeparator + "EXTENT";


    @Override
    public void run() {
        switch (operation) {
            case SAVE -> createResources();
            case LOAD -> loadExtent();
        }

        getNumberOfUsedResources();
        printAgeOfResourcesAsDays();
        printResourceTypes();
        printResourceAuthors();


    }

    public void createResources() {
        Resource resource1 = new Book("Title 1", List.of("Firstname Lastname"), Optional.of("Reader Name"), LocalDate.of(2010, 5, 2));
        Resource resource2 = new Book("Title 2", List.of("Firstname Lastname", "FirstName2 Lastname2"), Optional.empty(), LocalDate.of(2015, 2, 10));
        Resource resource3 = new Book("Title 3", List.of("Firstname3 Lastname3"), Optional.of("Reader2 Name2"), LocalDate.of(2020, 12, 12));
        Resource resource4 = new Magazine("Magazine1", List.of("name1", "name2", "name3", "name4"), Optional.empty(), LocalDate.of(2022, 3, 1));
        Resource resource5 = new Magazine("Magazine2", List.of("name1", "name2", "name3", "name4"), Optional.of("Reader Name1"), LocalDate.of(2022, 2, 1));
        Resource resource6 = new Magazine("Magazine3", List.of("name1", "name2", "name3", "name4"), Optional.of("Reader Name2"), LocalDate.of(2022, 1, 1));

        try {
            Resource.saveExtent(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadExtent() {
        try {
            Resource.loadExtent(path);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getNumberOfUsedResources() {
        System.out.println("Currently used resources: " + Resource.getNumberOfResourcesThatAreCurrentlyUsed());
    }

    public void printResourceTypes() {
        Resource.getExtent().forEach(x -> {
            System.out.print("Resource type: ");
            x.printResourceType();
        });
    }

    public void printAgeOfResourcesAsDays() {
        int i = 0;
        Resource.getExtent().forEach(x -> {
            System.out.println("Resource number: " + i);
            System.out.println("Days old: " + x.getDaysSinceResourceWasReleased());
        });
    }

    public void printResourceAuthors() {
        Resource.getExtent().forEach(x -> {
            System.out.println(x.getAuthors());
        });
    }

    enum Operation {LOAD, SAVE}
}
