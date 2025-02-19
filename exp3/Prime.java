

public class Prime {
	public static void main(String []args) {
	  int count=0;
	  
	  System.out.println("The Total no. of Prime Number between 1 and 1000 is :");
	  for(int num=2;num<=1000;num++) {
		  if(isPrime(num)) {
			  System.out.print(" "+num);
			  count++;
		  }
	  }
	  System.out.println("\nTotal primes: " + count);
  }
public static boolean isPrime(int n) {
	for (int i=2; i <= Math.sqrt(n); i++) {
		if(n%i==0) {
		   return false;
		}
	}
	return true;
  }
}

