package blockchain;

import java.util.Date;

public class Test {

	public static void main(String args[]) {

		Blockchain obj = new Blockchain("A9847F", 3);

		Date startTime = new java.util.Date();
		obj.addBlock("<transaction_1>");
		obj.addBlock("<transaction_2>");
		obj.addBlock("<transaction_3>");
		obj.addBlock("<transaction_4>");
		obj.addBlock("<transaction_5>");
		Date endTime = new java.util.Date();

		// this is the tampering of previous hash that we have done in the block
		obj.getLatestBlock().setPreviousHash("ABCDEFG");

		obj.displayChain();

		obj.isValid();// this checks for the validity of the chain

		System.out.println("Single Block At Difficulty " + obj.getDifficulty() + " Is Mined In Average "
				+ (endTime.getTime() - startTime.getTime()) / 6000.0 + " Seconds");
	}

}