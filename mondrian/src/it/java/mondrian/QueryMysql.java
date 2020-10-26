package mondrian;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import org.olap4j.CellSet;
import org.olap4j.OlapConnection;
import org.olap4j.layout.RectangularCellSetFormatter;

public class QueryMysql {

    public static void main(String[] args) throws Exception {

        final String query = ""
//                + " WITH\r\n"
//                + "  MEMBER [Measures].[WeekDay] AS \r\n"
//                + "   ([Time.Weekly].[Day].CurrentMember.Properties(\"Day\"))"
                + " SELECT {[Measures].[Unit Sales],[Measures].[Sales Count]} ON COLUMNS, \r\n"
                + " [Time.Weekly].[1997].[10].Children ON ROWS "
                + "FROM [Sales]";

        Class.forName("mondrian.olap4j.MondrianOlap4jDriver");
        String schema = "C:\\ws\\bi\\mondrian\\demo\\FoodMart.xml;";
        final Connection connection = DriverManager
                .getConnection("jdbc:mondrian:Jdbc=" +
                        "jdbc:mysql://localhost/foodmart" +
                        "?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC" +
                        "&user=foodmart&password=foodmart;" +
                        "Catalog=" + schema);

        OlapConnection olapConnection = connection
                .unwrap(OlapConnection.class);

        final CellSet cellSet = olapConnection.createStatement()
                .executeOlapQuery(query);

        RectangularCellSetFormatter formatter = new RectangularCellSetFormatter(
                false);

        PrintWriter writer = new PrintWriter(System.out);
        formatter.format(cellSet, writer);
        writer.flush();
    }
}