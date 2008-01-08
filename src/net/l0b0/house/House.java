/**
 * 
 */
package net.l0b0.house;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
	
import net.l0b0.hypervolume.Hypercube;
import net.l0b0.hypervolume.HypercubeCollection;

/**
 * @author vengmark
 * TODO Add method to check for "islands" - One or more rooms which are not neighbors of neighbors etc. of all the other rooms.
 * Can maybe be done by recursively deleting neighbors of a randomly selected room. If the result still contains a room, 
 * then there are islands. More useful would be to return sets of rooms which are connected (recursively).
 * TODO Find big ASCII maps to stress-test algorithm.
 * TODO Add timing to execution.
 * TODO Add method to find "broken" rooms - I.e., where not all tiles are neighbors.
 */
public class House {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		House house = new House(null);
		try {
			if (args.length != 0) {
				house.setHouseMap(args[0]);
			} else {
				house.setHouseMap("src/net/l0b0/house/optrip.com test.txt");
			}
		} catch (IOException e) {
			System.out.println("Failed to set map.");
		}
		
		if (!house.validate()) {
			System.out.println("Giving up...");
		} else {
			
			int biggestRoomSize = 0;
			String biggestRoom1 = null;
			String biggestRoom2 = null;
			//Check each room against the following ones (Should be N*log(N), not N^2)
			for (String roomName1 : house.getHouseMap().keySet()) {
				for (String roomName2 : house.getHouseMap().tailMap(roomName1 + "\0").keySet()) { // Hack from http://java.sun.com/j2se/1.5.0/docs/api/java/util/TreeMap.html#tailMap(K)
					if (house.getHouseMap().get(roomName1).overlaps(house.getHouseMap().get(roomName2))) {
						System.out.println("Two of the rooms overlap. Something's wrong in the implementation.");
					}
					if(house.getHouseMap().get(roomName1).isNeighbor(house.getHouseMap().get(roomName2))) {
						if (house.getHouseMap().get(roomName1).getHypercubes().size()
								+ house.getHouseMap().get(roomName2).getHypercubes().size() > biggestRoomSize) {
							biggestRoom1 = roomName1;
							biggestRoom2 = roomName2;
							biggestRoomSize = house.getHouseMap().get(roomName1).getHypercubes().size()
							+ house.getHouseMap().get(roomName2).getHypercubes().size();
						}
					}
				}
			}
			System.out.println("The biggest neighbor collection are rooms "
					+ biggestRoom1 + " (" + house.getHouseMap().get(biggestRoom1).getHypercubes().size() + " tiles) and "
					+ biggestRoom2 + " (" + house.getHouseMap().get(biggestRoom2).getHypercubes().size() + " tiles), "
					+ "for a total size of " + biggestRoomSize + " tiles.");
		}
	}
	
	private TreeMap<String, HypercubeCollection> map = new TreeMap<String, HypercubeCollection>();

	/**
	 * @param hypercubes
	 */
	public House(HypercubeCollection hypercubes) {
		if (hypercubes != null) {
			for (int index = 0; index < hypercubes.getHypercubes().size(); index++) {
				this.map.put(Integer.toString(index), hypercubes);
			}
		}
	}

	/**
	 * @return the house
	 */
	public TreeMap<String, HypercubeCollection> getHouseMap() {
		return this.map;
	}

	/**
	 * @param house the house to set
	 */
	public void setHouseMap(TreeMap<String, HypercubeCollection> houseMap) {
		this.map = houseMap;
	}

	/**
	 * Read rooms from a file instead of a premade object.
	 * @param houseFile the file which contains the house map
	 * @throws IOException
	 */
	public void setHouseMap(String houseFile) throws IOException {
		BufferedReader inputStream = null;
		String line;
		Scanner lineScanner;
		int lineCount = 0;
		int columnCount;
		String name; // Room name (word in the current place in the grid)
		HypercubeCollection room; // Temporary room
		try {
			inputStream = new BufferedReader(new FileReader(houseFile));
			// Loop through the lines in the file
			while ((line = inputStream.readLine()) != null) {
				lineScanner = new Scanner(line);
                /* If the room already exists, then add a new Hypercube in that room.
                 * If not, create a new room, add the Hypercube, and add the room to the house.
                 */
				columnCount = 0; // Reset for each line
				while (lineScanner.hasNext()) {
					name = lineScanner.next();
					if (this.map.containsKey(name)) { // Existing room
						room = this.map.get(name);
						room.getHypercubes().add(new Hypercube(new int[]{lineCount, columnCount}));
					} else { // New room
						room = new HypercubeCollection(new TreeSet<Hypercube>(Hypercube.POSITION_ORDER));
						// Debug information
						//System.out.println("New room " + name);
						room.getHypercubes().add(new Hypercube(new int[]{lineCount, columnCount}));
					}
					// Debug information
					//System.out.println(name + " (" + (room.getHypercubes().size()-1) + "): [" + room.getHypercubes().last().getPosition()[0] + ", " + room.getHypercubes().last().getPosition()[1] + "]");
					this.map.put(name, room); // Replace old / insert new key
					columnCount++;
				}
				lineCount++;
            }
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't find file " + houseFile);
		} finally {
            if (inputStream != null) {
            	inputStream.close();
            }
		}
	}
	
	public boolean validate() {
		boolean valid = true;
		HypercubeCollection oneRoom = new HypercubeCollection();
		// Check for overlap
		for (String room : this.map.keySet()) {
			oneRoom.getHypercubes().addAll(this.map.get(room).getHypercubes());
			// Check for islands in rooms
			if (this.map.get(room).getIslands().size() != 1) {
				System.out.println("Sweet Mary Lou on a stick! You've got "
						+ this.map.get(room).getIslands().size()
						+ " islands in room " + room);
				valid = false;
			}
			// Check for overlap
			for (HypercubeCollection neighbor : this.map.values()) {
				if (!this.map.get(room).equals(neighbor) && this.map.get(room).overlaps(neighbor)) {
					System.out.println("Holy hollering monkey! Your house has overlapping rooms: "
							+ room + " & " + neighbor);
					valid = false;
				}
			}
		}
		
		// Check for islands in the whole house
		System.out.println("Your house contains "
				+ oneRoom.getHypercubes().size()
				+ " tiles.");
		if (oneRoom.getIslands().size() != 1) {
			System.out.println("Jumping Jack Burrow! Your house (size "
					+ oneRoom.getHypercubes().size()
					+ ") has islands!");
			valid = false;
		}

		return valid;
	}
}