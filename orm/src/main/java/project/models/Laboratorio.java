package project.models;

public class Laboratorio extends GenericModel{
  private String descricao;
  private String cnes;
  private String cnpj;
  private String crbm;
  private String nome_fantasia;

  public Laboratorio(String descricao, String cnes, String cnpj, String crbm, String nome_fantasia){
    this.descricao = descricao;
    this.cnes = cnes;
    this.cnpj = cnpj;
    this.crbm = crbm;
    this.nome_fantasia = nome_fantasia;
  }

  public Laboratorio(Long id, String descricao, String cnes, String cnpj, String crbm, String nome_fantasia){
    this.setId(id);
    this.descricao = descricao;
    this.cnes = cnes;
    this.cnpj = cnpj;
    this.crbm = crbm;
    this.nome_fantasia = nome_fantasia;
  }

  public String getDescricao(){
    return this.descricao;
  }

  public String getCNES(){
    return this.cnes;
  }

  public String getCNPJ(){
    return this.cnpj;
  }

  public String getCRBM(){
    return this.crbm;
  }

  public String getNomeFantasia(){
    return this.nome_fantasia;
  }

  public void setDescricao(String descricao){
    this.descricao = descricao;
  }

  public void setCNES(String cnes){
    this.cnes = cnes;
  }

  public void setCNPJ(String cnpj){
    this.cnpj = cnpj;
  }

  public void setCRBM(String crbm){
    this.crbm = crbm;
  }

  public void setNomeFantasia(String nome_fantasia){
    this.nome_fantasia = nome_fantasia;
  }
}
