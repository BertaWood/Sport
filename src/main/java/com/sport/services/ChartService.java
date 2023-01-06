package com.sport.services;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ChartService {

    private static CategoryDataset createDatasetFromHashMap(HashMap<String, Double> map)
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (String key : map.keySet()) {
            dataset.addValue(map.get(key), (Integer)10, key);
        }

        return dataset;
    }

    public static JFreeChart createChartFromDataset(CategoryDataset dataset) throws IOException
    {
        JFreeChart chart = ChartFactory.createBarChart(
                "Average age of players on different teams",
                null,
                "Age",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
        chart.setBackgroundPaint(Color.white);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        chart.removeLegend();

        ChartUtilities.saveChartAsPNG(new File("./histogram.png"), chart, 1100, 400);

        return chart;
    }

    public static JFreeChart createChartFromHashMap(HashMap<String, Double> map) throws IOException {
        return createChartFromDataset(createDatasetFromHashMap(map));
    }
}
