
public class EratosthenesSieve {
	boolean[] sieve;
	
	@SuppressWarnings("all")
	private EratosthenesSieve() {
		
	}
	
	public EratosthenesSieve(int max) {
		sieve = new boolean[max+1];
		sieve[0] = false;
		sieve[1] = false;
		for(int i = 2; i <= max ; i++) 
			sieve[i] = true;
		
		for(long i = 2; i <= max; i++) {
			if(!sieve[(int)i])continue;
		
			for(long j=i*i; j <= max ;j += i) 
				sieve[(int)j] = false;
			
		}
		
		
	}
	
	public boolean isPrime(int x) {
		return sieve[x];
	}
	
}
