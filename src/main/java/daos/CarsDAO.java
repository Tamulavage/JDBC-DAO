package daos;

import DBUtils.DBType;
import DBUtils.DBUtil;
import javafx.beans.binding.When;
import models.Cars;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarsDAO extends DAO<Cars> {
    private static final String INSERT = "Insert into labwork.car" +
            " ( id, make, model, model_year, vin, color )" +
            " values(?, ? , ?, ?, ?, ?)";
    private static final String GET_ONE = "SELECT * FROM labwork.car WHERE ID = ?";
    private static final String GET_ALL = "SELECT * FROM labwork.car";
    private static final String UPDATE = "UPDATE labwork.car" +
            "  SET id=?, make = ?, model = ?, model_year = ?, vin =?, color=? " +
            "WHERE id = ?";
    private static final String DELETE = "DELETE FROM labwork.car WHERE id = ?";


    public CarsDAO(Connection conn) {
        super(conn);
    }

    public Cars findById(int id) {
        Cars cars = null;
        try (PreparedStatement stmt = DBUtil.getConnection(DBType.MYSQLDB).prepareStatement(GET_ONE);) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                cars = new Cars();
                cars.setId(rs.getInt("id"));
                cars.setColor(rs.getString("color"));
                cars.setMake(rs.getString("make"));
                cars.setModel(rs.getString("Model"));
                cars.setModel_yr(rs.getString("Model_year"));
                cars.setVin(rs.getString("vin"));
            }

        } catch (SQLException e) {
            DBUtil.showErrorMessage(e);
        }
        return cars;
    }

    // TODO
    public List<Cars> findAll() {

        List<Cars> retVal = null;
        Cars singleCar = null;
        try (PreparedStatement stmt = DBUtil.getConnection(DBType.MYSQLDB).prepareStatement(GET_ALL);) {

            ResultSet rs = stmt.executeQuery();
            retVal = new ArrayList<>();
            while (rs.next()){
                singleCar = new Cars();
                singleCar.setId(rs.getInt("id"));
                singleCar.setColor(rs.getString("color"));
                singleCar.setMake(rs.getString("make"));
                singleCar.setModel(rs.getString("Model"));
                singleCar.setModel_yr(rs.getString("Model_year"));
                singleCar.setVin(rs.getString("vin"));
                retVal.add(singleCar);
            }

        } catch (SQLException e) {
            DBUtil.showErrorMessage(e);
        }


        return retVal;
    }

    public Cars create(Cars dto) {
        try (PreparedStatement stmt = this.connection.prepareStatement(INSERT);) {
            stmt.setInt(1, dto.getId());
            stmt.setString(2, dto.getMake());
            stmt.setString(3, dto.getModel());
            stmt.setString(4, dto.getModel_yr());
            stmt.setString(5, dto.getVin());
            stmt.setString(6, dto.getColor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            DBUtil.showErrorMessage(e);
        }
        return null;
    }

    public Cars update(Cars dto) {
        Cars car = null;
        try (PreparedStatement stmt = this.connection.prepareStatement(UPDATE);) {
            stmt.setInt(1, dto.getId());
            stmt.setString(2, dto.getMake());
            stmt.setString(3, dto.getModel());
            stmt.setString(4, dto.getModel_yr());
            stmt.setString(5, dto.getVin());
            stmt.setString(6, dto.getColor());
            stmt.setInt(7, dto.getId());
            stmt.executeUpdate();
            car = this.findById(dto.getId());
        } catch (SQLException e) {
            DBUtil.showErrorMessage(e);
        }
        return car;
    }

    public void delete(int id) {
        try (PreparedStatement stmt = this.connection.prepareStatement(DELETE);) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            DBUtil.showErrorMessage(e);
        }

    }
}
