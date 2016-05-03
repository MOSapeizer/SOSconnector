package sosconnector.Filter;

import java.util.Date;

/**
 * Created by zil on 2016/4/26.
 */
public class Analyze {

    private static int mb = 1024 * 1024;
    private static int COUNT = 0;
    private static int TOTAL = 0;
    private static String success = "sos:InsertObservationResponse";

    public Analyze(){

    }

    public static void statistic( String message ){
        if( check(message) ){
            COUNT++;
        }
        TOTAL++;
    }

    private static Boolean check(String message){
        return message.contains(success);
    }

    public static String result(){
        String result = "\nTotal data: " + TOTAL + ", success: " + COUNT + ", same: " + (TOTAL-COUNT)
                        + "\n" + usage() ;
        clean();
        return result;
    }

    private static String usage(){
        Runtime instance = Runtime.getRuntime();
        String result = "Total Memory: " + instance.totalMemory() / mb + "M"
                + ", Free Memory: " + instance.freeMemory() /mb + "M"
                + ", Used Memory: " + ( instance.totalMemory() - instance.freeMemory()) / mb  + "M"
                + ", Max Memory: " + instance.maxMemory() /mb + "M\n";

        return result;
    }

    private static void clean() {
        TOTAL = 0;
        COUNT = 0;
    }


}
