package be.intecbrussel.service;

import java.sql.Date;
import java.time.LocalDate;

public class OrderNumberHelper {
    // ORD-202203-0094
    // ORD is fixed; date is yyyyMM; seqnr starts with 0001, increments
    // during month but resets at 0001 when new month

    private Date lastOrderDate;
    private String lastOrderNrSeq;
    private Date now;
    private int monthNow;

    public OrderNumberHelper() {
        this.now = Date.valueOf(LocalDate.now());
        this.monthNow = LocalDate.now().getMonthValue();
    }

    public void setLastOrderDate(Date lastOrderDate) {
        this.lastOrderDate = lastOrderDate;
    }

    public void setLastOrderNrSeq(String lastOrderNrSeq) {
        this.lastOrderNrSeq = lastOrderNrSeq;
    }

    public String generateOrderNr() {
        return "ORD-" + orderNrDatePrefix() + "-" + orderNrSeq();
    }

    private int extractMonth(Date date) {
        StringBuilder sb = new StringBuilder();
        LocalDate localDate = date.toLocalDate();
        return localDate.getMonthValue();
    }

    private boolean switchMonths() {
        int currentMonth = extractMonth(now);
        int monthLastOrder = extractMonth(lastOrderDate);
        return currentMonth != monthLastOrder;
    }

    protected String orderNrDatePrefix() {
        StringBuilder sb = new StringBuilder();
        sb.append(now.toLocalDate().getYear());

        if (monthNow < 10) {
            sb.append("0" + monthNow);
        } else {
            sb.append(monthNow);
        }
        return sb.toString();
    }

    private String orderNrSeq() {
        if (switchMonths()) {
            return "0001";
        } else {
            int nr = Integer.parseInt(lastOrderNrSeq);
            nr = nr + 1;
            return String.format("%04d", nr);
        }
    }
}
