package project.models;

public class Medico extends GenericModel{
  private String crm;
  private String nome;

  public Medico(String crm, String nome) {
		this.crm = crm;
		this.nome = nome;
	}

  public Medico(Long id, String crm, String nome) {
		this.setId(id);
		this.crm = crm;
		this.nome = nome;
	}

  public String getCRM(){
    return crm;
  }

  public String getNome(){
    return nome;
  }

  public void setCRM(String crm){
    this.crm = crm;
  }

  public void setNome(String nome){
    this.nome = nome;
  }
}
