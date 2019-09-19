package case1.nl.controller.compositon;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import case1.nl.controller.SessionController;
//import org.primefaces.model.charts.ChartData;
//import org.primefaces.model.charts.axes.cartesian.CartesianScales;
//import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
//import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
//import org.primefaces.model.charts.bar.BarChartDataSet;
//import org.primefaces.model.charts.bar.BarChartModel;
//import org.primefaces.model.charts.bar.BarChartOptions;
//import org.primefaces.model.charts.optionconfig.title.Title;

/**
 *
 * @author PeterH
 */
@ViewScoped
@ManagedBean
public class ComponentsController implements Serializable {

    @ManagedProperty(value = "#{sessionController}")
    private SessionController session;

//    private BarChartModel barModel;
    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
//    public BarChartModel getBarModel() {
//        return barModel;
//
//    }
//
//    public void setBarModel(BarChartModel barModel) {
//        this.barModel = barModel;
//    }
    //</editor-fold>
    public ComponentsController() {
    }

//    @PostConstruct
//    public void init() {
//        createBarModel();
//    }
//    public void createBarModel() {
//        barModel = new BarChartModel();
//        ChartData data = new ChartData();
//
//        BarChartDataSet barDataSet = new BarChartDataSet();
//        barDataSet.setLabel("My First Dataset");
//
//        List<Number> values = new ArrayList<>();
//        values.add(65);
//        values.add(59);
//        values.add(80);
//        values.add(81);
//        values.add(56);
//        values.add(55);
//        values.add(40);
//        barDataSet.setData(values);
//
//        List<String> bgColor = new ArrayList<>();
//        bgColor.add("rgba(255, 99, 132, 0.2)");
//        bgColor.add("rgba(255, 159, 64, 0.2)");
//        bgColor.add("rgba(255, 205, 86, 0.2)");
//        bgColor.add("rgba(75, 192, 192, 0.2)");
//        bgColor.add("rgba(54, 162, 235, 0.2)");
//        bgColor.add("rgba(153, 102, 255, 0.2)");
//        bgColor.add("rgba(201, 203, 207, 0.2)");
//        barDataSet.setBackgroundColor(bgColor);
//
//        List<String> borderColor = new ArrayList<>();
//        borderColor.add("rgb(255, 99, 132)");
//        borderColor.add("rgb(255, 159, 64)");
//        borderColor.add("rgb(255, 205, 86)");
//        borderColor.add("rgb(75, 192, 192)");
//        borderColor.add("rgb(54, 162, 235)");
//        borderColor.add("rgb(153, 102, 255)");
//        borderColor.add("rgb(201, 203, 207)");
//        barDataSet.setBorderColor(borderColor);
//        barDataSet.setBorderWidth(1);
//
//        data.addChartDataSet(barDataSet);
//
//        List<String> labels = new ArrayList<>();
//        labels.add("January");
//        labels.add("February");
//        labels.add("March");
//        labels.add("April");
//        labels.add("May");
//        labels.add("June");
//        labels.add("July");
//        data.setLabels(labels);
//        barModel.setData(data);
//
//        //Options
//        BarChartOptions options = new BarChartOptions();
//        CartesianScales cScales = new CartesianScales();
//        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
//        CartesianLinearTicks ticks = new CartesianLinearTicks();
//        ticks.setBeginAtZero(true);
//        linearAxes.setTicks(ticks);
//        cScales.addYAxesData(linearAxes);
//        options.setScales(cScales);
//
//        Title title = new Title();
//        title.setDisplay(true);
//        title.setText("Bar Chart");
//        options.setTitle(title);
//
//        barModel.setOptions(options);
//    }
}
