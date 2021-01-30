package blockchain;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {

	private int difficulty;
	private List<Block> chain;
	private String version;
	private String latestBlockHash; // creating a list with name chain
	// the first block in blockchain is called the genesis block

	public Blockchain(String version, int difficulty) {

		this.difficulty = difficulty;
		this.latestBlockHash = "";
		this.version = version;
		this.chain = new ArrayList<Block>();
		this.chain.add(createGenesis());
	}

	private Block createGenesis() {
		Block genesis = new Block(this.version, new java.util.Date(), this.latestBlockHash, "0", this.difficulty,
				"<transactions>");
		this.latestBlockHash = genesis.getHash();
		return genesis;
	}

	public void addBlock(String data) // function to add blocks to blockchain
	{
		Block newBlock = new Block(this.version, new java.util.Date(), this.latestBlockHash, this.getLatestBlockProof(),
				this.difficulty, data);
		this.latestBlockHash = newBlock.getHash();
		this.chain.add(newBlock); // adding the block to chain
	}

	public void displayChain() {
		// loop to iterate through blocks and display content respectively
		for (int i = 0; i < chain.size(); i++) {
			System.out.println("Block: " + i);
			this.chain.get(i).displayBlock();
		}

	}

	public Block getLatestBlock() {
		// to get index of the last added block
		return this.chain.get(chain.size() - 1);
	}

	private String getLatestBlockProof() {
		return this.chain.get(chain.size() - 1).getProof();
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public int getDifficulty() {
		return this.difficulty;
	}

	public void isValid() {
		// to check if the blockchain is valid
		// chain is invalid if the data in the block is tampered
		for (int i = chain.size() - 1; i > 0; i--) {

			if (!(chain.get(i).getHash().equals(chain.get(i).calculateHash()))) {
				System.out.println("Chain is not valid");
				return;
			}

			if (!(chain.get(i).getPreviousHash().equals(chain.get(i - 1).getHash()))) {
				System.out.println("Chain is not valid");
				return;
			}

			
		}

		System.out.println("Chain is valid.");

	}

}