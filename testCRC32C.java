public class testCRC32C {
    public static void main(String[] args){
        entryClass a = new entryClass("hello there",0,0,"c7bf21b1efab8a25b61fd0acff3c81571d42a81343fa5140f762bf92b76a026e");
        System.out.println(a.getHashRec() + "," + a.getHashCom());
    }
}
