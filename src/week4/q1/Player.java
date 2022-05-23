package week4.q1;
/*
 * Player.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */

import java.io.FileNotFoundException;


public class Player {
    private String description; // description of images array for two players filled using command line arguments
    private final String turnText;// text array used to show the player turn text
    private String name;
    private int score;
    private String descriptionCopy;
    private String answerHint = "";
    private final Picture picture = new Picture();

    public Player(String turnText) {
        this.turnText = turnText;
    }

    public String getDescription() {
        return description;
    }

    //creating getter and setter for all fields

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
        this.descriptionCopy = description;
        for (int i = 0; i < description.length(); i++) {
            answerHint += ".";
        }
    }

    /**
     * @param characterGuessed
     */
    public void updateDescription(String characterGuessed) {
        this.description = description.replaceFirst(characterGuessed, "*");
    }

    /**
     * @return
     */
    public String getTurnText() {
        return turnText;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDescriptionCopy() {
        return descriptionCopy;
    }

    public String getAnswerHint() {
        return answerHint;
    }

    /**
     * @param characterGuessed
     */
    public void setAnswerHint(String characterGuessed) {
        int pos = description.indexOf(characterGuessed);
        this.answerHint = answerHint.substring(0, pos) +
                characterGuessed + answerHint.substring(pos + 1);
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(String pictureFileName) throws FileNotFoundException {
        picture.setImageVector(pictureFileName);
    }
}
