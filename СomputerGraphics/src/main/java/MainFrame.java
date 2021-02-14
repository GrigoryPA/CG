import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.MathContext;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;



public class MainFrame {
	private Display2D display;
	private JToolBar toolBar1;
	private JButton AddRow;
	private JButton DeleteRow;
	private JButton Draw;
	private JFrame frame;
	private DefaultTableModel tableModel;
	private JTable Table;
	private JScrollPane scroller; 
	private Figure2D figureRealTime;
    private Figure2D figureOriginal;
    private Curve2D curve;
	private JPanel FilterPanel;
	private JButton ScaleUp;
    private JButton ScaleDown;
    private JButton RotateRight;
    private JButton RotateLeft;
    private JButton Bezier;
    private JToolBar toolBar2;
    public double deltaAngel = Math.PI/18;
    private double countRotateAngel=0;

    public void MakeAndShow() {


		frame=new JFrame("������ 2� ������ � ����������� �������");
        frame.setBounds(100,50, 400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AddRow=new JButton(new ImageIcon("src/main/resources/icons/Add48x48.png"));
        DeleteRow=new JButton(new ImageIcon("src/main/resources/icons/Delete48x48.png"));
        Draw = new JButton(new ImageIcon("src/main/resources/icons/Rectangle48x48.png"));
        ScaleUp= new JButton(new ImageIcon("src/main/resources/icons/ScaleUp48x48.png"));
        ScaleDown= new JButton(new ImageIcon("src/main/resources/icons/ScaleDown48x48.png"));
        RotateRight = new JButton(new ImageIcon("src/main/resources/icons/RotateRight48x48.png"));
        RotateLeft= new JButton(new ImageIcon("src/main/resources/icons/RotateLeft48x48.png"));
        Bezier= new JButton(new ImageIcon("src/main/resources/icons/Bezier48x48.png"));

        AddRow.setToolTipText("Add row");
        DeleteRow.setToolTipText("Delete row");
        Draw.setToolTipText("Draw");
        ScaleUp.setToolTipText("Scale up");
        ScaleDown.setToolTipText("Scale down");
        RotateRight.setToolTipText("Rotate right");
        RotateLeft.setToolTipText("Rotate left");
        Bezier.setToolTipText("Draw curve Bezier");

        toolBar1 = new JToolBar();
        toolBar1.add(AddRow);
        toolBar1.add(DeleteRow);
        toolBar1.add(Draw);
        toolBar1.add(Bezier);
        frame.add(toolBar1, BorderLayout.NORTH);
        
        String[] headers = {"X (0<=X<700)", "Y (0<=Y<500)"};
        String [][] data;
        data=new String[1][2];
        tableModel=new DefaultTableModel(data, headers);
        Table=new JTable(tableModel);
        Table.setAutoCreateRowSorter(true);

        /* �� ��������
        Font font = Table.getFont();
        font = font.deriveFont((float) (font.getSize2D() * 10));
        Table.setFont(font);
        */

        Table.setRowHeight(Table.getRowHeight()+10);
        scroller=new JScrollPane(Table);
        frame.add(scroller, BorderLayout.CENTER);


        toolBar2 = new JToolBar();
        toolBar2.add(RotateLeft);
        toolBar2.add(RotateRight);
        toolBar2.add(ScaleUp);
        toolBar2.add(ScaleDown);
        frame.add(toolBar2, BorderLayout.SOUTH);

        
        frame.setVisible(true);
        
		// �������� � ����������� �������� �����
        RotateLeft.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    display.Clear();
                    display.AddCoordinateAxes();
                    figureRealTime = new Figure2D(Table);//���� ��� ����� ��� ������� � �������
                    try {
                        countRotateAngel +=-1*deltaAngel;
                        figureRealTime.Rotate(-1, countRotateAngel);
                        figureRealTime.AddFigure2DOnDisplay2D();
                        display.UpdateImage();
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, "������ �� ���������� �� �����������!");
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "������� ��������� ��������!");
                }
            }
        });

        RotateRight.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    display.Clear();
                    display.AddCoordinateAxes();
                    figureRealTime = new Figure2D(Table);//���� ��� ����� ��� ������� � �������
                    try {
                        countRotateAngel +=1*deltaAngel;
                        figureRealTime.Rotate(1, countRotateAngel);
                        figureRealTime.AddFigure2DOnDisplay2D();
                        display.UpdateImage();
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, "������ �� ���������� �� ��������!");
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "������� �������� ��������!");
                }
            }
        });


        AddRow.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                tableModel.addRow(new String[] {"",""});
            }
        });
        
        Draw.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                display = new Display2D();
                display.AddCoordinateAxes();
            	figureOriginal = new Figure2D(Table);
            	figureOriginal.AddFigure2DOnDisplay2D();
            	display.CreateAndOpenImage();
            	figureRealTime=figureOriginal;
            }
        });

        Bezier.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                display = new Display2D();
                display.AddCoordinateAxes();
                curve = new Curve2D(Table);
                curve.ResultPoints();
                curve.AddCurve2DOnDisplay2D();
                display.CreateAndOpenImage();
            }
        });
        
        ScaleUp.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                display.Clear();
                display.AddCoordinateAxes();
            	try {
					figureRealTime.ScaleUp(1);
					figureRealTime.AddFigure2DOnDisplay2D();
					display.UpdateImage();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "������ �� ���������� �� �����������!");
				}
            }
        });

        ScaleDown.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                display.Clear();
                display.AddCoordinateAxes();
                try {
                        figureRealTime.ScaleUp(-1);
                        figureRealTime.AddFigure2DOnDisplay2D();
                        display.UpdateImage();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "������ �� ���������� �� �����������!");
                }
            }
        });
        
        DeleteRow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int[] rows=Table.getSelectedRows();
    			if (rows.length>0) {
    				tableModel.removeRow(rows[0]);
    			}
    			else
    				JOptionPane.showMessageDialog(frame, "������ ��������", "��������������", 0);
            }
        });
	}
	
	public static void main(String[] args) {
		// �������� � ����������� �������� �����
		new MainFrame().MakeAndShow();
	}
	
	
}
