public class Lawyer {
    private int friendliness;
    private int punctuality;
    private int competence;
    private int minutesForService;
    private int timeLeft;
    private int totalTime;
    private String name;

    public Lawyer() {
    }

    public Lawyer(int f, int p, int c, String employeeName) {
        friendliness = f;
        punctuality = p;
        competence = c;
        name = employeeName;
    }

    public void setFriendliness(int f) {
        friendliness = f;
    }

    public void setPunctuality(int p) {
        punctuality = p;
    }

    public void setCompetence(int c) {
        competence = c;
    }

    public void changeFriendliness(int f) {
        friendliness = friendliness + f;
    }

    public void changePunctuality(int p) {
        punctuality = punctuality + p;
    }

    public void changeCompetence(int c) {
        competence = competence + c;
    }

    public int getFriendliness() {
        return friendliness;
    }

    public int getPunctuality() {
        return punctuality;
    }

    public int getCompetence() {
        return competence;
    }

    public String getName() {
        return name;
    }

    public boolean isBusy() {
        return (timeLeft > 0);
    }

    public void reduceRemainingTime() {
        int reduce = 2;
        //uses characteristics defined in private instance variables to determine amount time is
        //reduced, or, if variable reduces falls into the negative, imposes penalty increasing time.
        if (friendliness > 1) {
            reduce = reduce * friendliness;
        }
        else if (friendliness < 1) {
            reduce = reduce - friendliness;
        }
        if (punctuality > 1) {
            reduce = reduce * punctuality;
        } else if (punctuality < 1) {
            reduce = 0;
        }
        if (competence > 1) {
            if (competence > 1) {
                reduce = reduce * (competence - 1);
            } else {
                reduce = reduce * competence;
            }
        } else if (competence < 1) {
            reduce = reduce - competence;
        }
        totalTime = totalTime + reduce;
        timeLeft = timeLeft - reduce;
    }

    public int getTimeRemaining() {
        return timeLeft;
    }

    public void setTimeRemaining(int time) {
        timeLeft = time * 5;
    }

    public int getTotalTime() {
        return totalTime;
    }
}