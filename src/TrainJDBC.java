
import java.sql.*; //from jdbc
import java.util.Scanner;

import static java.sql.DriverManager.*;

public class TrainJDBC {



        public static void main(String[] args) {
            Connection conn = null;
            try {
                // Path of database
                String url = "/Users/kristinwichstrom/Documents/SofwareDevelopment/database/TrainDB.db";
                // This path is provided to get connection
                conn = getConnection(url);
/* We will look at the rest in class
                 // Contains a statement for connection conn
                Statement stmt = conn.createStatement();

                // A string containing the SQL syntax
                String sql;
                sql = "SELECT * FROM Departure";

                // Execute the statement and return the selected to ResultSet rs
                ResultSet rs = stmt.executeQuery(sql);

                //Provide the PresentDepartures function with the result set rs to print to screen
                 PresentDepartures(rs);

                 //We try something new:
                // use scanner to get user input  departure station
                System.out.println("Which station do you wish to find departures for?");
                Scanner scanner = new Scanner(System.in);
                String departureStation = scanner.nextLine();

                //Make new sql string with the read station
                sql = "SELECT * FROM Departure WHERE stationname='"+departureStation+"'";

                //Execute statement and return to rs
                rs =stmt.executeQuery(sql);
                PresentDepartures(rs);
                // The same with a prepared statement
                String psql= "SELECT * FROM Departure WHERE stationname= ?";
                PreparedStatement pstmt = null;
                pstmt = conn.prepareStatement(psql);
                pstmt.setString(1, "København H");
                rs = pstmt.executeQuery();
                PresentDepartures(rs);

                // Do the join from the yesterdays exercise
                String ptripsql= "SELECT D1.stationname as startstation,D1.time as starttime, " +
                        "D2.stationname as endstation, D2.time as endtime" +
                        "  FROM Departure as D1 " +
                        "join Departure as D2 on D1.trainid =D2.trainid " +
                        "WHERE D1.stationname= ? and D2.stationname= ? and D1.time<D2.time and D1.time >= ?";
                PreparedStatement pTripStmt = conn.prepareStatement(ptripsql);
                System.out.println("Which station do you wish to find departures for?");
                departureStation = scanner.nextLine();
                System.out.println("Which station do you want to go to?");
                String endStation = scanner.nextLine();
                System.out.println("Which time do you depart (as float)?");
                float  ftime = scanner.nextFloat();
                pTripStmt.setString(1,departureStation);
                pTripStmt.setString(2,endStation);
                pTripStmt.setFloat(3, ftime);
                rs = pTripStmt.executeQuery();
                PresentTrips(rs);

                // Insert and Update
                String sqlInsert = "INSERT INTO departure (stationname, trainid, time) " +
                        " VALUES ('København H',2,5.00),('Høje Tåstrup',2,5.15)," +
                        "('Roskilde',2,5.22),('Ringsted',2,5.38),('Odense',2,6.36)";
                PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsert);
                int rowsaffected=pstmtInsert.executeUpdate();
                System.out.println("Number of rows affected "+ rowsaffected);

*/

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    // Print function from ResultSet

    static public void PresentDepartures(ResultSet res)
            throws SQLException {
        if (res == null)
            System.out.println("No records for customer");
        while (res != null & res.next()) {
            String name = res.getString("stationname");
            int train = res.getInt("trainid");
            float time = res.getFloat("time");
            System.out.println(train + " " + name +" " + time);
        }
    }

    static public void PresentTrips(ResultSet res)
            throws SQLException {
        if (res == null)
            System.out.println("No records for customer");
        while (res != null & res.next()) {
            String depname = res.getString("startstation");
            float deptime = res.getFloat("starttime");
            String endname = res.getString("endstation");
            float arrtime = res.getFloat("endtime");
            System.out.println(depname + " " +deptime+ " " + endname +" " + arrtime);
        }
    }

}


