package Swcb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Zil on 2016/10/18.
 */

@XmlRootElement(name = "DataTable")
public class DataTable {

    private Row[] rows;

    public DataTable() {
    }

    @XmlElement(name = "Row")
    public Row[] getRows() {
        return rows;
    }

    public void setRows(Row[] row) {
        this.rows = row;
    }
}
