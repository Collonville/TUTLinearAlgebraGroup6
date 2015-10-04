import java.text.DecimalFormat;

public class Eigenvalue {
	private int size = -1;
	private double[] eigen;
	private double[][] eigenVec;
	
	Eigenvalue(double[][] matrix, int repeat){
		size = matrix.length;
		
		double B[][], C[][], r[], v[][], v0[], v1[], v2[], eps;
		int i1, i2, ind, ct, m, n;

		eps = 1.0e-10;
		m   = 30;

		eigen = new double[size];
		eigenVec = new double[size][size];
		B   = new double [size][size];
		C   = new double [size][size];
		v   = new double [size][size];

		r  = new double [size];
		v0 = new double [size];
		v1 = new double [size];
		v2 = new double [size];

		for (i1 = 0; i1 < size; i1++) {
			for (i2 = 0; i2 < size; i2++)
				B[i1][i2] = 0.0;
			B[i1][i1] = 1.0;
			v0[i1]    = 1.0;
		}

		ind = power(0, size, m, repeat, eps, matrix, B, C, r, v, v0, v1, v2);
				
		if (ind == 0)
			System.out.println("None Eigen");
		else {
			System.out.println(ind + " Eigen found");
			for (i1 = 0; i1 < ind; i1++) {
				eigen[i1] = r[i1];
				for (i2 = 0; i2 < size; i2++){
					eigenVec[i2][i1] = v[i1][i2];
				}
			}
		}
/*
		eigen = new double[size];
		eigenVec = new double[size][size];
		double[][] A = new double[size][size];
		
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				A[i][j] = matrix[i][j];
			}
		}
		
		//ŒÅ—L’l‚ÌŒvŽZ
		for(int i = 0; i < repeat; i++){
			LUDecomposition lu = new LUDecomposition(A);
			A = multi(lu.getU(), lu.getL());
		}
		
		for(int i = 0; i < size; i++){
			eigen[i] = A[i][i];
		}*/
	}
	
