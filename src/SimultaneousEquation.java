import java.text.DecimalFormat;


public class SimultaneousEquation {
	private int size = -1;
	private double[] xVec;
	
	SimultaneousEquation(double[][] L, double[][] U, double[] b){
		size = b.length;
		
		//L行列またはU行列とbベクトルの大きさが一致しない時
		if(size != L.length || size != U.length){
			System.out.println("Matrix & b vector is not same size!");
			return;
		}
		
		//LUx = b --> Ly = b --> Ux = y
		xVec = gaussJordanMethod(U, gaussJordanMethod(L, b));
	}
	
	//ガウス・ジョルダン法による解法
	private double[] gaussJordanMethod(double[][] a, double[] b){
		double inv_pivot, temp;
		double big;
		int pivot_row = 0;
		int[] row = new int[size + 10];

		for(int ipv = 0; ipv < size; ipv++){
	      /* ---- 最大値探索 ---------------------------- */
			big = 0.0;
			for(int i = ipv; i < size ; i++){
				if(Math.abs(a[i][ipv]) > big){
					big = Math.abs(a[i][ipv]);
					pivot_row = i;
				}
			}
			if(big == 0.0){
				//return 0;
				System.out.print("big is 0");
			}	
			row[ipv] = pivot_row;

	      //行の入れ替え
	      if(ipv != pivot_row){
	         for(int i = 0; i < size ; i++){
	            temp = a[ipv][i];
	            a[ipv][i] = a[pivot_row][i];
	            a[pivot_row][i] = temp;
	         }
	         temp = b[ipv];
	         b[ipv] = b[pivot_row];
	         b[pivot_row] = temp;
	      }

	      inv_pivot = 1.0 / a[ipv][ipv];
	      a[ipv][ipv] = 1.0;
	      for(int j = 0; j < size ; j++){
	         a[ipv][j] *= inv_pivot;
	      }
	      b[ipv] *= inv_pivot;

	      for(int i = 0; i < size ; i++){
	         if(i != ipv){
	            temp = a[i][ipv];
	            a[i][ipv] = 0.0;
	            for(int j = 0; j < size ; j++){
	               a[i][j] -= temp*a[ipv][j];
	            }
	            b[i] -= temp * b[ipv];
	         }
	      }
	   }

	   //列の入れ替え
	   for(int j = size -1 ; j > 0 ; j--){
	      if(j != row[j]){
	         for(int i = 0 ; i < size ; i++){
	            temp = a[i][j];
	            a[i][j] = a[i][row[j]];
	            a[i][row[j]] = temp;
	         }
	      }
	   }
	  
	   return b;
	}
	
	//拡大係数行列の生成(Expansion Coefficient Matrix)
	private double[][] getECMatrix(double[][] mat, double[] b){
		int size = b.length;
		
		//拡大係数行列(Expansion Coefficient Matrix)
		double[][] ecMat = new double[size][size + 1];
		
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size + 1; j++){
				if(j == size){
					ecMat[i][j] = b[i];
				}else{
					ecMat[i][j] = mat[i][j];
				}
			}
		}
		
		return ecMat;
	}

	public void printXVec(){
		if(size == -1){
			System.out.println("Matrix is not defined");
			return;
		}
		
		DecimalFormat df = new DecimalFormat("0.0000");
		for(int i = 0; i < size; i++){
			System.out.println("x[" + i + "] = " + df.format(xVec[i]));
		}
	}
}
