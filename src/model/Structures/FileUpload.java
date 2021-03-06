package model.Structures;

import model.Country;
import model.Cd;
import model.Employee;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class FileUpload extends Thread{

    private BufferedReader br;
    private BufferedReader rb;
    private Cd fb;
    private int amount;
    private static linkList<Country> countries;

    public FileUpload(BufferedReader br, BufferedReader rb, Cd fb, int amount) {
        this.br = br;
        this.rb = rb;
        this.fb = fb;
        this.amount = amount;
    }

    @Override
    public void run() {
        override();

        try {
            readCountry();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int num = 0;
        int num1 = 0;
        int limit = (int) Math.ceil(amount/2);
        int limit2 = (int) Math.ceil(amount/limit);
        String line1 = null;
        String line2 = "null";

        try {
            line1 = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (line1 != null && num < limit) {
            try {
                line1 = br.readLine();
                if (line1 != null) {
                    String name = line1.split(",")[0];
                    while (line2 != null  && num1 < limit2){
                        line2 = rb.readLine();

                        if (line2 != null) {
                            String lastName = line2.split(",")[0];
                            String sex = (Math.random() < 0.5) ? "Female" : "Male";
                            String birtday = getRandomDate();
                            String height = new DecimalFormat("#.00").format((Math.random() * (1.80 - 1.50) + 1.50));
                            String nationality = getNationality();
                            Employee p = new Employee(fb.getTotalEmployees() + 1, name, lastName, sex, birtday, height, nationality);
                            fb.addEmployee(p);
                            num1++;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            num++;
            num1 = 0;
        }
        try {
            br.close();
            rb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void override() {
        fb.setTotalEmployees(0);
        fb.setRBTEmployeeFullName(new TreeRBT<>());
        fb.setBSTEmployeeLastName(new TreeBST<>());
        fb.setRBTEmployeeName(new TreeRBT<>());
        fb.setAVLEmployeeCode(new TreeAVL<>());
    }

    public  int[] distribution(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        double random = Math.random();
        int initYear = 0;
        int endYear = 0;

        if (random >= 0 && random < 0.19){
            initYear = year - 14;
            endYear = year;
        }else if (random >= 0.19 && random < 0.32){
            initYear = year - 24;
            endYear = year - 15;
        }else if(random >= 0.32 && random < 0.72){
            initYear = year - 54;
            endYear = year - 25;
        }else if(random >= 0.72 && random < 0.85){
            initYear = year - 64;
            endYear = year - 55;
        }else{
            initYear = 1900;
            endYear = year - 65;
        }
        return new int[]{initYear, endYear};
    }


    public String getRandomDate() {
        int[] limit = distribution();
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), ((int) (Math.random()*(13-1)+1)), ((int) (Math.random()*(31-1)+1)));
        return new SimpleDateFormat("dd").format(cal.getTime()) + "/" + new SimpleDateFormat("MM").format(cal.getTime()) + "/" + ((int) (Math.random()*(limit[1]-limit[0])+limit[0]));
    }

    public static String getNationality() {
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
        BufferedReader bn = new BufferedReader(new FileReader("data/population_by_country_2020.csv"));
        countries = new linkList<>();
        String line = bn.readLine();
        double init = 0;

        while (line != null) {
            line = bn.readLine();
            if (line != null) {
                String[] data = line.split(",");
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
        bn.close();
    }
}
