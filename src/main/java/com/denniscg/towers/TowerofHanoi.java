package com.denniscg.towers;

import com.denniscg.towers.enums.PegName;
import com.denniscg.towers.exceptions.IncorrectInputException;

/**
 * Hello world!
 *
 */
public class TowerofHanoi {

	public static final int NUM_OF_PEG = 3;
	public static final int MAX_OF_DISC = 256;
	private int numOfDisk = Integer.MIN_VALUE;
	private Peg[] pegs;

	
	
	
	public static void main(String[] args) {
        int paramdisk = 0;
        try{
            // argument check ( argment number / type )
            if(args.length != 1){
                throw new IncorrectInputException("Incorrect Number of Argment");
            }else{
                paramdisk = Integer.parseInt(args[0]);
            }
            if(paramdisk > MAX_OF_DISC){
                throw new IncorrectInputException("Incorrect Number of Disk");
            }
            TowerofHanoi towerofhanoi = new TowerofHanoi(paramdisk);
            towerofhanoi.perform(); 

        }catch (IncorrectInputException e){
            System.out.println(e.toString());
            printHelp();
            System.exit(-1);

        }catch (NumberFormatException e){
            System.out.println(e.toString());
            printHelp();
            System.exit(-1);

        }catch (Exception e){
            System.out.println(e.toString());
            System.exit(-1);
        }	}

	/**
	 *
	 * Constructor of TowerofHanoi Class
	 * 
	 * @param takes
	 *            number of disks by int less than equal to 9
	 * @throws Exception
	 *
	 */
	protected TowerofHanoi(int numOfDisk) throws Exception {

		this.numOfDisk = numOfDisk; // set Class variable

		pegs = new Peg[NUM_OF_PEG]; // make Peg
		for (int i = 0; i < NUM_OF_PEG; i++) {
			pegs[i] = new Peg(i, numOfDisk);
		}

		for (int i = numOfDisk - 1; i >= 0; i--) { // set size of each disks
			pegs[PegName.A.ordinal()].pushDisk(new Disk(i));
		}

	}

	/**
	 *
	 * Move all disks from source to target peg, this is a interface, wrapper method
	 * of doPerform, which actually takes arguments needed.
	 * 
	 * @throws Exception
	 *
	 */
	protected void perform() throws Exception {
		System.out.printf("%-15s        %-60s\n", "Move", "Peg Configuration");
		System.out.printf("        %-15s%-20s%-20s%-20s\n", "", "A", "B", "C");
		printTower("init");

		// process all disks
		doPerform(numOfDisk, PegName.A.ordinal(), // From
				PegName.C.ordinal(), // To
				PegName.B.ordinal()); // Util
	}

	/**
	 *
	 * Move all disks from source to target peg.
	 * 
	 * @param diskNum
	 *            indicates number to disks
	 * @param fromPeg
	 *            indicates source peg
	 * @param toPeg
	 *            indicates target peg
	 * @param tempPeg
	 *            indicates utility peg
	 * @throws Exception
	 *
	 */
	protected void doPerform(int diskNum, int fromPeg, int toPeg, int tempPeg) throws Exception {
		Disk tempDisk;
		// Move disks recursively
		if (diskNum > 0) {

			// Move disks from first disk to n-1 th disk of original Peg into
			// utility Peg
			doPerform(diskNum - 1, fromPeg, tempPeg, toPeg);

			// Move a disk, and print out the status of Tower
			tempDisk = pegs[fromPeg].popDisk();
			pegs[toPeg].pushDisk(tempDisk);
			printTower((tempDisk.getSize() + 1) + " from " + pegs[fromPeg].getPegName() + " to "
					+ pegs[toPeg].getPegName());

			// Move disks from first disk to n-1 th disk of utility Peg
			// into target Peg
			doPerform(diskNum - 1, tempPeg, toPeg, fromPeg);
		}
	}

	/**
	 * 
	 * Print the status of Tower
	 * 
	 * @param str
	 *            is string to print
	 * @throws Exception
	 *
	 */
	protected void printTower(String str) throws Exception {

		// print out the status of tower
		printTowerMove(str);

		// print out the Peg Configuration
		printTowerPeg();

	}

	/**
	 *
	 * Print the move of tower
	 * 
	 * @param str
	 *            is movement informaiton
	 *
	 */
	protected void printTowerMove(String str) {
		System.out.printf("%-15s        ", str);
	}

	/**
	 *
	 * Print the Peg configuration of tower
	 * 
	 * @throws Exception
	 */
	protected void printTowerPeg() throws Exception {
		for (int i = 0; i < NUM_OF_PEG; i++) {
			pegs[i].printPeg();
		}
		System.out.println("");
	}

	/**
	 *
	 * Print the help info
	 */
	protected static void printHelp() {
		System.out.println("#####################################################\n\n"
				+ "Usage: TowerofHanoi num\n number must be less than 10\n\n"
				+ "#####################################################\n");
	}

}
