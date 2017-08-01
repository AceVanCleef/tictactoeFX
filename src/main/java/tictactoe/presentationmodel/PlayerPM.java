package tictactoe.presentationmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Degonas on 18.07.2017.
 */
public class PlayerPM {

    /* identifies the player and allows player selection by ID. */
    private final IntegerProperty id = new SimpleIntegerProperty();

    /* stores the current name shown in the GUI */
    private final StringProperty name = new SimpleStringProperty();

    /* stores the initial, unchangable name */
    private final String initialName;

    /* #Score: stores how often a player has won */
    private final IntegerProperty score = new SimpleIntegerProperty();


    public PlayerPM(int id){
        setId(id);
        setName("Player " + id);
        initialName = getName();
    }


    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getScore() {
        return score.get();
    }

    public IntegerProperty scoreProperty() {
        return score;
    }

    public void setScore(int score) {
        this.score.set(score);
    }

    public String getInitialName(){
        return initialName;
    }
}
