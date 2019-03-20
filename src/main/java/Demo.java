import DBUtils.DBType;
import DBUtils.DBUtil;
import daos.CarsDAO;
import models.Cars;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        try (Connection conn = DBUtil.getConnection(DBType.MYSQLDB);) {
            CarsDAO carsDAO = new CarsDAO(conn);

            // Create
            Cars newCar = new Cars();
            newCar.setVin("234fdss");
            newCar.setModel_yr("1904");
            newCar.setModel("Ford");
            newCar.setMake("POS");
            newCar.setColor("Rusty");
            newCar.setId(55);

            carsDAO.create(newCar);
            System.out.println("New car: " + newCar);

            // find id
            Cars car = carsDAO.findById(2);
            System.out.println(car);

            // update
            newCar.setModel_yr("1905");
            carsDAO.update(car);
            System.out.println("New year:" + newCar);

            // delete
            carsDAO.delete(55);

            // find All
            List<Cars> myCarTable = carsDAO.findAll();
            for(Cars myCar: myCarTable){
                System.out.println("Dump of table: "+ myCar);
            }


        } catch (SQLException e) {

            DBUtil.showErrorMessage(e);

        }
    }
}
