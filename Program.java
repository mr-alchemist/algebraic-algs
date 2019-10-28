import java.math.BigInteger;
import java.math.MathContext;
import java.math.BigDecimal;

import storage.*;

public class Program {

	public static void main(String[] args) {
		
		Stopwatch sw = new Stopwatch();
		long a = 1234567890L;
		long b = 12;
		
		
		//НАИБОЛЬШИЙ ОБЩИЙ ДЕЛИТЕЛЬ
		sw.start();
		System.out.println("getGCF1 " + getGCF1(a, b) );//Поиск НОД, используя вычитание
		System.out.println("getGCF1: "+ sw.getDuration()+" ms");
		System.out.println("");
		
		sw.start();
		System.out.println("getGCF2 " + getGCF2(a, b) );
		System.out.println("getGCF2: "+ sw.getDuration()+" ms");//Поиск НОД через остаток от деления
		System.out.println("");
		
		//ВОЗВЕДЕНИЕ В СТЕПЕНЬ
		double base = 1.000_001;
		int p = 1_000_000;
		sw.start();
		double res = power1(base, p);//итеративный алгоритм
		sw.stop();
		System.out.println("power1: " + res + " (" + sw.getDuration() + " ms)" );
		
		sw.start();
		res = power2(base, p);//Через степень двойки с домножением
		sw.stop();
		System.out.println("power2: " + res + " (" + sw.getDuration() + " ms)" );
		
		sw.start();
		res = power3(base, p);//Возведение в степень используя двоичное разложение показателя степени
		sw.stop();
		System.out.println("power3: " + res + " (" + sw.getDuration() + " ms)" );
		
		base = 1.000_000_001;
		p = 1_000_000_000;
		
		sw.start();
		res = power1(base, p);//итеративный алгоритм
		sw.stop();
		System.out.println("power1: " + res + " (" + sw.getDuration() + " ms)" );
		
		sw.start();
		res = power2(base, p);//Через степень двойки с домножением
		sw.stop();
		System.out.println("power2: " + res + " (" + sw.getDuration() + " ms)" );
		
		sw.start();
		res = power3(base, p);//Возведение в степень используя двоичное разложение показателя степени
		sw.stop();
		System.out.println("power3: " + res + " (" + sw.getDuration() + " ms)" );
		
		
		//ПРОСТЫЕ ЧИСЛА
		int N = 10_000;
		int cnt = 0;
		
		//isPrime1(Через перебор делителей)
		/*System.out.println("Test isPrime1: getting prime numbers count between 1 and " + N);
		sw.start();
		cnt = 0;
		for(int i=2;i <= N;i++) 
			if(isPrime1(i)) 
				cnt++;
		System.out.println("cnt: " + cnt);
		System.out.println(sw.getDuration()+ " ms");
		System.out.println("");*/
		
		
		
		//isPrime2(Несколько оптимизаций перебора делителей)
		System.out.println("Test isPrime2: getting prime numbers count between 1 and " + N);
		sw.start();
		cnt = 0;
		for(int i=2;i <= N;i++) 
			if(isPrime2(i)) 
				cnt++;
		System.out.println("cnt: " + cnt);
		System.out.println(sw.getDuration()+ " ms");
		System.out.println("");
		
		//isPrime2UseArray(Несколько оптимизаций перебора делителей, с использованием массива)
		System.out.println("Test isPrime2UseArray: getting prime numbers count between 1 and " + N);
		sw.start();
		arrayWithPrimes = new FactorArray<Integer>();
		cnt = 0;
		for(int i=2;i <= N;i++) {
			if(isPrime2UseArray(i)) {
				cnt++;
				arrayWithPrimes.add(i);
			}
		}
		System.out.println("cnt: " + cnt);
		System.out.println(sw.getDuration()+ " ms");
		System.out.println("");
		
		//EratosthenesSieve(Решето Эратосфена)
		System.out.println("Test EratosthenesSieve: getting prime numbers count between 1 and " + N);
		sw.start();
		EratosthenesSieve sieve = new EratosthenesSieve(N);
		cnt = 0;
		for(int i=2;i <= N;i++) {
			if(sieve.isPrime(i)) {
				cnt++;
			}
		}
		System.out.println("cnt: " + cnt);
		System.out.println(sw.getDuration()+ " ms");
		System.out.println("");
		
		//ЧИСЛА ФИБОНАЧЧИ:
		int n = 10_000;
		/*sw.start();
		System.out.println(getFNumRec(n).toString());//getFNumRec использует рекурсию
		System.out.println("getFNumRec: "+ sw.getDuration()+" ms");
		System.out.println("");*/
		
		sw.start();
		System.out.println(getFNumIter(n).toString());//getFNumIter использует итерацию
		System.out.println("getFNumIter: "+ sw.getDuration()+" ms");
		System.out.println("");
		
		sw.start();
		System.out.println(getFNumGold200(n).toString());//getFNumGold200 через формулу золотого сечения(используется BigDecimal, значение кв. корня из 5 взято с точностью до 200 знака после запятой)
		System.out.println("getFNumGold: "+ sw.getDuration()+" ms");
		System.out.println("");
		
		sw.start();
		System.out.println(getFNum(n).toString()); //getFNum вычисляет число Фибоначчи через умножение матриц 
		System.out.println("getFNum: "+ sw.getDuration()+" ms");
		System.out.println("");
		
		
		
		//Находим, начиная с какого n getFNumGold даёт ошибочный результат
		for(int i=1;i <= 1000;i++) {
			BigInteger Fn1 = getFNum(i);
			BigInteger Fn2 = getFNumGold200(i);
			if(Fn1.equals(Fn2))continue;
			System.out.println("F("+ i + "): "+Fn1.toString());
			break;
		}
		
		
		
		
		
	}
	
