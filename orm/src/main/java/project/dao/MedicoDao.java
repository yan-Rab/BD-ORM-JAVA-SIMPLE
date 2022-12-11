package project.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project.models.Medico;

public class MedicoDao extends ConexaoDB{
	private static final String SELECT_ALL_SQL = "SELECT * FROM medico;";
  private static final String INSERT_SQL = "INSERT INTO medico (crm, nome) VALUES (?, ?);";
	private static final String UPDATE_SQL = "UPDATE medico SET crm = ?, nome = ? WHERE id = ?;";
	private static final String DELETE_SQL = "DELETE FROM medico WHERE id = ?;";
	private static final String COUNT = "SELECT count(1) FROM medico;";

	public Integer count() {
		Integer count = 0;
		try (PreparedStatement preparedStatement = prepararSQL(COUNT)) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			printSQLException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		return count;
	}

	public Medico insert(Medico entidade) {
		try (PreparedStatement preparedStatement = prepararSQL(INSERT_SQL,
				java.sql.Statement.RETURN_GENERATED_KEYS)) {

			preparedStatement.setString(1, entidade.getCRM());
			preparedStatement.setString(2, entidade.getNome());

			preparedStatement.executeUpdate();

			ResultSet result = preparedStatement.getGeneratedKeys();
			if (result.next()) {
				entidade.setId(result.getLong(1));
			}
		} catch (SQLException e) {
			printSQLException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		return entidade;
	}

	public Medico findByField(String fieldName, String value) {
    String sql = "SELECT * FROM medico WHERE ? = ?";

		Medico entidade = null;
		try (PreparedStatement preparedStatement = prepararSQL(sql)) {
			preparedStatement.setString(1, fieldName);
      preparedStatement.setString(2, value);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				entidade = new Medico(rs.getLong("id"), rs.getString("crm"), rs.getString("nome"));
			}
		} catch (SQLException e) {
			printSQLException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		return entidade;
	}

	public List<Medico> selectAll() {
		List<Medico> medicos = new ArrayList<>();
		try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_SQL)) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				long id = rs.getLong("id");
				String crm = rs.getString("crm");
				String nome = rs.getString("nome");
				medicos.add(new Medico(id, crm, nome));
			}
		} catch (SQLException e) {
			printSQLException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		return medicos;
	}

	public boolean delete(int id) throws SQLException {
		try (PreparedStatement statement = prepararSQL(DELETE_SQL)) {
			statement.setInt(1, id);
			return statement.executeUpdate() > 0;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public void update(Medico entidade) throws SQLException {
		try (PreparedStatement statement = prepararSQL(UPDATE_SQL)) {
			statement.setString(1, entidade.getCRM());
			statement.setString(2, entidade.getNome());
			statement.setLong(3, entidade.getId());

		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
