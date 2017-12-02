package cop2251.fall17.week8.ellison;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class CodeTester {
	public static void main(String args[]) throws FileNotFoundException{
		Map<String, Integer> girlsByYear = new HashMap<>();
		Map<String, Integer> boysByYear = new HashMap<>();
		
		File dir = new File("src/data");
        File [] files = dir.listFiles();
        FileInputStream input = null;
        StringBuilder sb = new StringBuilder();
        int year = 1900;
        Map<String, Integer> innerBoyMap = new HashMap<>();
        Map<String, Integer> innerGirlMap = new HashMap<>();
        // for each file in the directory...
        for (File f : files)
        {
             /*  TODO: 

                 while the file f is not empty
                    read a line get the name, gender, and ranking
                    add the data to the maps    
             */                           
        	input = new FileInputStream(f);
        	
        	byte byteArr[] = new byte[(int) f.length()];
        	
        	
			try {
				input.read(byteArr);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
        	String inputString = null;
			try {
				inputString = new String(byteArr, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			sb.append(inputString);
			sb.append("\n");
			if((year + 1) == 2016) {
				break;
			} else {
			year++;
			}
        }
        String sbToString = sb.toString();
        System.out.print(sbToString);
        String recordArray[] = sbToString.split("\n");
        for(int i = 0; i < recordArray.length; i++) {
        	String singleRecArr[] = recordArray[i].split(",");
        	if(singleRecArr.length == 1) {
        		break;
        	}
        	String name = singleRecArr[0];
        	char gender = singleRecArr[1].charAt(0);
        	int rank = Integer.parseInt(singleRecArr[2].trim());
        	
        	//System.out.println(name + " " + gender + " " + rank);
        	
        	if(gender == 'M') {
        		innerBoyMap.put(name, rank);
        	} else {
        		innerGirlMap.put(name, rank);
        	}
        }
        
	}

}
