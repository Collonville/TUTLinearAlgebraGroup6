
public class LUDecomposition {
	private int size = -1; //行列の大きさ
	private double[][] L; //L行列
	private double[][] U; //U行列
	
	LUDecomposition(double[][] matrix){
		size = matrix.length;
		
		L = new double[size][size];
		U = new double[size][size];
		
		double[][] valMat = new double[size][size];
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				valMat[i][j] = matrix[i][j];
			}
		}
		
		double p;
		for(int i = 0; i < size; i++){
			p = valMat[i][i];
			
			for(int j = i; j < size; j++){
				L[j][i] = valMat[j][i];
				valMat[i][j] /= p;
			}		
			
			for(int k = i + 1; k < size; k++){
				p = valMat[k][i];
				for(int j = i; j < size; j++){
					valMat[k][j] -= valMat[i][j] * p;
				}
			}
			for(int j = i; j < size; j++){
				U[i][j] = valMat[i][j];
			}
		}
	}
	
	public void printLU(){
		System.out.println("L Matrix");
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				System.out.print(L[i][j] + " ");
			}
			System.out.println("");
		}
		
		System.out.println("U Matrix");
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				System.out.print(U[i][j] + " ");
			}
			System.out.println("");
		}
	}
	
	public double getDeterminant(){
		double det = 1.0;
		double determinant = 0.0;
		
		//行列が定義されていない場合は異常値を返す
		if(size == -1)
			return Integer.MAX_VALUE;
		
		for(int i = 0; i < size; i++){
			det *= L[i][i];
		}
		determinant = det;
		det = 1.0;
		
		for(int i = 0; i < size; i++){
			det *= U[i][i];
		}
		determinant *= det;
		return determinant;
	}

	public double[][] getL(){
		return L;
	}
	
	public double[][] getU(){
		return U;
	}
}


