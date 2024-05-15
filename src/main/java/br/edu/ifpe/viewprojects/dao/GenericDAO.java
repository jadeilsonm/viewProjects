package br.edu.ifpe.viewprojects.dao;

import br.edu.ifpe.viewprojects.shareds.FabricConnect;
import br.edu.ifpe.viewprojects.shareds.IConnection;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class GenericDAO<T> implements IGenericDAO<T> {

    private String table;
    private IConnection connection;

    public GenericDAO() {
        this.connection = FabricConnect.getConnection();
    }

    @Override
    public void insert(T object) {
        this.table = getTableName(object);
        StringBuilder sql = new StringBuilder("");
        sql.append("INSERT INTO " + table + " (");
        Field[] fields = object.getClass().getDeclaredFields();
        fields = Arrays.stream(fields).filter(field -> !field.getName().equals("id")).toArray(Field[]::new);
        int countIndex = 1;
        for (Field field : fields) {
            if (countIndex != fields.length) {
                sql.append(field.getName() + ", ");
            }
            else {
                sql.append(field.getName());
            }
            countIndex ++;
        }
        sql.append(") VALUES (");
        int countIndexValue = 1;
        for (Field field : fields) {
            if (countIndexValue != fields.length) {
                sql.append("?, ");
            }
            else {
                sql.append("?");
            }
            countIndexValue ++;
        }
        sql.append(")");
        try {
            if (this.connection == null) {
                this.connection = FabricConnect.getConnection();
            }
            PreparedStatement stm = connection.toConnect().prepareStatement(sql.toString());
            countIndexValue = 0;
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    var type = field.getType();
                    switch (type.getTypeName())
                    {
                        case "java.lang.String":
                            stm.setString(countIndexValue + 1, (String) field.get(object));
                            break;
                        case "java.lang.Integer":
                            stm.setInt(countIndexValue + 1, (int) field.get(object));
                            break;
                        case "java.lang.Double":
                            stm.setDouble(countIndexValue + 1, field.getDouble(object));
                            break;
                        case "java.lang.Float":
                            stm.setFloat(countIndexValue + 1, field.getFloat(object));
                            break;
                        case "java.lang.Long":
                            stm.setLong(countIndexValue + 1, field.getLong(object));
                            break;
                        case "java.lang.Boolean":
                            stm.setByte(countIndexValue + 1, field.getBoolean(object) ? (byte) 1 : (byte) 0);
                            break;
                        case "java.time.LocalDate":
                            stm.setDate(countIndexValue + 1, java.sql.Date.valueOf((java.time.LocalDate) field.get(object)));
                            break;
                        default:
                            break;
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                countIndexValue ++;
            }
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(T object, Integer id) {

    }

    @Override
    public void delete(Integer id) throws RuntimeException {

        StringBuilder sql = new StringBuilder("");
        sql.append("DELETE FROM " + table + " WHERE id = ?");
        try {
            PreparedStatement stm = connection.toConnect().prepareStatement(sql.toString());
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T findById(Integer id) throws RuntimeException {
        String sql = "SELECT * FROM " + table + " WHERE id = " + id;
        T result = null;
        try {
            Statement stm = connection.toConnect().createStatement();
            ResultSet resultSet = stm.executeQuery(sql);
            result = (T) resultSet.getObject(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public List<T> findAll() throws RuntimeException {
        String sql = "SELECT * FROM " + table;
        List<T> result = null;
        try {
            Statement stm = connection.toConnect().createStatement();
            ResultSet resultSet = stm.executeQuery(sql);
            Integer columnCount = 1;
            while (resultSet.next()) {
                var resultObject = resultSet.getObject(columnCount);
                result.add((T) resultObject);
                columnCount ++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    private String getTableName(T o) {
        Class<?> clazz = o.getClass();
        return clazz.getSimpleName().toLowerCase() + 's';
    }
}
