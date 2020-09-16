package secondProgram;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/*
 * File: BenchmarkGUI.java
 * Author: Alexander Szelestey
 * Date: April 12, 2020
 * Purpose: To produce a report of the text files created in CMSC451-Project1
 * References:
 * https://github.com/HeartOfGold523/Design-and-Analysis-of-Computer-Algorithms/tree/master/Project%201/Project1/src
 * https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html
 * https://docs.oracle.com/javase/7/docs/api/javax/swing/table/TableModel.html
 * https://stackoverflow.com/questions/16010776/read-text-file-and-display-it-in-jtable
 * https://www.geeksforgeeks.org/java-swing-jfilechooser/
 * https://www.geeksforgeeks.org/program-coefficient-variation/
 * https://www.youtube.com/watch?v=2d4i6BXQPFA
 */
public class BenchmarkGUI {

	public static void main(String[] args) {
		Runnable r = new Runnable() {

			public void run() {
				new BenchmarkGUI().initialize();
			}
		};
		EventQueue.invokeLater(r);
	}

	// Initialize
	private void initialize() {
		JFileChooser fc = new JFileChooser("C:\\Users\\Alex\\eclipse-workspace\\CMSC451-Project1\\");
		DecimalFormat df = new DecimalFormat("##.##%");
		try {
			if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				JFrame frame = new JFrame();
				frame.setLayout(new BorderLayout());
				JTable table = new JTable();
				String readLine;
				MyTableModel tableModel = new MyTableModel();
				File file = fc.getSelectedFile();
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				List<DataValues> DataValuesList = new ArrayList<>();
				while ((readLine = br.readLine()) != null) {
					String[] splitData = readLine.split(" ");
					DataValues DataValues = new DataValues();
					DataValues.setSize(splitData[0]);
					List<Long> counts = new ArrayList<>();
					for (int i = 1; i < splitData.length; i += 2) {
						counts.add(Long.parseLong(splitData[i]));
					}
					DataValues.setAvgCount(mean(counts, counts.size()));
					double countCoef = coefficientOfVariation(counts, counts.size());
					String countVal = df.format(countCoef);
					DataValues.setCoefCount(countVal);
					List<Long> times = new ArrayList<>();
					for (int i = 2; i < splitData.length; i += 2) {
						times.add(Long.parseLong(splitData[i]));
					}
					DataValues.setAvgTime(mean(times, times.size()));
					double timeCoef = coefficientOfVariation(times, times.size());
					String timeVal = df.format(timeCoef);
					DataValues.setCoefTime(timeVal);
					DataValuesList.add(DataValues);
				}
				tableModel.setList(DataValuesList);
				table.setModel(tableModel);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(new JScrollPane(table));
				frame.setTitle("Benchmark Report - "+file.getName());
				frame.pack();
				frame.setVisible(true);
			}
		} catch (IOException e) {
		}
	}

	// Implement formulas
	// Reference: https://www.geeksforgeeks.org/program-coefficient-variation/
	static double mean(List<Long> arr, int n) {
		double sum = 0;
		for (int i = 0; i < n; i++) {
			sum = sum + arr.get(i);
		}
		return sum / n;
	}

	static double standardDeviation(List<Long> arr, int n) {
		double sum = 0;
		for (int i = 0; i < n; i++) {
			sum = sum + (arr.get(i) - mean(arr, n)) * (arr.get(i) - mean(arr, n));
		}
		return Math.sqrt(sum / (n - 1));
	}

	static double coefficientOfVariation(List<Long> arr, int n) {
		return (standardDeviation(arr, n) / mean(arr, n));
	}

	// Generated Getters and Setters
	class DataValues {

		private String size;
		private Double avgCount;
		private String coefCount;
		private Double avgTime;
		private String coefTime;

		public String getSize() {
			return size;
		}

		public void setSize(String size) {
			this.size = size;
		}

		public Double getAvgCount() {
			return avgCount;
		}

		public void setAvgCount(Double avgCount) {
			this.avgCount = avgCount;
		}

		public String getCoefCount() {
			return coefCount;
		}

		public void setCoefCount(String coefCount) {
			this.coefCount = coefCount;
		}

		public Double getAvgTime() {
			return avgTime;
		}

		public void setAvgTime(Double avgTime) {
			this.avgTime = avgTime;
		}

		public String getCoefTime() {
			return coefTime;
		}

		public void setCoefTime(String coefTime) {
			this.coefTime = coefTime;
		}
	}

	// Create Table by reading in content from a text file
	// https://stackoverflow.com/questions/16010776/read-text-file-and-display-it-in-jtable
	class MyTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;
		private List<DataValues> list = new ArrayList<>();
		private String[] columnNames = { "Size", "Avg Count", "Coef Count", "Avg Time", "Coef Time" };

		public void setList(List<DataValues> list) {
			this.list = list;
			fireTableDataChanged();
		}

		@Override
		public String getColumnName(int column) {
			return columnNames[column];
		}

		public int getRowCount() {
			return list.size();
		}

		public int getColumnCount() {
			return columnNames.length;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return list.get(rowIndex).getSize();
			case 1:
				return list.get(rowIndex).getAvgCount();
			case 2:
				return list.get(rowIndex).getCoefCount();
			case 3:
				return list.get(rowIndex).getAvgTime();
			case 4:
				return list.get(rowIndex).getCoefTime();
			default:
				return null;
			}
		}
	}
}