package small_problems;
import java.util.BitSet;
public class CompressedGene {
	private BitSet bitSet;
	private int length;
	
	public CompressedGene(String gene)
	{
		compress(gene);
	}
	private void compress(String gene) // this methos looks at each char in String and when it sees A, it adds 00 to bit string.
	{
		length = gene.length();
		bitSet = new BitSet (length *2);
		//reverse enough capacity for all bits
		final String upperGene = gene.toUpperCase();
		//convert to upper case for consistency
		//convert String to bit representation
		for(int i=0; i<length; i++)
		{
			final int firstLocation = 2*i;
			final int secondLocation = 2*i + 1;
			
			switch(upperGene.charAt(i))
			{
			case 'A': // 00 are next two bits
				bitSet.set(firstLocation, false);
				bitSet.set(secondLocation, false);
				break;
			case 'C': // 01 are next two bits
				bitSet.set(firstLocation, false);
				bitSet.set(secondLocation, true);
				break;
			case 'G': // 10 are next two bits
				bitSet.set(firstLocation, true);
				bitSet.set(secondLocation, false);
				break;
			case 'T': // 11 are next two bits
				bitSet.set(firstLocation, true);
				bitSet.set(secondLocation, true);
				break;
			default:
				throw new IllegalArgumentException("The provided gene String contains charactesr othen than ACGT");
			}
		}
	}
	public String decompress() //reads two bit string at a time, determine whch char to add to the end of the STRİNG representation.
	{
		if(bitSet == null) {return " ";}
		StringBuilder builder = new StringBuilder(length);
		for(int i=0; i<(length * 2); i+= 2)
		{
			final int firstBit = (bitSet.get(i) ? 1: 0);
			final int secondBit = (bitSet.get(i+1) ? 1 : 0);
			final int lastBits = firstBit << 1 | secondBit;
			switch(lastBits)
			{
			case 0b00: //00 is 'A'
				builder.append('A');
				break;
			case 0b01: //00 is 'G'
				builder.append('G');
				break;
			case 0b10: //00 is 'C'
				builder.append('C');
				break;
			case 0b11: //00 is 'T'
				builder.append('T');
				break;
			}
		}
		return builder.toString();	
	}
	public static void main(String[] args) // main method checks whether the final result is same as the original String using equalsIgoneCase.
	{
		final String original =
		"TAGGGATTAACCGTTATATATATATAGCCATGGATCGATTATATAGGGATTAACCGTTATATATATATAGCCATGGATCGATTATA";
		CompressedGene compressed = new CompressedGene(original);
		final String decompressed = compressed.decompress();
		System.out.println(decompressed);
		System.out.println("Original is the same as decompressed: " + original.equalsIgnoreCase(decompressed));
	}
}
