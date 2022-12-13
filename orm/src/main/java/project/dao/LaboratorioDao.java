package project.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project.models.Laboratorio;

public class LaboratorioDao extends ConexaoDB{
  private static final String SELECT_ALL_SQL = "SELECT * FROM laboratorio;";
  private static final String INSERT_SQL = "INSERT INTO laboratorio (descricao, cnes, cnpj, crbm, nome_fantasia) VALUES (?, ?, ?, ?, ?);";
	private static final String UPDATE_SQL = "UPDATE laboratorio SET descricao = ?, cnes = ?, cnpj = ?, crbm = ?, nome_fantasia = ? WHERE id = ?;";
	private static final String DELETE_SQL = "DELETE FROM laboratorio WHERE id = ?;";
	private static final String COUNT = "SELECT count(1) FROM laboratorio;";

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

	public Laboratorio insert(Laboratorio entidade) {
		try (PreparedStatement preparedStatement = prepararSQL(INSERT_SQL,
				java.sql.Statement.RETURN_GENERATED_KEYS)) {

			preparedStatement.setString(1, entidade.getDescricao());
			preparedStatement.setString(2, entidade.getCNES());
      preparedStatement.setString(3, entidade.getCNPJ());
      preparedStatement.setString(4, entidade.getCRBM());
      preparedStatement.setString(5, entidade.getNomeFantasia());

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

	public Laboratorio findByField(String fieldName, String value) {
    String sql = "SELECT * FROM laboratorio WHERE ? = ?";

		Laboratorio entidade = null;
		try (PreparedStatement preparedStatement = prepararSQL(sql)) {
			preparedStatement.setString(1, fieldName);
      preparedStatement.setString(2, value);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				entidade = new Laboratorio(
          rs.getLong("id"),
          rs.getString("descricao"),
          rs.getString("cnes"),
          rs.getString("cnpj"),
          rs.getString("crbm"),
          rs.getString("nome_fantasia")
        );
			}
		} catch (SQLException e) {
			printSQLException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		return entidade;
	}

	public List<Laboratorio> selectAll() {
		List<Laboratorio> laboratorios = new ArrayList<>();
		try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_SQL)) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				long id = rs.getLong("id");
        String descricao = rs.getString("descricao");
				String cnes = rs.getString("cnes");
				String cnpj = rs.getString("cnpj");
        String crbm = rs.getString("crbm");
				String nome_fantasia = rs.getString("nome_fantasia");
				laboratorios.add(new Laboratorio(id, descricao, cnes, cnpj, crbm, nome_fantasia));
			}
		} catch (SQLException e) {
			printSQLException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		return laboratorios;
	}

	public boolean delete(int id) throws SQLException {
		try (PreparedStatement statement = prepararSQL(DELETE_SQL)) {
			statement.setInt(1, id);
			return statement.executeUpdate() > 0;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public void update(Laboratorio entidade) throws SQLException {
		try (PreparedStatement statement = prepararSQL(UPDATE_SQL)) {
      statement.setString(1, entidade.getDescricao());
      statement.setString(2, entidade.getCNES());
      statement.setString(3, entidade.getCNPJ());
      statement.setString(4, entidade.getCRBM());
      statement.setString(5, entidade.getNomeFantasia());
			statement.setLong(6, entidade.getId());

		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
