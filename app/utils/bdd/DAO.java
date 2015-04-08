package utils.bdd;

import java.sql.SQLException;
import java.util.List;

public interface DAO<Model> {

    /**
     * Creates a new model and returns him with his new generated id.
     * @param object an model
     * @return a synchronized model with db
     * @throws SQLException
     */
    Model create(Model parObject) throws SQLException;

    /**
     * Remove the specified model from the DB
     * @param object an model
     * @throws SQLException
     */
    void remove(Model parObject)  throws SQLException;

    /**
     * Update the specified model. Returns the synchronized model with db
     * @param object an model
     * @return a synchronized model with db
     * @throws SQLException
     */
    Model update(Model parObject) throws SQLException;

    /**
     * Retrieves an model based on his id
     * @param id an model id
     * @return a model
     * @throws SQLException
     */
    Model find(long parId) throws SQLException;

//    /**
//     * Retrieves an model based on his email
//     * @param name an email
//     * @return a model
//     * @throws SQLException
//     */
//    Model find(String name) throws SQLException;

    /**
     * Retrieves all models from the database
     * @return list of all models
     * @throws SQLException
     */
    List<Model> findAll() throws SQLException;

}
