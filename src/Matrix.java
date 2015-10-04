
public class Matrix {
	private  double matrix[][];
	private  int size;
	
	Matrix(double[][] mat){
		size = mat.length;
		
		double[][] valMat = new double[size][size];
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				valMat[i][j] = mat[i][j];
			}
		}
		matrix = valMat;
	}
	
	public double[][] inverse(){
		if(size == -1){
			System.out.print("Matrix is not defined");
			return matrix;
		}
		
		double[][] inverse = new double[size][size];
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				inverse[i][j] = matrix[i][j];
			}
		}
		
        int n = matrix.length;
        for (int k = 0; k < n; k++) {
            double t = inverse[k][k];

            for (int i = 0; i < n; i++) 
            	inverse[k][i] /= t;
            inverse[k][k] = 1 / t;
            for (int j = 0; j < n; j++)
                if (j != k) {
                    double u = inverse[j][k];
                    for (int i = 0; i < n; i++)
                        if (i != k) 
                        	inverse[j][i] -= inverse[k][i] * u;
                        else        
                        	inverse[j][i]  = - u / t;
                }
        }

	    
	    return inverse;
	}
	
	public void showMat(){
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println("");
		}
	}

	public double[][] getMat(){
		return matrix;
	}
}
