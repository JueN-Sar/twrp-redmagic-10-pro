package com.zte.timeutil.calendar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public class CalendarWrapper implements Serializable {
    private static final long serialVersionUID = -4287759184630652424L;
    private List<DayWrapper> dayList;
    private Map<String, DayWrapper> dayMap;
    private List<YearWrapper> years;

    public CalendarWrapper() {
        this.dayMap = new ConcurrentHashMap();
        this.dayList = new ArrayList();
    }

    public CalendarWrapper(List<YearWrapper> list, Map<String, DayWrapper> map, List<DayWrapper> list2) {
        this.dayMap = new ConcurrentHashMap();
        new ArrayList();
        this.years = list;
        this.dayMap = map;
        this.dayList = list2;
    }

    public CalendarWrapper(List<YearWrapper> list) {
        this.dayMap = new ConcurrentHashMap();
        this.dayList = new ArrayList();
        this.years = list;
    }
}
