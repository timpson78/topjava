package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Transactional(readOnly = true)
public class JdbcUserRepositoryImpl implements UserRepository {

    private static final BeanPropertyRowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);
    private static final RoleRowMapper ROW_MAPPER_ROLE = new RoleRowMapper();
    private static final UserWithRoleExtractor USER_ROLE_EXTRACTOR = new UserWithRoleExtractor();

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertUser;


    private static class RoleRowMapper extends BeanPropertyRowMapper<Role>
    {
        @Nullable
        @Override
        public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
                return  Role.valueOf(rs.getString("role"));
        }
    }

    private static final class UserWithRoleExtractor implements ResultSetExtractor<List<User>> {

        @Nullable
        @Override
        public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {

            Map<Integer,User> userMap =  new ConcurrentHashMap<>();
            while (rs.next()) {

                int id= rs.getInt("id");
                if (userMap.containsKey(id))
                {
                    User user=userMap.get(id);
                    Set<Role> roles=user.getRoles();
                    roles.add(Role.valueOf(rs.getString("role")));
                    user.setRoles(roles);
                    userMap.put(id,user);
                }
                 else {
                    userMap.put(id,new User(id,
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getInt("calories_per_day"),
                            rs.getBoolean("enabled"),
                            rs.getDate("registered"),
                            EnumSet.of(Role.valueOf(rs.getString("role"))
                            )));
                }
            }

            return new ArrayList<>(userMap.values());
        }
    }

    @Autowired
    public JdbcUserRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertUser = new SimpleJdbcInsert(dataSource)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional
    public User save(User user) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
        if (user.isNew()) {
            Number newKey = insertUser.executeAndReturnKey(parameterSource);
            user.setId(newKey.intValue());
           /* Set<Role> roles=user.getRoles();
            for (Role role:roles) {
                MapSqlParameterSource parameterRoleSource = new MapSqlParameterSource()
                        .addValue("id", newKey)
                        .addValue("role", role.name());
                namedParameterJdbcTemplate.update(
                        "INSERT INTO user_roles (user_id,role) VALUES (:id,:role) ", parameterRoleSource);
            }*/
            ArrayList<Role> roles=new ArrayList(user.getRoles());
            jdbcTemplate.batchUpdate(
                    "INSERT INTO user_roles (user_id,role) VALUES (?,?)", new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps, int i) throws SQLException {
                                ps.setInt(1,(Integer)newKey);
                                ps.setString(2,roles.get(i).name());
                        }

                        @Override
                        public int getBatchSize() {
                            return roles.size();
                        }
                    }
            );

        } else if (namedParameterJdbcTemplate.update(
                "UPDATE users SET name=:name, email=:email, password=:password, " +
                        "registered=:registered, enabled=:enabled, calories_per_day=:caloriesPerDay WHERE id=:id", parameterSource) == 0) {
            return null;
        }
        return user;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id=?", id) != 0;
    }

    @Override
    public User get(int id) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE id=?", ROW_MAPPER, id);
        User user=DataAccessUtils.singleResult(users);
        if (user!=null) {
            user.setRoles(
                    jdbcTemplate.query(
                            "SELECT * FROM user_roles WHERE user_id=?", ROW_MAPPER_ROLE, user.getId()));
        }
        return user;
    }

    @Override
    public User getByEmail(String email) {
//        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE email=?", ROW_MAPPER, email);
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE email=?", ROW_MAPPER, email);
        User user=DataAccessUtils.singleResult(users);
        user.setRoles(
                jdbcTemplate.query(
                        "SELECT * FROM user_roles WHERE user_id=?", ROW_MAPPER_ROLE, user.getId()));
        return user;
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query("SELECT * FROM users u LEFT JOIN user_roles r ON  u.id=r.user_id ORDER BY name, email",USER_ROLE_EXTRACTOR);
    }
}
