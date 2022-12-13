package project.services;

import java.sql.SQLException;
import java.util.List;

import project.dao.LaboratorioDao;
import project.models.Laboratorio;


public class LaboratorioServico {
  private LaboratorioDao laboratorioDao = new LaboratorioDao();

  public Laboratorio save(Laboratorio entity){
    return laboratorioDao.insert(entity);
  }

  public List<Laboratorio> listAll(){
    return laboratorioDao.selectAll();
  }

  public void update(Laboratorio entity) throws SQLException{
    laboratorioDao.update(entity);
  }

  public void delete(Integer id) throws SQLException{
    laboratorioDao.delete(id);
  }
}
