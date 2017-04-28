import java.util.Random;
public class Disasters {

    public static String friendliness(double probability) {
        String output = "";
        Random rg = new Random();
        int r = rg.nextInt(5);
        if (probability < .02) {
            if (r == 0) {
                output = " initiated verbal argument with client. Suspended until matter is reviewed.";
            } else if (r == 1) {
                output = " scalded unpaid intern with hot coffee. Terminated without severance.";
            } else if (r == 2) {
                output = " became heated during performance review. Resigned unexpectedly.";
            } else if (r == 3) {
                output = " left note on desk reading \">:(\" . Presumably out for the day.";
            } else if (r == 4) {
                output = " feels underappreciated. Used a vacation day.";
            } else if (r == 5) {
                output = " is crying in the bathroom until further notice.";
            }
        }
        return output;
    }

    public static String punctuality(double probability) {
        String output = "";
        Random rg = new Random();
        int r = rg.nextInt(5);
        if (probability < .02) {
            if (r == 0) {
                output = " has been out to lunch for two and half hours";
            } else if (r == 1) {
                output = " took a five thirty minutes ago.";
            } else if (r == 2) {
                output = " was last seen talking on the phone about a ski trip to Aspen in the .";
            } else if (r == 3) {
                output = " isn't in the office";
            } else if (r == 4) {
                output = " isn't answering their phone.";
            } else if (r == 5) {
                output = " isn't in the office. Receptionist texted, received automatic reply. \"I'm driving. I'll text you back.\"";
            }
        }
        return output;
    }

    public static String competence(double probability) {
        String output = "";
        Random rg = new Random();
        int r = rg.nextInt(5);
        if (probability < .02) {
            if (r == 0) {
                output = " filed the wrong brief this morning and needs to catch up on work";
            } else if (r == 1) {
                output = " needs time to google tort law before meeting with client.";
            } else if (r == 2) {
                output = " has broken the photocopier";
            } else if (r == 3) {
                output = " appears to be drinking in meeting with client";
            } else if (r == 4) {
                output = " asked receptionist to meet with client on their behalf. Can't be tracked down.";
            } else if (r == 5)
                output = " got lost on way back from the courthouse";
        }

        return output;
    }

    public static String regularDisaster() {
        String output = "";
        Random rg = new Random();
        int r = rg.nextInt(3);

            if (r == 0) {
                output = "The copier machine has burst aflame. -1 Punctuality for all.";
            } else if (r == 1) {
                output = "The Christmas party has been canclled. -1 Friendliness for all.";
            } else if (r == 2) {
                output = "The State Bar has been dissolved. -1 Competence for all.";
            }
        

        return output;
    }
}

