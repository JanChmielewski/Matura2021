import java.io.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {

        File file = new File("/Users/janchmielewski/Downloads/DANE_2105/instrukcje.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String line;
        String result = "";
        HashMap<Character, Integer> counter = new HashMap<Character, Integer>();
        int max = -1;
        String commandMax = "";
        String currentCommand = "";
        int currentLenght = 0;

        while((line = bufferedReader.readLine()) != null) {
            String[] arg = line.split(" ");
            String command = arg[0];
            char c = arg[1].charAt(0);

            if(currentCommand.isEmpty()) {
                currentCommand = command;
            }

            if(!currentCommand.equals(command)) {
                if (max < currentLenght) {
                    max = currentLenght;
                    commandMax = currentCommand;
                }

                currentCommand = command;
                currentLenght = 1;

            } else {
                currentLenght++;
            }

            if (command.equalsIgnoreCase("dopisz")) {
                result = dopisz(result, c);
                int amount = counter.containsKey(c) ? counter.get(c) : 0;
                counter.put(c, amount + 1);
            } else if (command.equalsIgnoreCase("usun")) {
                result = usun(result);
            } else if (command.equalsIgnoreCase("zmien")) {
                result = zmien(result, c);
            } else if (command.equalsIgnoreCase("przesun")) {
                result = przesun(result, c);
            }
        }
        bufferedReader.close();

        int max1 = -1;
        char maxC = '0';
        for(char c : counter.keySet()) {
            if (counter.get(c) > max1) {
                max1 = counter.get(c);
                maxC = c;
            }
        }

        System.out.println(dlugoscListy(result));
        System.out.println(commandMax + " " + max);
        System.out.println(maxC + " " + max1);
        System.out.println(result);
    }

    private static String przesun(String txt, char c) {
        int t = txt.indexOf(c);
        if(t == -1) {
            return txt;
        } if( c == 'Z') {
            c = 'A';
        } else {
            c++;
        }
        char[] chars = txt.toCharArray();
        chars[t] = c;
        return new String(chars);
    }

    private static String zmien(String txt, char c) {
        char[] chars = txt.toCharArray();
        chars[chars.length - 1] = c;
        return new String(chars);

    }

    private static String usun(String txt) {
        return txt.substring(0, txt.length() - 1);
    }

    static String dopisz(String txt, char c) {
        return txt + c;
    }

    static int dlugoscListy(String txt) {
        return txt.length();
    }

}
