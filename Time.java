public class Time {
    public static String convertToHours(int time) {
        int hours;
        int minutes;
        String output = "";
        hours = time / 60;
        minutes = time % 60;
        output = output.concat(Integer.toString(hours));
        if (minutes < 10) {
            output = output.concat(":0");
        } else {
            output = output.concat(":");
        }
        output = output.concat(Integer.toString(minutes));
        return output;
    }
}