	//Поиск НОД, используя вычитание
	private static long getGCF1(long a, long b) {
		long min, max;
		if(a > b) {
			max = a;
			min = b;
		}
		else {
			max = b;
			min = a;
		}
		
		if(min == 0) return max;
		while(max >= min)
			max -= min;
		
		if(max == 0)return min;
		
		while(min >= max)
			min -= max;
		
		if(min == 0)return max;
		return 1;
		
	}
	
	//Поиск НОД через остаток от деления
	private static long getGCF2(long a, long b) {
		long min, max;
		if(a > b) {
			max = a;
			min = b;
		}
		else {
			max = b;
			min = a;
		}
		
		if(min == 0)return max;
		
		long r = max%min;
		
		if(r == 0)return min;
		
		long r2 = min%r;
		
		if(r2==0)return r;
		
		return 1;
	}
	
	//Простой итеративный алгоритм возведения в степень
	private static double power1(double a, int b) {
		double res = 1;
		for(int i = 0; i < b; i++) 
			res *= a;
		return res;
	}
	
	//Через степень двойки с домножением
	private static double power2(double a, int b) {
		double res = 1;
		if(b >= 1)
			res = a;
		int p = 1;
		for(int i = 2; i <= b; i = i*2) {
			res = res*res;
			p = i;
		}
		for(;p<b;p++) 
			res *= a;
		
		return res;
	}
	
	//Возведение в степень используя двоичное разложение показателя степени
	private static double power3(double a, int b) {
		double res = 1;
		int power = b;
		int i = 1;
		double multer = a;
		while(power > 0) {
			if((power & i) != 0) {
				power -= i;
				res *= multer;
			}
			i = i << 1;
			multer *= multer;
		}
		
		return res;
	}
	
	private static double power4(double a, int b) {
		double res;
		
		if(b == 0)return 1;
		if(b == 1)return a;
		
		if(b%2==0) {
			double subpower = power4(a, b/2);
			res = subpower*subpower;
		}
		else {
			double subpower2 = power4(a, b-1);
			res = subpower2*a;
		}
		
		
		return res;
	}
	
	//Через простой перебор делителей
	private static boolean isPrime1(int x) {
		int c = 0;
		
		for(int i = 1; i <= x ;i++) 
			if(x%i == 0)c++;
			
		return (c == 2);
	}
	
	//Через перебор делителей, с оптимизацией
	private static boolean isPrime2(int x) {
		
		if(x == 2)return true;
		
		if(x%2 == 0)return false;
		
		for(int i = 3; i*i <= x ;i += 2) 
			if(x%i == 0)return false;
			
		return true;
	}
	
	private static IDynamicArray<Integer> arrayWithPrimes;
	//Через перебор делителей, с оптимизацией, испольует массив с простыми числами
	private static boolean isPrime2UseArray(int x) {
		if(x == 2)return true;
		if(x%2 == 0)return false;
		
		int lastPrime = 2;
		for(int j = 0; j < arrayWithPrimes.size() ; j++) {
			int curPrime = arrayWithPrimes.get(j);
			if(curPrime*curPrime > x)break;
			if(x%curPrime == 0)return false;
			lastPrime = curPrime;
		}
		
		int i;
		if(lastPrime == 2)
			i = 3;
		else
			i = lastPrime + 2;
		
		
		for(; i*i <= x ;i += 2) 
			if(x%i == 0)return false;
			
		return true;
	}
	
	//Нахождение числа Фибоначчи, рекурсивный метод
	private static BigInteger getFNumRec(int n) {
		if(n < 0)return null;
		if(n == 0)return BigInteger.ZERO;
		if(n == 1 || n ==2)return BigInteger.ONE;
		return getFNumRec(n-2).add(getFNumRec(n-1));
	}
	
