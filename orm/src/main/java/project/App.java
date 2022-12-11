package project;

import java.util.List;

import project.models.Medico;
import project.services.MedicoServico;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        MedicoServico medicoServico = new MedicoServico();
        medicoServico.save(new Medico("CRM-TESTE", "Fernando Tavares"));

        List<Medico> medicos = medicoServico.listAll();

        for(int i = 0; i < medicos.size(); i++){
            System.out.println("=-=-=-=----=-=--=-");
            System.out.println(medicos.get(i).getId());
            System.out.println(medicos.get(i).getCRM());
            System.out.println(medicos.get(i).getNome());
        }
    }
}
