import java.util.*;
import java.time.Duration;

public class LawFirm extends Thread{
    public static void main (String [] args) throws InterruptedException{
        System.out.print ('\f');
        Scanner kb = new Scanner(System.in);
        Thread stop = new Thread();
        String userResponse;

        Title.drawTitle();
        stop.sleep(1000);
        System.out.println("Law Firm Simulator is a thrilling test of your managerial style pitted against");
        stop.sleep(2000);
        System.out.println("a throng of unruly lawyers. Attorneys have three attributes: \n");
        stop.sleep(2000);
        System.out.println("************************************************************************************************************************");
        System.out.println("Friendliness: If friendliness reaches 0, the lawyer will be at risk of resigning.");
        stop.sleep(2000);
        System.out.println("************************************************************************************************************************");
        System.out.println("\nPunctuality: The lower a lawyer's punctuality, the longer s/he takes to help a client.");
        stop.sleep(2000);
        System.out.println("************************************************************************************************************************");
        System.out.println("\nCompetence: Makes lawyers assist clients marginally faster. When at 0, however, can increase help times drastically.");
        stop.sleep(1000);
        System.out.println("************************************************************************************************************************");
        stop.sleep(2000);
        System.out.println("Regular setbacks will occur, i.e. the copier breaking down. Rarer issues, such as an attorney quitting,");
        stop.sleep(2000);
        System.out.println("will only occur if their stats get dangerously low. Godspeed!");
        stop.sleep(2000);
        System.out.println("Press any key to continue.");
        userResponse = kb.nextLine();
        System.out.print ('\f');

        
        long totalTime;
        do {
            System.out.println("Enter workday duration in hours: (a value between 0 and 24)");
            totalTime = kb.nextLong();
        } while (totalTime < 0 || totalTime > 24);
        totalTime = totalTime * 60;

        double arrivalProb;
        do {
            System.out.println("Enter probability of customer arrival (0 to 100): ");
            arrivalProb = kb.nextDouble();
        } while (arrivalProb < 0 || arrivalProb > 100);
        arrivalProb = arrivalProb / 100;

        int meetingDuration;
        do{
            System.out.println("Enter average meeting duration: ");
            meetingDuration = kb.nextInt();
        } while(meetingDuration < 0 || meetingDuration > totalTime);
        double hourly;
        do{
            System.out.println("Enter how much you will pay your lawyers hourly: ");
            hourly = kb.nextDouble();
        } while (hourly < 0);
        double clientHourly;
        do {
            System.out.println("Enter how much you will charge your clients hourly: ");
            clientHourly = kb.nextDouble();
        } while(clientHourly < 0);
        kb.nextLine();
        do {
            System.out.println("Would you like to enable live managerial control? Enter y for yes and n for n :");
            userResponse = kb.nextLine();
        } while (!(userResponse.equalsIgnoreCase("y") || userResponse.equalsIgnoreCase("n")));

        System.out.println("Running simulation...");
        stop.sleep(1000);
        System.out.println("...");
        stop.sleep(1000);
        System.out.println("...");
        try {
            LawFirm.lawFirmSimulate(totalTime, arrivalProb, meetingDuration, hourly, clientHourly, userResponse);
        } catch (IllegalArgumentException e) {
            System.out.println("This law firm cannot abide the illegal argument you just presented. Program will now exit.");
            System.exit(0);
        }
        /*Scanner kb = new Scanner(System.in);
        System.out.println("Enter how many lawyers(up to 10) you wish to have: ");
        int numLawyers = kb.nextInt();
        Random rg = new Random();
        int r1, r2, r3;
        Lawyer [] lawyers = new Lawyer[numLawyers];
        RandomNameGenerator attorneyNames = new RandomNameGenerator();
        for (int i = 0; i < lawyers.length; i ++) { //generates a team of lawyer objects into array
        r1 = rg.nextInt(4);
        r2 = rg.nextInt(4);
        r3 = rg.nextInt(4);
        Lawyer attorney = new Lawyer(r1, r2, r3, attorneyNames.generate());
        lawyers[i] = attorney;
        }
        System.out.println("Your team: ");
        for (int i = 0; i < lawyers.length; i++) {
        System.out.println(lawyers[i].getName());
        } */
    }

