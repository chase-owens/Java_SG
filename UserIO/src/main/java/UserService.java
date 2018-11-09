/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Scanner;

/**
 *
 * @author chaseowens
 */
public class UserService implements UserIO {

    final Scanner capture = new Scanner(System.in);

    private void print(String message) {
        System.out.println(message);
    }

    @Override
    public void ioPrint(String message) {
        print(message);
    }

    private double readDouble(String prompt) {
        boolean valid = false;
        double doubleEntered = 0.0;

        do {
            try {
                print(prompt);
                String input = capture.nextLine();
                doubleEntered = Double.parseDouble(input);
                valid = true;
            } catch (NumberFormatException ex) {
            }

        } while (!valid);

        return doubleEntered;
    }

    @Override
    public double ioReadDouble() {
        return readDouble("Please enter a number with two decimal places");
    }

    private double readDoubleRange(String prompt, double min, double max) {
        boolean valid = false;
        double doubleEntered = 0.0;

        do {
            try {
                print("Min: " + min + "Max: " + max);
                print(prompt);
                String input = capture.nextLine();
                doubleEntered = Double.parseDouble(input);
                if (doubleEntered >= min || doubleEntered <= max) {
                    valid = true;
                }
            } catch (NumberFormatException ex) {
            }
        } while (!valid);

        return doubleEntered;
    }

    @Override
    public double ioReadDoubleRange() {
        double double1 = readDouble("Enter floor");
        double double2 = readDouble("Enter ceiling");
        return readDoubleRange("Please enter a number with two decimal places", double1, double2);
    }

    private float readFloat(String prompt) {
        boolean valid = false;
        float floatEntered = 0;

        do {
            try {
                print(prompt);
                String input = capture.nextLine();
                floatEntered = Float.parseFloat(input);
                valid = true;
            } catch (NumberFormatException ex) {
            }

        } while (!valid);

        return floatEntered;
    }

    @Override
    public float ioReadFloat() {
        return readFloat("Please enter a float.");
    }

    private float readFloatRange(String prompt, float min, float max) {
        boolean valid = false;
        float floatEntered = 0;

        do {
            try {
                print("Min: " + min + "Max: " + max);
                print(prompt);
                String input = capture.nextLine();
                floatEntered = Float.parseFloat(input);
                if (floatEntered >= min || floatEntered <= max) {
                    valid = true;
                }
            } catch (NumberFormatException ex) {
            }
        } while (!valid);

        return floatEntered;
    }

    @Override
    public float ioReadFloatRange() {
        float min = readFloat("Enter float floor");
        float max = readFloat("Enter float ceiling");
        return readFloatRange("Enter a float with between the min max range: ", min, max);
    }

    private int readInt(String prompt) {
        boolean valid = false;
        int intEntered = 0;

        do {
            try {
                print(prompt);
                String input = capture.nextLine();
                intEntered = Integer.parseInt(input);
                valid = true;
            } catch (NumberFormatException ex) {
            }

        } while (!valid);

        return intEntered;
    }

    @Override
    public int ioReadInt() {
        return readInt("Enter a whole number: ");
    }

    private int readIntRange(String prompt, int min, int max) {
        boolean valid = false;
        int intEntered = 0;

        do {
            try {
                print("Min: " + min + "Max: " + max);
                print(prompt);
                String input = capture.nextLine();
                intEntered = Integer.parseInt(input);
                if (intEntered >= min || intEntered <= max) {
                    valid = true;
                }
            } catch (NumberFormatException ex) {
            }
        } while (!valid);

        return intEntered;
    }

    @Override
    public int ioReadIntRange() {
        int floor = readInt("Pick a number that will be the floor of your range");
        int ceiling = readInt("Pick a number that will be the floor of your ceiling");

        return readIntRange("Enter a nimber within this range", floor, ceiling);
    }

    private long readLong(String prompt) {
        boolean valid = false;
        long longEntered = 0;

        do {
            try {
                print(prompt);
                String input = capture.nextLine();
                longEntered = Long.parseLong(input);
                valid = true;
            } catch (NumberFormatException ex) {
            }

        } while (!valid);

        return longEntered;
    }

    @Override
    public long ioReadLong() {
        return readLong("Enter a number with lots of decimal places: ");
    }

    private long readLongRange(String prompt, long min, long max) {
        boolean valid = false;
        long longEntered = 0;

        do {
            try {
                print("Min: " + min + "Max: " + max);
                print(prompt);
                String input = capture.nextLine();
                longEntered = Long.parseLong(input);
                if (longEntered >= min || longEntered <= max) {
                    valid = true;
                }
            } catch (NumberFormatException ex) {
            }
        } while (!valid);

        return longEntered;
    }

    @Override
    public long ioReadLongRange() {
        long min = readLong("enter the lower bounds of a float range");
        long max = readLong("enter the upper bounds of a float range");
        return readLongRange("Enter a float within the range specified: ", min, max);
    }

    private String readString(String prompt) {
        print(prompt);
        String input = capture.nextLine();
        return input;
    }

    @Override
    public String ioReadString() {
        return readString("Enter whatever you want.");
    }

    @Override
    public int ioReadIntRAnge() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
