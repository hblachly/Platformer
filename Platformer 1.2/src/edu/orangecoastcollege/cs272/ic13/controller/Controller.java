package edu.orangecoastcollege.cs272.ic13.controller;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import edu.orangecoastcollege.cs272.ic13.model.DBModel;
import edu.orangecoastcollege.cs272.ic13.model.User;
import edu.orangecoastcollege.cs272.ic13.model.Level;
import edu.orangecoastcollege.cs272.ic13.model.Platform;
import edu.orangecoastcollege.cs272.ic13.model.Enemy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Controller {

    private static Controller theOne;

    private static final String USER_DB_NAME = "user.db";
    private static final String LEVEL_DB_NAME = "level.db";

    private static final String USER_TABLE_NAME = "user";
    private static final String[] USER_FIELD_NAMES = { "_id", "name", "email", "role", "password"};
    private static final String[] USER_FIELD_TYPES = { "INTEGER PRIMARY KEY", "TEXT", "TEXT", "TEXT", "TEXT"};

    private static final String LEVEL_TABLE_NAME = "video_game";
    private static final String[] LEVEL_FIELD_NAMES = { "_id", "name", "x", "y", "map", "creator"};
    private static final String[] LEVEL_FIELD_TYPES = { "INTEGER PRIMARY KEY", "TEXT", "INTEGER", "INTEGER", "TEXT", "TEXT"};
    private static final String LEVEL_FILE = "level.csv";

    // Below is the relationship table "user_games" which associates users with the video games in their inventory
    private static final String USER_LEVEL_TABLE_NAME = "user_games";
    private static final String[] USER_LEVEL_FIELD_NAMES = { "_id", "name", "start", "map", "creator"};
    private static final String[] USER_LEVEL_FIELD_TYPES = { "INTEGER PRIMARY KEY", "TEXT", "TEXT", "TEXT", "TEXT"};

    private User mCurrentUser;
    private DBModel mUserDB;
    private DBModel mLevelsDB;
    private DBModel mUserLevelsDB;

    private ObservableList<User> mAllUsersList;
    private ObservableList<Level> mAllLevelsList;
    
    private Pane currentPane;
    private Scene scene;

    private Controller() {
    }

    public static Controller getInstance() {
        if (theOne == null) {
            theOne = new Controller();
            theOne.mAllUsersList = FXCollections.observableArrayList();
            theOne.mAllLevelsList = FXCollections.observableArrayList();

            try {
                // Create the user table in the database
                theOne.mUserDB = new DBModel(USER_DB_NAME, USER_TABLE_NAME, USER_FIELD_NAMES, USER_FIELD_TYPES);
                ArrayList<ArrayList<String>> resultsList = theOne.mUserDB.getAllRecords();
                for (ArrayList<String> values : resultsList)
                {
                    int id = Integer.parseInt(values.get(0));
                    String name = values.get(1);
                    String email = values.get(2);
                    String role = values.get(3);
                    theOne.mAllUsersList.add(new User(id, name, email, role));
                }

                // Create the video game table in the database, loading games from the CSV file
                theOne.mLevelsDB = new DBModel(LEVEL_DB_NAME, LEVEL_TABLE_NAME, LEVEL_FIELD_NAMES, LEVEL_FIELD_TYPES);
                theOne.initializeLevelDBFromFile();
                resultsList = theOne.mLevelsDB.getAllRecords();
                String[] map;
                int x, y;
                Point start = new Point();
                String creator;
                for (ArrayList<String> values : resultsList)
                {
                    int id = Integer.parseInt(values.get(0));
                    String name = values.get(1);
                    x = Integer.parseInt(values.get(2));
                    y = Integer.parseInt(values.get(3));
                    start.x = x;
                    start.y = y;
                    map = values.get(4).split(" ");
                    creator = values.get(5);

                    theOne.mAllLevelsList.add(new Level(id, name, start, map, creator));
                }


                // Create the relationship table between users and the video games they own
                //theOne.mUserLevelsDB= new DBModel(DB_NAME, USER_LEVEL_TABLE_NAME, USER_LEVEL_FIELD_NAMES, USER_LEVEL_FIELD_TYPES);


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return theOne;
    }

    public boolean isValidPassword(String password)
    {
        // Valid password must contain (see regex below):
        // At least one lower case letter
        // At least one digit
        // At least one special character (@, #, $, %, !)
        // At least one upper case letter
        // At least 8 characters long, but no more than 16
        return password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=[\\]{};':\"\\\\\\|,.<>\\/?]).{8,16}$");
    }

    public boolean isValidEmail(String email)
    {
        return email.matches(
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    }

    public String signUpUser(String name, String email, String password)
    {
        if (!isValidEmail(email))
            return "Email adress not valid. Please try a different address.";

        for (User u : theOne.mAllUsersList)
            if (email.equalsIgnoreCase(u.getEmail()))
                return "Email adress already used. Please sign in or use a different adress.";

        //if (!isValidPassword(password))
            //return "Password must be at least 8 characters, including 1 uppercase letter, 1 lowercase letter,a number (0-9), and 1 special character.";

        String[] values = {name, email, "user", password};
        try
        {
            int id = theOne.mUserDB.createRecord(Arrays.copyOfRange(USER_FIELD_NAMES, 1, USER_FIELD_NAMES.length), values);
            theOne.mCurrentUser = new User(id, name, email, "user");
            theOne.mAllUsersList.add(mCurrentUser);
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "Error creating user, please try again.";
        }

        return "SUCCESS";
    }

    public String signInUser(String email, String password) {
        for (User u : theOne.mAllUsersList)
        {
            if (u.getEmail().equalsIgnoreCase(email))
            {
                try
                {
                    ArrayList<ArrayList<String>> userResults = theOne.mUserDB.getRecord(String.valueOf(u.getId()));
                    String storedPassword = userResults.get(0).get(4);
                    if (password.equals(storedPassword))
                    {
                        mCurrentUser = u;
                        return "SUCCESS";
                    }
                    else
                        break;
                }
                catch (SQLException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return "Incorrect email and/or password. Please try again.";
    }

    public ObservableList<Level> getLevelsForCurrentUser()
    {
        ObservableList<Level> userLevelsList = FXCollections.observableArrayList();
        //TODO: Implement this method
        try
        {
            ArrayList<ArrayList<String>> resultsList = theOne.mUserLevelsDB.getRecord(String.valueOf(theOne.mCurrentUser.getId()));
            int gameId;
            for (ArrayList<String> values : resultsList)
            {
                gameId = Integer.parseInt(values.get(1));
                for (Level l : theOne.mAllLevelsList)
                {
                    if (l.getId() == gameId)
                    {
                        userLevelsList.add(l);
                        break;
                    }
                }
            }
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return userLevelsList;
    }

    public User getCurrentUser()
    {
        return mCurrentUser;
    }

    public ObservableList<User> getAllUsers() {
        return theOne.mAllUsersList;
    }

    public ObservableList<Level> getAllLevels() {
        return theOne.mAllLevelsList;
    }

    private int initializeLevelDBFromFile() throws SQLException {
        int recordsCreated = 0;

        // If the result set contains results, database table already has
        // records, no need to populate from file (so return false)
        if (theOne.mLevelsDB.getRecordCount() > 0)
            return 0;

        try {
            // Otherwise, open the file (CSV file) and insert user data
            // into database
            Scanner fileScanner = new Scanner(new File(LEVEL_FILE));
            // First read is for headings:
            fileScanner.nextLine();
            // All subsequent reads are for user data
            while (fileScanner.hasNextLine()) {
                String[] data = fileScanner.nextLine().split(",");
                // Length of values is one less than field names because values
                // does not have id (DB will assign one)
                String[] values = new String[LEVEL_FIELD_NAMES.length - 1];
                values[0] = data[0];
                values[1] = data[1];
                values[2] = data[2];
                values[3] = data[3];
                values[4] = data[4];
                theOne.mLevelsDB.createRecord(Arrays.copyOfRange(LEVEL_FIELD_NAMES, 1, LEVEL_FIELD_NAMES.length), values);
                recordsCreated++;
            }

            // All done with the CSV file, close the connection
            fileScanner.close();
        } catch (FileNotFoundException e) {
            return 0;
        }
        return recordsCreated;
    }

    public void setPane()
    {
    	currentPane = (Pane) scene.getRoot();
    }
    
    public void setScene(Scene scene)
    {
    	this.scene = scene;
    }
    
    public Scene getCurrentScene()
    {
    	return scene;
    }
    
    public Pane getCurrentPane()
    {
    	return currentPane;
    }
}
