
public class QRDecomposition {
	private int size = -1;
	
	private double[][] Q;
	private double[][] R;

	QRDecomposition(double[][] matrix){
		size = matrix.length;
		R = new double[size][size];
		Q = new double[size][size];
		
		double[][] r = new double[size][size];
		//copy
        for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				r[i][j] = matrix[i][j];
			}
		}
		
		getR(r);
		Q = getQ(matrix, R);
				
		
		System.out.println("R");
		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix.length; j++){
				System.out.print(R[i][j] + " ");
			}
			System.out.println("");
		}
		System.out.println("Q");
		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix.length; j++){
				System.out.print(Q[i][j] + " ");
			}
			System.out.println("");
		}
	}
    private void getR(double[][] x) {
        for (int k = 0; k < size; k++) {
            double[] v = x[k];
            double   u = Math.sqrt(innerProduct(k, v, v));
            if (v[k] < 0) 
            	u = -u;
            v[k] += u;  
            double t = 1.0 / (v[k] * u);
            for (int j = k + 1; j < size; j++) {
                double[] w = x[j];
                double   s = t * innerProduct(k, v, w); 
                for (int i = k; i < size; i++) 
                	w[i] -= s * v[i];
            }
            v[k] = -u;
            
            R[k]= v;
        }
    }
    
    private  double innerProduct(int ns, int ne, double[] lhs, double[] rhs) {
        if (ns < 0 || ne < 0 || ns > ne)
            throw new IndexOutOfBoundsException("error in RVector\n");

        double  s = 0.0;
        int n5 = (ne - ns) % 5;
        
        for (int i = ns; i < ns + n5; i++) 
        	s += lhs[i] * rhs[i];
        
        for (int i = ns + n5; i < ne; i += 5)
            s += lhs[i] * rhs[i] + lhs[i+1] * rhs[i+1]
               + lhs[i+2] * rhs[i+2] + lhs[i+3] * rhs[i+3]
               + lhs[i+4] * rhs[i+4];
        
        return s;
    }

    private  double innerProduct(int ns, double[] lhs, double[] rhs) {
        return innerProduct(ns, lhs.length, lhs, rhs);
    }

    private double[][] getQ(double[][] x, double[][] r) {
    	double[][] q = new double[size][size];
    	
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
            	x[k][i] /= r[k][k];
            }
            for (int j = k + 1; j < size; j++){
                for (int i = 0; i < size; i++){
                    x[j][i] -= r[j][k] * x[k][i];
                }
            }
        }
        
        //copy
        for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				q[i][j] = x[i][j];
			}
		}
        
        return q;
    }
    
    public double[][] R(){
    	return R;
    }
    public double[][] Q(){
    	return Q;
    }
    
}
