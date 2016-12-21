package com.demo.common.extreme.view;

import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.view.AbstractHtmlView;
import org.extremecomponents.table.view.CompactToolbar;
import org.extremecomponents.util.HtmlBuilder;

/**
 * @author A Kim
 * @time 2010-11-9
 * @file CompactView.java
 */

public class CompactView extends AbstractHtmlView {
    protected void beforeBodyInternal(TableModel model) {
        getTableBuilder().tableStart();

        getTableBuilder().theadStart();
        
        getTableBuilder().titleRowSpanColumns();

        //toolbar(getHtmlBuilder(), getTableModel());
        
        getTableBuilder().filterRow();

        getTableBuilder().headerRow();

        getTableBuilder().theadEnd();

        getTableBuilder().tbodyStart();
    }

    protected void afterBodyInternal(TableModel model) {
        getCalcBuilder().defaultCalcLayout();

        getTableBuilder().tbodyEnd();

        getTableBuilder().tableEnd();
        
        toolbar(getHtmlBuilder(), getTableModel());
    }
    
    protected void toolbar(HtmlBuilder html, TableModel model) {
        new CompactToolbar(html, model).layout();
    }
}
