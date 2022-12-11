package project.services;

import java.sql.SQLException;
import java.util.List;

import project.dao.MedicoDao;
import project.models.Medico;

public class MedicoServico {
  private MedicoDao medicoDao = new MedicoDao();

  public Medico save(Medico entity){
    return medicoDao.insert(entity);
  }

  public List<Medico> listAll(){
    return medicoDao.selectAll();
  }

  public void update(Medico entity) throws SQLException{
    medicoDao.update(entity);
  }

  public void delete(Integer id) throws SQLException{
    medicoDao.delete(id);
  }
}
