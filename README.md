# Java-Week-8-Assignment
Last assignment of Java II (COP 2251)

In this assignment, I was tasked with creating a helper class for the JavaFX application BabyNameChart, which allows a user to enter a name and see the number of people born with that name from the year 1900 to the year 2015.
The helper class I created (NameHelper.java) loads the name data from the provided data files (provided by the U.S. Social Security Administration, see https://www.ssa.gov/oact/babynames/limits.html)
into nested Hash Maps sorted by gender (boysByYear, girlsByYear). Both hash maps are structured to have an outer key of the year for which the nams are ranked, and the value being the inner hash map. The inner map's key is a name and the value is the rank of the name in that given year.
This yeilds a data structure such as boysByYear<Integer,Map<String, Integer>> and girlsByYear<Integer, Map<String, Integer>>.

# Application in Action

![alt-text](https://github.com/terellison/Java-Week-8-Assignment/blob/master/BabyNameChart.png)
