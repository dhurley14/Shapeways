import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.util.HashMap;

public class pract{

    //private static ArrayList<String> alist = new ArrayList<String>();
    private static HashMap<String, Integer> amap = new HashMap<String, Integer>();
    //private static String[] aList = new String[7251];
    private static int aCount = 0;
    public static void main(String args[]) throws IOException{
        int count = 0;
        int lineCount = 0;
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        HashMap<String, Integer> smap = new HashMap<String, Integer>();
        File f = new File("thing8.txt");
        // lefile.txt contains a word appearing
        // n number of times.  The objective
        // is to creat a hashmap of the word
        // as the key and number of times the 
        // word appears as its value.

        Scanner in = new Scanner(f);

        while(in.hasNextLine()){
            String[] bands = in.nextLine().split(",");
            //System.out.println("Size of bands = " + bands.length);
            for(int i = 0; i<bands.length; i++){

                String band = bands[i];
                if(map.containsKey(band)){
                    int n = map.get(band).intValue();
                    n++;
                    map.put(band,new Integer(n));
                }
                else{
                    map.put(band,new Integer(1));
                }

                if(map.get(band).intValue() >= 50){
                    // add to special hashmap
                    smap.put(band,new Integer(map.get(band).intValue()));
                }
            }
        }
        in.close();

        //System.out.println(smap.entrySet());
        //System.out.println("smap size = " + smap.size());

        // now search through each band and see if a band in bands[] 
        // appears in smap.  If more than one does, write that pair out to a text file.
        in = new Scanner(f);
        PrintWriter out = new PrintWriter("output.txt");
        while(in.hasNextLine()){
            lineCount++;
            String[] bandz = in.nextLine().split(",");
            //out.println("List #"+lineCount);
            for(int i = 0; i<bandz.length; i++){

                if(smap.containsKey(bandz[i])){
                    //System.out.println("Found: " + bandz[i] + " in smap at Line: " + lineCount);
                    out.print(bandz[i]+",");
                }
            }
            out.print("\n");
            //System.out.println("bandz: " + bandz[0]);
        }
        out.close();
        in.close();

        in = new Scanner(new File("output.txt"));

        PrintWriter outFinal = new PrintWriter("pairs.txt");
        while(in.hasNextLine()){
            String[] list = in.nextLine().split(",");
            if(list[0].isEmpty()){
                //do nothing
            }
            else{
                for(int i = 0; i<list.length; i++){
                    for(int j = i+1; j<list.length; j++){
                        if(!searchPairs(list[i],list[j])){
                            //outFinal.println(list[i]+":"+list[j]);
                            System.out.println(aCount);
                            amap.put(list[i]+":"+list[j], new Integer(0));
                            //alist.add(aCount,list[i]+":"+list[j]);
                            //aList[aCount] = list[i]+":"+list[j];
                            aCount++;
                        }
                    }

                }
            }

        }
        /*
        for(int i = 0; i<aCount; i++){
            outFinal.println(alist.get(i));
        }
        */
        Object[] amapString = amap.keySet().toArray();
        for(int i = 0; i<amapString.length; i++){
            outFinal.println(amapString[i]);
        }
        //outFinal.println(amap.keySet());
        outFinal.close();
        in.close();
    }
 
    private static boolean searchPairs(String eye, String jay){
        //Scanner s = new Scanner("pairs.txt");
        if(amap.containsKey(eye+":"+jay) || amap.containsKey(jay+":"+eye)){
            return true;
        }
        return false;
        
        /*
        for(int i = 0; i<aCount; i++){
            String temp = alist.get(i);
            if(temp.equals(eye+":"+jay) && !temp.equals(null)){
                //s.close();
                return true;
            }
            if(temp.equals(jay+":"+eye) && !temp.equals(null)){
                //s.close();
                return true;
            }
        }
        //s.close();
        return false;
        */
    }

}
