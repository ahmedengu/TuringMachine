import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ahmed Kamel Taha on 26/12/2015.
 */
public class Turing {

    Set<String> states;
    Set<String> alphabets;
    Set<String[]> transitions;
    String tape;
    int headPosition;
    String currestState;
    boolean writeInFile;


    public Turing(Set<String> states, Set<String> alphabets, Set<String[]> transitions, String tape, int headPosition, boolean writeInFile) {
        this.states = states;
        this.alphabets = alphabets;
        this.transitions = transitions;
        this.tape = tape;
        this.headPosition = headPosition;
        currestState = "qs";
        this.writeInFile = writeInFile;


    }

    public boolean turIt() throws IOException {
        File file = new File("output.txt");
        file.createNewFile();
        FileWriter writer = null;
        writer = new FileWriter(file);

        while (!(currestState.equals(("qa"))) && !(currestState.equals("qr"))) {
            if (writeInFile) {
                writer.write(tape.substring(0, headPosition) + "'" + currestState + "'" + tape.substring(headPosition) + "\n");
            } else
                System.out.println(tape.substring(0, headPosition) + "'" + currestState + "'" + tape.substring(headPosition));
            String[] foundTransition = new String[5];
            for (String[] transition : transitions
                    ) {
//                System.out.println(transition[0] + " "+ transition[1] + " "+ " "+ transition[2]+ " "+ transition[3]+ " "+ transition[4] );
                if ((transition[0].contains(currestState) || transition[0].contains("_")) && transition[1].contains("" + tape.charAt(headPosition))) {
                    foundTransition = transition;
                }
            }

            if (foundTransition[0] == null) {
                if (writeInFile) {
                    writer.write("no valid transition , state=" + currestState + " tape=" + tape.charAt(headPosition) + "\n");
                } else
                    System.out.println("no valid transition , state=" + currestState + " tape=" + tape.charAt(headPosition));
                return false;
            } else {
                currestState = foundTransition[2];
                tape = tape.substring(0, headPosition) + foundTransition[3] + tape.substring(headPosition + 1);
            }

            if (foundTransition[4].equals("R")) {
                headPosition++;
            } else {
                headPosition--;
            }

            if (headPosition < 0) {

                int tapHeadPos = 0;
                while (headPosition < 0) {
                    tape = "_" + tape;
                    headPosition++;
                    tapHeadPos++;
                }
                headPosition = tapHeadPos;

            }
            while (tape.length() <= headPosition) {
                tape += "_";
            }
        }


        if (currestState.equals("qa")) {
            if (writeInFile) {
                writer.write(tape.replace("'", "").replace("qa", "") + "\n");

                writer.write("accepted\n");
                writer.flush();
                writer.close();
            } else {
                System.out.println(tape.replace("'", "").replace("qa", ""));

                System.out.println("accepted");
            }
            return true;
        } else {
            if (writeInFile) {
                writer.write(tape.replace("'", "").replace("qa", "") + "\n");

                writer.write("rejected\n");
                ;
                writer.flush();
                writer.close();
            } else {
                System.out.println(tape.replace("'", "").replace("qa", ""));

                System.out.println("rejected");
            }
            return false;
        }
    }


    public Set<String> getStates() {
        return states;
    }

    public void setStates(Set<String> states) {
        this.states = states;
    }

    public Set<String> getAlphabets() {
        return alphabets;
    }

    public void setAlphabets(Set<String> alphabets) {
        this.alphabets = alphabets;
    }

    public Set<String[]> getTransitions() {
        return transitions;
    }

    public void setTransitions(Set<String[]> transitions) {
        this.transitions = transitions;
    }

    public String getTape() {
        return tape;
    }

    public void setTape(String tape) {
        this.tape = tape;
    }

    public int getHeadPosition() {
        return headPosition;
    }

    public void setHeadPosition(int headPosition) {
        this.headPosition = headPosition;
    }

    public String getCurrestState() {
        return currestState;
    }

    public void setCurrestState(String currestState) {
        this.currestState = currestState;
    }
}
