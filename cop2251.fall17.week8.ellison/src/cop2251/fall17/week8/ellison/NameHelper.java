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
	
	/* Loads the data from the data files into one of two Maps depending on the gender character found
		in the record with the name. Currently uses binary file IO but will be revised and simplified */
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
        	String name = sanitize(singleRecArr[0]);
        	char gender = singleRecArr[1].charAt(0);
        	int rank = Integer.parseInt(singleRecArr[2].trim());
        	
        	if(gender == 'M') {
        		boysByYear.get(Integer.toString(year)).put(name, rank);
        	} else {
        		girlsByYear.get(Integer.toString(year)).put(name, rank);
        	}
        }
	}
	public int getRank(String year, String name, String gender) { // Returns the rank of a name in a given year
		name = sanitize(name);
		if(gender.equals("F")) {
			if (girlsByYear.get(year).get(name) == null) {
				return 0;
			}
			else {
				return girlsByYear.get(year).get(name);
			}
		} else {
			if (boysByYear.get(year).get(name) == null) {
				return 0;
			}
			else {
				return boysByYear.get(year).get(name);
			}
		}
	}
	
	// Returns a boolean value as to whether a given name is present for a given gender
	public boolean isNamePresent(String name, String gender) { 
		boolean nameIsPresent = false;
		name = sanitize(name);
		for(int year = 1900; year < 2016; year++) {
			if(gender == "M") {
				if(boysByYear.get(Integer.toString(year)).containsKey(name)) {
					nameIsPresent = true;
					break;
				} else {
					nameIsPresent = false;
				}
			} else {
				if(girlsByYear.get(Integer.toString(year)).containsKey(name)) {
					nameIsPresent = true;
					break;
				} else {
					nameIsPresent = false;
					}
				}
			}
		return nameIsPresent;
	}
	
	// Returns all the years present in the data files
	public Set<String> getYears() { 
		Set<String> treeSet = new TreeSet<>();
		treeSet.addAll(boysByYear.keySet());
		return treeSet;
	}
	
	// Returns name passed with the first letter uppercase and all other characters lowercase
	private String sanitize(String input) { 
		String output;
		StringBuilder cleanInput = new StringBuilder();
		cleanInput.append(Character.toUpperCase(input.charAt(0)));
		cleanInput.append(input.substring(1,input.length()).toLowerCase());
		output = cleanInput.toString();
		return output;
	}
}
