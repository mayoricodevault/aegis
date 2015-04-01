package com.tierconnect.cr24.dashboard.component;

import com.tierconnect.cr24.dashboard.DashboardUI;
import com.tierconnect.cr24.dashboard.data.cr24.cr24DataGenerator;
import com.tierconnect.cr24.dashboard.domain.Movie;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class TopJobsTotalsChart extends Chart {

    public TopJobsTotalsChart() {
        setCaption("New Jobs");
        getConfiguration().setTitle("");
        getConfiguration().getChart().setType(ChartType.BAR);
        getConfiguration().getChart().setAnimation(true);
        getConfiguration().getxAxis().getLabels().setEnabled(false);
        getConfiguration().getxAxis().setTickWidth(0);
        getConfiguration().getyAxis().setTitle("");
        setSizeFull();

        List<Movie> movies = new ArrayList<Movie>(DashboardUI.getDataProvider()
                .getMovies());

        List<Series> series = new ArrayList<Series>();
        for (int i = 0; i < 6; i++) {
            Movie movie = movies.get(i);
            PlotOptionsBar opts = new PlotOptionsBar();
            opts.setColor(cr24DataGenerator.chartColors[5 - i]);
            opts.setBorderWidth(0);
            opts.setShadow(false);
            opts.setPointPadding(0.4);
            opts.setAnimation(false);
            ListSeries item = new ListSeries(movie.getTitle(), movie.getScore());
            item.setPlotOptions(opts);
            series.add(item);

        }
        getConfiguration().setSeries(series);

        Credits c = new Credits("");
        getConfiguration().setCredits(c);

        PlotOptionsBar opts = new PlotOptionsBar();
        opts.setGroupPadding(0);
        getConfiguration().setPlotOptions(opts);

    }
}
