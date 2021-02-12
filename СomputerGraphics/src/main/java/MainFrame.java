import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private Figure2D figure;
	private JTextField SearchSpace;
	private JPanel FilterPanel;
	private JButton ScaleUp;
    private JButton RotateRight;
    private JButton RotateLeft;
    private JToolBar toolBar2;

    public void MakeAndShow() {
		frame=new JFrame("Рисуем 2Д объект и увеличиваем масштаб");
        frame.setBounds(100,100, 400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AddRow=new JButton(new ImageIcon("src/main/resources/icons/add.png"));
        DeleteRow=new JButton(new ImageIcon("src/main/resources/icons/delete.png"));
        Draw = new JButton(new ImageIcon("src/main/resources/icons/Draw.png"));
        ScaleUp= new JButton(new ImageIcon("src/main/resources/icons/ScaleUp.png"));
        RotateRight = new JButton(new ImageIcon("src/main/resources/icons/Draw.png"));
        RotateLeft= new JButton(new ImageIcon("src/main/resources/icons/ScaleUp.png"));
        
        AddRow.setToolTipText("Add row");
        DeleteRow.setToolTipText("Delete row");
        Draw.setToolTipText("Draw");
        ScaleUp.setToolTipText("Scale up");
        RotateRight.setToolTipText("Rotate right");
        RotateLeft.setToolTipText("Rotate left");

        toolBar1 = new JToolBar();
        toolBar1.add(AddRow);
        toolBar1.add(DeleteRow);
        toolBar1.add(Draw);
        frame.add(toolBar1, BorderLayout.NORTH);


        
        String[] headers = {"X (0<=X<700)", "Y (0<=Y<500)"};
        String [][] data;
        data=new String[1][2];
        tableModel=new DefaultTableModel(data, headers);
        Table=new JTable(tableModel);
        Table.setAutoCreateRowSorter(true);
        scroller=new JScrollPane(Table);
        frame.add(scroller, BorderLayout.CENTER);

        SearchSpace=new JTextField("Кэф увеличения (>0)",20);
        toolBar2 = new JToolBar();
        toolBar2.add(RotateLeft);
        toolBar2.add(RotateRight);
        toolBar2.add(ScaleUp);
        toolBar2.add(SearchSpace,-1);
        frame.add(toolBar2, BorderLayout.SOUTH);

        
        frame.setVisible(true);
        
		// Создание и отображение экранной формы
        
        AddRow.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                tableModel.addRow(new String[] {"",""});
            }
        });
        
        Draw.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                display = new Display2D();
                display.AddCoordinateAxes();
            	figure = new Figure2D(Table);
            	figure.AddFigure2DOnDisplay2D();
            	display.CreateAndOpenImage();
            }
        });
        
        ScaleUp.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                display = new Display2D();
                display.AddCoordinateAxes();
            	figure = new Figure2D(Table);
            	try {
					figure.ScaleUp(SearchSpace);
					figure.AddFigure2DOnDisplay2D();
					display.CreateAndOpenImage();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Фигура не помещается на изображение!");
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
    				JOptionPane.showMessageDialog(frame, "Ошибка удаления", "Предупреждение", 0);
            }
        });
	}
	
	public static void main(String[] args) {
		// Создание и отображение экранной формы
		new MainFrame().MakeAndShow();
	}
	
	
}
