import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NearestNeighbor {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		//Print my info
		System.out.println("Programming Fundamentals");
		System.out.println("NAME: Zachary Bennitt");
		System.out.println("PROGRAMMING ASSIGNMENT 3");
		System.out.println(" ");
		
		Scanner scan = new Scanner(System.in);
		
		//Allow user to input file names
		System.out.print("Enter the name of the training file: ");
		File fileTraining = new File(scan.nextLine());
		Scanner inputTraining = new Scanner(fileTraining);
		System.out.print("Enter the name if the testing file: ");
		File fileTesting = new File(scan.nextLine());
		Scanner inputTesting = new Scanner(fileTesting);
		scan.close();
		
		//Create arrays for attributes and class
		double[][] attributeTraining = new double[75][4];
		double[][] attributeTesting = new double[75][4];
		String[] classTraining = new String[75];
		String[] classTesting = new String[75];
		String[] predictor = new String[75];
		
		classTraining = arrayClasses(inputTraining, classTraining);
		classTesting = arrayClasses(inputTesting, classTesting);
		
		attributeTraining = arrayAttributes(inputTraining, attributeTraining);
		attributeTesting = arrayAttributes(inputTesting, attributeTesting);
		
		//Loop to predict and record predictions
		int near = 0;
		for (int a = 0; a < 75; a++) {
			near = distance(attributeTraining[a][0], attributeTraining[a][1], attributeTraining[a][2], attributeTraining[a][3], attributeTraining);
			predictor[a] = classTraining[near];
		}
		prediction(predictor, classTesting);
		scan.close();
	}
	
	static double[][] arrayAttributes(Scanner scan, double[][] attributes) {
		
		String[][] arrayAtt = new String[75][4];
		int x = 0;
		String row;
		while (scan.hasNextLine()) {
			row = scan.nextLine();
			String[] y = row.split(",");
			for (int a = 0; a < 4; a++) {
				arrayAtt[x][a] = y[a];
				attributes[x][a] = Double.parseDouble(arrayAtt[x][a]);
			}
			x++;		
		}
		return attributes;
	}
	
	static String[] arrayClasses(Scanner scan, String[] classes) {
		int x = 0;
		String row = " ";
		while (scan.hasNextLine()) {
			row = scan.nextLine();
			String[] y = row.split(",");
			classes[x] = y[4];
			x++;
		}
		return classes;
	}
	
	//Method to find shortest distance and nearest neighbor
	static int distance(double sLength, double sWidth, double pLength, double pWidth, double[][] attributeTraining) {
		int x = 0;
		double sLengthSum, sWidthSum, pLengthSum, pWidthSum, dist;
		double sLengthTry, sWidthTry, pLengthTry, pWidthTry, distTry;
		
		sLengthSum = Math.pow((attributeTraining[0][0] - sLength), 2);
		sWidthSum = Math.pow((attributeTraining[0][1] - sWidth), 2);
		pLengthSum = Math.pow((attributeTraining[0][2] - pLength), 2);
		pWidthSum = Math.pow((attributeTraining[0][3] - pWidth), 2);
		dist = Math.sqrt(sLengthSum + sWidthSum + pLengthSum + pWidthSum);
		for (int a = 0; a < 75; a++) {
			sLengthTry = Math.pow((attributeTraining[a][0] - sLength), 2);
			sWidthTry = Math.pow((attributeTraining[a][1] - sWidth), 2);
			pLengthTry = Math.pow((attributeTraining[a][2] - pLength), 2);
			pWidthTry = Math.pow((attributeTraining[a][3] - pWidth), 2);
			distTry = Math.sqrt(sLengthTry + sWidthTry + pLengthTry + pWidthTry);
			if (distTry < dist) {
				x = a;
				dist = distTry;
			}
		}
		return x;
	}
	
	//Method to print prediction and accuracy
	static void prediction(String[] labelTrue, String[] labelPredicted) {
		
		double predict = 0;
		int x = 1;
		System.out.println("EX: TRUE LABEL, PREDICTED LABEL");
		
		for (int a = 0; a < 75; a++) {
			System.out.println(x + ": " + labelTrue[a] + " " + labelPredicted[a]);
			if (labelPredicted[a].equals(labelTrue[a])) {
				predict++;
			}
			x++;
		}
		
		double accuracy = (predict/labelTrue.length);
		System.out.println("ACCURACY: " + accuracy);
		
	}

}
