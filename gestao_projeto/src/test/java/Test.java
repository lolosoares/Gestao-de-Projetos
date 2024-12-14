import java.sql.SQLException;
import mz.uem.model.Dao.Conexao;
import mz.uem.model.Dao.ProjetoDAO;
import mz.uem.model.model.Projeto;
import mz.uem.relatorio.RelatorioProjeto;

public class Test {
    ProjetoDAO projetoDAO=new ProjetoDAO(Conexao.getConnection());
    public Test() throws SQLException{
         Projeto projeto = new Projeto();
        /*projeto.setId(1);
        projeto.setNome("Sistema de Gestão");
        projeto.setDescricao("Desenvolver um sistema de gestão para empresas.");
        projeto.setDataInicio(LocalDate.of(2024, 1, 1));
        projeto.setDataFim(LocalDate.of(2024, 12, 31));
        projeto.setResponsavel(2);
*/
        projeto=projetoDAO.buscarPorId(1);
        String nomePdf = "relatorio_projeto_sistema_gestao.pdf";
        new RelatorioProjeto(projeto, nomePdf);
        

    }
   
    public static void main(String[] args) throws SQLException {
        System.out.println("Hello world!");
         //new AtribuirTarefasForm();
        new Test();
    }

    }

