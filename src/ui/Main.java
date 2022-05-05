package ui;

import model.Structures.linkList;
import model.Country;
import model.Cd;
import model.User;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    private static final String SRC_FILE = "data/Data.txt";
    private static linkList<Country> countries;

    public static void main(String[] args) throws Exception {
        mainDriver();
    }

    public static void mainDriver() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("data/Names_2010Census.csv"));
        BufferedReader rb = new BufferedReader(new FileReader("data/babynames-clean.csv"));
        Cd fb = new Cd(); //Crud
        init(br, rb, fb);
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(SRC_FILE));
        os.writeObject(fb);
        os.close();
    }

    public static void init(BufferedReader br, BufferedReader rb, Cd fb) throws IOException {
        try {
            readCountry();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int num = 1;
        int num1 = 1;
        DecimalFormat df = new DecimalFormat("#.00");
        String cord = br.readLine();


        while (cord != null && num < 20) {
            try {
                cord = br.readLine();
                if (cord != null) {
                    String name = cord.split(",")[0];

                    String cord1 = rb.readLine();

                    while (cord1 != null  && num1 < 100){
                        cord1 = rb.readLine();

                        String ln = cord1.split(",")[0];
                        String sex = (Math.random() < 0.5)? "Female": "Male";
                        String bd = randomDate();
                        String height =  df.format((Math.random()*(1.80-1.50)+1.50));
                        String nationality = getNationality();

                        User p = new User(fb.getTotalUsers() + 1, name, ln, sex, bd, height, nationality); //Person

                        fb.addUser(p);
                        num1++;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            num++;
            num1 = 1;
        }
        try {
            br.close();
            rb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] distribution(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        double random = Math.random();
        int sYear = 0; //Start of year
        int eYear = 0; //End of year

        if (random >= 0 && random < 0.19){
            sYear = year - 14;
            eYear = year;
        }else if (random >= 0.19 && random < 0.32){
            sYear = year - 24;
            eYear = year - 15;
        }else if(random >= 0.32 && random < 0.72){
            sYear = year - 54;
            eYear = year - 25;
        }else if(random >= 0.72 && random < 0.85){
            sYear = year - 64;
            eYear = year - 55;
        }else{
            sYear = 1900;
            eYear = year - 65;
        }
        return new int[]{sYear, eYear};
    }



    public static String randomDate() {
        int[] limit = distribution();
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), ((int) (Math.random()*(13-1)+1)), ((int) (Math.random()*(31-1)+1)));

        return new SimpleDateFormat("dd").format(cal.getTime()) + "/" + new SimpleDateFormat("MM").format(cal.getTime()) + "/" + ((int) (Math.random()*(limit[1]-limit[0])+limit[0]));
    }

    public static String getNationality(){
        double random = Math.random();
        boolean flag = true;
        int cont = 0;

        while (flag){
            if (random >= countries.get(cont).getInit() && random < countries.get(cont).getEnd()){
                flag = false;
                return countries.get(cont).getName();
            }
            cont++;
        }
        return null;
    }

    public static void readCountry() throws IOException {
        BufferedReader brr = new BufferedReader(new FileReader("data/population_by_country_2020.csv"));
        countries = new linkList<>();
        String cord = brr.readLine();
        double init = 0;

        while (cord != null) {
            cord = brr.readLine();

            if (cord != null) {

                String[] data = cord.split(",");
                String name = data[0];
                double end;

                end = ((Double.parseDouble(data[1])/(790*1000000))/10);

                if (countries.isEmpty()) {
                    countries.add(new Country(name, init, end));

                }else{
                    init = (countries.get(countries.size()-1).getEnd());
                    end = end + init + 0.0000496;
                    countries.add(new Country(name, init, end));
                }
            }else {
                break;
            }

        }
        brr.close();
    }
}

