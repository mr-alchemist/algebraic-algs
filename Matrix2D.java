import java.math.BigInteger;

public class Matrix2D {
	private int width;
	private int height;
	BigInteger[][] matrix;
	
	@SuppressWarnings("all")
	private Matrix2D() {
		
	}
	
	public Matrix2D(int height, int width) {
		this.width = width;
		this.height = height;
		matrix = new BigInteger[height][width];
	}
	
	public int width() {
		return width;
	}
	
	public int height() {
		return height;
	}
	
	public BigInteger getElement(int i, int j) {
		return matrix[i][j];
	}
	
	public Matrix2D multiply(Matrix2D m) {//this*m
		if(this.width != m.height)
			return null;
		
		
		Matrix2D mres = new Matrix2D(this.height, m.width);
		for(int i = 0; i < this.height; i++) {
			for(int j = 0; j < m.width; j++) {
				BigInteger elem = new BigInteger("0");
				for(int k = 0; k < this.width; k++) {
					elem = elem.add( 
							this.matrix[i][k].multiply(m.matrix[k][j])
							);
							
				}
				mres.matrix[i][j] = elem;
			}
		}
		
		return mres;
	}
	
	public void setElement(int i, int j, BigInteger elem) {
		matrix[i][j] = elem;
	}
	
	public void print() {
		for(int i=0; i < height(); i++) {
			String s = "";
			for(int j = 0; j < width() ; j++) {
				s += matrix[i][j].toString() + " ";
			}
			System.out.println(s);
		}
	} 
	
	public static Matrix2D IdentityMatrix(int n) {
		Matrix2D res = new Matrix2D(n, n);
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(i == j)
					res.setElement(i, j, BigInteger.ONE);
				else
					res.setElement(i, j, BigInteger.ZERO);
			}
		}
		return res;
	}
	
}
