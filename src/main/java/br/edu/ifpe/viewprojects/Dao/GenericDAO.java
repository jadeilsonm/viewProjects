package br.edu.ifpe.viewprojects.Dao;

import br.edu.ifpe.viewprojects.Shareds.FabricConnect;
import br.edu.ifpe.viewprojects.Shareds.IConnection;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class GenericDAO<T> implements IGenericDAO<T> {

    private String table;
    private IConnection connection;

    public GenericDAO() {
        this.table = getTableName();
        this.connection = FabricConnect.getConnection();
    }

    @Override
    public void insert(T object) {
        StringBuilder sql = new StringBuilder("");
        sql.append("INSERT INTO " + table + " (");
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            sql.append(field.getName() + ", ");
        }
        sql.append(") VALUES (");
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                sql.append(field.get(object) + ", ");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        sql.append(")");
        System.out.println(sql.toString());
        try {
            Statement stm = connection.toConnect().createStatement();
            stm.executeQuery(sql.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(T object) {

    }

    @Override
    public void delete(T entity) throws RuntimeException {
        Integer id = null;
        String name = null;
        try {
            Field idField = entity.getClass().getDeclaredField("id");
            Field nameField = entity.getClass().getDeclaredField("name");
            idField.setAccessible(true);
            nameField.setAccessible(true);
            id = (Integer) idField.get(entity);
            name = (String) nameField.get(entity);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        StringBuilder sql = new StringBuilder("");
        sql.append("DELETE FROM " + table + " WHERE ");
        if (id != null) {
            sql.append("id = " + id);
        } else if (name != null) {
            sql.append("name = " + name);
        } else {
            throw new RuntimeException("The entity does not have an id or name field.");
        }
        try {
            Statement stm = connection.toConnect().createStatement();
            stm.executeQuery(sql.toString());
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

    private String getTableName() {
        Class<?> clazz = ((Class<?>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
        return clazz.getSimpleName().toLowerCase() + 's';
    }
}
