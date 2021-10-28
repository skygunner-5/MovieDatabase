package com.company.views;

import java.util.Scanner;

public class CmdLineView {
    private boolean addMovies;

    public CmdLineView(boolean addMovies) {
        this.addMovies = addMovies;
    }

    //check if the user wants to keep adding movies to the database
    public boolean shouldAddMovies(){
        System.out.println("Would you like to add another movie?(y or n): ");
        Scanner input = new Scanner(System.in);
        String shouldContinue = input.next();
        if(shouldContinue.equalsIgnoreCase("y"))
            return true;
        else
            return false;
    }

    //get the title of the film, make sure it's a string
    public String getTitle(){
        System.out.println("What is the film title?: ");
        Scanner input = new Scanner(System.in);
        while(input.hasNextInt() || input.hasNextFloat()){
            System.out.println("Error: title must be a text string!");
            System.out.println("Please enter the film title: ");
            input = new Scanner(System.in);
        }
        //use next line since the title may have spaces
        String titleEntry = input.nextLine();
        return titleEntry;
    }

    //release data should be integer to start, convert to string
    public String getReleaseDate(){
        System.out.println("What is the film's release date?: ");
        Scanner input = new Scanner(System.in);
        String releaseEntry = Integer.toString(input.nextInt());
        return releaseEntry;
    }

    public String getRating(){
        System.out.println("What is the film's rating?: ");
        Scanner input = new Scanner(System.in);
        while(input.hasNextInt() || input.hasNextFloat()){
            System.out.println("Error: rating must be a text string!");
            System.out.println("Please enter the film's rating: ");
            input = new Scanner(System.in);
        }
        String ratingEntry = input.next();
        return ratingEntry;
    }

    public boolean isAddMovies() {
        return addMovies;
    }

    public void setAddMovies(boolean addMovies) {
        this.addMovies = addMovies;
    }
}
