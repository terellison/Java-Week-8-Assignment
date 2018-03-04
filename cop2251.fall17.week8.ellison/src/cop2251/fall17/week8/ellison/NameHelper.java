// Terry Ellison SPC ID: 2335229

package cop2251.fall17.week8.ellison;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class NameHelper {
	private Map<String, Map<String, Integer>> girlsByYear = new HashMap<>();
	private Map<String, Map<String, Integer>> boysByYear = new HashMap<>();
	
	/* Loads the data from the data files into one of two Maps depending on the gender character found
		in the record with the name. */
	public void load() throws FileNotFoundException{ 
		
		
		File dir = new File("src/data");
        File [] files = dir.listFiles();
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
            
            Scanner input = new Scanner(f);
            while(input.hasNextLine()) {
            	String inputString = input.nextLine();
            	String[] record = inputString.split(",");
            	String name = sanitize(record[0]);
            	char gender = record[1].charAt(0);
            	int rank = Integer.parseInt(record[2]);
            	
            	if(gender == 'M') 
            		boysByYear.get(Integer.toString(year)).put(name, rank);
            	else
            		girlsByYear.get(Integer.toString(year)).put(name, rank);
            }
            year++;
            input.close();
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
