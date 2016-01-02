import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

public class TuringInit {

    public TuringInit() {
        Set<String> states = new HashSet<>();
        Set<String> alphabets = new HashSet<>();
        Set<String[]> transitions = new HashSet<String[]>();
        String tape=new String();
        int headPosition=0;


        boolean readFromFile = true;
        String[] tmpReturn = initInput(readFromFile,states,alphabets,transitions,tape,headPosition);
        tape=tmpReturn[0];
        headPosition=Integer.parseInt(tmpReturn[1]);
        boolean writeInFile = true;

        printTheInput(writeInFile,states,alphabets,transitions,tape,headPosition);

        Turing turing = new Turing(states,alphabets,transitions,tape,headPosition,writeInFile);
        try {
            turing.turIt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printTheInput(boolean writeInFile, Set<String> states, Set<String> alphabets, Set<String[]> transitions, String tape, int headPosition) {
        if (!writeInFile) {
            printStates( states);
            printAlphabets(alphabets);
            printTransitions(transitions);
            printX("tape :", tape);
            printX("head position :", Integer.toString(headPosition));
        }else{


            try {
                File file = new File("file.txt");
                file.createNewFile();
                FileWriter writer = null;
                writer = new FileWriter(file);
                writer.write("states :\n");
                for (String state : states) {
                    writer.write(state+"\n");
                }
                writer.write("alphabets :\n");
                for (String alph : alphabets) {
                    writer.write(alph+"\n");
                }

                writer.write("transitions\n");
                int i = 0;
                for (String[] transition : transitions) {
                    writer.write("transition #" + i+"\n");
                    for (String t : transition
                            ) {
                        writer.write(t + " ");
                    }
                    writer.write("\n");
                    writer.write(tape+"\n");
                    writer.write(headPosition+"\n");
                    i++;
                }
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    private static void printX(String x, String tape) {
        System.out.println(x);
        System.out.println(tape);
    }

    private static void printTransitions(Set<String[]> transitions) {
        System.out.println("transitions");
        int i = 0;
        for (String[] transition : transitions) {
            System.out.println("transition #" + i);
            for (String t : transition
                    ) {
                System.out.print(t + " ");
            }
            System.out.println();
            i++;
        }
    }

    private static void printAlphabets(Set<String> alphabets) {
        System.out.println("alphabets :");
        for (String alph : alphabets) {
            System.out.println(alph);
        }
    }

    private static void printStates(Set<String> states) {
        System.out.println("states :");
        for (String state : states) {
            System.out.println(state);
        }
    }

    private static String[] initInput(boolean readFromFile,Set<String> states,Set<String> alphabets,Set<String[]> transitions,String tape,int headPosition) {

        Scanner scan = new Scanner(System.in);
        if (readFromFile) {
            try {
                scan = new Scanner(new File("input.txt"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        if(!readFromFile)
        System.out.println("----- the program will not make sure that the input is valid -----");

        scanStates(scan, states,readFromFile);

        scanAlphbets(scan, alphabets,readFromFile);

        scanTransitions(scan, transitions,readFromFile);

        tape = scanTape(scan,readFromFile);

        headPosition = scanHeadPosition(scan,readFromFile);
        return new String[]{tape,Integer.toString(headPosition)};
    }

    private static int scanHeadPosition(Scanner scan,boolean readFromFile) {
        int headPosition;
        if(!readFromFile)
        System.out.println("enter the head position :");
        headPosition = scan.nextInt();
        return headPosition;
    }

    private static String scanTape(Scanner scan,boolean readFromFile) {
        String tape;
        if(!readFromFile)
        System.out.println("enter the tape :");
        tape = scan.nextLine();
        return tape;
    }

    private static void scanTransitions(Scanner scan, Set<String[]> transitions,boolean readFromFile) {
        if(!readFromFile)
        System.out.println("enter # of transitions :");
        int numberOftransitions = scan.nextInt();

        scan.nextLine();
        for (int i = 0; i < numberOftransitions; i++) {
            if(!readFromFile)
            System.out.println("enter transition #" + i);
            String[] tempTransation = new String[5];
            if(!readFromFile)
            System.out.println("read state");
            tempTransation[0] = scan.nextLine();
            if(!readFromFile)
            System.out.println("read alphabet");
            tempTransation[1] = scan.nextLine();
            if(!readFromFile)
            System.out.println("write state");
            tempTransation[2] = scan.nextLine();
            if(!readFromFile)
            System.out.println("write alphabet");
            tempTransation[3] = scan.nextLine();
            if(!readFromFile)
            System.out.println("read direction (R,L)");
            tempTransation[4] = scan.nextLine();

            transitions.add(tempTransation);

        }
    }

    private static void scanAlphbets(Scanner scan, Set<String> alphabets , boolean readFromFile) {
        if(!readFromFile)
        System.out.println("enter # of alphabet :");
        int numberOfAlph = scan.nextInt();

        scan.nextLine();
        for (int i = 0; i < numberOfAlph; i++) {
            if(!readFromFile)
            System.out.println("enter alphabet #" + i);
            alphabets.add(scan.nextLine());
        }
    }

    private static void scanStates(Scanner scan, Set<String> states,boolean readFromFile) {
        if(!readFromFile)
        System.out.println("enter # of states (qa is the accept state \n" +
                " qr is the reject state \n" +
                " qs is the start state):");
        int numberOfStates = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < numberOfStates; i++) {
            if(!readFromFile)
            System.out.println("enter state #" + i);

            states.add(scan.nextLine());
        }
    }
}
