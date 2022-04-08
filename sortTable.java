import java.io.*;

public class sortTable {
    //Setup program-wide variables
    final static String[] paths = {"old entries.txt","new entry.txt","new entries.txt", "errors.txt"};
    static boolean hasNewEntry = false, isNotAllowed = false;
    public static void main(String[] args){
        //Setup main function variables
        final int namePos = 0, scorePos = 1, teamPos = 2, hashPos = 3;
        boolean isDuplicate = false, is3536Member = false;
        String[] checkAllowed = notAllowed.getNotAllowed();

        //Read new entry from file and split into string array to declare entryClass object
        String[] newEntrySplit = readNewEntry().split(",");
        entryClass newEntry = new entryClass(newEntrySplit[namePos], Integer.parseInt(newEntrySplit[scorePos]), Integer.parseInt(newEntrySplit[teamPos]), newEntrySplit[hashPos]);

        System.out.println("Comp hash: " + newEntry.getHashCom());
        System.out.println("Rec hash:  " + newEntry.getHashRec());

        //Compares the new entry's name to a string array of not-allowed names
        for (String string : checkAllowed) {
            if(newEntrySplit[0].toLowerCase().equals(string.toLowerCase())){
                hasNewEntry = false;
                isNotAllowed = true;
            }
        }
        
        //Checks to see if new entry is valid
        if (hasNewEntry && (newEntry.getHashCom().equals(newEntry.getHashRec()))){
            newEntry = new entryClass(newEntrySplit[namePos], Integer.parseInt(newEntrySplit[scorePos]), Integer.parseInt(newEntrySplit[teamPos]), newEntrySplit[hashPos]);

            String[] oldEntries = readOldEntries();

            entryClass[] entries = new entryClass[6];
            for(int i = 0; i < 5; i++){
                entries[i] = new entryClass(setOldEntry(oldEntries[i]));
            }
            entries[5] = newEntry;

            //Detect 3536 team members
            if(newEntry.getTeamNum() == 3536){
                is3536Member = true;
            }

            int i = 0;
            //Test to see if person already has a higher/equal score on the leaderboard
            for (entryClass entry : entries) {
                if(i == 5){
                    break;
                }
                System.out.println(i);
                System.out.println(entry.getName());
                System.out.println(newEntry.getName());
                i++;

                if(entry.getName().equals(newEntry.getName())){
                    System.out.println("New entry: " + newEntry.getName());
                    System.out.println("Old entry: " + entry.getName());
                    System.out.println("Duplicate name detected");
                    if(entry.getTeamNum() == newEntry.getTeamNum()){
                        if(entry.getScore() >= newEntry.getScore()){
                            //duplicate detected
                            isDuplicate = true;
                        } else{
                            //Duplicate has higher score
                            entry = newEntry;
                            newEntry = new entryClass();
                        }
                    }
                }
            }
            
            if(!isDuplicate && !is3536Member){
                sortEntries(entries);
            } else{
                String logEntries = readLog();
                if(is3536Member){
                    writeToFile(paths[3], logEntries + "Entry is 3536 member, not accepted. (" + readNewEntry() + ")");
                } else if (isDuplicate){
                    writeToFile(paths[3], logEntries + "Entry is duplicate with lower score, not writing to leaderboard. (" + readNewEntry() + ")");
                }
            }

        } else{
            String logEntries = readLog();
            if(isNotAllowed){
                writeToFile(paths[3], logEntries + "Un-allowed string detected in name category, not accepted. (" + readNewEntry() + ")");
            }else{
                writeToFile(paths[3], logEntries + "Computed SHA-256 hash does not match recieved hash, sorting failed. (" + readNewEntry() + ")");
            }
        }
    }

    private static String readLog(){
        String oldEntries = "";
        try{
            File newFile = new File(paths[3]);
            FileReader fr = new FileReader(newFile); 
            BufferedReader br = new BufferedReader(fr);

            while(true){
                String newLine = br.readLine();
                if(newLine != null){
                    oldEntries += newLine + "\n";
                } else{
                    break;
                }
            }
            fr.close();
        } catch(Exception e){
            e.printStackTrace();
        }

        return oldEntries;
    }

    private static void sortEntries(entryClass[] entries){
        int[][] sortTableVars = new int[6][2];
            for(int i = 0; i < 6; i++){
                sortTableVars[i][0] = i;
                sortTableVars[i][1] = entries[i].getScore();
            }

            int tempVarI = 0, n = sortTableVars.length;
            entryClass tempVarEC = new entryClass();
            for(int i = 0; i < n; i++){
                for(int j = 1; j < 6; j++){
                    if(sortTableVars[j-1][1] < sortTableVars[j][1]){
                        //swap elements
                        tempVarEC = entries[j-1];
                        entries[j-1] = entries[j];
                        entries[j] = tempVarEC;
                        tempVarEC = new entryClass();

                        tempVarI = sortTableVars[j-1][1];
                        sortTableVars[j-1][1] = sortTableVars[j][1];
                        sortTableVars[j][1] = tempVarI;
                        tempVarI = 0;
                    }    
                }
            }

            writeToFile(paths[2], getOutputString(entries));
    }

    private static void writeToFile(String filename, String output){
        try (PrintWriter out = new PrintWriter(filename)) {
            out.print(output);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private static String getOutputString(entryClass[] entries){
        String output = "";

        for(int i = 0; i < entries.length - 1; i++){
            if(i != entries.length - 2){
                output = output + entries[i].getName() + "," + entries[i].getScore() + "," + entries[i].getTeamNum() + "," + entries[i].getHashCom() + "\n";
            } else{
                output = output + entries[i].getName() + "," + entries[i].getScore() + "," + entries[i].getTeamNum() + "," + entries[i].getHashCom();
            }
        }

        return output;
    }

    private static String[] readOldEntries(){
        String inputLine = "";
        String[] entryLines = new String[5];

        try{
            File file = new File(paths[2]);
            FileReader fr = new FileReader(file); 
            BufferedReader br = new BufferedReader(fr);
            inputLine = br.readLine();

            for(int i = 0; i < 5; i++){
                if(inputLine == null){
                    break;
                }
                entryLines[i] = inputLine;
                inputLine = br.readLine();
            }

            br.close();
        } catch(Exception e){
            e.printStackTrace();
        }

        return entryLines;
    }

    private static String[] setOldEntry(String entry){
        return entry.split(",");
    }

    private static String readNewEntry(){
        String newInput = "";
        try{
            File newFile = new File(paths[1]);
            FileReader fr = new FileReader(newFile); 
            BufferedReader br = new BufferedReader(fr);
            newInput = br.readLine();

            if(newInput != null){
                hasNewEntry = true;
            } else{
                hasNewEntry = false;
            }
            fr.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        return newInput;
    }
}