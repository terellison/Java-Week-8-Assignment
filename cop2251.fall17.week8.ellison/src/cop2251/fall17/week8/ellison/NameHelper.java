// Terry Ellison SPC ID: 2335229

package cop2251.fall17.week8.ellison;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class NameHelper {
	private Map<String, Map<String, Integer>> girlsByYear = new HashMap<>();
	private Map<String, Map<String, Integer>> boysByYear = new HashMap<>();
	public void load() throws FileNotFoundException{
		File dir = new File("src/data");
        File [] files = dir.listFiles();
        FileInputStream input = null;
        StringBuilder sb = new StringBuilder();
        int year = 1900;
        // for each file in the directory...
        for (File f : files)
        {
             /*  TODO: 

                 while the file f is not empty
                    read a line get the name, gender, and ranking
                    add the data to the maps    
             */                           
        	Map<String, Integer> innerBoyMap = new HashMap<>();
            Map<String, Integer> innerGirlMap = new HashMap<>();
        	
             
            girlsByYear.put(Integer.toString(year), innerGirlMap);
            boysByYear.put(Integer.toString(year), innerBoyMap);
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
        String sbToString = sb.toString().trim();
        String recordArray[] = sbToString.split("\n");
        year = 1900;
        for(int i = 0; i <= recordArray.length - 1; i++) {
        	String singleRecArr[] = recordArray[i].split(",");
        	if(singleRecArr.length == 1) {
        		year++;
        		continue;
        	}
        	String name = singleRecArr[0];
        	char gender = singleRecArr[1].charAt(0);
        	int rank = Integer.parseInt(singleRecArr[2].trim());
        	
        	
        	if(gender == 'M') {
        		boysByYear.get(Integer.toString(year)).put(name, rank);
        	} else {
        		girlsByYear.get(Integer.toString(year)).put(name, rank);
        	}
        }
	}

	
	
	public int getRank(String year, String name, String gender) {
		StringBuilder trueName = new StringBuilder();
		
		trueName.append(Character.toUpperCase(name.charAt(0)));
		trueName.append(name.substring(1,name.length()).toLowerCase());
		
		name = trueName.toString();
		
		if(gender == "M") {
			return boysByYear.get(year).get(name);
		} else {
			return girlsByYear.get(year).get(name);
		}
		
	}
	
	public boolean isNamePresent(String name, String gender) {
		boolean nameIsPresent = false;
		StringBuilder trueName = new StringBuilder();
		
		trueName.append(Character.toUpperCase(name.charAt(0)));
		trueName.append(name.substring(1,name.length()).toLowerCase());
		
		name = trueName.toString();
		
		
		for(int year = 1900; year < 2016; year++) {
			if(gender == "M") {
				if(boysByYear.get(Integer.toString(year)).containsKey(name)) {
					nameIsPresent = true;
				} else {
					continue;
				}
			} else {
				if(girlsByYear.get(Integer.toString(year)).containsKey(name)) {
					nameIsPresent = true;
				} else {
					continue;
				}
			}
			}
		return nameIsPresent;
	}
	
	public Set<String> getYears() {
		Set<String> treeSet = new TreeSet<>();
		
		treeSet.addAll(boysByYear.keySet());
		
		return treeSet;
	}
}
