package mz.uem.relatorio;


import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import mz.uem.controller.TarefaController;
import mz.uem.model.model.Projeto;
import mz.uem.model.model.Tarefa;

public class RelatorioProjeto {
    TarefaController tarefaController=new TarefaController();
    public RelatorioProjeto(Projeto projeto,String nomeArquivoPdf){
        gerarRelatorioProjeto(projeto, nomeArquivoPdf);
    }
    public void gerarRelatorioProjeto(Projeto projeto, String nomeArquivoPdf) {
    try {
        // Configurações do PDF
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(nomeArquivoPdf));
        document.open();

        // Adicionar detalhes do projeto
        adicionarDetalhesProjeto(document, projeto);

        // Consultar e adicionar tarefas associadas ao projeto
        List<Tarefa> tarefas = tarefaController.buscarTarefasPorProjeto(projeto.getId());
        adicionarTarefasAoRelatorio(document, tarefas);

        adicionarRodape(document);
        
                // Finalizar documento
                document.close();
        
                System.out.println("Relatório gerado com sucesso em: " + nomeArquivoPdf);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    private void adicionarRodape(Document document) throws DocumentException {
    // Adicionando a data de impressão e a marca registrada
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
    Paragraph rodape = new Paragraph();
    rodape.setAlignment(Element.ALIGN_CENTER);
    rodape.setSpacingBefore(20f); // Espaço antes do rodapé
    rodape.add(new Phrase("Relatório gerado em: " + agora.format(formatter), 
                          FontFactory.getFont(FontFactory.HELVETICA, 10)));
    rodape.add(new Phrase("\n© 2024 Robson Soares", 
                          FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10)));

    document.add(rodape);
    }

        private void adicionarDetalhesProjeto(Document document, Projeto projeto) throws DocumentException {
    document.add(new Paragraph("Relatório do Projeto", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
    document.add(new Paragraph(" "));
    document.add(new Paragraph("Nome: " + projeto.getNome(), FontFactory.getFont(FontFactory.HELVETICA, 12)));
    document.add(new Paragraph("Descrição: " + projeto.getDescricao(), FontFactory.getFont(FontFactory.HELVETICA, 12)));
    document.add(new Paragraph("Data de Início: " + projeto.getDataInicio(), FontFactory.getFont(FontFactory.HELVETICA, 12)));
    document.add(new Paragraph("Data de Fim: " + projeto.getDataFim(), FontFactory.getFont(FontFactory.HELVETICA, 12)));
    document.add(new Paragraph("Responsável (ID): " + projeto.getResponsavel(), FontFactory.getFont(FontFactory.HELVETICA, 12)));
    document.add(new Paragraph(" "));
}

public void adicionarTarefasAoRelatorio(Document document, List<Tarefa> tarefas) throws DocumentException{
    document.add(new Paragraph("Tarefas Associadas", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
    document.add(new Paragraph(" "));

    // Configuração da tabela
    PdfPTable table = new PdfPTable(5); // 5 colunas: Nome, Descrição, Data Início, Data Fim, Estado
    table.setWidthPercentage(100);
    table.setSpacingBefore(10f);
    table.setSpacingAfter(10f);

    // Configuração da fonte do cabeçalho
    Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE);
    // Cabeçalhos
     PdfPCell cell;
    cell = new PdfPCell(new Phrase("Nome", headerFont));
    cell.setBackgroundColor(BaseColor.GRAY);
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setPadding(8);
    table.addCell(cell);

    cell = new PdfPCell(new Phrase("Descrição", headerFont));
    cell.setBackgroundColor(BaseColor.GRAY);
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setPadding(8);
    table.addCell(cell);

    cell = new PdfPCell(new Phrase("Data Início", headerFont));
    cell.setBackgroundColor(BaseColor.GRAY);
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setPadding(8);
    table.addCell(cell);

    cell = new PdfPCell(new Phrase("Data Fim", headerFont));
    cell.setBackgroundColor(BaseColor.GRAY);
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setPadding(8);
    table.addCell(cell);

    cell = new PdfPCell(new Phrase("Estado", headerFont));
    cell.setBackgroundColor(BaseColor.GRAY);
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setPadding(8);
    table.addCell(cell);


    // Preenchendo as tarefas
    for (Tarefa tarefa : tarefas) {
        table.addCell(tarefa.getNome());
        table.addCell(tarefa.getDescricao());
        table.addCell(tarefa.getDataInicio().toString());
        table.addCell(tarefa.getDataFim().toString());
        table.addCell(tarefa.getEstado().toString());
    }

    document.add(table);
}

}