    public static void lawFirmSimulate(long totalTime, double arrivalProb, int meetingDuration, double hourly, double clientHourly, String userResponse) throws InterruptedException {
        Random rg = new Random();
        Scanner kb = new Scanner(System.in);
        String managerChoice;

        ClientGenerator arrival = new ClientGenerator(arrivalProb);
        Queue<Integer> arrivalTimes = new LinkedList<Integer>();
        Queue<Lawyer> attorneys = new LinkedList<Lawyer>();
        Queue<Lawyer> attorneysOccupied = new LinkedList<Lawyer>();
        int r1, r2, r3;
        int next;
        int clients = 0;
        int count = 0;
        int busy = 0;
        int numOccupied = 0;
        double disasterProbability;
        int currentMinute;

        Averager waitTimes = new Averager();
        Averager longerWaitTimes = new Averager();
        RandomNameGenerator attorneyNames = new RandomNameGenerator();

        Lawyer current = new Lawyer();
        if (meetingDuration <= 0 || arrivalProb < 0 || arrivalProb > 1 || totalTime < 0)
            throw new IllegalArgumentException("Values out of range"); 

        System.out.println("Enter how many lawyers(up to 10) you wish to have: ");
        int numLawyers = kb.nextInt();
        if (numLawyers > 10 || numLawyers < 0) {
            throw new IllegalArgumentException();
        }

        System.out.println("Your team: ");

        Thread thread = new Thread();
        for (int i = 0; i < numLawyers; i ++) { //generates a team of lawyer objects into a queue
            thread.sleep(1000);
            r1 = rg.nextInt(2) + 1;
            r2 = rg.nextInt(2) + 1;
            r3 = rg.nextInt(2) + 1;
            Lawyer attorney = new Lawyer(r1, r2, r3, attorneyNames.generate());
            System.out.println("");
            System.out.println(attorney.getName());
            System.out.println("Friendliness:      " + attorney.getFriendliness());
            System.out.println("____________________\n Punctuality:      " + attorney.getPunctuality());
            System.out.println("____________________\n Competence:       " + attorney.getCompetence());
            attorneys.add(attorney);
        }

        String disaster;
        for (currentMinute = 0; currentMinute < totalTime; currentMinute++) {

            disasterProbability = Math.random();
            if (arrival.query()) {
                if (disasterProbability < 0.08 && disasterProbability > 0.02) {
                    disaster = Disasters.regularDisaster();
                    System.out.println("*********************************************************************************************");
                    System.out.println(disaster);
                    System.out.println("*********************************************************************************************");
                    if (disaster.contains("copier")) {
                        for (int i = 0; i < busy; i++) {
                            if (!(attorneysOccupied.isEmpty())) {
                                current = attorneysOccupied.remove();
                                current.setPunctuality(current.getPunctuality() - 1);
                                attorneysOccupied.add(current);
                            }
                        }
                        for (int i = (numLawyers - busy); i > 0; i--) {
                            if (!(attorneys.isEmpty())) {
                                current = attorneys.remove();
                                current.setPunctuality(current.getPunctuality() - 1);
                                attorneys.add(current);
                            }
                        }
                    } else if (disaster.contains("Christmas")) {
                        for (int i = 0; i < busy; i++) {
                            if (!(attorneysOccupied.isEmpty())) {
                                current = attorneysOccupied.remove();
                                current.setFriendliness(current.getFriendliness() - 1);
                                attorneysOccupied.add(current);
                            }
                        }
                        for (int i = (numLawyers - busy); i > 0; i--) {
                            if (!(attorneys.isEmpty())) {
                                current = attorneys.remove();
                                current.setFriendliness(current.getFriendliness() - 1);
                                attorneys.add(current);
                            }
                        }
                    } else if (disaster.contains("Bar")) {
                        for (int i = 0; i < busy; i++) {
                            if (!(attorneysOccupied.isEmpty())) {
                                current = attorneysOccupied.remove();
                                current.setCompetence(current.getCompetence() - 1);
                                attorneysOccupied.add(current);
                            }
                        }
                        for (int i = (numLawyers - busy); i > 0; i--) {
                            if (!(attorneys.isEmpty())) {
                                current = attorneys.remove();
                                current.setCompetence(current.getCompetence() - 1);
                                attorneys.add(current);
                            }
                        }
                    }
                }
                thread.sleep(400);
                System.out.println("\n" + Time.convertToHours(currentMinute));
                System.out.println("Client arrived.");
                if (attorneys.isEmpty()) {
                    thread.sleep(400);
                    System.out.println("No available attorneys. Lost a client at " + currentMinute);
                } else {
                    thread.sleep(400);
                    arrivalTimes.add(currentMinute);
                    current = attorneys.remove();
                    if (current.getFriendliness() <= 0 && disasterProbability < .02) {
                        System.out.println("*********************************************************************************************");
                        thread.sleep(100);
                        System.out.println("*********************************************************************************************");
                        thread.sleep(100);
                        System.out.println("*********************************************************************************************");
                        thread.sleep(100);
                        System.out.println(current.getName() + Disasters.friendliness(disasterProbability));
                        System.out.println("*********************************************************************************************");
                        thread.sleep(100);
                        System.out.println("*********************************************************************************************");
                        thread.sleep(100);
                        System.out.println("*********************************************************************************************");
                        numLawyers = numLawyers - 1;
                    } else if (current.getPunctuality() <= 0 && disasterProbability < .02) {
                        System.out.println("*********************************************************************************************");
                        thread.sleep(100);
                        System.out.println("*********************************************************************************************");
                        thread.sleep(100);
                        System.out.println("*********************************************************************************************");
                        thread.sleep(100);
                        System.out.println(current.getName() + Disasters.punctuality(disasterProbability));
                        System.out.println("*********************************************************************************************");
                        thread.sleep(100);
                        System.out.println("*********************************************************************************************");
                        thread.sleep(100);
                        System.out.println("*********************************************************************************************");
                        thread.sleep(100);
                        current.setTimeRemaining(100);
                        attorneysOccupied.add(current);
                        busy = busy + 1;
                    } else if (current.getCompetence() <= 0 && disasterProbability < .02) {
                        System.out.println("*********************************************************************************************");
                        thread.sleep(100);
                        System.out.println("*********************************************************************************************");
                        thread.sleep(100);
                        System.out.println("*********************************************************************************************");
                        thread.sleep(100);
                        System.out.println(current.getName() + Disasters.competence(disasterProbability));
                        System.out.println("*********************************************************************************************");
                        thread.sleep(100);
                        System.out.println("*********************************************************************************************");
                        thread.sleep(100);
                        System.out.println("*********************************************************************************************");
                        thread.sleep(100);
                        current.setTimeRemaining(100);
                        attorneysOccupied.add(current);
                        busy = busy + 1;
                    }
                    else {
                        current.setTimeRemaining(meetingDuration);
                        attorneysOccupied.add(current);
                        busy = busy + 1;
                        System.out.println("Client received by " + current.getName());
                        System.out.println(current.getName() + " is providing service to client.");
                    }
                }

                if (!(attorneys.isEmpty()) && !(arrivalTimes.isEmpty())) {
                    next = arrivalTimes.remove();
                    waitTimes.addNumber(currentMinute - next);

                }
            }
            for (int i = 0; i < busy; i++) {
                if (!(attorneysOccupied.isEmpty())) {
                    Lawyer temp = new Lawyer();
                    temp = attorneysOccupied.remove();
                    if (temp.getTimeRemaining() <= 0) {
                        attorneys.add(temp);
                        busy = busy - 1;
                        clients = clients + 1;
                    } else {
                        temp.reduceRemainingTime();
                        attorneysOccupied.add(temp);
                    }
                }
            }

            if (userResponse.equalsIgnoreCase("y")) {
                if (count == 15) {
                    System.out.println("****************************State of your team:*************************************");
                    for (int i = 0; i < busy; i++) {
                        if (!(attorneysOccupied.isEmpty())) {
                            current = attorneysOccupied.remove();
                            System.out.println("");
                            System.out.println(current.getName());
                            System.out.println("Friendliness: " + current.getFriendliness() + "| Punctuality: " + current.getPunctuality() + "|Competence: " + current.getCompetence());
                            System.out.println("______________________________________________________________________________________________________________");
                            attorneysOccupied.add(current);
                        }
                    }
                    for (int i = (numLawyers - busy); i > 0; i--) {
                        if (!(attorneys.isEmpty())) {
                            current = attorneys.remove();
                            System.out.println("");
                            System.out.println(current.getName());
                            System.out.println("Friendliness: " + current.getFriendliness() + "| Punctuality: " + current.getPunctuality() + "|Competence: " + current.getCompetence());
                            System.out.println("______________________________________________________________________________________________________________");
                            attorneys.add(current);
                        }
                    }
                    System.out.println("Choose a managerial strategy: ");
                    System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
                    System.out.println("| A) Order pizza and express employee appreciation (+2 Friendliness for all employees. -1 Punctuality. -1 Competence)       |");
                    System.out.println("| B) Sternly remind employees that this is a LAW FIRM (+2 Punctuality for all employees. -1 Friendliness. -1 Competence.)   |");
                    System.out.println("| C) Give a short refresher lecture on legal basics in the conference room (+1 Competence for all employees)                |");
                    System.out.println("|___________________________________________________________________________________________________________________________|");

                    boolean yes = true;
                    while (yes) {
                        try {
                            Scanner bk = new Scanner(System.in);
                            managerChoice = bk.nextLine();
                            if (managerChoice.equalsIgnoreCase("a")) {
                                yes = false;
                                for (int i = 0; i < busy; i++) {
                                    if (!(attorneysOccupied.isEmpty())) {
                                        current = attorneysOccupied.remove();
                                        current.setFriendliness(current.getFriendliness() + 2);
                                        current.setPunctuality(current.getPunctuality() - 1);
                                        current.setCompetence(current.getCompetence() - 1);
                                        attorneysOccupied.add(current);
                                    }
                                }
                                for (int i = (numLawyers - busy); i > 0; i--) {
                                    if (!(attorneys.isEmpty())) {
                                        current = attorneys.remove();
                                        current.setFriendliness(current.getFriendliness() + 2);
                                        current.setPunctuality(current.getPunctuality() - 1);
                                        current.setCompetence(current.getCompetence() - 1);
                                        attorneys.add(current);
                                    }
                                }
                                count = 0;
                            }
                            else if (managerChoice.equalsIgnoreCase("b")) {
                                yes = false;
                                for (int i = 0; i < busy; i++) {
                                    if (!(attorneysOccupied.isEmpty())) {
                                        current = attorneysOccupied.remove();
                                        current.setPunctuality(current.getPunctuality() + 2);
                                        current.setFriendliness(current.getFriendliness() - 1);
                                        current.setCompetence(current.getCompetence() - 1);
                                        attorneysOccupied.add(current);
                                    }
                                }
                                for (int i = (numLawyers - busy); i > 0; i--) {
                                    if (!(attorneys.isEmpty())) {
                                        current = attorneys.remove();
                                        current.setPunctuality(current.getPunctuality() + 2);
                                        current.setFriendliness(current.getFriendliness() - 1);
                                        current.setCompetence(current.getCompetence() - 1);
                                        attorneys.add(current);
                                    }

                                }
                                count = 0;
                            }
                            else if (managerChoice.equalsIgnoreCase("c")) {
                                yes = false;
                                for (int i = 0; i < busy; i++) {
                                    if (!(attorneysOccupied.isEmpty())) {
                                        current = attorneysOccupied.remove();
                                        current.setCompetence(current.getCompetence() + 1);
                                        attorneysOccupied.add(current);
                                    }
                                }
                                for (int i = (numLawyers - busy); i > 0; i--) {
                                    if (!(attorneys.isEmpty())) {
                                        current = attorneys.remove();
                                        current.setCompetence(current.getCompetence() + 1);
                                        attorneys.add(current);
                                    }

                                }
                                count = 0;
                            } else {
                                throw new IllegalArgumentException();
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println("The letter of the law dictates that you enter an 'A', a 'B', or a 'C'.");
                        }
                    }
                }

            }
            count = count + 1;
        }
        while (!arrivalTimes.isEmpty()) {
            next = arrivalTimes.remove();
            longerWaitTimes.addNumber(totalTime - next);
        }
        System.out.println("");
        System.out.println(" ________________________");
        System.out.println("|                        |");
        System.out.println("|Clients assisted: " + clients);
        System.out.println("|________________________|");

        int totalHours = 0;
        while (!(attorneysOccupied.isEmpty())) {
            attorneys.add(attorneysOccupied.remove());
        }

        NetProfit profits = new NetProfit();
        profits.addLosses(totalTime, hourly, numLawyers);

        profits.addProfits(clients, clientHourly, meetingDuration);
        System.out.println("\n******************************");
        System.out.println("Net Profit: $" + profits.calculateProfits());
        System.out.println("\n******************************");
        if (waitTimes.howManyNumbers() > 0)
            System.out.println("Average wait for clients: " + waitTimes.average() + " minutes");
        if (longerWaitTimes.howManyNumbers() > 0)
            System.out.println("Average wait for customers left on queue: " + longerWaitTimes.average() + " minutes");
    }
}
