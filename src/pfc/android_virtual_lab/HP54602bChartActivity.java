package pfc.android_virtual_lab;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import pfc.android_virtual_lab.util.Constants;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HP54602bChartActivity extends RoboActivity{
	
	private Bundle extras;
	private String measure1, measure2;
	private final static String TAG = "HP54602bChartActivity";
	private XYSeriesRenderer trace1Renderer, trace2Renderer;
	private XYSeries trace1, trace2;
	private GraphicalView chart;	
	
	private XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
    private XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
	
	@InjectView(R.id.hp54602b_function1_result) TextView function1_measure;
	@InjectView(R.id.hp54602b_function2_result) TextView function2_measure;
	@InjectView(R.id.hp54602b_chart) LinearLayout chartLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chart);		
		
		extrasAssignment();
		
		Log.d(TAG, "Measure 1 -> " + measure1);
		Log.d(TAG, "Measure 2 -> " + measure2);
		
		function1_measure.setText(function1_measure.getText() + measure1);
		function2_measure.setText(function2_measure.getText() + measure2);
	}	
	
	@Override
	protected void onResume(){
		super.onResume();
		if(chart == null){
			initChart();
			chart = ChartFactory.getLineChartView(this, dataset, renderer);
			chartLayout.addView(chart);
		}else{
			chart.repaint();
		}
	}
	
	private void extrasAssignment(){
		extras = getIntent().getExtras();
		
		// Measures assignment
		measure1 = extras.getString(Constants.HP54602B_MEASURE_1);
		measure2 = extras.getString(Constants.HP54602B_MEASURE_2);
		// Traces assignment
		trace1 = (XYSeries) extras.get(Constants.HP54602B_TRACE_1);
		trace2 = (XYSeries) extras.get(Constants.HP54602B_TRACE_2);
	}
	
	private void initChart(){
		trace1Renderer = new XYSeriesRenderer();
		trace2Renderer = new XYSeriesRenderer();
		trace1Renderer.setColor(Color.CYAN);
		trace2Renderer.setColor(Color.RED);
		trace1Renderer.setLineWidth(3);
		trace2Renderer.setLineWidth(3);
		renderer.addSeriesRenderer(trace1Renderer);
		renderer.addSeriesRenderer(trace2Renderer);
		dataset.addSeries(trace1);
		dataset.addSeries(trace2);
	}
	
}
