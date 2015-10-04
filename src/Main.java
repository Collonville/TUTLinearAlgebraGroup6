import java.text.DecimalFormat;


public class Main {
	public static void main(String[] args) {
		//double[][] a = {{2,1,8,2,5},{9,1,9,2,3},{1,7,3,5,7},{3,3,8,1,6},{3,2,4,7,9}};
		//double[] b = {4.0, 5.0 ,6, 9, 2};
		Matrix matA = new Matrix(CSV.readMatrix("matrix.csv"));
		Vector vecB = new Vector(CSV.readExpansion("expansion.csv"));
		
		//3次のテスト用行列
		double[][] c = {{1,1,2},{0,2,-1},{0,0,3}};
		Matrix matC = new Matrix(c);

		//LU分解
		LUDecomposition lu = new LUDecomposition(matA.getMat());
		//lu.printLU();
		
		//LU分解による行列式の計算
		DecimalFormat df = new DecimalFormat("0.000E0");
		System.out.println("|A| = " + df.format(lu.getDeterminant()));
		
		//LU分解から連立１次方程式を解く
		SimultaneousEquation se = new SimultaneousEquation(lu.getL(), lu.getU(), vecB.getVec());
		
		//解の表示
		se.printXVec();

		/*
		 * 固有値、固有ベクトルの計算を行う
		 * 固有値に虚数が含まれると正常に解けない
		 * 繰り返し回数は100以上が安定。少なすぎると解が求まらない
		 */
		Eigenvalue eg = new Eigenvalue(matC.getMat(), 200);
		eg.printEigen();
	}	
}