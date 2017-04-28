public class NetProfit {
    private double profits = 0;
    private double losses = 0;
    
    public void addLosses(long totalTime, double hourly, int numLawyers) {
        losses = losses + ((((double)(totalTime/60)) * hourly) * (double)(numLawyers));
        System.out.println("Losses: $" + losses);
    }
    public void addProfits(int clients, double clientHourly, int meetingDuration) {
        profits = ((double)(clients)) * clientHourly;
        System.out.println("Profits: $" + profits);
    }
    public double calculateProfits() {
        profits = profits - losses;
        return profits;
    }
}