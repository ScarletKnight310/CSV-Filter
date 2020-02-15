package base;

import java.util.Date;

// Holds key info for resources
public class Resource
{
    String name;
    Date replace_date;
    String home_location;

    // for holding the current resource's status
    String status_Label;
    int status_Id;



    public Resource(String name, String replace_date,
                    String home_location, String status)
    {
        // the replaces are for cleaning extra stuff on the strings
        this.name = name.replace("\"","");
        this.replace_date = new Date(replace_date);
        this.home_location = home_location;

        // takes out "Other: " as well/ made a status Id for faster comparisons
        this.status_Label = ((status.replace("\"","")).replaceFirst("Other: ",""));
        calcId();
    }

    // checks if the replacement date is older than the current date
    public boolean isOld()
    {
        return replace_date.before(new Date());
    }

    public String toString()
    {
        return name + " / " + replace_date + " / " + home_location + " / " + status_Label + " -> " + status_Id;
    }

    private void calcId()
    {
        switch(status_Label) {
            case ("Available"):
                status_Id = 1;
                break;
            case("Checked Out"):
                status_Id = 2;
                break;
            case("Non-circulating"):
                status_Id = 3;
                break;
            case("Contained"):
                status_Id = 4;
                break;
        }
    }
}
