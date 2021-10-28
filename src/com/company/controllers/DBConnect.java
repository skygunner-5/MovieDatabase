package com.company.controllers;

import com.company.models.Movie;
import com.company.views.CmdLineView;

import java.sql.*;
import java.util.ArrayList;

public class DBConnect {

    private String dbName;
    private String url;

    public DBConnect(String dbName) {
        this.dbName = dbName;
        //make sure to have the matching folder in the sqlite folder
        this.url = "jdbc:sqlite:C:/sqlite/db/" + dbName;
    }

    public void createNewDatabase() {

        //try catch to create new database with the provided url
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                //System.out.println("The driver name is " + meta.getDriverName());
               //System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addTables(){

        //lay out the data needed for each entry
        String sql = "CREATE TABLE IF NOT EXISTS movies (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	title text NOT NULL,\n"
                + "	releaseDate text NOT NULL,\n"
                + " rating text NOT NULL\n"
                + ");";

        try(Connection conn = DriverManager.getConnection(url)){
            Statement statement = conn.createStatement();
            statement.execute(sql);
            //System.out.println("Tables added");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addData(){

        CmdLineView view = new CmdLineView(true);
        //get each of the pieces of data from prompting the user
        String title = view.getTitle();
        String releaseDate = view.getReleaseDate();
        String rating = view.getRating();
        //format for adding data into a table
        String sql = "INSERT INTO movies(title, releaseDate, rating) VALUES ('" + title + "', '" + releaseDate + "', '" + rating + "');";
        //System.out.println(sql);
        try(Connection conn = DriverManager.getConnection(url)){
            Statement statement = conn.createStatement();
            statement.execute(sql);
            //System.out.println("Data added");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    //to use the database data in java, we need to create an array list of the Movie model type
    public ArrayList<Movie> getData(){
        String sql = "SELECT id, title, releaseDate, rating FROM movies";
        ArrayList<Movie> movieList = new ArrayList<Movie>();

        try(Connection conn = DriverManager.getConnection(url)){
            Statement statement = conn.createStatement();
            //since we're returning something from an SQL statement, we need a variable of type ResultSet
            ResultSet movies = statement.executeQuery(sql);
            //executes as long as there is another line to read in from a database
            while(movies.next())
            {
                int id = movies.getInt("id");
                String title = movies.getString("title");
                String releaseDate = movies.getString("releaseDate");
                String rating = movies.getString("rating");
                Movie movie = new Movie(id, title, releaseDate, rating);

                //transfer the result set to the movies array list
                movieList.add(movie);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return movieList;
    }
}
