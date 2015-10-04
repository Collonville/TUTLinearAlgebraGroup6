import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class CSV {
	public static int dimention = -1;
	
	public static final double[][] readMatrix(String filename){
		String ret[] = getLines(filename);

		int row = ret.length;

		String col_data[];
		col_data = ret[0].split(",");
		int col = col_data.length;

		double [][]sequ = new double[row][col];

		String data[];
	    try{
		    for(int i=0; i<ret.length; i++){
		    	data = ret[i].split(",");
		    	for(int j=0; j<data.length; j++){
		    		sequ[i][j] = Double.valueOf(data[j]);
		    	}
		    }
		}catch(ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
		} 
		
		return sequ;       
	}
	
	private static String[] getLines(String fileName){
		ArrayList<String> list = new ArrayList<String>();
		try{
			File csv = new File(fileName);
			BufferedReader br = new BufferedReader(new FileReader(csv));
		    String line = null;

		    while((line = br.readLine()) != null){
			list.add(line);
		    }
		    br.close();
		}catch(IOException err){
			err.printStackTrace();
		}
	    
		return(String[]) list.toArray(new String[list.size()]);
	}
	
	public static double[] readExpansion(String filePath){
		ArrayList<Double> data = new ArrayList<Double>();
		try{
			FileInputStream fis = new FileInputStream(filePath);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);

			String line;
			while((line = br.readLine()) != null){
				 String[] cols = line.split(",");
				 for(int i = 0; i < cols.length; i++){
					 data.add(Double.parseDouble(cols[i]));
					 dimention++;
				 }
					 
			}
		    br.close();
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		int i = 0;
		double[] ret = new double[data.size()];
		Iterator<Double> iterator = data.iterator();
		while(iterator.hasNext()){
			ret[i] = iterator.next().doubleValue();
			i++;
		}
		
		return ret;
	}
	
	public static double[] parseInts(String[] s){
        double[] x = new double[s.length];
        for(int i = 0; i < s.length; i++){
            x[i] = Double.parseDouble(s[i]);
        }
        return x;
    }
}
