package org.example.content.ui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChart extends JPanel {
    private DefaultCategoryDataset dataset;
    private JTable table;
    private JScrollPane scrollPane;

    public BarChart(JTable table) {
        // 初始化表格数据集
        this.table = table;
        this.dataset = new DefaultCategoryDataset();
        this.scrollPane = new JScrollPane(table);
        // 统计各科目分数区间的数量
        Map<String, Integer> countMap = new HashMap<String, Integer>();
        for (int i = 0; i <= 100; i++) {
            String range = getRange(i);
            countMap.put(range, 0);
        }
        for (int i = 0; i < table.getRowCount(); i++) {
            String subject = (String) table.getValueAt(i, 0);
            double score = (double) table.getValueAt(i, 1);
            String range = getRange(score);
            countMap.put(range, countMap.getOrDefault(range, 0) + 1);
        }
        // 将数量添加到数据集中
        for (String range : countMap.keySet()) {
            dataset.addValue(countMap.get(range), "Count", range);
        }
        // 创建柱状图
        JFreeChart chart = ChartFactory.createBarChart("Subject Scores", "Score Range", "Count",
                dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(79, 129, 189));
        renderer.setDrawBarOutline(false);
        renderer.setShadowVisible(false);
        // 设置 X 轴标签
        CategoryAxis xAxis = plot.getDomainAxis();
        xAxis.setTickLabelFont(xAxis.getTickLabelFont().deriveFont(14f));
        // 设置 Y 轴标签
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setTickLabelFont(yAxis.getTickLabelFont().deriveFont(14f));
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // 创建图表面板并添加到 JPanel 中
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 350));
        add(chartPanel);
    }

    private String getRange(double score) {
        if (score >= 90) {
            return "A";
        } else if (score >= 80) {
            return "B";
        } else if (score >= 70) {
            return "C";
        } else if (score >= 60) {
            return "D";
        } else {
            return "E";
        }
    }

}