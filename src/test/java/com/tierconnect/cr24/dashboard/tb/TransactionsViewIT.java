package com.tierconnect.cr24.dashboard.tb;

import com.tierconnect.cr24.dashboard.tb.pageobjects.TBLoginView;
import com.tierconnect.cr24.dashboard.tb.pageobjects.TBMainView;
import com.tierconnect.cr24.dashboard.tb.pageobjects.TBReportsView;
import com.tierconnect.cr24.dashboard.tb.pageobjects.TBTransactionsView;
import com.vaadin.testbench.TestBenchTestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TransactionsViewIT extends TestBenchTestCase {

    private TBLoginView loginView;
    private TBMainView mainView;

    @Before
    public void setUp() {
        loginView = TBUtils.openInitialView();
        mainView = loginView.login();
    }

    @Test
    public void testFilter() {
        TBTransactionsView transactionsView = mainView.openTransactionsView();
        transactionsView.setFilter("Madrid");
        Assert.assertFalse(transactionsView.listingContainsCity("London"));
        transactionsView.setFilter("");
    }

    @Test
    public void testCreateReport() {
        TBTransactionsView transactionsView = mainView.openTransactionsView();
        List<String> titles = transactionsView.selectFirstTransactions(5);
        TBReportsView reportsView = transactionsView
                .createReportFromSelection();
        Assert.assertTrue(reportsView.isDisplayed());

        for (String title : titles) {
            Assert.assertTrue(reportsView.hasReportForTitle(title));
        }
    }

    @After
    public void tearDown() {
        loginView.getDriver().quit();
    }
}
