package regi.italishpizza;

/**
 * Created by user on 14/07/08.
 */
public class Booking {

    private String headline;
    private String reporterName;
    private String date;

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "[ headline=" + headline + ", reporter Name=" +
                reporterName + " , date=" + date + "]";
    }
}
