package com.company.run;

import com.company.model.Book;
import com.company.model.Magazine;
import com.company.model.Resource;
import com.company.model.Review;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Application implements Runnable {

    private final Operation operation = Operation.SAVE;
    private final String path = "/home/bfs/extent/extent.hehe";


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
        isResourceBeingUsed(Resource.getExtent().get(ThreadLocalRandom.current().nextInt(Resource.getExtent().size())));
        getReviews();
        getNumberOfReviews();

    }

    public void createResources() {
        System.out.println("SAVING TO FILE AT: " + path);

        Resource resource1 = new Book("Title 1", List.of("Firstname Lastname"), Optional.of("Reader Name"), LocalDate.of(2010, 5, 2)
                , new Review("lorem ipsum 1", "ReviewerFirstname ReviewerLastname"));
        Resource resource2 = new Book("Title 2", List.of("Firstname Lastname", "FirstName2 Lastname2"), Optional.empty(), LocalDate.of(2015, 2, 10)
                , new Review("lorem ipsum 2", "ReviewerFirstname ReviewerLastname"));
        Resource resource3 = new Book("Title 3", List.of("Firstname3 Lastname3"), Optional.of("Reader2 Name2"), LocalDate.of(2020, 12, 12)
                , new Review("lorem ipsum 3", "ReviewerFirstname ReviewerLastname"));
        Resource resource4 = new Magazine("Magazine1", List.of("name1", "name2", "name3", "name4"), Optional.empty(), LocalDate.of(2022, 3, 1)
                , new Review("lorem ipsum 4", "ReviewerFirstname ReviewerLastname"));
        Resource resource5 = new Magazine("Magazine2", List.of("name1", "name2", "name3", "name4"), Optional.of("Reader Name1"), LocalDate.of(2022, 2, 1)
                , new Review("lorem ipsum 5", "ReviewerFirstname ReviewerLastname"));
        Resource resource6 = new Magazine("Magazine3", List.of("name1", "name2", "name3", "name4"), Optional.of("Reader Name2"), LocalDate.of(2022, 1, 1)
                , new Review("lorem ipsum 6", "ReviewerFirstname ReviewerLastname"));

        try {
            Resource.saveExtent(path);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println();
    }

    public void loadExtent() {
        System.out.println("LOADING FROM FILE: " + path);

        try {
            Resource.loadExtent(path);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println();
    }

    public void getNumberOfUsedResources() {
        System.out.println("Metoda klasowa");
        System.out.println("Currently used resources: " + Resource.getNumberOfResourcesThatAreCurrentlyUsed());
        System.out.println();
    }

    public void isResourceBeingUsed(Resource r) {
        System.out.println("Atrybut opcjonalny");
        System.out.println("Resource: " + r.getTitle() + " is used? " + r.getCurrentReader().isPresent());
        System.out.println();
    }

    public void printResourceTypes() {
        System.out.println("Przeciążenie");
        Resource.getExtent().forEach(x -> {
            System.out.print("Resource type: ");
            x.printResourceType();
        });
        System.out.println();
    }

    public void printAgeOfResourcesAsDays() {
        System.out.println("Atrybut wyliczalny (pochodny)");
        AtomicInteger i = new AtomicInteger(1);
        Resource.getExtent().forEach(x -> {
            System.out.println("Resource number: " + i);
            System.out.println("Days old: " + x.getDaysSinceResourceWasReleased());
            i.getAndIncrement();
        });
        System.out.println();
    }

    public void printResourceAuthors() {
        System.out.println("Atrybut powtarzalny");
        Resource.getExtent().forEach(x -> {
            System.out.println(x.getAuthors());
        });
        System.out.println();
    }

    public void getReviews() {
        System.out.println("Atrybut złożony");
        Resource.getExtent().forEach(x -> {
            System.out.println(x.getReview());
        });
        System.out.println();
    }
    public void getNumberOfReviews() {
        System.out.println("Atrybut klasowy");
        System.out.println(Review.getNumberOfReviews());
        System.out.println();
    }

    enum Operation {LOAD, SAVE}
}
