import java.util.Random;
public class RandomNameGenerator {
    Boolean bob = true;
    Boolean vicki = true;
    Boolean cassie = true;
    Boolean regina = true;
    Boolean zach = true;
    Boolean nessa = true;
    Boolean colleen = true;
    Boolean nathan = true;
    Boolean allison = true;
    Boolean victoria = true;
    Boolean amin = true;
    Boolean erin = true;
    Boolean adam = true;
    Boolean casey = true;
    Boolean jim = true;
    Boolean bill = true;
    Boolean hillary = true;

    public String generate() {
        String Bob = "Bob";
        String Vicki = "Vicki";
        String Cassie = "Cassie";
        String Regina = "Regina";
        String Zach = "Zach";
        String Nessa = "Nessa";
        String Colleen = "Colleen";
        String Nathan = "Nathan";
        String Allison = "Allison";
        String Victoria = "Victoria";
        String Amin = "Amin";
        String Erin = "Erin";
        String Adam = "Adam";
        String Casey = "Casey";
        String Jim = "Jim";
        String Bill = "Bill";
        String Hillary = "Hillary";
        Boolean randomTrue = true;
        while (randomTrue = true) {
        Random rg = new Random();
        int numEmployees = rg.nextInt(14) + 1;

        if (numEmployees == 0 && bob) {
            bob = false;
            return "0";
        } else if (numEmployees == 1 && vicki) {
            vicki = false;
            return Vicki;
        } else if (numEmployees == 2 && cassie) {
            cassie = false;
            return Cassie;
        } else if (numEmployees == 3 && regina) {
            regina = false;
            return Regina;
        } else if (numEmployees == 4 && zach) {
            zach = false;
            return Zach;
        } else if (numEmployees == 5 && nessa) {
            nessa = false;
            return Nessa;
        } else if (numEmployees == 6 && colleen) {
            colleen = false;
            return Colleen;
        }  else if (numEmployees == 7 && bill) {
            bill = false;
            return Bill;
        } else if (numEmployees == 8 && casey) {
            casey = false;
            return Casey;
        } else if (numEmployees == 9 && jim) {
            jim = false;
            return Jim;
        } else if (numEmployees == 10 && nathan) {
            nathan = false;
            return Nathan;
        } else if (numEmployees == 11 && allison) {
            allison = false;
            return Allison;
        } else if (numEmployees == 12 && victoria) {
            victoria = false;
            return Victoria;
        } else if (numEmployees == 13 && amin) {
            amin = false;
            return Amin;
        } else if (numEmployees == 14 && erin) {
            erin = false;
            return Erin;
        } else if (numEmployees == 15 && adam) {
            adam = false;
            return Adam;
        }
    }
        return "Oh no";

    }

}

