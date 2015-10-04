import java.text.DecimalFormat;


public class Main {
	public static void main(String[] args) {
		//double[][] a = {{2,1,8,2,5},{9,1,9,2,3},{1,7,3,5,7},{3,3,8,1,6},{3,2,4,7,9}};
		//double[] b = {4.0, 5.0 ,6, 9, 2};
		Matrix matA = new Matrix(CSV.readMatrix("matrix.csv"));
		Vector vecB = new Vector(CSV.readExpansion("expansion.csv"));
		
		//3���̃e�X�g�p�s��
		double[][] c = {{1,1,2},{0,2,-1},{0,0,3}};
		Matrix matC = new Matrix(c);

		//LU����
		LUDecomposition lu = new LUDecomposition(matA.getMat());
		//lu.printLU();
		
		//LU�����ɂ��s�񎮂̌v�Z
		DecimalFormat df = new DecimalFormat("0.000E0");
		System.out.println("|A| = " + df.format(lu.getDeterminant()));
		
		//LU��������A���P��������������
		SimultaneousEquation se = new SimultaneousEquation(lu.getL(), lu.getU(), vecB.getVec());
		
		//���̕\��
		se.printXVec();

		/*
		 * �ŗL�l�A�ŗL�x�N�g���̌v�Z���s��
		 * �ŗL�l�ɋ������܂܂��Ɛ���ɉ����Ȃ�
		 * �J��Ԃ��񐔂�100�ȏオ����B���Ȃ�����Ɖ������܂�Ȃ�
		 */
		Eigenvalue eg = new Eigenvalue(matC.getMat(), 200);
		eg.printEigen();
	}	
}