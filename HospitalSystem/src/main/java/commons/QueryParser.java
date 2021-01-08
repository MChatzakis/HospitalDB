/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commons;

import database.DBConnection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Manos Chatzakis
 */
public class QueryParser {

    public static JSONObject parseQuery(String query) throws SQLException, ClassNotFoundException {
        JSONObject data = new JSONObject();
        JSONArray colArray = new JSONArray();

        DBConnection conn = new DBConnection();

        int totalColumns = 0;
        int rowCounter = 0;

        ResultSet res = null;
        ResultSetMetaData metRes = null;
        res = conn.executeQuery(query);
        metRes = res.getMetaData();

        totalColumns = metRes.getColumnCount();
        data.put("totalCols", totalColumns);

        for (int i = 0; i < totalColumns; i++) {
            colArray.put(metRes.getColumnLabel(i + 1));
        }
        data.put("ColumnNames", colArray);

        while (res != null && res.next()) {
            for (int i = 0; i < totalColumns; i++) {
                String colName = metRes.getColumnLabel(i + 1);
                data.put(colName + "" + rowCounter, res.getString(colName));
            }
            rowCounter++;
        }

        data.put("totalRows", rowCounter);

        conn.closeDBConnection();
        return data;
    }

    public static void executeRandomQuery(String query) throws SQLException, ClassNotFoundException {
        DBConnection conn = new DBConnection();
        conn.updateQuery(query);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(parseQuery("SELECT * FROM patients").toString());

    }
}
