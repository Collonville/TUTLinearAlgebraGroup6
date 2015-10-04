
public class Vector {
	private double vector[];
	private int size;
	
	Vector(double[] mat){
		size = mat.length;
		
		double[] valMat = new double[size];
		for(int i = 0; i < size; i++){
			valMat[i] = mat[i];
		}
		vector = valMat;
	}
	
	public void showVec(){
		for(int i = 0; i < size; i++){
			System.out.print(vector[i] + " ");
		}
		System.out.println("");
	}

	public double[] getVec(){
		return vector;
	}
}
