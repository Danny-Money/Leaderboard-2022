import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class entryClass{

    private String name, hashRec, hashCom;
    private int score, teamNum;

    entryClass(String name, int score, int teamNum, String hash){
        this.name = name;
        this.score = score;
        this.teamNum = teamNum;
        this.hashRec = hash.toLowerCase();
        try{
            this.hashCom = toHexString(getSHA(name + "," + score + "," + teamNum + "Daniel's Idea")).toLowerCase();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    entryClass(String[] entryLine){
        this.name = entryLine[0];
        this.score = Integer.parseInt(entryLine[1]);
        this.teamNum = Integer.parseInt(entryLine[2]);
        this.hashRec = entryLine[3];
        try{
            this.hashCom = toHexString(getSHA(this.name + "," + this.score + "," + this.teamNum + "Daniel's Idea")).toLowerCase();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    entryClass(){
        this.name = "NoEntry";
        this.score = 0;
        this.teamNum = 0;
        this.hashRec = "0";
        this.hashCom = "0";
    }

    public String getName(){
        return name;
    }

    public int getScore(){
        return score;
    }

    public int getTeamNum(){
        return teamNum;
    }

    public String getHashRec(){
        return hashRec;
    }

    public String getHashCom(){
        return hashCom;
    }

    private static byte[] getSHA(String input) throws NoSuchAlgorithmException
    { 
        // Static getInstance method is called with hashing SHA 
        MessageDigest md = MessageDigest.getInstance("SHA-256"); 
  
        // digest() method called 
        // to calculate message digest of an input 
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8)); 
    }
    
    private static String toHexString(byte[] hash)
    {
        // Convert byte array into signum representation 
        BigInteger number = new BigInteger(1, hash); 
  
        // Convert message digest into hex value 
        StringBuilder hexString = new StringBuilder(number.toString(16)); 
  
        // Pad with leading zeros
        while (hexString.length() < 32) 
        { 
            hexString.insert(0, '0'); 
        } 
  
        return hexString.toString(); 
    }
}