	private int power(int i, int n, int m, int ct, double eps, double A[][], double B[][],
            double C[][], double r[], double v[][], double v0[], double v1[], double v2[])
	{
		double l1, l2, x;
		int i1, i2, i3, ind, k, k1;
					// ‰ŠúÝ’è
		ind = i;
		k   = 0;
		l1  = 0.0;
		for (i1 = 0; i1 < n; i1++) {
			l1     += v0[i1] * v0[i1];
			v1[i1]  = v0[i1];
		}
		l1 = Math.sqrt(l1);
					// ŒJ‚è•Ô‚µŒvŽZ
		while (k < ct) {
						// ŠÛ‚ßŒë·‚ÌC³
			if (k%m == 0) {
				l2 = 0.0;
				for (i1 = 0; i1 < n; i1++) {
					v2[i1] = 0.0;
					for (i2 = 0; i2 < n; i2++)
						v2[i1] += B[i1][i2] * v1[i2];
					l2 += v2[i1] * v2[i1];
				}
				l2 = Math.sqrt(l2);
				for (i1 = 0; i1 < n; i1++)
					v1[i1] = v2[i1] / l2;
			}
						// ŽŸ‚Ì‹ßŽ—
			l2 = 0.0;
			for (i1 = 0; i1 < n; i1++) {
				v2[i1] = 0.0;
				for (i2 = 0; i2 < n; i2++)
					v2[i1] += A[i1][i2] * v1[i2];
				l2 += v2[i1] * v2[i1];
			}
			l2 = Math.sqrt(l2);
			for (i1 = 0; i1 < n; i1++)
				v2[i1] /= l2;
						// Žû‘©”»’è
							// Žû‘©‚µ‚½ê‡
			if (Math.abs((l2-l1)/l1) < eps) {
				k1 = -1;
				for (i1 = 0; i1 < n && k1 < 0; i1++) {
					if (Math.abs(v2[i1]) > 0.001) {
						k1 = i1;
						if (v2[k1]*v1[k1] < 0.0)
							l2 = -l2;
					}
				}
				k    = ct;
				r[i] = l2;
				for (i1 = 0; i1 < n; i1++)
					v[i][i1] = v2[i1];
				if (i == n-1)
					ind = i + 1;
				else {
					for (i1 = 0; i1 < n; i1++) {
						for (i2 = 0; i2 < n; i2++) {
							C[i1][i2] = 0.0;
							for (i3 = 0; i3 < n; i3++) {
								x          = (i1 == i3) ? A[i1][i3] - l2 : A[i1][i3];
								C[i1][i2] += x * B[i3][i2];
							}
						}
					}
					for (i1 = 0; i1 < n; i1++) {
						for (i2 = 0; i2 < n; i2++)
							B[i1][i2] = C[i1][i2];
					}
					for (i1 = 0; i1 < n; i1++) {
						v1[i1] = 0.0;
						for (i2 = 0; i2 < n; i2++)
							v1[i1] += B[i1][i2] * v0[i2];
					}
					for (i1 = 0; i1 < n; i1++)
						v0[i1] = v1[i1];
					ind = power(i+1, n, m, ct, eps, A, B, C, r, v, v0, v1, v2);
				}
			}
							// Žû‘©‚µ‚È‚¢ê‡
			else {
				for (i1 = 0; i1 < n; i1++)
					v1[i1] = v2[i1];
				l1 = l2;
				k++;
			}
		}
	
		return ind;
	}

	
	double[][] hess(double[][] A, double[][] Q,int n)
	{
		double[][] W,E,Qi,Qj,AA,QQ;
        double[] wi, ai;
        W = new double[n][n];
        E = new double[n][n];
        Qi = new double[n][n];
        Qj = new double[n][n];
        AA = new double[n][n];
        QQ = new double[n][n];
        
        wi = new double[n];
        ai = new double[n];
        
 
        double ss, s, alpha, t, si;
        int i, j, k;
        for (i = 0; i < n; i++){
            for (j = 0; j < n; j++)
            	Q[i][j] = E[i][j] = ((i == j) ? 1.0 : 0.0);
        }
        for (i = 0; i < n - 2; i++){
            for (k = 0; k < n; k++){ 
                    ai[k] = ((k <= i) ? 0.0 : A[k][i]);
                    wi[k] = ((k <= i) ? 0.0 : ai[k]); 
            }
            ss = 0.0;
            for (k = i + 1 ; k < n; k++)
                    ss += Math.pow(ai[k], 2); 
            s = Math.sqrt(ss);
            si = 1.0;
            if (ai[i + 1] < 0)
            	si = -1.0;
            
            wi[i + 1] = ai[i + 1] + si * s;
            t=0.0;
            for (k = 0; k < n; k++)
                    t+=wi[k]*wi[k];
            alpha=2.0/t;
            for (j = 0; j < n; j++){
                    for (k = 0; k < n; k++)
                            W[k][j]=wi[k]*wi[j]; 
            }
            for (k = 0; k < n; k++){
                    for (j = 0; j < n; j++)
                                    Qi[k][j]=E[k][j]-alpha*W[k][j]; 
            }
            QQ = multi(Qi, Q);
            AA = multi(Qi, A);
            for (k = 0; k < n; k++){
                for (j = 0; j < n; j++){
                    Qj[k][j] = Qi[j][k]; 
                    Q[k][j] = QQ[k][j]; 
                }
            }
            A = multi(AA, Qj);
        }
        
        double[][] hessMat = new double[n][n];
        
        for (k = 0; k < n; k++){
            for (j = 0; j < n; j++){

                	hessMat[k][j] = A[k][j];
                
            }
        }
        
        return hessMat;
	}

	
	private double[][] multi(double A[][], double B[][])
    {
        int i,j,k;
        int size = A.length;
        double[][] mat = new double[size][size];

        for(i=0;i<size;i++) {
            for(j=0;j<size;j++) {
                for(k=0;k<size;k++) {
                	mat[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        
        return mat;
    }

	public void printEigen(){
		if(size == -1){
			System.out.println("Eigen is not defined");
		}
		DecimalFormat df = new DecimalFormat("0.0000");
		
		for(int i = 0; i < size; i++){
			System.out.print("ƒÉ" + (i + 1) + " = " + df.format(eigen[i]) + "  ");
			System.out.print("ƒÉ" + (i + 1) + "Vector = ");
			for(int j = 0; j < size; j++){
				System.out.print(df.format(eigenVec[j][i]) + " ");
			}
			System.out.println("");
		}
	}
	
	public double[] getEigen(){
		return eigen;
	}
}
