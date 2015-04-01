package com.tierconnect.cr24.dashboard.component;

import com.tierconnect.cr24.dashboard.DashboardUI;
import com.tierconnect.cr24.dashboard.domain.RecentActivity;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;

import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("serial")
public final class RecentActivitiesTable extends Table {

    @Override
    protected String formatPropertyValue(final Object rowId,
            final Object colId, final Property<?> property) {
        SimpleDateFormat formatter
                = new SimpleDateFormat("yyyy.mm.dd e 'at' hh:mm:ss");

        String result = super.formatPropertyValue(rowId, colId, property);
        if (colId.equals("timeStamp")) {
            if (property != null && property.getValue() != null) {
                Date r = (Date) property.getValue();
                String ret = formatter.format(r);
                result = ret;
            } else {
                result = "";
            }
        }
        return result;
    }

    public RecentActivitiesTable() {
        setCaption("Recent Activities");

        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        setSortEnabled(true);
        setColumnAlignment("DateFormat", Align.CENTER);
        setRowHeaderMode(RowHeaderMode.INDEX);
        setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
        setSizeFull();

        List<RecentActivity> recentActivity = new ArrayList<RecentActivity>(
                DashboardUI.getDataProvider().getAllRecentActivity());
        Collections.sort(recentActivity, new Comparator<RecentActivity>() {
            @Override
            public int compare(final RecentActivity o1, final RecentActivity o2) {
                return o2.getTimestamp().compareTo(o1.getTimestamp());
            }
        });

        setContainerDataSource(new BeanItemContainer<RecentActivity>(
                RecentActivity.class, recentActivity.subList(0, 10)));

        setVisibleColumns("title", "revenue");
        setColumnHeaders("Title", "Revenue");
        setColumnExpandRatio("title", 2);
        setColumnExpandRatio("revenue", 1);

        setSortContainerPropertyId("revenue");
        setSortAscending(false);
    }

}
