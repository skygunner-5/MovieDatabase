package com.company.controllers;


import com.company.models.Movie;
import com.company.views.CmdLineView;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
       DBConnect db = new DBConnect("movies.db");
       db.createNewDatabase();
       db.addTables();
       CmdLineView view = new CmdLineView(true);

       //continue prompting the user for movies as long as they want to continue
       while(view.isAddMovies()){
           db.addData();
           if(view.shouldAddMovies()){
               view.setAddMovies(true);
           }
           else
               view.setAddMovies(false);
       }
       /* use this for non-user input
       db.addData("Star Wars", "1977", "PG");
       db.addData("CODA", "2021", "PG-13");
       db.addData("The Life of Emile Zola", "1937", "NR");
       db.addData("The Night House", "2021", "R");
       */
        //print out each line of the movie database using array list
       ArrayList<Movie> theMovies = db.getData();
        for(Movie movie : theMovies){
            System.out.println(movie.toString());
        }


    }
}
