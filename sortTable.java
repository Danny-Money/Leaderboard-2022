import java.io.*;

public class sortTable {
    //Setup program-wide variables
    final static String[] paths = {"old entries.txt","new entry.txt","new entries.txt", "errors.txt"};
    final static String[] notAllowed  = {"break","cancel","ahole", "anus","ash0le","ash0les","asholes","ass","Ass Monkey","Assface","assh0le","assh0lez","asshole","assholes","assholz","asswipe","azzhole","bassterds","bastard","bastards","bastardz","basterds","basterdz","Biatch","bitch","bitches","Blow Job","boffing","butthole","buttwipe","c0ck","c0cks","c0k","Carpet Muncher","cawk","cawks","Clit","cnts","cntz","cock","cockhead","cock-head","cocks","CockSucker","cock-sucker","crap","cum","cunt","cunts","cuntz","dick","dild0","dild0s","dildo","dildos","dilld0","dilld0s","dominatricks","dominatrics","dominatrix","dyke","enema","f u c k","f u c k e r","fag","fag1t","faget","fagg1t","faggit","faggot","fagg0t","fagit","fags","fagz","faig","faigs","fart","flipping the bird","fuck","fucker","fuckin","fucking","fucks","Fudge Packer","fuk","Fukah","Fuken","fuker","Fukin","Fukk","Fukkah","Fukken","Fukker","Fukkin","g00k","God-damned","h00r","h0ar","h0re","hells","hoar","hoor","hoore","jackoff","jap","japs","jerk-off","jisim","jiss","jizm","jizz","knob","knobs","knobz","kunt","kunts","kuntz","Lezzian","Lipshits","Lipshitz","masochist","masokist","massterbait","masstrbait","masstrbate","masterbaiter","masterbate","masterbates","Motha Fucker","Motha Fuker","Motha Fukkah","Motha Fukker","Mother Fucker","Mother Fukah","Mother Fuker","Mother Fukkah","Mother Fukker","mother-fucker","Mutha Fucker","Mutha Fukah","Mutha Fuker","Mutha Fukkah","Mutha Fukker","n1gr","nastt","nigger;","nigur;","niiger;","niigr;","orafis","orgasim;","orgasm","orgasum","oriface","orifice","orifiss","packi","packie","packy","paki","pakie","paky","pecker","peeenus","peeenusss","peenus","peinus","pen1s","penas","penis","penis-breath","penus","penuus","Phuc","Phuck","Phuk","Phuker","Phukker","polac","polack","polak","Poonani","pr1c","pr1ck","pr1k","pusse","pussee","pussy","puuke","puuker","qweir","recktum","rectum","retard","sadist","scank","schlong","screwing","semen","sex","sexy","Sh!t","sh1t","sh1ter","sh1ts","sh1tter","sh1tz","shit","shits","shitter","Shitty","Shity","shitz","Shyt","Shyte","Shytty","Shyty","skanck","skank","skankee","skankey","skanks","Skanky","slag","slut","sluts","Slutty","slutz","son-of-a-bitch","tit","turd","va1jina","vag1na","vagiina","vagina","vaj1na","vajina","vullva","vulva","w0p","wh00r","wh0re","whore","xrated","xxx","b!+ch","bitch","blowjob","clit","arschloch","fuck","shit","ass","asshole","b!tch","b17ch","b1tch","bastard","bi+ch","boiolas","buceta","c0ck","cawk","chink","cipa","clits","cock","cum","cunt","dildo","dirsa","ejakulate","fatass","fcuk","fuk","fux0r","hoer","hore","jism","kawk","l3itch","l3i+ch","masturbate","masterbat*","masterbat3","motherfucker","s.o.b.","mofo","nazi","nigga","nigger","nutsack","phuck","pimpis","pusse","pussy","scrotum","sh!t","shemale","shi+","sh!+","slut","smut","teets","tits","boobs","b00bs","teez","testical","testicle","titt","w00se","jackoff","wank","whoar","whore","*damn","*dyke","*fuck*","*shit*","@$$","amcik","andskota","arse*","assrammer","ayir","bi7ch","bitch*","bollock*","breasts","butt-pirate","cabron","cazzo","chraa","chuj","Cock*","cunt*","d4mn","daygo","dego","dick*","dike*","dupa","dziwka","ejackulate","Ekrem*","Ekto","enculer","faen","fag*","fanculo","fanny","feces","feg","Felcher","ficken","fitt*","Flikker","foreskin","Fotze","Fu(*","fuk*","futkretzn","gook","guiena","h0r","h4x0r","hell","helvete","hoer*","honkey","Huevon","hui","injun","jizz","kanker*","kike","klootzak","kraut","knulle","kuk","kuksuger","Kurac","kurwa","kusi*","kyrpa*","lesbo","mamhoon","masturbat*","merd*","mibun","monkleigh","mouliewop","muie","mulkku","muschi","nazis","nepesaurio","nigger*","orospu","paska*","perse","picka","pierdol*","pillu*","pimmel","piss*","pizda","poontsee","poop","porn","p0rn","pr0n","preteen","pula","pule","puta","puto","qahbeh","queef*","rautenberg","schaffer","scheiss*","schlampe","schmuck","screw","sh!t*","sharmuta","sharmute","shipal","shiz","skribz","skurwysyn","sphencter","spic","spierdalaj","splooge","suka","b00b*","testicle*","titt*","twat","vittu","wank*","wetback*","wichser","wop*","yed","zabourah"};
    static boolean hasNewEntry = false, swearWord = false;
    public static void main(String[] args){
        //Setup main function variables
        final int namePos = 0, scorePos = 1, teamPos = 2, hashPos = 3;
        boolean isDuplicate = false, is3536Member = false;

        //Read new entry from file and split into string array to declare entryClass object
        String[] newEntrySplit = readNewEntry().split(",");
        entryClass newEntry = new entryClass(newEntrySplit[namePos], Integer.parseInt(newEntrySplit[scorePos]), Integer.parseInt(newEntrySplit[teamPos]), newEntrySplit[hashPos]);

        //Compares the new entry's name to a string array of not-allowed names
        for (String string : notAllowed) {
            if(newEntrySplit[0].toLowerCase().equals(string.toLowerCase())){
                hasNewEntry = false;
                swearWord = true;
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

            //Test to see if person already has a higher/equal score on the leaderboard
            for (entryClass entry : entries) {
                if(entry.getName().equals(newEntry.getName())){
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
            }

        } else{
            if(swearWord){
                writeToFile(paths[3], "Swear word detected in name category, not accepted.");
            }else{
                writeToFile(paths[3], "Computed SHA-256 hash does not match recieved hash, sorting failed.");
            }
        }
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