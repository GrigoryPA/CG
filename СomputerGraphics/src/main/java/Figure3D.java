import javax.swing.*;
import java.awt.*;

public class Figure3D {
	//public Color colorFigure;
	public int[][] pointsResult;
	public double[][] points;
	public double countScale = 1;
	public double[][] Isometric =  {{0.707107, -0.408248, 0.577353, 0},
									{0, 0.816497, 0.577345, 0},
									{-0.707107, -0.408248, 0.577353, 0},
									{0, 0, 0, 1}};
	public double[][] ProjectionOnXOY =    {{1, 0, 0, 0},
											{0, 1, 0, 0},
											{0, 0, 0, 0},
											{0, 0, 0, 1}};
	public double[][] RotationY90 =  {{0, 0, 1, 0},
									{0, 1, 0, 0},
									{-1, 0, 0, 0},
									{0, 0, 0, 1}};
	public double[][] Dimetric =    {{0.925820, 0.133631, -0.353553, 0},
									{0, 0.935414, 0.353553, 0},
									{0.377964, -0.327329, 0.866025, 0},
									{0, 0, 0, 1}};

	public Figure3D(JTable Table) {
		points = new double[Table.getRowCount()*2][4];
		pointsResult = new int[Table.getRowCount()*2][2];
		try {
			for(int i=0, j = 0; i<Table.getRowCount();i++) {
				points[j][0]=Integer.parseInt((String) Table.getValueAt(i, 0));
				points[j][1]=Integer.parseInt((String) Table.getValueAt(i, 1));
				points[j][2]=Integer.parseInt((String) Table.getValueAt(i, 2));
				points[j][3]=1;
				j++;
				points[j][0]=Integer.parseInt((String) Table.getValueAt(i, 3));
				points[j][1]=Integer.parseInt((String) Table.getValueAt(i, 4));
				points[j][2]=Integer.parseInt((String) Table.getValueAt(i, 5));
				points[j][3]=1;
				j++;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "All values should be integer!\nBlank cells and rows are not allowed!");
		}
	}

	public Figure3D(double P[][]) {
		points = new double[P.length][4];
		pointsResult = new int[P.length][2];
			for(int i=0; i<P.length; i++) {
				points[i][0]=P[i][0];
				points[i][1]=P[i][1];
				points[i][2]=P[i][2];
				points[i][3]=1;
			}
	}

	public void AddFigureOnDisplay(Color colorFigure) {
		//Line3D.AddLineSigmentOnDisplayBresenham(pointsResult[0][0], pointsResult[0][1],pointsResult[pointsResult.length-1][0], pointsResult[pointsResult.length-1][1]);
		for(int i=0;i<pointsResult.length-1;i+=2) {
			Line3D.AddLineSigmentOnDisplayBresenham(pointsResult[i][0], pointsResult[i][1], pointsResult[i + 1][0], pointsResult[i + 1][1], colorFigure);
		}
	}

	public void DimetricProjection() {
		double[][] M = points;

		M = Multiply(M,Dimetric);
		M = Multiply(M,ProjectionOnXOY);
		for(int i=0;i<M.length;i++) {
			pointsResult[i][0] = (int) M[i][0];
			pointsResult[i][1] = (int) M[i][1];
		}
	}

	public void IsometricProjection() {
		double[][] M = points;
		M = Multiply(M,Isometric);
		M = Multiply(M,ProjectionOnXOY);
		for(int i=0;i<M.length;i++) {
			pointsResult[i][0] = (int) M[i][0];
			pointsResult[i][1] = (int) M[i][1];
		}
	}
	/*
	public void ScaleUp(int updown) {
		double[][] b = new double[2][2];
		try {
			if(updown==-1 && countScale>=2) {
				countScale--;
				b[0][0] = countScale / (countScale + 1);  b[0][1] = 0;
				b[1][0] = 0;                              b[1][1] = countScale / (countScale + 1);
				points = Multiply(points,b);
			}
			else if(updown==1){
				countScale++;
				b[0][0] = countScale / (countScale - 1);  b[0][1] = 0;
				b[1][0] = 0;                              b[1][1] = countScale / (countScale - 1);
				points = Multiply(points, b);
			}
			System.out.println(countScale+"   "+updown);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null,
					"All values should be integer!\nBlank cells and rows are not allowed");
		}
	}*/

	public void Rotate( double rotateX, double rotateY) {
		double[][] b = {{Math.cos(rotateY), Math.sin(rotateX)*Math.sin(rotateY),  -Math.sin(rotateY)*Math.cos(rotateX),0},
						{0,                 Math.cos(rotateX),             Math.sin(rotateX), 0},
						{Math.sin(rotateY),        -Math.sin(rotateX)*Math.cos(rotateY), Math.cos(rotateX)*Math.cos(rotateY),0},
						{0,                 0,                      0, 1}};
		points=Multiply(points, b);
	}

	/*public void Reflexion( int axis) {
		double[][] b = new double[2][2];
			if(axis==2) {
				b[0][0] = -1; b[0][1] = 0;
				b[1][0] = 0; b[1][1] = 1;
			}
			else{
				b[0][0] = 1; b[0][1] = 0;
				b[1][0] = 0; b[1][1] = -1;
			}

		points=Multiply(points,b);
	}*/
	
	public double[][] Multiply(double[][] A, double[][] B) {
		double[][] c=new double[A.length][B[0].length];
		for (int i=0; i<A.length; ++i)
		    for (int j=0; j<B[0].length; ++j)
		    	c[i][j] = (int)(A[i][0] * B[0][j] + A[i][1] * B[1][j] + A[i][2] * B[2][j] + A[i][3] * B[3][j]) ;
		return c;
	}
}