	//Нахождение числа Фибоначчи, итеративный метод
	private static BigInteger getFNumIter(int n) {
		if(n < 0)return null;
		if(n == 0)return BigInteger.ZERO;
		if(n == 1 || n == 2)return BigInteger.ONE;
		BigInteger Fnm1 = BigInteger.ONE;
		BigInteger Fnm2 = BigInteger.ONE;
		
		BigInteger Fn = BigInteger.ZERO;
		for(int i = 3; i <= n  ; i++) {
			Fn = Fnm1.add(Fnm2);
			Fnm2 = Fnm1;
			Fnm1 = Fn;
		}
		return Fn;
	}
	
	//Нахождение числа Фибоначчи, по формуле золотого сечения (точность корня из 5 - 100 знаков после запятой)
	private static BigInteger getFNumGold100(int n) {
		if(n < 0)return null;
		if(n == 0)return BigInteger.ZERO;
		if(n == 1 || n == 2)return BigInteger.ONE;
		BigDecimal bdSqrt5 = new BigDecimal("2.2360679774997896964091736687312762354406183596115257242708972454105209256378048994144144083787822749");
		//BigDecimal bdSqrt5 = new BigDecimal("2.23606797749978969640917366873127623544061835961152572427089724541052092563780489941441440837878227496950817615077378350425326772444707386358636012153345270886677817319187916581127664532263985658053576");
		BigDecimal Fi = bdSqrt5;
		Fi = Fi.add(BigDecimal.ONE);
		Fi = Fi.divide(new BigDecimal(2));
		
		
		BigDecimal bdRes = Fi.pow(n);
		bdRes = bdRes.multiply(bdSqrt5);
		bdRes = bdRes.divide(new BigDecimal(5));
		bdRes = bdRes.add(new BigDecimal(0.5));
		
		BigInteger res = bdRes.toBigInteger();
		return res;
	}
	
	//Нахождение числа Фибоначчи, по формуле золотого сечения (точность корня из 5 - 200 знаков после запятой)
	private static BigInteger getFNumGold200(int n) {
		if(n < 0)return null;
		if(n == 0)return BigInteger.ZERO;
		if(n == 1 || n == 2)return BigInteger.ONE;
		//BigDecimal bdSqrt5 = new BigDecimal("2.2360679774997896964091736687312762354406183596115257242708972454105209256378048994144144083787822749");
		BigDecimal bdSqrt5 = new BigDecimal("2.23606797749978969640917366873127623544061835961152572427089724541052092563780489941441440837878227496950817615077378350425326772444707386358636012153345270886677817319187916581127664532263985658053576");
		BigDecimal Fi = bdSqrt5;
		Fi = Fi.add(BigDecimal.ONE);
		Fi = Fi.divide(new BigDecimal(2));
		
		
		BigDecimal bdRes = Fi.pow(n);
		//BigDecimal bdRes = powerBigDecimal(Fi,n);
		bdRes = bdRes.multiply(bdSqrt5);
		bdRes = bdRes.divide(new BigDecimal(5));
		bdRes = bdRes.add(new BigDecimal(0.5));
		
		BigInteger res = bdRes.toBigInteger();
		return res;
	}
	
	//Нахождение числа Фибоначчи, с помощью умножения матриц
	private static BigInteger getFNum(int n) {
		if(n < 0)return null;
		if(n == 0)return BigInteger.ZERO;
		if(n == 1 || n ==2)return BigInteger.ONE;
		int power = n - 1;
		Matrix2D matrix = new Matrix2D(2, 2);
		matrix.setElement(0, 0, BigInteger.ONE);
		matrix.setElement(0, 1, BigInteger.ONE);
		matrix.setElement(1, 0, BigInteger.ONE);
		matrix.setElement(1, 1, BigInteger.ZERO);
		matrix = powerMatrix(matrix, power);
		return matrix.getElement(0, 0);
	}
	
	private static Matrix2D powerMatrix(Matrix2D a, int b) {
		if(b <= 0)return null;
		
		Matrix2D res = Matrix2D.IdentityMatrix(a.width());
		
		int power = b;
		int i = 1;
		Matrix2D multer = a;
		while(power > 0) {
			if((power & i) != 0) {
				power -= i;
				res = multer.multiply(res);
			}
			i = i << 1;
			multer = multer.multiply(multer);
		}
		
		return res;
	}
	
	private static BigDecimal powerBigDecimal(BigDecimal a, int b) {
		BigDecimal res = BigDecimal.ONE;
		int power = b;
		int i = 1;
		BigDecimal multer = a;
		while(power > 0) {
			if((power & i) != 0) {
				power -= i;
				res = res.multiply(multer);
			}
			i = i << 1;
			multer = multer.multiply(multer);
		}
		
		return res;
	}
	

}
