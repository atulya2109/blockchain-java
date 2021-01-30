package blockchain;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest; //this class is used under the package java.security
import java.security.NoSuchAlgorithmException; //This exception is thrown when a particular cryptographic algorithm is requested but is not available in the environment
import java.util.Base64;
import java.util.Date;

public class Block {

	private String version; // stores version
	private Date Timestamp; // stores date and time of any transaction or creation of the corresponding
							// block
	private String proof;
	private String hash; // it contains encoded data
	private String previousHash; // stored of the previous block
	private String data;
	private int difficulty;

	public Block(String version, Date timestamp, String previousHash, String previousProof, int difficulty,
			String data) {
		this.difficulty = difficulty;
		this.previousHash = previousHash;
		this.version = version; // 'this' keyword used to refer current instance class variables
		this.Timestamp = timestamp;
		this.data = data;
		this.mine(previousProof);
		this.hash = calculateHash(); // computing hash by calling computeHash() function
	}

	public void mine(String previousProof) {

		System.out.println("Mining " + this.data);
		for (BigInteger i = new BigInteger("0");; i = i.add(BigInteger.ONE)) {

			String hash = sha256(previousProof + i);
			if (hash.substring(0, this.difficulty).equals("0".repeat(this.difficulty))) {
				this.proof = i.toString(10);
				break;
			}
		}
	}

	private static String sha256(String dataToHash) {

		MessageDigest digest;
		String changed = null;
		// using Exception Handling
		try {
			// Static getInstance method is called with hashing SHA
			// After digest has been called, the MessageDigestobject is reset to its
			// initialized state.
			digest = MessageDigest.getInstance("SHA-256");
			// digest() method called
			// to calculate message digest of an input
			// and return array of bytes
			byte[] hash = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));
			// BASE64 is a binary-to-text ecoding schema
			changed = Base64.getEncoder().encodeToString(hash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return changed;
	}

	public int getDifficulty() {
		return this.difficulty;
	}

	public String calculateHash() // computes hash using SHA-256 and BASE 64 algorithm
	{
		String dataToHash = "" + this.version + this.Timestamp + this.previousHash + this.data + this.proof;
		return sha256(dataToHash);
	}

	// setting getters and setters for the code
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Date getTimestamp() {
		return Timestamp;
	}

	public void setTimestamp(Date timestamp) {
		Timestamp = timestamp;
	}

	public String getHash() {

		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getPreviousHash() {
		return previousHash;
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	public String getData() {
		return data;
	}

	public String getProof() {
		return this.proof;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void displayBlock() {

		System.out.println("Version: " + this.getVersion());
		System.out.println("Timestamp: " + this.getTimestamp());
		System.out.println("PreviousHash: " + this.getPreviousHash());
		System.out.println("Hash: " + this.getHash());
		System.out.println("Proof: " + this.getProof());
		System.out.println("Difficulty: " + this.getDifficulty());
		System.out.println(); // to change line
	}
}